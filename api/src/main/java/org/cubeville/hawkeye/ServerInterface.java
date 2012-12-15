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
