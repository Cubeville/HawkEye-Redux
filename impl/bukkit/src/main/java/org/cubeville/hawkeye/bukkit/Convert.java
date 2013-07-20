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

import org.cubeville.hawkeye.HawkEye;
import org.cubeville.hawkeye.block.BlockState;
import org.cubeville.hawkeye.bukkit.command.BukkitConsole;
import org.cubeville.hawkeye.bukkit.entity.BukkitPlayer;
import org.cubeville.hawkeye.command.CommandSender;
import org.cubeville.hawkeye.command.ConsoleCommandSender;
import org.cubeville.hawkeye.entity.EntityType;
import org.cubeville.hawkeye.entity.Player;
import org.cubeville.hawkeye.location.Block;
import org.cubeville.hawkeye.location.Location;
import org.cubeville.hawkeye.location.World;

/**
 * HawkEye/Bukkit object conversion utilities
 */
public class Convert {

	/**
	 * Console
	 */
	public static ConsoleCommandSender console(org.bukkit.command.ConsoleCommandSender console) {
		return HawkEye.getConsoleSender();
	}

	public static org.bukkit.command.ConsoleCommandSender console(ConsoleCommandSender console) {
		return ((BukkitConsole) console).getBukkitConsole();
	}

	/**
	 * Player
	 */
	public static Player player(org.bukkit.entity.Player player) {
		return HawkEye.getServerInterface().getPlayer(player.getName());
	}

	public static org.bukkit.entity.Player player(Player player) {
		return ((BukkitPlayer) player).getBukkitPlayer();
	}

	/**
	 * CommandSender
	 */
	public static CommandSender commandSender(org.bukkit.command.CommandSender sender) {
		if (sender instanceof org.bukkit.command.ConsoleCommandSender) return console((org.bukkit.command.ConsoleCommandSender) sender);
		else if (sender instanceof org.bukkit.entity.Player) return player((org.bukkit.entity.Player) sender);
		else return null;
	}

	public static org.bukkit.command.CommandSender commandSender(CommandSender sender) {
		if (sender instanceof ConsoleCommandSender) return console((ConsoleCommandSender) sender);
		else if (sender instanceof Player) return player((Player) sender);
		else return null;
	}

	/**
	 * World
	 */
	public static World world(org.bukkit.World world) {
		return HawkEye.getServerInterface().getWorld(world.getName());
	}

	public static org.bukkit.World world(World world) {
		return ((BukkitWorld) world).getBukkitWorld();
	}

	/**
	 * Location
	 */
	public static Location location(org.bukkit.Location location) {
		return new Location(world(location.getWorld()), location.getX(), location.getY(), location.getZ());
	}

	public static org.bukkit.Location location(Location location) {
		return new org.bukkit.Location(world(location.getWorld()), location.getX(), location.getY(), location.getZ());
	}

	/**
	 * Block
	 */
	public static Block block(org.bukkit.block.Block block) {
		return new BukkitBlock(block);
	}

	public static org.bukkit.block.Block block(Block block) {
		return ((BukkitBlock) block).getBukkitBlock();
	}

	/**
	 * BlockState
	 */
	public static BlockState blockState(org.bukkit.block.BlockState blockState) {
		return new BlockState((short) blockState.getTypeId(), blockState.getRawData());
		// TODO Convert BlockData
	}

	public static org.bukkit.block.BlockState blockState(BlockState blockState) {
		// TODO Reconstruct Bukkit BlockState somehow
		return null;
	}

	/**
	 * EntityType
	 */
	public static EntityType entityType(org.bukkit.entity.EntityType entityType) {
		return EntityType.getById(entityType.getTypeId());
	}

	public static org.bukkit.entity.EntityType entityType(EntityType entityType) {
		return org.bukkit.entity.EntityType.fromId(entityType.getId());
	}

}
