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

}
