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

public class PlayerParser implements ParameterParser {

	@Override
	public Pair<String, Map<String, Object>> process(String parameter, CommandSender sender) throws CommandException {
		List<String> players = new ArrayList<String>();

		String[] list = parameter.split(",");
		for (int i = 0; i < list.length; i++) {
			int id = HawkEye.getDataManager().getPlayerId(list[i]);

			if (id == -1) throw new CommandException("Could not find player: " + list[i]);
			else players.add(String.valueOf(id));
		}

		String sql = "`player_id` IN (" + StringUtil.buildString(players, ",") + ")";
		return Pair.of(sql, null);
	}

}
