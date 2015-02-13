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

import java.util.List;
import java.util.Map;

import org.cubeville.hawkeye.command.CommandException;
import org.cubeville.hawkeye.command.CommandSender;

public interface QueryManager {

	/**
	 * Creates a SearchQuery for the specified user
	 *
	 * @param sender The command sender running a search
	 * @param parameters The parameters specified by the sender
	 * @param callback Callback handler for when the search is complete
	 * @return The created query
	 * @throws CommandException If any of the parameters are invalid
	 */
	SearchQuery createQuery(CommandSender sender, String parameters, Callback callback) throws CommandException;

	/**
	 * Registers a search parameter
	 *
	 * Note: PRE_QUERY parsers should implement PreParameterParser whereas
	 * POST_QUERY parsers should implement PostParameterParser. PRE_POST_QUERY
	 * parsers should implement both.
	 *
	 * @param parameter Search parameter prefix
	 * @param parser Class that parses this parameter value
	 * @return True if parameter was registered, false if it was already taken
	 */
	boolean registerParameter(String parameter, Class<? extends ParameterParser> parser);

	/**
	 * Gets a list of parameter parsers for the specified parameter set
	 *
	 * @param query Query to get parsers for
	 * @param parameters List of parameters for the search
	 *           key - parameter prefix
	 *           value - parameter values
	 * @return List of parameter parsers for the query
	 * @throws CommandException If there are any invalid parameters
	 */
	List<ParameterParser> getParsers(SearchQuery query, Map<String, List<String>> parameters) throws CommandException;

}
