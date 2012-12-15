package org.cubeville.hawkeye.location;

public class WorldVector extends Vector {

	private final World world;

	public WorldVector(World world, double x, double y, double z) {
		super(x, y, z);
		this.world = world;
	}

	public WorldVector(World world, int x, int y, int z) {
		super(x, y, z);
		this.world = world;
	}

	public WorldVector(World world, float x, float y, float z) {
		super(x, y, z);
		this.world = world;
	}

	public WorldVector(World world, Vector vector) {
		super(vector);
		this.world = world;
	}

	public World getWorld() {
		return world;
	}

	public BlockWorldVector toBlockWorldVector() {
		return new BlockWorldVector(world, getBlockX(), getBlockY(), getBlockZ());
	}

}
