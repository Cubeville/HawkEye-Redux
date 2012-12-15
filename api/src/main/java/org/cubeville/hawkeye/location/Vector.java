package org.cubeville.hawkeye.location;

/**
 * Immutable vector position class
 */
public class Vector {
	private final double x;
	private final double y;
	private final double z;

	public Vector(double x, double y, double z) {
		this.x = x;
		this.y = y;
		this.z = z;
	}

	public Vector(int x, int y, int z) {
		this.x = x;
		this.y = y;
		this.z = z;
	}

	public Vector(float x, float y, float z) {
		this.x = x;
		this.y = y;
		this.z = z;
	}

	public Vector(Vector vector) {
		x = vector.x;
		y = vector.y;
		z = vector.z;
	}

	public Vector() {
		x = 0;
		y = 0;
		z = 0;
	}

	public double getX() {
		return x;
	}

	public int getBlockX() {
		return (int) Math.floor(x);
	}

	public double getY() {
		return y;
	}

	public int getBlockY() {
		return (int) Math.floor(y);
	}

	public double getZ() {
		return z;
	}

	public double getBlockZ() {
		return (int) Math.floor(z);
	}

	public BlockVector toBlockVector() {
		return new BlockVector(getBlockX(), getBlockY(), getBlockZ());
	}

}
