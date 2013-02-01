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

package org.cubeville.hawkeye.search.parsers;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.cubeville.hawkeye.HawkEye;
import org.cubeville.hawkeye.command.CommandException;
import org.cubeville.hawkeye.command.CommandSender;
import org.cubeville.hawkeye.search.ParameterParser;
import org.cubeville.util.Pair;
import org.cubeville.util.StringUtil;

public class WorldParser implements ParameterParser {

	@Override
	public Pair<String, Map<String, Object>> process(List<String> parameters, CommandSender sender) throws CommandException {
		List<String> worlds = new ArrayList<String>();
		List<String> worldsNot = new ArrayList<String>();

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
