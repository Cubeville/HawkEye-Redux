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
	 * @param prefix The table prefix
	 * @return True if connection was established
	 * @throws DatabaseException If any errors occurred
	 */
	boolean connect(String hostname, String port, String database, String username, String password, String prefix) throws DatabaseException;

	/**
	 * Attempts to get a database connection
	 *
	 * @return Database connection
	 * @throws SQLException If any errors occurred
	 */
	Connection getConnection() throws SQLException;

	/**
	 * Closes the connection to the database
	 */
	void close();

}
