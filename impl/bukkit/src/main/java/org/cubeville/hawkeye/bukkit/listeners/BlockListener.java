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

package org.cubeville.hawkeye.bukkit.listeners;

import org.bukkit.Location;
import org.bukkit.block.BlockState;
import org.bukkit.entity.Player;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;

import org.cubeville.hawkeye.DefaultActions;
import org.cubeville.hawkeye.bukkit.Convert;
import org.cubeville.hawkeye.bukkit.HawkEvent;
import org.cubeville.hawkeye.bukkit.HawkEyeListener;
import org.cubeville.hawkeye.bukkit.HawkEyePlugin;
import org.cubeville.hawkeye.model.BlockBreakEntry;
import org.cubeville.hawkeye.model.BlockPlaceEntry;

public class BlockListener extends HawkEyeListener {

	public BlockListener(HawkEyePlugin plugin) {
		super(plugin);
	}

	@HawkEvent(action = DefaultActions.BLOCK_PLACE)
	public void onBlockPlace(BlockPlaceEvent event) {
		Player player = event.getPlayer();
		Location loc = event.getBlockPlaced().getLocation();
		BlockState old = event.getBlockReplacedState();
		BlockState placed = event.getBlockPlaced().getState();

		log(new BlockPlaceEntry(player.getName(), Convert.location(loc), Convert.blockState(old), Convert.blockState(placed)));
	}

	@HawkEvent(action = DefaultActions.BLOCK_BREAK)
	public void onBlockBreak(BlockBreakEvent event) {
		Player player = event.getPlayer();
		Location loc = event.getBlock().getLocation();
		BlockState broken = event.getBlock().getState();

		log(new BlockBreakEntry(player.getName(), Convert.location(loc), Convert.blockState(broken)));
	}

}
