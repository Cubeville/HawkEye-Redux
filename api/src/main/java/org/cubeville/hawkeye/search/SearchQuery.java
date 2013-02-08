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

package org.cubeville.hawkeye.search;

import static org.cubeville.hawkeye.util.DatabaseUtil.close;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import org.cubeville.hawkeye.HawkEye;
import org.cubeville.hawkeye.command.CommandException;
import org.cubeville.hawkeye.command.CommandSender;
import org.cubeville.hawkeye.model.DatabaseEntry;
import org.cubeville.hawkeye.model.Entry;

public class SearchQuery implements Runnable {

	// TODO Fix up the relationship between querymanager and searchquery

	/**
	 * CommandSender running this query
	 */
	private final CommandSender sender;

	/**
	 * Search parameters for this query
	 */
	private final String parameters;

	/**
	 * Callback to run when this query is completed
	 */
	private final Callback callback;

	public SearchQuery(CommandSender sender, String parameters, Callback callback) {
		this.sender = sender;
		this.parameters = parameters;
		this.callback = callback;
	}

	/**
	 * Gets the command sender running this query
	 *
	 * @return CommandSender
	 */
	public CommandSender getSender() {
		return sender;
	}

	/**
	 * Gets the parameters attached to this query
	 *
	 * @return Search parameters
	 */
	public String getParameters() {
		return parameters;
	}

	/**
	 * Runs the search query
	 */
	@Override
	public void run() {
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		List<Entry> results = new ArrayList<Entry>();

		try {
			conn = HawkEye.getDatabase().getConnection();
			stmt = HawkEye.getQueryManager().getQuery(conn, this);
			rs = stmt.executeQuery();

			while (rs.next()) {
				results.add(createEntry(rs));
			}

			HawkEye.getQueryManager().processResults(results, this);
		} catch (CommandException e) {
			// TODO Inform the user
		} catch (SQLException e) {
			callback.error(e);
		} finally {
			close(conn);
			close(stmt);
			close(rs);
		}

		callback.execute(results);
	}

	/**
	 * Creates an Entry object from a database row
	 *
	 * @param rs Database result set
	 * @return HawkEye entry
	 * @throws SQLException If any database errors occur
	 */
	private Entry createEntry(ResultSet rs) throws SQLException {
		int id = rs.getInt("id");
		int player = rs.getInt("player_id");
		String action = rs.getString("action");
		Timestamp date = rs.getTimestamp("date");
		int world = rs.getInt("world_id");
		double x = rs.getDouble("x");
		double y = rs.getDouble("y");
		double z = rs.getDouble("z");
		String data = rs.getString("data");
		int extra = rs.getInt("nbt");

		DatabaseEntry entry = new DatabaseEntry(id, player, action, date, world, x, y, z, data, extra);
		return entry.toEntry();
	}

}
