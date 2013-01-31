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

public class SimpleQueryManager implements QueryManager {

	/**
	 * Default parameter parser (used if no parameter is prefixed)
	 */
	private final ParameterParser defaultParser;

	/**
	 * Custom parameter parsers (keyed by prefix)
	 */
	private final Map<String, ParameterParser> parameters = new HashMap<String, ParameterParser>();

	public SimpleQueryManager() {
		// If no parameter is specified it will fallback to the default parser
		defaultParser = new PlayerParser();
		registerParameter("default", defaultParser);
		registerParameter("p", defaultParser);

		registerParameter("r", new RadiusParser());
		registerParameter("t", new TimeParser());
		registerParameter("a", new ActionParser());
		registerParameter("f", new FilterParser());
		registerParameter("l", new LocationParser());
		registerParameter("w", new WorldParser());
	}

	@Override
	public Statement getQuery(Connection connection, String params, CommandSender sender) throws CommandException, SQLException {
		String query = "SELECT * FROM " + table("data") + " WHERE ";

		List<String> conditions = new LinkedList<String>();
		Map<String, Object> binds = new HashMap<String, Object>();

		// Default condition so it doesn't break if no parameters are processed
		conditions.add("true");

		Map<ParameterParser, List<String>> parameters = parseInput(params);

		for (Map.Entry<ParameterParser, List<String>> parameter : parameters.entrySet()) {
			ParameterParser parser = parameter.getKey();
			List<String> values = parameter.getValue();
			Pair<String, Map<String, Object>> parsed = parser.process(values, sender);

			conditions.add(parsed.getLeft());
			if (parsed.getRight() != null) binds.putAll(parsed.getRight());
		}

		// Build full query
		query += "(" + StringUtil.buildString(conditions, ") AND (") + ")";

		// TODO Add ordering and limiting

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
		prefix = prefix.toLowerCase();
		if (parameters.containsKey(prefix)) return false;

		parameters.put(prefix, parser);
		return true;
	}

	private Map<ParameterParser, List<String>> parseInput(String input) throws CommandUsageException {
		List<String> args = StringUtil.split(input, " ");
		Map<ParameterParser, List<String>> ret = new HashMap<ParameterParser, List<String>>();

		ParameterParser parser = null;

		for (int i = 0; i < args.size(); i++) {
			String arg = args.get(i);
			if (arg.isEmpty()) continue;

			if (parser == null) {
				if (!arg.contains(":")) {
					// No parameter specified, fallback to default parser
					parser = defaultParser;
				} else {
					String[] parts = arg.split(":");
					String key = parts[0];

					// Get the parser for this parameter
					if (!parameters.containsKey(key)) {
						throw new CommandUsageException("Invalid parameter specified: &7" + key);
					} else {
						parser = parameters.get(key);

						if (parts.length == 1) {
							// User left a space between parameter and value

							if (i == (args.size() - 1)) {
								// Last parameter
								throw new CommandUsageException("Invalid argument specified: &7" + arg);
							} else {
								continue;
							}
						} else {
							// Get just the value
							arg = parts[1];
						}
					}
				}
			}

			// At this point arg should be equal to just the value (no parameter)

			if (parser != null) {
				List<String> values;

				if (ret.containsKey(parser)) {
					values = ret.get(parser);
				} else {
					values = new ArrayList<String>();
				}

				values.addAll(StringUtil.split(arg));
				ret.put(parser, values);
			}
		}

		return ret;
	}

}
