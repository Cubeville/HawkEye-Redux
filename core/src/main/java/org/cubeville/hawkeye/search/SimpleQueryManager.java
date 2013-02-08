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
import org.cubeville.hawkeye.search.parsers.BlockParser;
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
	 * Custom parameter parsers (keyed by stage, value keyed by prefix)
	 */
	private final Map<Stage, Map<String, ParameterParser>> parameters = new HashMap<Stage, Map<String, ParameterParser>>();

	public SimpleQueryManager() {
		// If no parameter is specified it will fallback to the default parser
		defaultParser = new PlayerParser();
		registerParameter("default", defaultParser, Stage.PRE_QUERY);
		registerParameter("p", defaultParser, Stage.PRE_QUERY);

		registerParameter("r", new RadiusParser(), Stage.PRE_QUERY);
		registerParameter("t", new TimeParser(), Stage.PRE_QUERY);
		registerParameter("a", new ActionParser(), Stage.PRE_QUERY);
		registerParameter("f", new FilterParser(), Stage.PRE_QUERY);
		registerParameter("l", new LocationParser(), Stage.PRE_QUERY);
		registerParameter("w", new WorldParser(), Stage.PRE_QUERY);
		registerParameter("b", new BlockParser(), Stage.PRE_POST_QUERY);
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

			if (parser instanceof PostParameterParser) {
				((PostParameterParser) parser).validate(values);
			}

			if (parser instanceof PreParameterParser) {
				Pair<String, Map<String, Object>> parsed = ((PreParameterParser) parser).process(values, sender);

				conditions.add(parsed.getLeft());
				if (parsed.getRight() != null) binds.putAll(parsed.getRight());
			}
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
	public boolean registerParameter(String prefix, ParameterParser parser, Stage stage) {
		if (stage == Stage.PRE_POST_QUERY) {
			if (isRegistered(prefix)) return false;
			registerParameter(prefix, parser, Stage.PRE_QUERY);
			registerParameter(prefix, parser, Stage.POST_QUERY);
			return true;
		}

		Map<String, ParameterParser> parsers;
		if (parameters.containsKey(stage)) {
			parsers = parameters.get(stage);
		} else {
			parsers = new HashMap<String, ParameterParser>();
		}

		prefix = prefix.toLowerCase();
		if (parsers.containsKey(prefix)) return false;

		switch (stage) {
		case PRE_QUERY:
			if (!(parser instanceof PreParameterParser))
				throw new IllegalArgumentException("Must be PreParameterParser to register on stage PRE_QUERY!");
			break;
		case POST_QUERY:
			if (!(parser instanceof PostParameterParser))
				throw new IllegalArgumentException("Must be PostParameterParser to register on stage POST_QUERY!");
			break;
		case PRE_POST_QUERY:
			return false;
		}

		parsers.put(prefix, parser);
		parameters.put(stage, parsers);
		return true;
	}

	private boolean isRegistered(String prefix) {
		for (Map<String, ParameterParser> parsers : parameters.values()) {
			if (parsers.containsKey(prefix.toLowerCase())) return true;
		}

		return false;
	}

	private Map<ParameterParser, List<String>> parseInput(String input) throws CommandUsageException {
		List<String> args = StringUtil.split(input, " ");
		Map<ParameterParser, List<String>> ret = new HashMap<ParameterParser, List<String>>();

		Map<String, ParameterParser> preParsers = parameters.get(Stage.PRE_QUERY);
		Map<String, ParameterParser> postParsers = parameters.get(Stage.POST_QUERY);

		ParameterParser parser = null;
		ParameterParser parser2 = null;

		for (int i = 0; i < args.size(); i++) {
			String arg = args.get(i);
			if (arg.isEmpty()) continue;

			if (parser == null && parser2 == null) {
				if (!arg.contains(":")) {
					// No parameter specified, fallback to default parser
					parser = defaultParser;
				} else {
					String[] parts = arg.split(":");
					String key = parts[0];

					if (!isRegistered(key)) {
						throw new CommandUsageException("Invalid parameter specified: &7" + key);
					}

					// Get the pre parser for this parameter
					if (preParsers.containsKey(key)) {
						parser = preParsers.get(key);
					}

					// Get the post parser for this parameter
					if (postParsers.containsKey(key)) {
						parser2 = postParsers.get(key);
					}

					// Neither parser exists
					if (parser == null && parser2 == null) {
						throw new CommandUsageException("Invalid parameter specified: &7" + key);
					}

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

				parser = null;
			}

			if (parser2 != null) {
				List<String> values;

				if (ret.containsKey(parser2)) {
					values = ret.get(parser2);
				} else {
					values = new ArrayList<String>();
				}

				values.addAll(StringUtil.split(arg));
				ret.put(parser2, values);

				parser2 = null;
			}
		}

		return ret;
	}

}
