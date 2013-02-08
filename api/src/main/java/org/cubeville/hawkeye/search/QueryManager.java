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

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

import org.cubeville.hawkeye.command.CommandException;
import org.cubeville.hawkeye.model.Entry;

public interface QueryManager {

	public enum Stage {
		/**
		 * The parameter works by putting its data in the search query
		 */
		PRE_QUERY,
		/**
		 * The parameter works by processing the data after it is retrieved
		 */
		POST_QUERY,
		/**
		 * The parameter runs on both stages listed above
		 */
		PRE_POST_QUERY;
	}

	/**
	 * Gets an sql statement based on parameters provided by the command sender
	 *
	 * @param connection Database connection to use
	 * @param query The search query being run
	 * @return SQL statement to get search results from the database
	 * @throws CommandException If any of the parameters specified are invalid
	 * @throws SQLException If there are any errors with the database
	 */
	PreparedStatement getQuery(Connection connection, SearchQuery query) throws CommandException, SQLException;

	/**
	 * Runs a set of database results through the post search parsers
	 *
	 * @param results The database results to parse
	 * @param query The search query being run
	 * @throws CommandException If any of the parameters specified are invalid
	 */
	void processResults(List<Entry> results, SearchQuery query) throws CommandException;

	/**
	 * Registers a search parameter
	 *
	 * Note: PRE_QUERY parsers should implement PreParameterParser whereas
	 * POST_QUERY parsers should implement PostParameterParser. PRE_POST_QUERY
	 * parsers should implement both.
	 *
	 * @param prefix Search parameter prefix
	 * @param parser Parser that parses this parameter value
	 * @param stage The parsing stage this parameter is registered on
	 * @return True if parameter was registered, false if it was already taken
	 */
	boolean registerParameter(String parameter, ParameterParser parser, Stage stage);

}
