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

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerLoginEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.plugin.java.JavaPlugin;

import org.cubeville.hawkeye.HawkEyeEngine;
import org.cubeville.hawkeye.PluginEngine;
import org.cubeville.hawkeye.bukkit.entity.BukkitPlayer;
import org.cubeville.hawkeye.bukkit.listeners.BlockListener;
import org.cubeville.hawkeye.bukkit.listeners.InteractListener;
import org.cubeville.hawkeye.bukkit.listeners.PlayerListener;
import org.cubeville.hawkeye.config.HawkEyeConfig;
import org.cubeville.util.StringUtil;

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
		saveResource("config.yml", false);

		BukkitServer server = new BukkitServer(this, getServer());

		File configFile = new File(getDataFolder(), "config.yml");
		HawkEyeConfig config = new HawkEyeConfig(configFile);
		config.save();

		engine = new HawkEyeEngine(server, config);

		getServer().getPluginManager().registerEvents(this, this);

		new BlockListener(this).registerEvents();
		new InteractListener(this).registerEvents();
		new PlayerListener(this).registerEvents();
	}

	@Override
	public void onDisable() {
		engine.shutdown();
	}

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		String command = cmd.getName() + " " + StringUtil.buildString(args);
		engine.handleCommand(Convert.commandSender(sender), command);
		return true;
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
