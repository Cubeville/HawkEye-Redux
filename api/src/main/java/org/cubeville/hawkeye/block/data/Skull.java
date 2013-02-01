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

package org.cubeville.hawkeye.block.data;

import java.util.Map;

import org.cubeville.hawkeye.NBT;
import org.cubeville.lib.jnbt.ByteTag;
import org.cubeville.lib.jnbt.CompoundTag;
import org.cubeville.lib.jnbt.StringTag;
import org.cubeville.lib.jnbt.Tag;

public class Skull extends BaseBlockData {

	private final byte type;
	private final String player;
	private final byte rotation;

	/**
	 * Deserialization constructor
	 *
	 * @param data Tag to deserialize from
	 */
	public Skull(CompoundTag tag) {
		Map<String, Tag> data = tag.getValue();

		type = ((ByteTag) data.get(NBT.BLOCK.SKULL.TYPE)).getValue();
		rotation = ((ByteTag) data.get(NBT.BLOCK.SKULL.ROTATION)).getValue();

		if (data.containsKey(NBT.BLOCK.SKULL.PLAYER)) {
			player = ((StringTag) data.get(NBT.BLOCK.SKULL.PLAYER)).getValue();
		} else {
			player = "";
		}
	}

	/**
	 * Gets the type of this skull
	 */
	public byte getType() {
		return type;
	}

	/**
	 * Returns whether or not this skull has a custom player
	 */
	public boolean hasPlayer() {
		return !(player == null || player.isEmpty());
	}

	/**
	 * Gets the player this skull represents
	 */
	public String getPlayer() {
		return player;
	}

	/**
	 * Gets the rotation of this skull
	 */
	public byte getRotation() {
		return rotation;
	}

	@Override
	public void serialize(Map<String, Tag> map) {
		map.put(NBT.BLOCK.SKULL.TYPE, new ByteTag(NBT.BLOCK.SKULL.TYPE, type));
		map.put(NBT.BLOCK.SKULL.ROTATION, new ByteTag(NBT.BLOCK.SKULL.ROTATION, rotation));

		if (hasPlayer()) {
			map.put(NBT.BLOCK.SKULL.PLAYER, new StringTag(NBT.BLOCK.SKULL.PLAYER, player));
		}
	}

}
