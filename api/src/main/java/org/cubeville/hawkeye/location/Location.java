package org.cubeville.hawkeye.location;

/**
 * Immutable location class
 */
public class Location {

	/**
	 * World this location is in
	 */
	private final World world;

	/**
	 * X coordinate of this location
	 */
	private final double x;

	/**
	 * Y coordinate of this location
	 */
	private final double y;

	/**
	 * Z coordinate of this location
	 */
	private final double z;

	/**
	 * Constructs a location
	 *
	 * @param world World the location is in
	 * @param x X coordinate of the location
	 * @param y Y coordinate of the location
	 * @param z Z coordinate of the location
	 */
	public Location(World world, double x, double y, double z) {
		this.world = world;
		this.x = x;
		this.y = y;
		this.z = z;
	}

	public Location(World world, int x, int y, int z) {
		this.world = world;
		this.x = x;
		this.y = y;
		this.z = z;
	}

	public Location(World world, float x, float y, float z) {
		this.world = world;
		this.x = x;
		this.y = y;
		this.z = z;
	}

	public Location(Location location) {
		world = location.world;
		x = location.x;
		y = location.y;
		z = location.z;
	}

	/**
	 * Constructs a location at the origin of the world
	 *
	 * @param world World the location is in
	 */
	public Location(World world) {
		this.world = world;
		x = 0;
		y = 0;
		z = 0;
	}

	/**
	 * Gets the world this location is in
	 *
	 * @return World this location is in
	 */
	public World getWorld() {
		return world;
	}

	/**
	 * Gets this location's x coordinate
	 *
	 * @return Location's x coordinate
	 */
	public double getX() {
		return x;
	}

	/**
	 * Gets this location's block x coordinate
	 *
	 * @return Location's x coordinate rounded to nearest block
	 */
	public int getBlockX() {
		return (int) x;
	}

	/**
	 * Gets this location's y coordinate
	 *
	 * @return Location's y coordinate
	 */
	public double getY() {
		return y;
	}

	/**
	 * Gets this location's block y coordinate
	 *
	 * @return Location's y coordinate rounded to nearest block
	 */
	public int getBlockY() {
		return (int) y;
	}

	/**
	 * Gets this location's z coordinate
	 *
	 * @return Location's z coordinate
	 */
	public double getZ() {
		return z;
	}

	/**
	 * Gets this location's block z coordinate
	 *
	 * @return Location's z coordinate rounded to nearest block
	 */
	public double getBlockZ() {
		return (int) z;
	}

}
