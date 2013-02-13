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

package org.cubeville.hawkeye.bukkit;

import org.bukkit.Server;

import org.cubeville.hawkeye.AbstractServerInterface;
import org.cubeville.hawkeye.HawkEye;
import org.cubeville.hawkeye.ServerImplementation;
import org.cubeville.hawkeye.bukkit.command.BukkitConsole;
import org.cubeville.hawkeye.command.ConsoleCommandSender;
import org.cubeville.hawkeye.entity.Player;
import org.cubeville.hawkeye.location.World;

public class BukkitServer extends AbstractServerInterface {

	/**
	 * Plugin reference
	 */
	private final HawkEyePlugin plugin;

	/**
	 * Internal bukkit server reference
	 */
	private final Server server;

	public BukkitServer(HawkEyePlugin plugin, Server server) {
		this.plugin = plugin;
		this.server = server;

		for (org.bukkit.World world : server.getWorlds()) {
			// AbstractServerInterface will cache it
			HawkEye.getDataManager().registerWorld(getWorld(world.getName()));
		}

		for (org.bukkit.entity.Player player : server.getOnlinePlayers()) {
			// AbstractServerInterface will cache it
			HawkEye.getDataManager().registerPlayer(getPlayer(player.getName()));
		}
	}

	@Override
	protected ConsoleCommandSender loadConsoleSender() {
		return new BukkitConsole(server.getConsoleSender());
	}

	@Override
	protected Player loadPlayer(String name) {
		return plugin.getPlayer(name);
	}

	@Override
	protected World loadWorld(String name) {
		return plugin.getWorld(name);
	}

	@Override
	public ServerImplementation getImplementation() {
		return ServerImplementation.BUKKIT;
	}

	@Override
	public int scheduleSyncTask(long delay, Runnable task) {
		return server.getScheduler().scheduleSyncDelayedTask(plugin, task, delay);
	}

	@Override
	public int scheduleSyncRepeatingTask(long delay, long period, Runnable task) {
		return server.getScheduler().scheduleSyncRepeatingTask(plugin, task, delay, period);
	}

	@Override
	public int scheduleAsyncTask(long delay, Runnable task) {
		return server.getScheduler().runTaskLaterAsynchronously(plugin, task, delay).getTaskId();
	}

	@Override
	public int scheduleAsyncRepeatingTask(long delay, long period, Runnable task) {
		return server.getScheduler().runTaskTimerAsynchronously(plugin, task, delay, period).getTaskId();
	}

	@Override
	public void cancelTask(int id) {
		server.getScheduler().cancelTask(id);
	}

	@Override
	public void cancelAllTasks() {
		server.getScheduler().cancelTasks(plugin);
	}

	@Override
	public void registerCommand(String command) {
		// TODO Auto-generated method stub

	}

	@Override
	public boolean registerCommandAlias(String command, String alias) {
		// TODO Auto-generated method stub
		return false;
	}

}
