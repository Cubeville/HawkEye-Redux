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

package org.cubeville.hawkeye.bukkit.data;

import org.cubeville.hawkeye.block.BlockState;
import org.cubeville.hawkeye.bukkit.Convert;

public class BukkitBlockState extends BlockState {

	private final org.bukkit.block.BlockState blockState;

	public BukkitBlockState(org.bukkit.block.BlockState blockState) {
		super(Convert.material(blockState.getType()), blockState.getRawData(), Convert.blockData(blockState));
		this.blockState = blockState;
	}

	public org.bukkit.block.BlockState getBukkitBlockState() {
		return blockState;
	}

}
