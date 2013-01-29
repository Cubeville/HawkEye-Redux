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

package org.cubeville.hawkeye.item.data;

import java.util.HashMap;
import java.util.Map;

import org.cubeville.hawkeye.item.FireworkShape;
import org.cubeville.hawkeye.model.NBTSerializable;
import org.cubeville.lib.jnbt.ByteTag;
import org.cubeville.lib.jnbt.CompoundTag;
import org.cubeville.lib.jnbt.Tag;

public class FireworkExplosion implements NBTSerializable {

	private final byte shapeId;
	private final boolean flicker;
	private final boolean trail;

	/**
	 * Deserialization constructor
	 *
	 * @param data Tag to deserialize from
	 */
	public FireworkExplosion(CompoundTag tag) {
		Map<String, Tag> data = tag.getValue();
		shapeId = ((ByteTag) data.get("Type")).getValue();
		flicker = (((ByteTag) data.get("Flicker")).getValue() == 1);
		trail = (((ByteTag) data.get("Trail")).getValue() == 1);
	}

	/**
	 * Gets the shape of this firework explosion
	 */
	public FireworkShape getShape() {
		return FireworkShape.getById(shapeId);
	}

	/**
	 * Gets the id of the shape of this firework explosino
	 */
	public byte getShapeId() {
		return shapeId;
	}

	/**
	 * Returns whether or not this firework explosion has the flicker effect
	 */
	public boolean hasFlicker() {
		return flicker;
	}

	/**
	 * Returns whether or not this firework explosion has the trail effect
	 */
	public boolean hasTrail() {
		return trail;
	}

	@Override
	public CompoundTag serialize() {
		Map<String, Tag> data = new HashMap<String, Tag>();
		data.put("Type", new ByteTag("Type", shapeId));
		data.put("Flicker", new ByteTag("Flicker", (byte) (flicker ? 1 : 0)));
		data.put("Trail", new ByteTag("Trail", (byte) (trail ? 1 : 0)));
		return new CompoundTag("", data);
	}

}
