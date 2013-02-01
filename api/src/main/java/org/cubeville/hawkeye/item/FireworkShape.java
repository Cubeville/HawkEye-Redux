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

package org.cubeville.hawkeye.item;

import java.util.HashMap;
import java.util.Map;

public enum FireworkShape {

	/**
	 * Small ball shape
	 */
	BALL(0),
	/**
	 * Large ball shape
	 */
	LARGE_BALL(1),
	/**
	 * Star shape
	 */
	STAR(2),
	/**
	 * Creeper face shape
	 */
	CREEPER(3),
	/**
	 * Burst effect
	 */
	BURST(4);

	private final int id;

	/**
	 * Mapping of ids to firework shapes for quick access
	 */
	private static final Map<Integer, FireworkShape> idMap = new HashMap<Integer, FireworkShape>(values().length);

	private FireworkShape(int id) {
		this.id = id;
	}

	/**
	 * Gets the id of this shape
	 *
	 * @return This shape's id
	 */
	public int getId() {
		return id;
	}

	@Override
	public String toString() {
		return super.toString().replace("_", " ").toLowerCase();
	}

	/**
	 * Gets a shape by its id
	 *
	 * @param id Id of shape to get
	 * @return Firework shape with the specified id or null if it doesn't exist
	 */
	public static FireworkShape getById(int id) {
		return idMap.containsKey(id) ? idMap.get(id) : null;
	}

	static {
		for (FireworkShape shape : values()) {
			idMap.put(shape.id, shape);
		}
	}

}
