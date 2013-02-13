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

import java.util.List;
import java.util.Map;

import org.cubeville.hawkeye.command.CommandException;
import org.cubeville.hawkeye.command.CommandUsageException;
import org.cubeville.hawkeye.search.ParameterParser;
import org.cubeville.hawkeye.search.SearchQuery;
import org.cubeville.util.Pair;
import org.cubeville.util.StringUtil;
import org.cubeville.util.TimeUtil;

public class TimeParser extends ParameterParser {

	private int time;

	public TimeParser(List<String> parameters, SearchQuery query) throws CommandException {
		super(parameters, query);
	}

	@Override
	public int getParseOrder() {
		return 7;
	}

	@Override
	public void parse() throws CommandException {
		if (parameters.size() > 1) throw new CommandUsageException("Invalid radius specified: &7" + StringUtil.buildString(parameters, ","));
		String parameter = parameters.get(0);

		try {
			time = TimeUtil.parseTime(parameter);
		} catch (IllegalArgumentException e) {
			throw new CommandException(e.getMessage());
		}
	}

	@Override
	public Pair<String, Map<String, Object>> preProcess() {
		String sql = "UNIX_TIMESTAMP(`date`) > " + (TimeUtil.now() - time);

		return Pair.of(sql, null);
	}

}
