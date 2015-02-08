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

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import org.cubeville.hawkeye.entity.Player;
import org.cubeville.hawkeye.location.World;
import org.cubeville.hawkeye.model.DatabaseEntry;
import org.cubeville.hawkeye.model.Entry;
import org.cubeville.util.CaseInsensitiveMap;
import org.cubeville.util.LRUCache;

public class SimpleDataManager implements DataManager {

	/**
	 * Map containing action database names and their corresponding actions
	 */
	private final Map<String, Action> actions = new CaseInsensitiveMap<Action>();

	/**
	 * Map containing action aliases
	 */
	private final Map<String, Action> aliases = new CaseInsensitiveMap<Action>();

	/**
	 * Map containing player ids and their UUIDs
	 */
	private final Map<Integer, UUID> players = new HashMap<Integer, UUID>();

	/**
	 * Reverse player map for getting player id from uuid
	 */
	private final Map<UUID, Integer> playerIds = new HashMap<UUID, Integer>();

	/**
	 * Map containing world ids and their names
	 */
	private final Map<Integer, String> worlds = new HashMap<Integer, String>();

	/**
	 * Reverse world map for getting world id from name
	 */
	private final Map<String, Integer> worldIds = new CaseInsensitiveMap<Integer>();

	/**
	 * Entry cache
	 */
	private final LRUCache<Integer, Entry> entryCache = new LRUCache<Integer, Entry>(5000);

	public SimpleDataManager() {
		for (DefaultActions action : DefaultActions.values()) {
			registerAction(action);

			for (String alias : action.getAliases()) {
				registerActionAlias(action, alias);
			}
		}

		if (HawkEye.getDatabase().hasConnection()) {
			loadWorlds();
			loadPlayers();
		}
	}

	@Override
	public Collection<Action> getActions() {
		return Collections.unmodifiableCollection(actions.values());
	}

	@Override
	public Action getAction(String name) {
		if (actions.containsKey(name)) return actions.get(name);
		if (aliases.containsKey(name)) return aliases.get(name);
		return null;
	}

	@Override
	public boolean registerAction(Action action) {
		String name = action.getName();
		if (actions.containsKey(name)) return false;

		// Real names overwrite aliases
		if (aliases.containsKey(name)) aliases.remove(name);
		actions.put(name, action);
		return true;
	}

	@Override
	public boolean registerActionAlias(Action action, String alias) {
		// Don't register aliases for an action that isn't registered
		if (!getActions().contains(action)) return false;
		if (actions.containsKey(alias) || aliases.containsKey(alias)) return false;

		aliases.put(alias, action);
		return true;
	}

	@Override
	public UUID getPlayer(int id) {
		return players.get(id);
	}

	@Override
	public int getPlayerId(UUID player) {
		return playerIds.containsKey(player) ? playerIds.get(player) : -1;
	}

	@Override
	public int getPlayerId(Player player) {
		return getPlayerId(player.getUUID());
	}

	@Override
	public int registerPlayer(Player player) {
		int id = getPlayerId(player);
		if (id != -1 || !HawkEye.getDatabase().hasConnection()) return id;

		UUID uuid = player.getUUID();

		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		String sql = "INSERT INTO " + table("players") + " (uuid) VALUES (?)";

		try {
			conn = HawkEye.getDatabase().getConnection();
			ps = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
			ps.setString(1, uuid.toString());
			if (ps.executeUpdate() == 0) {
				throw new SQLException("Database player insert failed");
			}

			rs = ps.getGeneratedKeys();
			if (rs.next()) {
				id = rs.getInt(1);
			} else {
				throw new SQLException("Could not obtain user id");
			}
		} catch (SQLException e) {
			HawkEye.getLogger().error("Could not register player '" + uuid + "'", e);
		} finally {
			close(conn);
			close(ps);
			close(rs);
		}

		if (id != -1) {
			players.put(id, uuid);
			playerIds.put(uuid, id);
		}

		return id;
	}

	@Override
	public World getWorld(int id) {
		return HawkEye.getWorld(worlds.get(id));
	}

	@Override
	public int getWorldId(String world) {
		return worldIds.containsKey(world) ? worldIds.get(world) : -1;
	}

	@Override
	public int getWorldId(World world) {
		return getWorldId(world.getName());
	}

	@Override
	public int registerWorld(World world) {
		int id = getWorldId(world);
		if (id != -1 || !HawkEye.getDatabase().hasConnection()) return id;

		String name = world.getName();

		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		String sql = "INSERT INTO " + table("worlds") + " (name) VALUES (?)";

		try {
			conn = HawkEye.getDatabase().getConnection();
			ps = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
			ps.setString(1, name);
			if (ps.executeUpdate() == 0) {
				throw new SQLException("Database world insert failed");
			}

			rs = ps.getGeneratedKeys();
			if (rs.next()) {
				id = rs.getInt(1);
			} else {
				throw new SQLException("Could not obtain world id");
			}
		} catch (SQLException e) {
			HawkEye.getLogger().error("Could not register world '" + name + "'", e);
		} finally {
			close(conn);
			close(ps);
			close(rs);
		}

		if (id != -1) {
			worlds.put(id, name);
			worldIds.put(name, id);
		}

		return id;
	}

	@Override
	public Entry getEntry(int id) {
		if (entryCache.containsKey(id)) return entryCache.get(id);

		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		String sql = "SELECT * FROM " + table("data") + " WHERE id = ? LIMIT 1";

		Entry entry = null;

		try {
			conn = HawkEye.getDatabase().getConnection();
			ps = conn.prepareStatement(sql);
			ps.setInt(1, id);
			rs = ps.executeQuery();

			if (rs.next()) {
				id = rs.getInt("id");
				int player = rs.getInt("player_id");
				String action = rs.getString("action");
				Timestamp date = rs.getTimestamp("date");
				int world = rs.getInt("world_id");
				double x = rs.getDouble("x");
				double y = rs.getDouble("y");
				double z = rs.getDouble("z");
				String data = rs.getString("data");
				byte[] nbt = rs.getBytes("nbt");

				DatabaseEntry dbEntry = new DatabaseEntry(id, player, action, date, world, x, y, z, data, nbt);
				entry = dbEntry.toEntry();
			}
		} catch (SQLException e) {
			HawkEye.getLogger().error("Could not retrieve entry #" + id, e);
		} finally {
			close(conn);
			close(ps);
			close(rs);
		}

		if (entry != null) entryCache.put(id, entry);
		return entry;
	}

	@Override
	public void cacheEntry(Entry entry) {
		entryCache.put(entry.getId(), entry);
	}

	private void loadWorlds() {
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		String sql = "SELECT * FROM " + table("worlds");

		try {
			conn = HawkEye.getDatabase().getConnection();
			ps = conn.prepareStatement(sql);
			rs = ps.executeQuery();

			while (rs.next()) {
				int id = rs.getByte("id");
				String name = rs.getString("name");

				worlds.put(id, name);
				worldIds.put(name, id);
			}
		} catch (SQLException e) {
			HawkEye.getLogger().error("Could not load worlds", e);
		} finally {
			close(conn);
			close(ps);
			close(rs);
		}
	}

	private void loadPlayers() {
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		String sql = "SELECT * FROM " + table("players");

		try {
			conn = HawkEye.getDatabase().getConnection();
			ps = conn.prepareStatement(sql);
			rs = ps.executeQuery();

			while (rs.next()) {
				int id = rs.getInt("id");
				UUID uuid = UUID.fromString(rs.getString("uuid"));

				players.put(id, uuid);
				playerIds.put(uuid, id);
			}
		} catch (SQLException e) {
			HawkEye.getLogger().error("Could not load players", e);
		} finally {
			close(conn);
			close(ps);
			close(rs);
		}
	}

}
