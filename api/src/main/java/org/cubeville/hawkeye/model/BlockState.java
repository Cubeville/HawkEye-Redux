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

package org.cubeville.hawkeye.model;

import org.cubeville.hawkeye.item.Blocks;

/**
 * Represents the state of a block at a certain point in time
 */
public class BlockState {

	/**
	 * Block type
	 */
	private Blocks type;

	/**
	 * Block data value
	 */
	private byte data;

	public BlockState(Blocks type, byte data) {
		this.type = type;
		this.data = data;
	}

	/**
	 * Constructs a blockstate from a database serialized string
	 *
	 * @param str Database string
	 */
	public BlockState(String str) {
		int id;
		byte data;
		String[] parts = str.split(":");

		try {
			id = Integer.parseInt(parts[0]);
		} catch (NumberFormatException e) {
			id = 0;
		}

		try {
			data = parts.length > 1 ? Byte.parseByte(parts[1]) : 0;
		} catch (NumberFormatException e) {
			data = 0;
		}

		type = Blocks.getById(id);
		this.data = data;
	}

	/**
	 * Gets the type of this block state
	 *
	 * @return Block type
	 */
	public Blocks getType() {
		return type;
	}

	/**
	 * Sets the type of this block state
	 *
	 * @param type Block type
	 */
	public void setType(Blocks type) {
		this.type = type;
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

	@Override
	public String toString() {
		String ret = String.valueOf(type.getId());
		if (data != 0) ret += ":" + String.valueOf(data);
		return ret;
	}

}
