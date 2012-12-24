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

package org.cubeville.hawkeye.search;

import java.util.Map;

import org.cubeville.hawkeye.command.CommandException;
import org.cubeville.hawkeye.command.CommandSender;
import org.cubeville.util.Pair;

/**
 * Represents a search parameter processor
 */
public interface ParameterParser {

	/**
	 * Creates an SQL where clause to search for a certain parameter
	 *
	 * Named parameters should be used to prevent SQL injection attempts. They
	 * use the format :variable. For example:
	 *
	 *   "action = :action AND data LIKE :filter"
	 *
	 * The return should also include a map of parameters and their values. For
	 * example:
	 *
	 *   "action": "block-break"
	 *   "filter": "%98%"
	 *
	 * @param parameter Parameter to search for
	 * @param sender Who is doing the search
	 * @return Pair containing SQL query and mapping of parameters to values
	 *            left value - SQL where clause to use a database query
	 *            right value - Map of named parameters and their values
	 * @throws CommandException If the parameter is invalid
	 */
	Pair<String, Map<String, Object>> process(String parameter, CommandSender sender) throws CommandException;

}
