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

public class Vector {

	/**
	 * X coordinate
	 */
	protected final double x;

	/**
	 * Y coordinate
	 */
	protected final double y;

	/**
	 * Z coordinate
	 */
	protected final double z;

	/**
	 * Constructs a vector
	 *
	 * @param x X coordinate
	 * @param y Y coordinate
	 * @param z Z coordinate
	 */
	public Vector(double x, double y, double z) {
		this.x = x;
		this.y = y;
		this.z = z;
	}

	public Vector(int x, int y, int z) {
		this.x = x;
		this.y = y;
		this.z = z;
	}

	public Vector(float x, float y, float z) {
		this.x = x;
		this.y = y;
		this.z = z;
	}

	public Vector(Vector vector) {
		x = vector.x;
		y = vector.y;
		z = vector.z;
	}

	/**
	 * Constructs a vector at the origin
	 */
	public Vector() {
		x = 0;
		y = 0;
		z = 0;
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
		if (!(obj instanceof Vector)) return false;
		Vector other = (Vector) obj;

		if (Double.doubleToLongBits(x) != Double.doubleToLongBits(other.x)) return false;
		if (Double.doubleToLongBits(y) != Double.doubleToLongBits(other.y)) return false;
		if (Double.doubleToLongBits(z) != Double.doubleToLongBits(other.z)) return false;

		return true;
	}

	@Override
	public String toString() {
		return "(" + x + "," + y + "," + z + ")";
	}


}
