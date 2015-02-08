/*
 * HawkEye Redux
 * Copyright (C) 2012-2013 Cubeville <http://www.cubeville.org> and contributors
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program. If not, see <http://www.gnu.org/licenses/>.
 */

package org.cubeville.hawkeye;

import static org.cubeville.hawkeye.util.DatabaseUtil.close;
import static org.cubeville.hawkeye.util.DatabaseUtil.table;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.locks.ReentrantLock;

import org.cubeville.hawkeye.config.HawkEyeConfig.Var;
import org.cubeville.hawkeye.config.PluginConfig;
import org.cubeville.hawkeye.model.Entry;
import org.cubeville.lib.jnbt.ByteArrayTag;
import org.cubeville.lib.jnbt.CompoundTag;
import org.cubeville.lib.jnbt.DoubleTag;
import org.cubeville.lib.jnbt.NBTOutputStream;
import org.cubeville.lib.jnbt.StringTag;
import org.cubeville.lib.jnbt.Tag;

public class SimpleConsumer implements Consumer {

	private final LinkedBlockingQueue<Entry> queue = new LinkedBlockingQueue<Entry>();
	private final ReentrantLock lock = new ReentrantLock();

	private final List<String> ignoredWorlds;
	private final List<String> ignoredPlayers;
	private final List<String> ignoredCommands;

	public SimpleConsumer() {
		PluginConfig config = HawkEye.getConfig();

		List<String> tmp = new ArrayList<String>();
		for (String s : config.getStringList(Var.IGNORED_WORLDS)) {
			tmp.add(s.toLowerCase());
		}
		ignoredWorlds = Collections.unmodifiableList(tmp);

		tmp.clear();
		for (String s : config.getStringList(Var.IGNORED_PLAYERS)) {
			tmp.add(s.toLowerCase());
		}
		ignoredPlayers = Collections.unmodifiableList(tmp);

		tmp.clear();
		for (String s : config.getStringList(Var.IGNORED_COMMANDS)) {
			tmp.add(s.toLowerCase());
		}
		ignoredCommands = Collections.unmodifiableList(tmp);
	}

	@Override
	public void addEntry(Entry entry) {
		queue.add(entry);
	}

	@Override
	public int size() {
		return queue.size();
	}

	@Override
	public void dumpToFile() {
		// Acquire lock
		if (!lock.tryLock()) return;

		try {
			new File("plugins/HawkEye/data/").mkdirs();
			long time = System.currentTimeMillis();

			CompoundTag tag;
			Map<String, Tag> entries = new HashMap<String, Tag>();
			int count = 0;

			while (!queue.isEmpty()) {
				Entry entry = queue.poll();
				Map<String, Tag> data = new HashMap<String, Tag>();

				data.put("player", new StringTag("player", entry.getPlayer().toString()));
				data.put("action", new StringTag("action", entry.getAction().getName()));
				data.put("date", new StringTag("date", entry.getTime().toString()));
				data.put("world", new StringTag("world", entry.getLocation().getWorld().getName()));
				data.put("x", new DoubleTag("x", entry.getLocation().getX()));
				data.put("y", new DoubleTag("y", entry.getLocation().getY()));
				data.put("z", new DoubleTag("z", entry.getLocation().getZ()));
				data.put("data", new StringTag("data", entry.getData()));
				data.put("nbt", new ByteArrayTag("nbt", entry.getNbt()));

				String name = "entry-" + ++count;
				entries.put(name, new CompoundTag(name, data));

				if (count % 1000 == 0) {
					tag = new CompoundTag("entries", entries);
					saveToFile(tag, "data-" + time + "-" + (count / 1000) + ".hawk");
				}
			}

			tag = new CompoundTag("entries", entries);
			saveToFile(tag, "data-" + time + "-" + ((count / 1000) + 1) + ".hawk");
		} finally {
			lock.unlock();
		}
	}

	/**
	 * Dumps an NBT tag to file
	 *
	 * @param entries Entry tag to dump
	 * @param file Name of file to save to
	 */
	private void saveToFile(Tag entries, String file) {
		NBTOutputStream nbt = null;
		try {
			// TODO get the right data folder based on server implementation
			File f = new File("plugins/HawkEye/data/" + file);
			nbt = new NBTOutputStream(new FileOutputStream(f));
			nbt.writeTag(entries);
		} catch (FileNotFoundException e) {
			HawkEye.getLogger().error("Unable to create data file", e);
		} catch (IOException e) {
			HawkEye.getLogger().error("Unable to create data file", e);
		} finally {
			try {
				if (nbt != null) nbt.close();
			} catch (IOException ignore) { }
		}
	}

	@Override
	public void run() {
		if (queue.isEmpty()) return;
		// Acquire lock
		if (!lock.tryLock()) return;

		try {
			// Dump to file if the database is not available
			if (!hasConnection()) {
				if (queue.size() > 900) dumpToFile();
				return;
			}

			Connection conn = null;
			PreparedStatement ps = null;
			String sql = "INSERT INTO " + table("data") + " (`player_id`, `action`, `date`, `world_id`, `x`, `y`, `z`, `data`, `nbt`) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";

			try {
				conn = HawkEye.getDatabase().getConnection();
				ps = conn.prepareStatement(sql);
				int count = 0;

				while (!queue.isEmpty()) {
					Entry entry = queue.poll();

					if (ignoredWorlds.contains(entry.getLocation().getWorld().getName())) continue;
					else if (ignoredPlayers.contains(entry.getPlayer())) continue;

					if (entry.getAction() == DefaultActions.PLAYER_COMMAND) {
						if (ignoredCommands.contains(entry.getData().split(" ", 2)[0].toLowerCase())) continue;
					}

					ps.setInt(1, getPlayerId(entry.getPlayer()));
					ps.setString(2, entry.getAction().getName());
					ps.setTimestamp(3, entry.getTime());
					ps.setInt(4, getWorldId(entry.getLocation().getWorld().getName()));
					ps.setDouble(5, entry.getLocation().getX());
					ps.setDouble(6, entry.getLocation().getY());
					ps.setDouble(7, entry.getLocation().getZ());
					ps.setString(8, entry.getData());
					ps.setBytes(9, entry.getNbt());

					ps.addBatch();

					// Execute after every 500 queries
					if (++count % 500 == 0) ps.executeBatch();
				}

				// Run any remaining queries
				ps.executeBatch();
			} catch (SQLException e) {
				HawkEye.getLogger().error("Could not insert database entry", e);
			} finally {
				close(conn);
				close(ps);
			}
		} finally {
			lock.unlock();
		}
	}

	@Override
	public void shutdown() {
		if (queue.isEmpty()) return;

		if (!hasConnection()) dumpToFile();
		else run();
	}

	/**
	 * Shorthand methods
	 */
	private boolean hasConnection() {
		return HawkEye.getDatabase().hasConnection();
	}

	private int getPlayerId(UUID player) {
		return HawkEye.getDataManager().getPlayerId(player);
	}

	private int getWorldId(String world) {
		return HawkEye.getDataManager().getWorldId(world);
	}

}
