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

import org.cubeville.hawkeye.command.CommandException;
import org.cubeville.hawkeye.model.Entry;

/**
 * Parser that receives the list of results after the database query is run.
 *
 * This type of parser should be used when you are unable to fine-tune your
 * results enough with just an SQL where clause. The most common example is
 * that it is difficult to run LIKE statements on the data column seeing as
 * if you searched for a block like stone (id 1), you would get many
 * extraneous results. Invalid search results can be cleared by iterating
 * over the result list and removing them.
 *
 * In general, it is a good idea to also add a LIKE clause to the database
 * query in order to let the database do some of the work and cut back on
 * number of results you have to process.
 */
public interface PostParameterParser extends ParameterParser {

	/**
	 * Validates the parameters passed in by the sender
	 *
	 * Note: This method is called before the search is run and should be used
	 * to perform sanity checks on the data entered by the user. It is best to
	 * catch any errors in the parameters here before the search is run.
	 *
	 * @param parameters Parameters to search for
	 * @throws CommandException If any of the parameters are invalid
	 */
	void validate(List<String> parameters) throws CommandException;

	/**
	 * Processes the database search results from a query based on parameters
	 * specified by a user.
	 *
	 * @param parameters Parameters to search for
	 * @param results The search results
	 */
	void process(List<String> parameters, List<Entry> results);

}
