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
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import org.cubeville.hawkeye.entity.Player;
import org.cubeville.hawkeye.location.World;
import org.cubeville.util.CaseInsensitiveMap;

public class SimpleDataManager implements DataManager {

	/**
	 * Map containing action database names and their corresponding actions
	 */
	private final Map<String, Action> actions = new CaseInsensitiveMap<Action>();

	/**
	 * Map containing player ids and their names
	 */
	private final Map<Integer, String> players = new HashMap<Integer, String>();

	/**
	 * Reverse player map for getting player id from name
	 */
	private final Map<String, Integer> playerIds = new CaseInsensitiveMap<Integer>();

	/**
	 * Map containing world ids and their names
	 */
	private final Map<Integer, String> worlds = new HashMap<Integer, String>();

	/**
	 * Reverse player map for getting world id from name
	 */
	private final Map<String, Integer> worldIds = new CaseInsensitiveMap<Integer>();

	public SimpleDataManager() {
		for (Action action : DefaultActions.values()) {
			registerAction(action);
		}

		if (HawkEye.getDatabase().hasConnection()) {
			loadWorlds();
			loadPlayers();
		}
	}

	@Override
	public Collection<Action> getActions() {
		return actions.values();
	}

	@Override
	public Action getAction(String name) {
		return actions.get(name);
	}

	@Override
	public boolean registerAction(Action action) {
		String name = action.getName().toLowerCase();
		if (actions.containsKey(name)) return false;

		actions.put(name, action);
		return true;
	}

	@Override
	public String getPlayer(int id) {
		return players.get(id);
	}

	@Override
	public int getPlayerId(String player) {
		return playerIds.containsKey(player) ? playerIds.get(player) : -1;
	}

	@Override
	public int getPlayerId(Player player) {
		return getPlayerId(player.getName());
	}

	@Override
	public int registerPlayer(Player player) {
		int id = getPlayerId(player);
		if (id != -1 || !HawkEye.getDatabase().hasConnection()) return id;

		String name = player.getName();

		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		String sql = "INSERT INTO " + table("players") + " (name) VALUES (?)";

		try {
			conn = HawkEye.getDatabase().getConnection();
			ps = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
			ps.setString(1, name);
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
			HawkEye.getLogger().error("Could not register player '" + name + "'", e);
		} finally {
			close(conn);
			close(ps);
			close(rs);
		}

		if (id != -1) {
			players.put(id, name);
			playerIds.put(name, id);
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
				String name = rs.getString("name");

				players.put(id, name);
				playerIds.put(name, id);
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
