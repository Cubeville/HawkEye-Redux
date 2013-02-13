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

package org.cubeville.hawkeye.search.parsers;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.cubeville.hawkeye.HawkEye;
import org.cubeville.hawkeye.command.CommandException;
import org.cubeville.hawkeye.search.ParameterParser;
import org.cubeville.hawkeye.search.SearchQuery;
import org.cubeville.util.Pair;
import org.cubeville.util.StringUtil;

public class WorldParser extends ParameterParser {

	private final List<String> worlds;
	private final List<String> worldsNot;

	public WorldParser(List<String> parameters, SearchQuery query) throws CommandException {
		super(parameters, query);

		worlds = new ArrayList<String>();
		worldsNot = new ArrayList<String>();
	}

	@Override
	public int getParseOrder() {
		return 10;
	}

	@Override
	public void parse() throws CommandException {
		for (String param : parameters) {
			boolean not = false;
			if (param.startsWith("!")) {
				not = true;
				param = param.substring(1);
			}

			int id = HawkEye.getDataManager().getWorldId(param);
			if (id == -1) throw new CommandException("Could not find world: &7" + param);

			if (not) worldsNot.add(String.valueOf(id));
			else worlds.add(String.valueOf(id));
		}
	}

	@Override
	public Pair<String, Map<String, Object>> preProcess() {
		String sql = "";

		if (!worlds.isEmpty()) {
			sql += "`world_id` IN (" + StringUtil.buildString(worlds, ",") + ")";
		}

		if (!worldsNot.isEmpty()) {
			if (!sql.isEmpty()) sql += " AND ";

			sql += "`world_id` NOT IN (" + StringUtil.buildString(worldsNot, ",") + ")";
		}
		return Pair.of(sql, null);
	}

}
