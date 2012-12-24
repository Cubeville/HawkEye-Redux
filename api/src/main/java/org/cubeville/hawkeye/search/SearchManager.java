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

import org.cubeville.hawkeye.command.CommandException;
import org.cubeville.hawkeye.command.CommandSender;

public interface SearchManager {

	/**
	 * Gets an SQL query based on parameters provided by the command sender
	 *
	 * @return SQL query to get search results from the database
	 * @throws CommandException If any of the parameters specified are invalid
	 */
	String getQuery(String params, CommandSender sender) throws CommandException;

	/**
	 * Registers a search parameter
	 *
	 * @param prefix Search parameter prefix
	 * @param parser Parser that parses this parameter value
	 * @return True if parameter was registered, false if it was already taken
	 */
	boolean registerParameter(String parameter, ParameterParser parser);

}
