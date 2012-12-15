package org.cubeville.hawkeye.location;

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
