/*
 * HawkEye
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

package org.cubeville.hawkeye.item;

/**
 * Interface representing item data that can be serialized for storage
 *
 * Any class implementing this interface should also provide the following:
 *  - A constructor accepting a String
 */
public interface ItemData {

	/**
	 * Creates a string representation of this item data
	 *
	 * The class implementing this interface should be able to reconstruct an
	 * identical item when this string is passed into its constructor.
	 *
	 * @return String representing this item data
	 */
	String serialize();

}
