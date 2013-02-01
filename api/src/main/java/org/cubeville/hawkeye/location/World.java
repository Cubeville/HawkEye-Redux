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

package org.cubeville.hawkeye.location;

import org.cubeville.hawkeye.Named;

public interface World extends Named {

	/**
	 * Gets the location at the specified coordinates in this world
	 *
	 * @param x X coordinate to get
	 * @param y Y coordinate to get
	 * @param z Z coordinate to get
	 * @return Location at x,y,z in this world
	 */
	Location getLocationAt(double x, double y, double z);
	Location getLocationAt(int x, int y, int z);

	/**
	 * Gets the block at the specified coordinates in this world
	 *
	 * @param x X coordinate to get
	 * @param y Y coordinate to get
	 * @param z Z coordinate to get
	 * @return Block at x,y,z in this world
	 */
	Block getBlockAt(double x, double y, double z);
	Block getBlockAt(int x, int y, int z);

}
