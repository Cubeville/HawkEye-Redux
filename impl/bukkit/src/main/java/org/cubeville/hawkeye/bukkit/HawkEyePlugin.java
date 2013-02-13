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

import java.io.File;

import org.bukkit.World;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerLoginEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.plugin.java.JavaPlugin;

import org.cubeville.hawkeye.HawkEyeEngine;
import org.cubeville.hawkeye.PluginEngine;
import org.cubeville.hawkeye.bukkit.entity.BukkitPlayer;
import org.cubeville.hawkeye.config.HawkEyeConfig;

public class HawkEyePlugin extends JavaPlugin implements Listener {

	/**
	 * Main plugin engine
	 */
	private static PluginEngine engine;

	/**
	 * Gets the plugin engine
	 *
	 * @return Plugin engine
	 */
	public static PluginEngine getEngine() {
		return engine;
	}

	@Override
	public void onEnable() {
		BukkitServer server = new BukkitServer(this, getServer());
		File configFile = new File(getDataFolder(), "config.yml");
		HawkEyeConfig config = new HawkEyeConfig(configFile);

		engine = new HawkEyeEngine(server, config);

		getServer().getPluginManager().registerEvents(this, this);
	}

	@Override
	public void onDisable() {
		engine.getDatabase().close();
	}

	/**
	 * Creates a HawkEye world from the specified world
	 *
	 * This method should only be accessed by the server interface.  Other
	 * classes should access hawkeye worlds through the server interface as it
	 * contains a built in cache layer.
	 *
	 * @param name Name of world
	 * @return Native HawkEye bukkit world
	 */
	protected BukkitWorld getWorld(String name) {
		World world = getServer().getWorld(name);
		return world == null ? null : new BukkitWorld(world);
	}

	/**
	 * Creates a HawkEye player from the specified player
	 *
	 * This method should only be accessed by the server interface.  Other
	 * classes should get hawkeye players through the server interface as it
	 * contains a built in cache layer.
	 *
	 * @param name Name of player
	 * @return Native HawkEye bukkit player
	 */
	protected BukkitPlayer getPlayer(String name) {
		Player player = getServer().getPlayerExact(name);
		return player == null ? null : new BukkitPlayer(player);
	}

	@EventHandler(priority = EventPriority.MONITOR)
	public void onPlayerLogin(PlayerLoginEvent event) {
		engine.getServerInterface().handleLogin(new BukkitPlayer(event.getPlayer()));
	}

	@EventHandler(priority = EventPriority.MONITOR)
	public void onPlayerQuit(PlayerQuitEvent event) {
		engine.getServerInterface().handleLogout(event.getPlayer().getName());
	}

}
