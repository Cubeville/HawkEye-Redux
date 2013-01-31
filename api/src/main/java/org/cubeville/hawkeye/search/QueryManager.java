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

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

import org.cubeville.hawkeye.command.CommandException;
import org.cubeville.hawkeye.command.CommandSender;

public interface QueryManager {

	/**
	 * Gets an sql statement based on parameters provided by the command sender
	 *
	 * @param connection Database connection to use
	 * @param params Parameters specified by command sender
	 * @param sender Who sent the command
	 * @return SQL statement to get search results from the database
	 * @throws CommandException If any of the parameters specified are invalid
	 * @throws SQLException If there are any errors with the database
	 */
	Statement getQuery(Connection connection, String params, CommandSender sender) throws CommandException, SQLException;

	/**
	 * Registers a search parameter
	 *
	 * @param prefix Search parameter prefix
	 * @param parser Parser that parses this parameter value
	 * @return True if parameter was registered, false if it was already taken
	 */
	boolean registerParameter(String parameter, ParameterParser parser);

	/**
	 * Registers a search parameter
	 *
	 * @param prefix Search parameter prefix
	 * @param parser Parser that parses this parameter value
	 * @param duplicate Whether or not this parameter can be specified more
	 *                   than once (i.e. there can only be one radius)
	 * @return True if parameter was registered, false if it was already taken
	 */
	boolean registerParameter(String parameter, ParameterParser parser, boolean duplicate);

}
