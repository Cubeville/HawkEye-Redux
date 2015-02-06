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

import org.cubeville.hawkeye.HawkEye;
import org.cubeville.hawkeye.Item;

/**
 * Represents the state of a block at a certain point in time
 */
public class BlockState {

	/**
	 * Block type
	 */
	private final Item type;

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
		this.type = type;
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
		byte data;
		String[] parts = str.split(":");

		String name = parts[0];

		try {
			data = parts.length > 1 ? Byte.parseByte(parts[1]) : 0;
		} catch (NumberFormatException e) {
			data = 0;
		}

		type = HawkEye.getDataManager().getItem(name);
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
		return type;
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
	public int hashCode() {
		int hash = 1;

		hash = 31 * hash + type.getName().hashCode();
		hash = 31 * hash + data;

		return hash;
	}

	@Override
	public boolean equals(Object obj) {
		if (obj == null) return false;
		if (!(obj instanceof BlockState)) return false;
		BlockState other = (BlockState) obj;

		if (type.getId() != other.type.getId()) return false;
		if (!type.getName().equals(other.type.getName())) return false;
		if (data != other.data) return false;
		// TODO Compare block data

		return true;
	}

	@Override
	public String toString() {
		StringBuilder ret = new StringBuilder().append(type.getName());
		if (data != 0) ret.append(":").append(data);
		return ret.toString();
	}

}
