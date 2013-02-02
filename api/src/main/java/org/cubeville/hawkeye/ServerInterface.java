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
	 * Schedules a repeating task with the server
	 *
	 * @param delay Delay before first execution
	 * @param period Period between subsequent executions
	 * @param task Task to execute
	 * @return Task id number
	 */
	int scheduleRepeatingTask(long delay, long period, Runnable task);

	/**
	 * Cancels a scheduled task
	 *
	 * @param id Id of the task to cancel
	 */
	void cancelTask(int id);

	/**
	 * Gets the server console
	 *
	 * @return Server's console command sender
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
