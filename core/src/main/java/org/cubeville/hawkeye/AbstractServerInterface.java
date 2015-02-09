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

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import org.cubeville.hawkeye.command.ConsoleCommandSender;
import org.cubeville.hawkeye.entity.Player;
import org.cubeville.hawkeye.location.World;

/**
 * Abstracted server interface to implement a caching layer
 */
public abstract class AbstractServerInterface implements ServerInterface {

	/**
	 * Cached console sender
	 */
	private ConsoleCommandSender consoleCache = null;

	/**
	 * Cached player objects
	 */
	private final Map<UUID, Player> playerCache = new HashMap<UUID, Player>();

	/**
	 * Cached world objects
	 */
	private final Map<String, World> worldCache = new HashMap<String, World>();

	@Override
	public ConsoleCommandSender getConsoleSender() {
		if (consoleCache == null) consoleCache = loadConsoleSender();
		return consoleCache;
	}

	/**
	 * Loads the console sender from the server layer
	 *
	 * @return Server's console sender implementation
	 */
	protected abstract ConsoleCommandSender loadConsoleSender();

	@Override
	public Player getPlayer(UUID uuid) {
		Player player = playerCache.get(uuid);
		if (player != null) return player;

		player = loadPlayer(uuid);
		if (player != null) playerCache.put(uuid, player);

		return player;
	}

	@Override
	public void handleLogin(Player player) {
		HawkEye.getDataManager().registerPlayer(player);
		playerCache.put(player.getUUID(), player);
	}

	@Override
	public void handleLogout(String player) {
		playerCache.remove(player.toLowerCase());
	}

	/**
	 * Loads a player directly from the server
	 *
	 * @param uuid UUID of player to load
	 * @return Server's player implementation
	 */
	protected abstract Player loadPlayer(UUID uuid);

	@Override
	public World getWorld(String name) {
		World world = worldCache.get(name.toLowerCase());
		if (world != null) return world;

		world = loadWorld(name);
		if (world != null) worldCache.put(name.toLowerCase(), world);

		return world;
	}

	/**
	 * Loads a world directly from the server
	 *
	 * @param name Name of world to load
	 * @return Server's world implementation
	 */
	protected abstract World loadWorld(String name);

}
