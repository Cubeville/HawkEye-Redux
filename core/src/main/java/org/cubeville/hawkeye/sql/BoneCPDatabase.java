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

import com.jolbox.bonecp.BoneCP;
import com.jolbox.bonecp.BoneCPConfig;

public class BoneCPDatabase implements Database {

	/**
	 * MySQL driver class
	 */
	public static final String DRIVER_CLASS = "com.mysql.jdbc.Driver";

	/**
	 * Connection pool manager
	 */
	private BoneCP pool;

	/**
	 * Table prefix
	 */
	private final String prefix;

	public BoneCPDatabase(String prefix) {
		this.prefix = prefix;
	}

	@Override
	public boolean connect(String hostname, String port, String database, String username, String password) throws DatabaseException {
		// Load the driver
		try {
			Class.forName(DRIVER_CLASS);
		} catch (Exception e) {
			throw new DatabaseException("Could not find MySQL driver", e);
		}

		// Build the jdbc url
		String dbUrl = "jdbc:mysql://" + hostname + ":" + port + "/" + database;

		BoneCPConfig config = new BoneCPConfig();
		config.setJdbcUrl(dbUrl);
		config.setUsername(username);
		config.setPassword(password);

		// Attempt to establish a connection
		try {
			pool = new BoneCP(config);
			return true;
		} catch (SQLException e) {
			throw new DatabaseException("Could not connect to database", e);
		}
	}

	@Override
	public Connection getConnection() throws SQLException {
		if (pool == null) {
			throw new SQLException("Database connection is not established");
		}

		return pool.getConnection();
	}

	@Override
	public String getPrefix() {
		return prefix;
	}

	@Override
	public void close() {
		if (pool != null) {
			pool.shutdown();
		}

		pool = null;
	}

}
