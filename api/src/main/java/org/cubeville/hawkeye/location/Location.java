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

package org.cubeville.hawkeye.location;

/**
 * Immutable location class
 */
public class Location {

	/**
	 * World this location is in
	 */
	private final World world;

	/**
	 * X coordinate of this location
	 */
	private final double x;

	/**
	 * Y coordinate of this location
	 */
	private final double y;

	/**
	 * Z coordinate of this location
	 */
	private final double z;

	/**
	 * Constructs a location
	 *
	 * @param world World the location is in
	 * @param x X coordinate of the location
	 * @param y Y coordinate of the location
	 * @param z Z coordinate of the location
	 */
	public Location(World world, double x, double y, double z) {
		this.world = world;
		this.x = x;
		this.y = y;
		this.z = z;
	}

	public Location(World world, int x, int y, int z) {
		this.world = world;
		this.x = x;
		this.y = y;
		this.z = z;
	}

	public Location(World world, float x, float y, float z) {
		this.world = world;
		this.x = x;
		this.y = y;
		this.z = z;
	}

	public Location(Location location) {
		world = location.world;
		x = location.x;
		y = location.y;
		z = location.z;
	}

	/**
	 * Constructs a location at the origin of the world
	 *
	 * @param world World the location is in
	 */
	public Location(World world) {
		this.world = world;
		x = 0;
		y = 0;
		z = 0;
	}

	/**
	 * Gets the world this location is in
	 *
	 * @return World this location is in
	 */
	public World getWorld() {
		return world;
	}

	/**
	 * Gets this location's x coordinate
	 *
	 * @return Location's x coordinate
	 */
	public double getX() {
		return x;
	}

	/**
	 * Gets this location's block x coordinate
	 *
	 * @return Location's x coordinate rounded to nearest block
	 */
	public int getBlockX() {
		return (int) x;
	}

	/**
	 * Gets this location's y coordinate
	 *
	 * @return Location's y coordinate
	 */
	public double getY() {
		return y;
	}

	/**
	 * Gets this location's block y coordinate
	 *
	 * @return Location's y coordinate rounded to nearest block
	 */
	public int getBlockY() {
		return (int) y;
	}

	/**
	 * Gets this location's z coordinate
	 *
	 * @return Location's z coordinate
	 */
	public double getZ() {
		return z;
	}

	/**
	 * Gets this location's block z coordinate
	 *
	 * @return Location's z coordinate rounded to nearest block
	 */
	public double getBlockZ() {
		return (int) z;
	}

	@Override
	public int hashCode() {
		int hash = 1;

		hash = 31 * hash + (world == null ? 0 : world.hashCode());
		long temp;
		temp = Double.doubleToLongBits(x);
		hash = 31 * hash + (int) (temp ^ (temp >>> 32));
		temp = Double.doubleToLongBits(y);
		hash = 31 * hash + (int) (temp ^ (temp >>> 32));
		temp = Double.doubleToLongBits(z);
		hash = 31 * hash + (int) (temp ^ (temp >>> 32));

		return hash;
	}

	@Override
	public boolean equals(Object obj) {
		if (obj == null) return false;
		if (!(obj instanceof Location)) return false;
		Location other = (Location) obj;

		if (world == null) {
			if (other.world != null) return false;
		} else if (!world.equals(other.world)) {
			return false;
		}

		if (Double.doubleToLongBits(x) != Double.doubleToLongBits(other.x)) return false;
		if (Double.doubleToLongBits(y) != Double.doubleToLongBits(other.y)) return false;
		if (Double.doubleToLongBits(z) != Double.doubleToLongBits(other.z)) return false;

		return true;
	}

	@Override
	public String toString() {
		return "Location[world=" + world + ",x=" + x + ",y=" + y + ",z=" + z + "]";
	}

}
