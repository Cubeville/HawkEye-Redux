package org.cubeville.hawkeye.location;

public class BlockWorldVector extends WorldVector {

	public BlockWorldVector(WorldVector vector) {
		super(vector.getWorld(), vector);
	}

	public BlockWorldVector(World world, int x, int y, int z) {
		super(world, x, y, z);
	}

	public BlockWorldVector(World world, double x, double y, double z) {
		super(world, x, y, z);
	}

	public BlockWorldVector(World world, float x, float y, float z) {
		super(world, x, y, z);
	}

	@Override
	public boolean equals(Object obj) {
		if (!(obj instanceof WorldVector)) return false;
		WorldVector other = (WorldVector) obj;

		return getWorld().getName() == other.getWorld().getName()
				&& getBlockX() == other.getBlockX()
				&& getBlockY() == other.getBlockY()
				&& getBlockZ() == other.getBlockZ();
	}

	@Override
	public BlockWorldVector toBlockWorldVector() {
		return this;
	}

}
