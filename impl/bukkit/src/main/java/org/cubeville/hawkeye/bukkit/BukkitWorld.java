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

package org.cubeville.hawkeye.bukkit;

import org.cubeville.hawkeye.entity.Entity;
import org.cubeville.hawkeye.location.Block;
import org.cubeville.hawkeye.location.Location;
import org.cubeville.hawkeye.location.World;
import org.cubeville.hawkeye.model.EntityData;

public class BukkitWorld implements World {

	private final org.bukkit.World world;

	public BukkitWorld(org.bukkit.World world) {
		this.world = world;
	}

	public org.bukkit.World getBukkitWorld() {
		return world;
	}

	@Override
	public String getName() {
		return world.getName();
	}

	@Override
	public String getDisplayName() {
		return world.getName();
	}

	@Override
	public Location getLocationAt(double x, double y, double z) {
		return new Location(this, x, y, z);
	}

	@Override
	public Location getLocationAt(int x, int y, int z) {
		return new Location(this, x, y, z);
	}

	@Override
	public Block getBlockAt(double x, double y, double z) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Block getBlockAt(int x, int y, int z) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void spawnEntity(Location location, Entity entity, EntityData data) {
		world.spawnEntity(Convert.location(location), Convert.entityType(entity.getType()));
		// TODO Apply entity data
	}

}
