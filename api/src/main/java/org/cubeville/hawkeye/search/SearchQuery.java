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
import java.util.Collections;
import java.util.List;
import java.util.Map;

import org.cubeville.hawkeye.HawkEye;
import org.cubeville.hawkeye.command.CommandException;
import org.cubeville.hawkeye.command.CommandSender;
import org.cubeville.hawkeye.model.DatabaseEntry;
import org.cubeville.hawkeye.model.Entry;

public abstract class SearchQuery implements Runnable {

	/**
	 * CommandSender running this query
	 */
	private final CommandSender sender;

	/**
	 * Search parameters (key = parameter name, value = parameter values)
	 */
	private final Map<String, List<String>> parameters;

	/**
	 * Callback to run when this query is completed
	 */
	private final Callback callback;

	/**
	 * Parameter parsers
	 */
	private final List<ParameterParser> parsers;

	/**
	 * SearchQuery constructor
	 *
	 * @param id Id of this search query for internal use
	 * @param sender CommandSender running this query
	 * @param parameters Query search parameters
	 * @param callback Query callback handler
	 * @throws CommandException If search parameters are invalid
	 */
	public SearchQuery(CommandSender sender, String parameters, Callback callback) throws CommandException {
		this.sender = sender;
		this.parameters = Collections.unmodifiableMap(parse(parameters));
		this.callback = callback;
		parsers = HawkEye.getQueryManager().getParsers(this, this.parameters);
	}

	/**
	 * Gets the command sender running this query
	 *
	 * @return Command sender running this query
	 */
	public CommandSender getSender() {
		return sender;
	}

	/**
	 * Gets a list of parameter parsers attached to this query
	 *
	 * @return Parser list
	 */
	public List<ParameterParser> getParsers() {
		return parsers;
	}

	/**
	 * Parses search parameters
	 *
	 * @param parameters Parameters specified by the command sender
	 * @return Map of parameters and their values
	 *          key - parameter prefix
	 *          value - list of parameter values
	 * @throws CommandException If any parameters are invalid
	 */
	protected abstract Map<String, List<String>> parse(String parameters) throws CommandException;

	/**
	 * Gets an sql statement based on parameters provided by the command sender
	 *
	 * @param connection Database connection to use
	 * @return SQL statement to get search results from the database
	 * @throws SQLException If there are any errors with the database
	 */
	protected abstract PreparedStatement createStatement(Connection connection) throws SQLException;

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
			stmt = createStatement(conn);
			rs = stmt.executeQuery();

			while (rs.next()) {
				results.add(createEntry(rs));
			}

			processResults(results);
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
	 * Runs query results through the parameter post processors
	 *
	 * @param results Database result set
	 */
	private void processResults(List<Entry> results) {
		for (ParameterParser parser : parsers) {
			parser.postProcess(results);
		}
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
		byte[] nbt = rs.getBytes("nbt");

		DatabaseEntry entry = new DatabaseEntry(id, player, action, date, world, x, y, z, data, nbt);
		return entry.toEntry();
	}

}
