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

import java.util.UUID;
import java.util.logging.Logger;

import org.cubeville.hawkeye.command.ConsoleCommandSender;
import org.cubeville.hawkeye.entity.Player;
import org.cubeville.hawkeye.location.World;

public interface ServerInterface {

	/**
	 * Gets the implementation this server is running
	 *
	 * @return Server implementation
	 */
	ServerImplementation getImplementation();

	/**
	 * Gets the Logger used by the server
	 *
	 * @return Server logger
	 */
	Logger getServerLogger();

	/**
	 * Schedules a task to run on the main server thread
	 *
	 * @param delay Delay before execution
	 * @param task Task to execute
	 * @return Task id number
	 */
	int scheduleSyncTask(long delay, Runnable task);

	/**
	 * Schedules a repeating task with the server
	 *
	 * @param delay Delay before first execution
	 * @param period Period between subsequent executions
	 * @param task Task to execute
	 * @return Task id number
	 */
	int scheduleSyncRepeatingTask(long delay, long period, Runnable task);

	/**
	 * Schedules a task to run asynchronously
	 *
	 * @param delay Delay before execution
	 * @param task Task to execute
	 */
	int scheduleAsyncTask(long delay, Runnable task);

	/**
	 * Schedules a repeating asynchronous task with the server
	 *
	 * @param delay Delay before first execution
	 * @param period Period between subsequent executions
	 * @param task Task to execute
	 * @return Task id number
	 */
	int scheduleAsyncRepeatingTask(long delay, long period, Runnable task);

	/**
	 * Cancels a scheduled task
	 *
	 * @param id Id of the task to cancel
	 */
	void cancelTask(int id);

	/**
	 * Cancels all scheduled tasks
	 */
	void cancelAllTasks();

	/**
	 * Gets the server console
	 *
	 * @return Server's console command sender
	 */
	ConsoleCommandSender getConsoleSender();

	/**
	 * Gets the specified player
	 *
	 * @param uuid UUID of player to get
	 * @return Player
	 */
	Player getPlayer(UUID uuid);

	/**
	 * Handles a login from the specified player
	 *
	 * @param player The player that logged in
	 */
	void handleLogin(Player player);

	/**
	 * Handles a logout from the specified player
	 *
	 * @param player The player that logged out
	 */
	void handleLogout(String player);

	/**
	 * Gets the specified world
	 *
	 * @param name Name of world to get
	 * @return World
	 */
	World getWorld(String name);

	/**
	 * Loads existing players and worlds to catch anything from before startup
	 */
	void loadExistingData();

	/**
	 * Registers a command with the server
	 *
	 * @param command Command to register
	 */
	void registerCommand(String command);

	/**
	 * Registers a command alias with the server
	 *
	 * @param command Command to register alias for
	 * @param alias Alias for the command
	 * @return Whether or not the alias was registered
	 */
	boolean registerCommandAlias(String command, String alias);

}
