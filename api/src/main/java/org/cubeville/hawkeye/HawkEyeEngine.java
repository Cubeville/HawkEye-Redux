package org.cubeville.hawkeye;

import org.cubeville.hawkeye.sql.Database;

/**
 * Main plugin engine interface
 */
public interface HawkEyeEngine {

	/**
	 * Gets the HawkEye version
	 *
	 * @return HawkEye version
	 */
	String getVersion();

	/**
	 * Gets the server compatibility layer
	 *
	 * @return Server interface
	 */
	ServerInterface getServerInterface();

	/**
	 * Gets the HawkEye database
	 *
	 * @return Database
	 */
	Database getDatabase();

}
