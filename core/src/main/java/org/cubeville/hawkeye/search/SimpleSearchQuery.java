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
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import org.cubeville.hawkeye.command.CommandException;
import org.cubeville.hawkeye.command.CommandSender;
import org.cubeville.hawkeye.command.CommandUsageException;
import org.cubeville.util.Pair;
import org.cubeville.util.StringUtil;

public class SimpleSearchQuery extends SearchQuery {

	public SimpleSearchQuery(CommandSender sender, String parameters, Callback callback) throws CommandException {
		super(sender, parameters, callback);
	}

	@Override
	protected Map<String, List<String>> parse(String parameters) throws CommandException {
		List<String> args = StringUtil.split(parameters, " ");
		Map<String, List<String>> ret = new HashMap<String, List<String>>();

		String parser = "";

		for (int i = 0; i < args.size(); i++) {
			String arg = args.get(i);
			if (arg.isEmpty()) continue;

			if (parser.isEmpty()) {
				if (!arg.contains(":")) {
					// No parameter specified, fallback to default parser
					parser = "default";
				} else {
					String[] parts = arg.split(":");
					parser = parts[0];

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

			if (!parser.isEmpty()) {
				List<String> values;

				if (ret.containsKey(parser)) {
					values = ret.get(parser);
				} else {
					values = new ArrayList<String>();
				}

				values.addAll(StringUtil.split(arg));
				ret.put(parser, values);

				parser = "";
			}
		}

		return ret;
	}

	@Override
	protected PreparedStatement createStatement(Connection connection) throws SQLException {
		String sql = "SELECT * FROM " + table("data") + " WHERE ";

		List<String> conditions = new LinkedList<String>();
		Map<String, Object> binds = new HashMap<String, Object>();

		// Default condition so it doesn't break if no parameters are processed
		conditions.add("true");

		for (ParameterParser parser : getParsers()) {
			Pair<String, Map<String, Object>> parsed = parser.preProcess();
			if (parsed == null) continue;

			conditions.add(parsed.getLeft());
			if (parsed.getRight() != null) binds.putAll(parsed.getRight());
		}

		// Build full query
		sql += "(" + StringUtil.buildString(conditions, ") AND (") + ")";

		// TODO Add ordering and limiting

		NamedParameterStatement stmt;

		try {
			stmt = new NamedParameterStatement(connection, sql);
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

}
