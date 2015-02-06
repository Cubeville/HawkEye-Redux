/*
 * HawkEye Redux
 * Copyright (C) 2012-2015 Cubeville <http://www.cubeville.org> and contributors
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

public interface ItemManager {

	/**
	 * Gets a collection of all registered items
	 *
	 * @return Collection of all known items
	 */
	Collection<Item> getItems();

	/**
	 * Gets an item by its name
	 *
	 * @param name Name of block/item
	 * @return Corresponding item
	 */
	Item getItem(String name);

	/**
	 * Registers a block/item
	 *
	 * @param item Item to register
	 * @return True if item was registered, false if not
	 */
	boolean registerItem(Item item);

	/**
	 * Attempts to match an item from user input
	 *
	 * @param input The user's input
	 * @return Item or null if no match was found
	 */
	Item matchItem(String input);

}
