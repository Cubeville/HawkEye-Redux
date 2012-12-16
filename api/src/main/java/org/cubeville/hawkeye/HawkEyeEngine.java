/*
 * HawkEye
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

package org.cubeville.hawkeye;

import org.cubeville.hawkeye.session.SessionManager;
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

	/**
	 * Gets the session manager
	 *
	 * @return Sesssion manager
	 */
	SessionManager getSessionManager();

}
