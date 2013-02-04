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
	private short id;

	/**
	 * Block data value
	 */
	private byte data;

	/**
	 * Custom data associated with the block (tile entity data)
	 */
	private BlockData blockData;

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
	 */
	public BlockState(String str) {
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

		// TODO Add blockdata support
		// Idea: Store blockdata in hawkeye_nbt table and only access it when
		// needed, i.e. if the user requests it or it is rolled back
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
	 * Sets the type of this block state
	 *
	 * @param type Block type
	 */
	public void setType(Item type) {
		id = type.getId();
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
	 * Sets the data value of this block state
	 *
	 * @param data Data value
	 */
	public void setData(byte data) {
		this.data = data;
	}

	/**
	 * Gets the tile entity data associated with this block state
	 *
	 * @return Block data
	 */
	public BlockData getBlockData() {
		return blockData;
	}

	/**
	 * Sets the tile entity data associated with this block state
	 *
	 * @param blockData Block data
	 */
	public void setBlockData(BlockData blockData) {
		this.blockData = blockData;
	}

	@Override
	public String toString() {
		String ret = String.valueOf(id);
		if (data != 0) ret += ":" + String.valueOf(data);
		return ret;
	}

}
