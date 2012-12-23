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

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import org.cubeville.hawkeye.entity.Player;
import org.cubeville.hawkeye.location.World;

public class SimpleDataManager implements DataManager {

	/**
	 * Map containing action database names and their corresponding actions
	 */
	private final Map<String, Action> actions = new HashMap<String, Action>();

	/**
	 * Map containing player ids and their names
	 */
	private final Map<Integer, String> players = new HashMap<Integer, String>();

	/**
	 * Map containg world ids and their names
	 */
	private final Map<Integer, String> worlds = new HashMap<Integer, String>();

	public SimpleDataManager() {
		for (Action action : DefaultActions.values()) {
			registerAction(action);
		}
	}

	@Override
	public Collection<Action> getActions() {
		return actions.values();
	}

	@Override
	public Action getAction(String name) {
		return actions.get(name);
	}

	@Override
	public boolean registerAction(Action action) {
		String name = action.getName().toLowerCase();
		if (actions.containsKey(name)) return false;

		actions.put(name, action);
		return true;
	}

	@Override
	public String getPlayer(int id) {
		return players.get(id);
	}

	@Override
	public void registerPlayer(Player player) {
		String name = player.getName();
		int id = 0;
		// TODO Insert into database and get id
		players.put(id, name);
	}

	@Override
	public World getWorld(int id) {
		return HawkEye.getWorld(worlds.get(id));
	}

	@Override
	public void registerWorld(World world) {
		String name = world.getName();
		int id = 0;
		// TODO Insert into database and get id
		worlds.put(id, name);
	}

}
