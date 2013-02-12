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

package org.cubeville.hawkeye.block;

import org.cubeville.hawkeye.Item;

/**
 * Represents the state of a block at a certain point in time
 */
public class BlockState {

	public static final BlockState NOTHING = new BlockState(Item.AIR, (byte) 0);

	/**
	 * Block type
	 */
	private final short id;

	/**
	 * Block data value
	 */
	private final byte data;

	/**
	 * Custom data associated with the block (tile entity data)
	 */
	private final BlockData blockData;

	public BlockState(Item type, byte data) {
		this(type, data, null);
	}

	public BlockState(Item type, byte data, BlockData blockData) {
		this(type.getId(), data, null);
	}

	public BlockState(short id, byte data) {
		this(id, data, null);
	}

	public BlockState(short id, byte data, BlockData blockData) {
		this.id = id;
		this.data = data;
		this.blockData = blockData;
	}

	/**
	 * Constructs a blockstate from a database serialized string
	 *
	 * @param str Database string
	 * @param nbt Block nbt data byte array
	 */
	public BlockState(String str, byte[] nbt) {
		short id;
		byte data;
		String[] parts = str.split(":");

		try {
			id = Short.parseShort(parts[0]);
		} catch (NumberFormatException e) {
			id = 0;
		}

		try {
			data = parts.length > 1 ? Byte.parseByte(parts[1]) : 0;
		} catch (NumberFormatException e) {
			data = 0;
		}

		this.id = id;
		this.data = data;
		blockData = null;

		if (nbt != null && nbt.length > 0) {
			// TODO Block data support
		}
	}

	public BlockState(String str) {
		this(str, null);
	}

	/**
	 * Gets the type of this block state
	 *
	 * @return Block type
	 */
	public Item getType() {
		return Item.getById(id);
	}

	/**
	 * Gets the id of this block state's type
	 *
	 * @return Block type id
	 */
	public short getTypeId() {
		return id;
	}

	/**
	 * Gets the data value of this block state
	 *
	 * @return Data value
	 */
	public byte getData() {
		return data;
	}

	/**
	 * Gets the tile entity data associated with this block state
	 *
	 * @return Block data
	 */
	public BlockData getBlockData() {
		return blockData;
	}

	@Override
	public String toString() {
		String ret = String.valueOf(id);
		if (data != 0) ret += ":" + String.valueOf(data);
		return ret;
	}

}
