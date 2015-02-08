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

import java.util.UUID;

import org.bukkit.Location;
import org.bukkit.block.Block;
import org.bukkit.block.BlockState;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.entity.Projectile;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.hanging.HangingBreakByEntityEvent;
import org.bukkit.event.hanging.HangingBreakEvent;
import org.bukkit.event.hanging.HangingPlaceEvent;
import org.bukkit.event.player.PlayerBucketEmptyEvent;
import org.bukkit.projectiles.ProjectileSource;

import org.cubeville.hawkeye.Action;
import org.cubeville.hawkeye.DefaultActions;
import org.cubeville.hawkeye.DefaultItems;
import org.cubeville.hawkeye.Item;
import org.cubeville.hawkeye.bukkit.Convert;
import org.cubeville.hawkeye.bukkit.HawkEvent;
import org.cubeville.hawkeye.bukkit.HawkEyeListener;
import org.cubeville.hawkeye.bukkit.HawkEyePlugin;
import org.cubeville.hawkeye.model.BlockBreakEntry;
import org.cubeville.hawkeye.model.BlockBucketEntry;
import org.cubeville.hawkeye.model.BlockPlaceEntry;
import org.cubeville.hawkeye.model.HangingBreakEntry;
import org.cubeville.hawkeye.model.HangingPlaceEntry;

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

		log(new BlockPlaceEntry(player.getUniqueId(), Convert.location(loc), Convert.blockState(old), Convert.blockState(placed)));
	}

	@HawkEvent(action = DefaultActions.BLOCK_BREAK)
	public void onBlockBreak(BlockBreakEvent event) {
		Player player = event.getPlayer();
		Location loc = event.getBlock().getLocation();
		BlockState broken = event.getBlock().getState();

		log(new BlockBreakEntry(player.getUniqueId(), Convert.location(loc), Convert.blockState(broken)));
	}

	@HawkEvent(action = DefaultActions.HANGING_BREAK)
	public void onHangingBreak(HangingBreakEvent event) {
		Entity removed = event.getEntity();
		Location loc = removed.getLocation();

		// Removed by environment unless we can find a player
		// TODO Environment UUID
		UUID remover = null;

		if (event instanceof HangingBreakByEntityEvent) {
			Entity entity = ((HangingBreakByEntityEvent) event).getRemover();

			if (entity instanceof Projectile) {
				ProjectileSource source = ((Projectile) entity).getShooter();
				if (source instanceof Entity) entity = (Entity) source;
			}

			if (entity instanceof Player) {
				remover = ((Player) entity).getUniqueId();
			}
		}

		log(new HangingBreakEntry(remover, Convert.location(loc), Convert.entity(removed)));
	}

	@HawkEvent(action = DefaultActions.HANGING_PLACE)
	public void onHangingPlace(HangingPlaceEvent event) {
		Player player = event.getPlayer();
		Entity placed = event.getEntity();
		Location loc = placed.getLocation();

		log(new HangingPlaceEntry(player.getUniqueId(), Convert.location(loc), Convert.entity(placed)));
	}

	@HawkEvent(action = {DefaultActions.LAVA_BUCKET, DefaultActions.WATER_BUCKET})
	public void onPlayerBucketEmpty(PlayerBucketEmptyEvent event) {
		Action action;
		Item type;

		switch (event.getBucket()) {
			case LAVA_BUCKET:
				action = DefaultActions.LAVA_BUCKET;
				type = DefaultItems.LAVA;
				break;
			case WATER_BUCKET:
			default:
				action = DefaultActions.WATER_BUCKET;
				type = DefaultItems.WATER;
				break;
		}

		Player player = event.getPlayer();
		Block block = event.getBlockClicked().getRelative(event.getBlockFace());
		Location loc = block.getLocation();
		BlockState old = block.getState();
		org.cubeville.hawkeye.block.BlockState liquid = new org.cubeville.hawkeye.block.BlockState(type, (byte) 0);

		log(new BlockBucketEntry(action, player.getUniqueId(), Convert.location(loc), Convert.blockState(old), liquid));
	}

}
