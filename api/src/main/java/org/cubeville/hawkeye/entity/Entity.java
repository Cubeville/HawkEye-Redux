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

package org.cubeville.hawkeye.entity;

import org.cubeville.hawkeye.model.EntityData;

public class Entity {

	/**
	 * Entity type
	 */
	private final int type;

	/**
	 * Custom entity data
	 */
	private final EntityData data;

	public Entity(EntityType type) {
		this(type.getId(), null);
	}

	public Entity(EntityType type, EntityData data) {
		this(type.getId(), data);
	}

	public Entity(int type) {
		this(type, null);
	}

	public Entity(int type, EntityData data) {
		this.type = type;
		this.data = data;
	}

	/**
	 * Constructs an entity from a database serialized string
	 *
	 * @param str Database string
	 * @param nbt Entity nbt data byte array
	 */
	public Entity(String str, byte[] nbt) {
		int type;

		try {
			type = Integer.parseInt(str);
		} catch (NumberFormatException e) {
			type = -1;
		}

		this.type = type;
		data = null;

		if (nbt != null && nbt.length > 0) {
			// TODO Entity data support
		}
	}

	public Entity(String str) {
		this(str, null);
	}

	/**
	 * Gets the type of this entity
	 *
	 * @return Entity type
	 */
	public EntityType getType() {
		return EntityType.getById(type);
	}

	/**
	 * Gets the id of the type of this entity
	 *
	 * @return Entity type id
	 */
	public int getTypeId() {
		return type;
	}

	/**
	 * Gets any custom entity data associated with this entity
	 *
	 * @return Entity data
	 */
	public EntityData getData() {
		return data;
	}

	@Override
	public String toString() {
		return String.valueOf(type);
	}

}
