package org.cubeville.hawkeye.location;

public interface World {

	/**
	 * Gets the name of this world
	 *
	 * @return Name of this world
	 */
	String getName();

	/**
	 * Gets the location at the specified coordinates in this world
	 *
	 * @param x X coordinate to get
	 * @param y Y coordinate to get
	 * @param z Z coordinate to get
	 * @return Location at x,y,z in this world
	 */
	Location getLocationAt(double x, double y, double z);
	Location getLocationAt(int x, int y, int z);

	/**
	 * Gets the block at the specified coordinates in this world
	 *
	 * @param x X coordinate to get
	 * @param y Y coordinate to get
	 * @param z Z coordinate to get
	 * @return Block at x,y,z in this world
	 */
	Block getBlockAt(double x, double y, double z);
	Block getBlockAt(int x, int y, int z);

}
