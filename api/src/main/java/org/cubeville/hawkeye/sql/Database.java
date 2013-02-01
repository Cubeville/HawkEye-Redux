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

package org.cubeville.hawkeye.sql;

import java.sql.Connection;
import java.sql.SQLException;

public interface Database {

	/**
	 * Attempts to establish a connection to a database
	 *
	 * @param hostname The hostname to connect to
	 * @param port The port to connect on
	 * @param database The database to use
	 * @param username The user to connect as
	 * @param password The password to connect with
	 * @return True if connection was established
	 * @throws DatabaseException If any errors occurred
	 */
	boolean connect(String hostname, String port, String database, String username, String password) throws DatabaseException;

	/**
	 * Attempts to get a database connection
	 *
	 * @return Database connection
	 * @throws SQLException If any errors occurred
	 */
	Connection getConnection() throws SQLException;

	/**
	 * Returns the table prefix
	 *
	 * @return Table prefix
	 */
	String getPrefix();

	/**
	 * Closes the connection to the database
	 */
	void close();

}
