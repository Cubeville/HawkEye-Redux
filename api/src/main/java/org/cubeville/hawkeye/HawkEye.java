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

import org.cubeville.hawkeye.command.ConsoleCommandSender;
import org.cubeville.hawkeye.config.Configuration;
import org.cubeville.hawkeye.entity.Player;
import org.cubeville.hawkeye.location.World;
import org.cubeville.hawkeye.session.SessionManager;
import org.cubeville.hawkeye.sql.Database;

public class HawkEye {

	/**
	 * Plugin engine
	 */
	private static PluginEngine engine;

	/**
	 * No instantiation
	 */
	private HawkEye() { }

	/**
	 * Gets the current HawkEye engine
	 *
	 * @return HawkEye engine instance
	 */
	public static PluginEngine getEngine() {
		return engine;
	}

	/**
	 * Sets the HawkEye engine. Can only be set once
	 *
	 * @param engine HawkEye engine instance
	 */
	public static void setEngine(PluginEngine engine) {
		if (HawkEye.engine != null) {
			throw new UnsupportedOperationException("HawkEye engine can only be defined once");
		}

		HawkEye.engine = engine;
	}

	/**
	 * Gets the server compatibility layer
	 *
	 * @return Server interface
	 */
	public static ServerInterface getServerInterface() {
		return engine.getServerInterface();
	}

	/**
	 * Gets the plugin configuration
	 *
	 * @return Plugin configuration
	 */
	public static Configuration getConfig() {
		return engine.getConfig();
	}

	/**
	 * Gets the HawkEye database
	 *
	 * @return Database
	 */
	public Database getDatabase() {
		return engine.getDatabase();
	}

	/**
	 * Gets the session manager
	 *
	 * @return Sesssion manager
	 */
	public SessionManager getSessionManager() {
		return engine.getSessionManager();
	}

	/**
	 * Gets the server's console sender
	 *
	 * @return Console sender
	 */
	public static ConsoleCommandSender getConsoleSender() {
		return engine.getConsoleSender();
	}

	/**
	 * Gets the specified player
	 *
	 * @param name Name of player to get
	 * @return Player
	 */
	public Player getPlayer(String name) {
		return engine.getPlayer(name);
	}

	/**
	 * Gets the specified world
	 *
	 * @param name Name of world to get
	 * @return World
	 */
	public World getWorld(String name) {
		return engine.getWorld(name);
	}

}
