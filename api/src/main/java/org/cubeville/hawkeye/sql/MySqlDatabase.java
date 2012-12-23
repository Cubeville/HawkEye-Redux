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

package org.cubeville.hawkeye.sql;

import java.sql.Connection;
import java.sql.SQLException;

/**
 * MySQL database manager backed by a basic connection pool
 */
public class MySqlDatabase implements Database {

	/**
	 * MySQL driver class
	 */
	public static final String DRIVER_CLASS = "com.mysql.jdbc.Driver";

	/**
	 * Connection pool manager
	 */
	private ConnectionManager manager;

	/**
	 * Table prefix
	 */
	private final String prefix;

	public MySqlDatabase(String prefix) {
		this.prefix = prefix;
	}

	@Override
	public boolean connect(String hostname, String port, String database, String username, String password) throws DatabaseException {
		// Load the driver
		try {
			Class.forName(DRIVER_CLASS);
		} catch (Exception ex) {
			throw new DatabaseException("Could not find MySQL driver", ex);
		}

		// Build the jdbc url
		String dbUrl = "jdbc:mysql://" + hostname + ":" + port + "/" + database;

		// Attempt to establish a connection
		try {
			manager = new ConnectionManager(dbUrl, username, password);
		} catch (SQLException ex) {
			throw new DatabaseException("Could not connect to database", ex);
		}

		return true;
	}

	@Override
	public Connection getConnection() throws SQLException {
		return manager.getConnection();
	}

	@Override
	public String getPrefix() {
		return prefix;
	}

	@Override
	public void close() {
		manager.closeConnections();
	}

}
