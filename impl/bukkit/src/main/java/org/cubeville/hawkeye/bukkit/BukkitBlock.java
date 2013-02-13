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

import org.cubeville.hawkeye.block.BlockState;
import org.cubeville.hawkeye.entity.Player;
import org.cubeville.hawkeye.location.Block;
import org.cubeville.hawkeye.location.World;

public class BukkitBlock extends Block {

	private final org.bukkit.block.Block block;

	public BukkitBlock(org.bukkit.block.Block block) {
		this.block = block;
	}

	public org.bukkit.block.Block getBukkitBlock() {
		return block;
	}

	@Override
	public int getType() {
		return block.getTypeId();
	}

	@Override
	public void setType(int id) {
		block.setTypeId(id);
	}

	@Override
	public byte getData() {
		return block.getData();
	}

	@Override
	public void setData(byte data) {
		block.setData(data);
	}

	@Override
	public BlockState getState() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void setState(BlockState state) {
		// TODO Auto-generated method stub

	}

	@Override
	public void setLocalState(Player player, BlockState state) {
		// TODO Auto-generated method stub

	}

	@Override
	public String getName() {
		return block.getType().toString();
	}

	@Override
	public World getWorld() {
		return Convert.world(block.getWorld());
	}

	@Override
	public int getX() {
		return block.getX();
	}

	@Override
	public int getY() {
		return block.getY();
	}

	@Override
	public int getZ() {
		return block.getZ();
	}

}
