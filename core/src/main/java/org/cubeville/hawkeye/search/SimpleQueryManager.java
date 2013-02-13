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

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
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

public class SimpleQueryManager implements QueryManager {

	/**
	 * Default parameter parser (used if no parameter is prefixed)
	 */
	private final Class<? extends ParameterParser> defaultParser;

	/**
	 * Custom parser constructors
	 */
	private final Map<String, Constructor<? extends ParameterParser>> parsers = new HashMap<String, Constructor<? extends ParameterParser>>();

	public SimpleQueryManager() {
		// If no parameter is specified it will fallback to the default parser
		defaultParser = PlayerParser.class;
		registerParameter("default", defaultParser);
		registerParameter("p", defaultParser);

		registerParameter("r", RadiusParser.class);
		registerParameter("t", TimeParser.class);
		registerParameter("a", ActionParser.class);
		registerParameter("f", FilterParser.class);
		registerParameter("l", LocationParser.class);
		registerParameter("w", WorldParser.class);
		registerParameter("b", BlockParser.class);
	}

	@Override
	public SearchQuery createQuery(CommandSender sender, String parameters, Callback callback) throws CommandException {
		return new SimpleSearchQuery(sender, parameters, callback);
	}

	@Override
	public boolean registerParameter(String prefix, Class<? extends ParameterParser> parser) {
		prefix = prefix.toLowerCase();
		if (parsers.containsKey(prefix)) return false;

		try {
			Constructor<? extends ParameterParser> constructor = parser.getConstructor(List.class, SearchQuery.class);
			constructor.setAccessible(true);
			parsers.put(prefix, constructor);

			return true;
		} catch (SecurityException e) {
			return false;
		} catch (NoSuchMethodException e) {
			return false;
		}
	}

	@Override
	public List<ParameterParser> getParsers(SearchQuery query, Map<String, List<String>> parameters) throws CommandException {
		List<ParameterParser> parsers = new ArrayList<ParameterParser>();

		for (Map.Entry<String, List<String>> entry : parameters.entrySet()) {
			ParameterParser parser = getParser(query, entry.getKey(), entry.getValue());
			if (parser != null) parsers.add(parser);
		}

		Collections.sort(parsers, comparator);

		return parsers;
	}

	/**
	 * Gets a parameter parser for the specified query
	 *
	 * @param query Query that needs a parser
	 * @param prefix Parameter prefix to get parser for
	 * @param values Parameter values
	 * @return Parameter parser
	 * @throws CommandException If an error occurs
	 */
	private ParameterParser getParser(SearchQuery query, String prefix, List<String> values) throws CommandException {
		prefix = prefix.toLowerCase();
		if (!parsers.containsKey(prefix)) throw new CommandUsageException("Invalid parameter specified: &7" + prefix);

		try {
			return parsers.get(prefix).newInstance(values, query);
		} catch (IllegalArgumentException ignore) {
		} catch (InstantiationException ignore) {
		} catch (IllegalAccessException ignore) {
		} catch (InvocationTargetException ignore) {
		}

		throw new CommandException("Error parsing parameter: &7" + prefix);
	}

	private static Comparator<ParameterParser> comparator = new Comparator<ParameterParser>() {
		@Override
		public int compare(ParameterParser p1, ParameterParser p2) {
			return p1.getParseOrder() - p2.getParseOrder();
		}
	};

}
