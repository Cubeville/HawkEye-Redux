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

import org.cubeville.hawkeye.entity.Player;
import org.cubeville.hawkeye.model.BlockState;

public abstract class Block {

	/**
	 * Gets this block's type ID
	 *
	 * @return Block ID
	 */
	public abstract int getType();

	/**
	 * Sets this block's type ID
	 *
	 * @param id New block type ID
	 */
	public abstract void setType(int id);

	/**
	 * Gets this block's metadata value
	 *
	 * @return Block metadata
	 */
	public abstract byte getData();

	/**
	 * Sets this block's metadata value
	 *
	 * @param data New metadata value
	 */
	public abstract void setData(byte data);

	/**
	 * Gets this block's current state
	 *
	 * @return Block's current state
	 */
	public abstract BlockState getState();

	/**
	 * Sets this block's current state
	 *
	 * @param state New block state
	 */
	public abstract void setState(BlockState state);

	/**
	 * Sets this block's current state for a certain player
	 *
	 * @param player Player to set state for
	 * @param state New block state
	 */
	public abstract void setLocalState(Player player, BlockState state);

	/**
	 * Gets this block's name
	 *
	 * It's important to store the block name in the database as Mojang has
	 * expressed that they would like to move away from the ID based system
	 * as they work on the Workbench API.
	 *
	 * @return Block's name
	 */
	public abstract String getName();

	public void setName(String name) {
		throw new UnsupportedOperationException("Block names cannot be set");
	}

	/**
	 * Gets the world this block is in
	 *
	 * @return This block's world
	 */
	public abstract World getWorld();

	/**
	 * Gets the x coordinate of this block
	 *
	 * @return This block's x coordinate
	 */
	public abstract int getX();

	/**
	 * Gets the y coordinate of this block
	 *
	 * @return This block's y coordinate
	 */
	public abstract int getY();

	/**
	 * Gets the z coordinate of this block
	 *
	 * @return This block's z coordinate
	 */
	public abstract int getZ();

}
