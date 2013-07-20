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

package org.cubeville.hawkeye;

import java.util.logging.Logger;

import org.cubeville.hawkeye.command.CommandManager;
import org.cubeville.hawkeye.command.CommandSender;
import org.cubeville.hawkeye.command.ConsoleCommandSender;
import org.cubeville.hawkeye.config.PluginConfig;
import org.cubeville.hawkeye.entity.Player;
import org.cubeville.hawkeye.location.World;
import org.cubeville.hawkeye.search.QueryManager;
import org.cubeville.hawkeye.session.SessionManager;
import org.cubeville.hawkeye.sql.Database;

/**
 * Main plugin engine interface
 */
public interface PluginEngine {

	/**
	 * Gets the HawkEye version
	 *
	 * @return HawkEye version
	 */
	String getVersion();

	/**
	 * Gets the logger
	 *
	 * @return Logger
	 */
	Logger getLogger();

	/**
	 * Gets the server compatibility layer
	 *
	 * @return Server interface
	 */
	ServerInterface getServerInterface();

	/**
	 * Gets the plugin configuration
	 *
	 * @return Plugin configuration
	 */
	PluginConfig getConfig();

	/**
	 * Gets the HawkEye database
	 *
	 * @return Database
	 */
	Database getDatabase();

	/**
	 * Gets the entry consumer
	 *
	 * @return Consumer
	 */
	Consumer getConsumer();

	/**
	 * Gets the session manager
	 *
	 * @return Session manager
	 */
	SessionManager getSessionManager();

	/**
	 * Gets the data manager
	 *
	 * @return Data manager
	 */
	DataManager getDataManager();

	/**
	 * Gets the query manager
	 *
	 * @return Query manager
	 */
	QueryManager getQueryManager();

	/**
	 * Gets the command manager
	 *
	 * @return Command manager
	 */
	CommandManager getCommandManager();

	/**
	 * Gets the display manager
	 *
	 * @return Display manager
	 */
	DisplayManager getDisplayManager();

	/**
	 * Gets the server's console sender
	 *
	 * @return Console sender
	 */
	ConsoleCommandSender getConsoleSender();

	/**
	 * Gets the specified player
	 *
	 * @param name Name of player to get
	 * @return Player
	 */
	Player getPlayer(String name);

	/**
	 * Gets the specified world
	 *
	 * @param name Name of world to get
	 * @return World
	 */
	World getWorld(String name);

	/**
	 * Passes a command through to the command manager
	 */
	void handleCommand(CommandSender sender, String command);

}
