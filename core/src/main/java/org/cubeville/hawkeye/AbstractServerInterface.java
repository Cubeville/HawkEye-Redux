/*
 * HawkEye Redux
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

import java.util.HashMap;
import java.util.Map;

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
	private final Map<String, Player> playerCache = new HashMap<String, Player>();

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
	public Player getPlayer(String name) {
		Player player = playerCache.get(name.toLowerCase());
		if (player != null) return player;

		player = loadPlayer(name);
		if (player != null) playerCache.put(name.toLowerCase(), player);

		return player;
	}

	/**
	 * Loads a player directly from the server
	 *
	 * @param name Name of player to load
	 * @return Server's player implementation
	 */
	protected abstract Player loadPlayer(String name);

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
