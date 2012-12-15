package org.cubeville.hawkeye.location;

public class BlockVector extends Vector {

	public BlockVector(Vector vector) {
		super(vector);
	}

	public BlockVector(int x, int y, int z) {
		super(x, y, z);
	}

	public BlockVector(double x, double y, double z) {
		super(x, y, z);
	}

	public BlockVector(float x, float y, float z) {
		super(x, y, z);
	}

	@Override
	public boolean equals(Object obj) {
		if (!(obj instanceof Vector)) return false;
		Vector other = (Vector) obj;

		return getBlockX() == other.getBlockX()
				&& getBlockY() == other.getBlockY()
				&& getBlockZ() == other.getBlockZ();
	}

	@Override
	public BlockVector toBlockVector() {
		return this;
	}

}
