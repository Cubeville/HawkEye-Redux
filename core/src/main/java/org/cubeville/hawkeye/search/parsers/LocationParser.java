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

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.cubeville.hawkeye.HawkEye;
import org.cubeville.hawkeye.command.CommandException;
import org.cubeville.hawkeye.command.CommandUsageException;
import org.cubeville.hawkeye.entity.Player;
import org.cubeville.hawkeye.location.Vector;
import org.cubeville.hawkeye.search.ParameterParser;
import org.cubeville.hawkeye.search.SearchQuery;
import org.cubeville.util.Pair;
import org.cubeville.util.StringUtil;

public class LocationParser extends ParameterParser {

	private int world;
	private Vector location;

	public LocationParser(List<String> parameters, SearchQuery query) throws CommandException {
		super(parameters, query);
	}

	@Override
	public void parse() throws CommandException {
		if (parameters.size() < 3 || parameters.size() > 4) {
			// x,y,z or world,x,y,z
			throw new CommandUsageException("Invalid location specified: &7" + StringUtil.buildString(parameters, ","));
		}

		if (!query.getSender().isPlayer() && parameters.size() < 4) {
			// World required if you're not a player
			throw new CommandUsageException("Invalid location specified: &7" + StringUtil.buildString(parameters, ","));
		}

		String worldName = parameters.size() == 3 ? ((Player) query.getSender()).getWorld().getName() : parameters.get(0);
		world = HawkEye.getDataManager().getWorldId(worldName);
		if (world == -1) throw new CommandUsageException("Invalid world specified: &7" + worldName);

		int offset = parameters.size() == 3 ? 0 : 1;
		String x = parameters.get(0 + offset);
		String y = parameters.get(1 + offset);
		String z = parameters.get(2 + offset);

		try {
			location = new Vector(Integer.parseInt(x), Integer.parseInt(y), Integer.parseInt(z));
		} catch (NumberFormatException e) {
			throw new CommandUsageException("Invalid location specified: &7" + StringUtil.buildString(parameters, ","));
		}
	}

	@Override
	public int getParseOrder() {
		return 13;
	}

	@Override
	public Pair<String, Map<String, Object>> preProcess() {
		String sql = "`world_id` = :worldloc AND FLOOR(`x`) = :xloc AND FLOOR(`y`) = :yloc AND FLOOR(`z`) = :zloc";
		Map<String, Object> binds = new HashMap<String, Object>();

		binds.put("worldloc", world);
		binds.put("xloc", location.getBlockX());
		binds.put("yloc", location.getBlockY());
		binds.put("zloc", location.getBlockZ());

		return Pair.of(sql, binds);
	}

}
