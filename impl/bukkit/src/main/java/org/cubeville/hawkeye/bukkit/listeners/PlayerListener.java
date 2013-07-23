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

package org.cubeville.hawkeye.bukkit.listeners;

import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.bukkit.event.player.PlayerCommandPreprocessEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;

import org.cubeville.hawkeye.DefaultActions;
import org.cubeville.hawkeye.bukkit.Convert;
import org.cubeville.hawkeye.bukkit.HawkEvent;
import org.cubeville.hawkeye.bukkit.HawkEyeListener;
import org.cubeville.hawkeye.bukkit.HawkEyePlugin;
import org.cubeville.hawkeye.model.ChatEntry;
import org.cubeville.hawkeye.model.CommandEntry;
import org.cubeville.hawkeye.model.PlayerLogEntry;

public class PlayerListener extends HawkEyeListener {

	public PlayerListener(HawkEyePlugin plugin) {
		super(plugin);
	}

	@HawkEvent(action = DefaultActions.PLAYER_JOIN)
	public void onPlayerJoin(PlayerJoinEvent event) {
		Player player = event.getPlayer();
		Location loc = player.getLocation();
		String ip = player.getAddress().getAddress().getHostAddress();

		log(new PlayerLogEntry(DefaultActions.PLAYER_JOIN, player.getName(), Convert.location(loc), ip));
	}

	@HawkEvent(action = DefaultActions.PLAYER_QUIT)
	public void onPlayerQuit(PlayerQuitEvent event) {
		Player player = event.getPlayer();
		Location loc = player.getLocation();
		String ip = player.getAddress().getAddress().getHostAddress();

		log(new PlayerLogEntry(DefaultActions.PLAYER_QUIT, player.getName(), Convert.location(loc), ip));
	}

	@HawkEvent(action = DefaultActions.PLAYER_CHAT)
	public void onPlayerChat(AsyncPlayerChatEvent event) {
		Player player = event.getPlayer();
		Location loc = player.getLocation();

		log(new ChatEntry(player.getName(), Convert.location(loc), event.getMessage()));
	}

	@HawkEvent(action = DefaultActions.PLAYER_COMMAND, ignoreCancelled = false)
	public void onPlayerCommand(PlayerCommandPreprocessEvent event) {
		Player player = event.getPlayer();
		Location loc = player.getLocation();

		log(new CommandEntry(player.getName(), Convert.location(loc), event.getMessage()));
	}

}
