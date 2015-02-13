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
import org.cubeville.hawkeye.model.Entry;
import org.cubeville.util.Pair;

/**
 * Represents a search parameter processor
 *
 * Parser implementations must contain a constructor that accepts a List and
 * a SearchQuery.
 */
public abstract class ParameterParser {

	/**
	 * Parameters specified by the sender
	 */
	protected final List<String> parameters;

	/**
	 * Query running the search
	 */
	protected final SearchQuery query;

	/**
	 * Constructor
	 *
	 * @param parameters Parameters to search for
	 * @param query The query running the search
	 */
	public ParameterParser(List<String> parameters, SearchQuery query) throws CommandException {
		this.parameters = parameters;
		this.query = query;
		parse();
	}

	/**
	 * Validates the parameters passed in by the sender
	 *
	 * Note: This method is called first and should be used to catch any
	 * invalid user input before a search is run.
	 *
	 * @throws CommandException If any of the parameters are invalid
	 */
	public abstract void parse() throws CommandException;

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
	 * @return Pair containing SQL query and mapping of parameters to values
	 *            left value - SQL where clause to use a database query
	 *            right value - Map of named parameters and their values
	 */
	public Pair<String, Map<String, Object>> preProcess() { return null; }

	/**
	 * Processes the database search results from a query based on parameters
	 * specified by a user.
	 *
	 * @param results The search results
	 */
	public void postProcess(List<Entry> results) { }

	/**
	 * Gets the parser order for this parameter.
	 *
	 * Lower numbers will be added to to query first.  Parse order exists to
	 * ensure that queries will match as closely as possible no matter what
	 * order the user enters parameters in.  This will allow the MySQL engine
	 * to cache matching searches and use indexes to their full extent.
	 *
	 * @return Parse order for this parameter
	 */
	public int getParseOrder() {
		return 50;
	}

}
