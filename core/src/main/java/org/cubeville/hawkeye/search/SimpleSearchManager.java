/*
 * HawkEye Redux
 * Copyright (C) 2012 Cubeville <http://www.cubeville.org> and contributors
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

import static org.cubeville.hawkeye.util.DatabaseUtil.table;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import org.cubeville.hawkeye.command.CommandException;
import org.cubeville.hawkeye.command.CommandSender;
import org.cubeville.hawkeye.command.CommandUsageException;
import org.cubeville.hawkeye.search.parsers.ActionParser;
import org.cubeville.hawkeye.search.parsers.FilterParser;
import org.cubeville.hawkeye.search.parsers.LocationParser;
import org.cubeville.hawkeye.search.parsers.PlayerParser;
import org.cubeville.hawkeye.search.parsers.RadiusParser;
import org.cubeville.hawkeye.search.parsers.TimeParser;
import org.cubeville.hawkeye.search.parsers.WorldParser;
import org.cubeville.util.Pair;
import org.cubeville.util.StringUtil;

public class SimpleSearchManager implements SearchManager {

	/**
	 * Default parameter parser (used if no parameter is prefixed)
	 */
	private final ParameterParser defaultParser;

	/**
	 * Custom parameter parsers (keyed by prefix)
	 */
	private final Map<String, Pair<ParameterParser, Boolean>> parameters = new HashMap<String, Pair<ParameterParser, Boolean>>();

	public SimpleSearchManager() {
		// If no parameter is specified it will fallback to the default parser
		defaultParser = new PlayerParser();
		registerParameter("default", defaultParser);
		registerParameter("p", defaultParser);

		registerParameter("r", new RadiusParser(), false);
		registerParameter("t", new TimeParser(), false);
		registerParameter("a", new ActionParser());
		registerParameter("f", new FilterParser());
		registerParameter("l", new LocationParser(), false);
		registerParameter("w", new WorldParser(), false);
	}

	@Override
	public Statement getQuery(Connection connection, String params, CommandSender sender) throws CommandException, SQLException {
		String query = "SELECT * FROM " + table("data") + " WHERE ";

		String[] parts = params.split(" ");
		List<String> conditions = new LinkedList<String>();
		Map<String, Object> binds = new HashMap<String, Object>();

		// Default condition so it doesn't break if no parameters are processed
		conditions.add("true");

		List<String> used = new ArrayList<String>();

		for (int i = 0; i < parts.length; i++) {
			String param = parts[i];
			ParameterParser parser;

			if (param.isEmpty()) continue;

			// Get parameter key/value
			String[] paramParts = param.split(":", 2);
			String key = paramParts.length == 1 ? "default" : paramParts[0].toLowerCase();
			String value = paramParts.length == 1 ? paramParts[0] : paramParts[1];

			if (key.equalsIgnoreCase("default")) {
				parser = defaultParser;
			} else {
				// Get custom parser
				Pair<ParameterParser, Boolean> pair = parameters.get(key);
				if (pair == null) {
					parser = null;
				} else {
					// Check for duplicate parameters
					if (!pair.getRight() && used.contains(key)) {
						throw new CommandUsageException("Duplicate parameter detected: '" + key + "'");
					} else {
						if (!pair.getRight()) used.add(key);
						parser = pair.getLeft();
					}
					// TODO Group together duplicate parameters so they don't get parsed into individual clauses
				}
			}

			if (parser == null) throw new CommandUsageException("Invalid parameter specified: '" + key + "'");

			// Parse parameter
			Pair<String, Map<String, Object>> data = parser.process(value, sender);
			conditions.add(data.getLeft());
			if (data.getRight() != null) binds.putAll(data.getRight());
		}

		// Build full query
		query += "(" + StringUtil.buildString(conditions, ") AND (") + ")";

		NamedParameterStatement stmt;

		try {
			stmt = new NamedParameterStatement(connection, query);
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}

		// Bind named parameters
		for (Map.Entry<String, Object> entry : binds.entrySet()) {
			stmt.setObject(entry.getKey(), entry.getValue());
		}

		return stmt.getStatement();
	}

	@Override
	public boolean registerParameter(String prefix, ParameterParser parser) {
		return registerParameter(prefix, parser, true);
	}

	@Override
	public boolean registerParameter(String prefix, ParameterParser parser, boolean duplicate) {
		prefix = prefix.toLowerCase();

		if (parameters.containsKey(prefix)) return false;

		parameters.put(prefix, Pair.of(parser, duplicate));
		return true;
	}

}
