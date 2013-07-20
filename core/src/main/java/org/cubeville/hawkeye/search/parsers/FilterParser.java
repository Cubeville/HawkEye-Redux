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
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.cubeville.hawkeye.command.CommandException;
import org.cubeville.hawkeye.search.ParameterParser;
import org.cubeville.hawkeye.search.SearchQuery;
import org.cubeville.util.Pair;
import org.cubeville.util.StringUtil;

public class FilterParser extends ParameterParser {

	private List<String> filters;

	public FilterParser(List<String> parameters, SearchQuery query) throws CommandException {
		super(parameters, query);
	}

	@Override
	public void parse() throws CommandException {
		filters = new ArrayList<String>();

		for (int i = 0; i < parameters.size(); i++) {
			filters.add(parameters.get(i));
		}
	}

	@Override
	public int getParseOrder() {
		return 30;
	}

	@Override
	public Pair<String, Map<String, Object>> preProcess() {
		List<String> conditions = new ArrayList<String>();
		Map<String, Object> binds = new HashMap<String, Object>();

		for (int i = 0; i < filters.size(); i++) {
			String name = "data" + i;
			String value = filters.get(i);

			conditions.add("(`data` LIKE :" + name + ")");
			binds.put(name, "%" + value + "%");
		}

		String sql = StringUtil.buildString(conditions, " OR ");
		return Pair.of(sql, binds);
	}

}
