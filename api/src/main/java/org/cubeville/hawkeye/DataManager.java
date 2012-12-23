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

import org.cubeville.hawkeye.entity.Player;
import org.cubeville.hawkeye.location.World;

public interface DataManager {

	/**
	 * Gets a collection containing all the existing loggable actions
	 *
	 * @return Collection of loggable actions
	 */
	Collection<Action> getActions();

	/**
	 * Gets an action by its database name
	 *
	 * @param name Action's database name
	 * @return Corresponding action
	 */
	Action getAction(String name);

	/**
	 * Registers a loggable action
	 *
	 * @param action Action to register
	 * @return True if action was registered, false if not
	 */
	boolean registerAction(Action action);

	/**
	 * Gets a player by their id number
	 *
	 * @param id Player id number
	 * @return Player's name
	 */
	String getPlayer(int id);

	/**
	 * Registers a player in the database
	 *
	 * @param player Player to register
	 */
	void registerPlayer(Player player);

	/**
	 * Gets a world by its id number
	 *
	 * @param id World id number
	 * @return World
	 */
	World getWorld(int id);

	/**
	 * Registers a world in the database
	 *
	 * @param world World to register
	 */
	void registerWorld(World world);

}
