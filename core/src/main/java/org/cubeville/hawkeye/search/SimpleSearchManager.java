/*
 * HawkEye
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

import java.util.HashMap;
import java.util.Map;

import org.cubeville.hawkeye.search.parsers.ActionParser;
import org.cubeville.hawkeye.search.parsers.BlockParser;
import org.cubeville.hawkeye.search.parsers.FilterParser;
import org.cubeville.hawkeye.search.parsers.LocationParser;
import org.cubeville.hawkeye.search.parsers.PlayerParser;
import org.cubeville.hawkeye.search.parsers.RadiusParser;
import org.cubeville.hawkeye.search.parsers.TimeParser;
import org.cubeville.hawkeye.search.parsers.WorldParser;

public class SimpleSearchManager implements SearchManager {

	private final Map<String, ParameterParser> parameters = new HashMap<String, ParameterParser>();

	public SimpleSearchManager() {
		PlayerParser parser = new PlayerParser();

		// If no parameter is specified it will fallback to the default parser
		registerParameter("default", parser);
		registerParameter("p", parser);

		registerParameter("r", new RadiusParser());
		registerParameter("t", new TimeParser());
		registerParameter("a", new ActionParser());
		registerParameter("b", new BlockParser());
		registerParameter("f", new FilterParser());
		registerParameter("l", new LocationParser());
		registerParameter("w", new WorldParser());
	}

	@Override
	public boolean registerParameter(String prefix, ParameterParser parser) {
		prefix = prefix.toLowerCase();

		if (parameters.containsKey(prefix)) return false;

		parameters.put(prefix, parser);
		return true;
	}

}
