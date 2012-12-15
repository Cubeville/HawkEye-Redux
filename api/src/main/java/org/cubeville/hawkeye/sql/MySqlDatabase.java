package org.cubeville.hawkeye.sql;

import java.sql.Connection;
import java.sql.SQLException;

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
	 * {@inheritDoc}
	 */
	@Override
	public boolean connect(String hostname, String port, String database, String username, String password, String prefix) throws DatabaseException {
		try {
			Class.forName(DRIVER_CLASS);
		} catch (Exception ex) {
			throw new DatabaseException("Could not find MySQL driver", ex);
		}

		// Build the jdbc url
		String dbUrl = "jdbc:mysql://" + hostname + ":" + port + "/" + database;

		try {
			manager = new ConnectionManager(dbUrl, username, password);
		} catch (SQLException ex) {
			throw new DatabaseException("Could not connect to database", ex);
		}

		return true;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Connection getConnection() throws SQLException {
		return manager.getConnection();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void close() {
		manager.closeConnections();
	}

}
