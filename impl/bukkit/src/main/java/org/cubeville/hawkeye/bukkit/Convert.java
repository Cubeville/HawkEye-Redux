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

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.cubeville.hawkeye.HawkEye;
import org.cubeville.hawkeye.block.BlockData;
import org.cubeville.hawkeye.block.BlockState;
import org.cubeville.hawkeye.block.data.BaseBlockData;
import org.cubeville.hawkeye.block.data.CommandBlock;
import org.cubeville.hawkeye.block.data.Jukebox;
import org.cubeville.hawkeye.block.data.MobSpawner;
import org.cubeville.hawkeye.block.data.NoteBlock;
import org.cubeville.hawkeye.block.data.Sign;
import org.cubeville.hawkeye.block.data.Skull;
import org.cubeville.hawkeye.bukkit.command.BukkitConsole;
import org.cubeville.hawkeye.bukkit.data.BukkitBlockState;
import org.cubeville.hawkeye.bukkit.data.BukkitItemStack;
import org.cubeville.hawkeye.bukkit.entity.BukkitPlayer;
import org.cubeville.hawkeye.command.CommandSender;
import org.cubeville.hawkeye.command.ConsoleCommandSender;
import org.cubeville.hawkeye.entity.EntityType;
import org.cubeville.hawkeye.entity.Player;
import org.cubeville.hawkeye.item.ItemData;
import org.cubeville.hawkeye.item.ItemStack;
import org.cubeville.hawkeye.item.data.BaseItemData;
import org.cubeville.hawkeye.item.data.BookItemData;
import org.cubeville.hawkeye.item.data.ColoredArmorItemData;
import org.cubeville.hawkeye.item.data.Enchantment;
import org.cubeville.hawkeye.item.data.EnchantmentStorageItemData;
import org.cubeville.hawkeye.item.data.FireworkChargeItemData;
import org.cubeville.hawkeye.item.data.FireworkExplosion;
import org.cubeville.hawkeye.item.data.FireworkItemData;
import org.cubeville.hawkeye.item.data.MapItemData;
import org.cubeville.hawkeye.item.data.MobHeadItemData;
import org.cubeville.hawkeye.item.data.PotionEffect;
import org.cubeville.hawkeye.item.data.PotionItemData;
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
		return new BukkitBlockState(blockState);
	}

	/**
	 * BlockState to BlockData
	 */
	public static BlockData blockData(org.bukkit.block.BlockState blockState) {
		if (blockState instanceof org.bukkit.block.Beacon) {
			// Bukkit has no beacon API yet
			return new BaseBlockData();
		} else if (blockState instanceof org.bukkit.block.CommandBlock) {
			org.bukkit.block.CommandBlock block = (org.bukkit.block.CommandBlock) blockState;

			return new CommandBlock(block.getName(), block.getCommand());
		} else if (blockState instanceof org.bukkit.block.CreatureSpawner) {
			org.bukkit.block.CreatureSpawner spawner = (org.bukkit.block.CreatureSpawner) blockState;

			return new MobSpawner(spawner.getCreatureTypeName());
		} else if (blockState instanceof org.bukkit.block.Jukebox) {
			org.bukkit.block.Jukebox jukebox = (org.bukkit.block.Jukebox) blockState;

			return new Jukebox(jukebox.getPlaying().getId());
		} else if (blockState instanceof org.bukkit.block.NoteBlock) {
			org.bukkit.block.NoteBlock noteBlock = (org.bukkit.block.NoteBlock) blockState;

			return new NoteBlock(noteBlock.getRawNote());
		} else if (blockState instanceof org.bukkit.block.Sign) {
			org.bukkit.block.Sign sign = (org.bukkit.block.Sign) blockState;

			return new Sign(sign.getLines());
		} else if (blockState instanceof org.bukkit.block.Skull) {
			org.bukkit.block.Skull skull = (org.bukkit.block.Skull) blockState;

			return new Skull(skull.getRawData(), skull.getOwner(), rotation(skull.getRotation()));
		} else {
			return new BaseBlockData();
		}
	}

	/**
	 * ItemStack
	 */
	public static ItemStack itemStack(org.bukkit.inventory.ItemStack itemStack) {
		return new BukkitItemStack(itemStack);
	}

	/**
	 * ItemMeta to ItemData
	 */
	public static ItemData itemData(org.bukkit.inventory.meta.ItemMeta itemMeta) {
		if (itemMeta == null) {
			return new BaseItemData(null, null, null);
		}

		String name = itemMeta.hasDisplayName() ? itemMeta.getDisplayName() : null;
		List<String> lore = itemMeta.hasLore() ? itemMeta.getLore() : null;
		List<Enchantment> enchantments = itemMeta.hasEnchants() ? enchants(itemMeta.getEnchants()) : null;
		BaseItemData base = new BaseItemData(name, lore, enchantments);

		if (itemMeta instanceof org.bukkit.inventory.meta.BookMeta) {
			org.bukkit.inventory.meta.BookMeta book = (org.bukkit.inventory.meta.BookMeta) itemMeta;
			String title = book.hasTitle() ? book.getTitle() : null;
			String author = book.hasAuthor() ? book.getAuthor() : null;
			List<String> pages = book.hasPages() ? book.getPages() : null;

			return new BookItemData(base, title, author, pages);
		} else if (itemMeta instanceof org.bukkit.inventory.meta.EnchantmentStorageMeta) {
			org.bukkit.inventory.meta.EnchantmentStorageMeta storage = (org.bukkit.inventory.meta.EnchantmentStorageMeta) itemMeta;
			List<Enchantment> stored = storage.hasStoredEnchants() ? enchants(storage.getStoredEnchants()) : null;

			return new EnchantmentStorageItemData(base, stored);
		} else if (itemMeta instanceof org.bukkit.inventory.meta.FireworkEffectMeta) {
			org.bukkit.inventory.meta.FireworkEffectMeta firework = (org.bukkit.inventory.meta.FireworkEffectMeta) itemMeta;
			FireworkExplosion effect = firework(firework.getEffect());

			return new FireworkChargeItemData(base, effect);
		} else if (itemMeta instanceof org.bukkit.inventory.meta.FireworkMeta) {
			org.bukkit.inventory.meta.FireworkMeta firework = (org.bukkit.inventory.meta.FireworkMeta) itemMeta;
			byte power = (byte) firework.getPower();
			List<FireworkExplosion> effects = fireworks(firework.getEffects());

			return new FireworkItemData(base, power, effects);
		} else if (itemMeta instanceof org.bukkit.inventory.meta.LeatherArmorMeta) {
			org.bukkit.inventory.meta.LeatherArmorMeta armor = (org.bukkit.inventory.meta.LeatherArmorMeta) itemMeta;
			int color = armor.getColor().asRGB();

			return new ColoredArmorItemData(base, color);
		} else if (itemMeta instanceof org.bukkit.inventory.meta.MapMeta) {
			org.bukkit.inventory.meta.MapMeta map = (org.bukkit.inventory.meta.MapMeta) itemMeta;
			byte scaling = (byte) (map.isScaling() ? 1 : 0);

			return new MapItemData(base, scaling);
		} else if (itemMeta instanceof org.bukkit.inventory.meta.PotionMeta) {
			org.bukkit.inventory.meta.PotionMeta potion = (org.bukkit.inventory.meta.PotionMeta) itemMeta;
			List<PotionEffect> effects = potion.hasCustomEffects() ? potionEffects(potion.getCustomEffects()) : null;

			return new PotionItemData(base, effects);
		} else if (itemMeta instanceof org.bukkit.inventory.meta.SkullMeta) {
			org.bukkit.inventory.meta.SkullMeta skull = (org.bukkit.inventory.meta.SkullMeta) itemMeta;
			String owner = skull.hasOwner() ? skull.getOwner() : null;

			return new MobHeadItemData(base, owner);
		} else {
			return base;
		}
	}

	/**
	 * Enchantments
	 */
	public static List<Enchantment> enchants(Map<org.bukkit.enchantments.Enchantment, Integer> enchants) {
		List<Enchantment> list = new ArrayList<Enchantment>();

		for (Map.Entry<org.bukkit.enchantments.Enchantment, Integer> enchant : enchants.entrySet()) {
			list.add(new Enchantment((short) enchant.getKey().getId(), enchant.getValue().shortValue()));
		}

		return list;
	}

	/**
	 * Firework explosions
	 */
	public static FireworkExplosion firework(org.bukkit.FireworkEffect firework) {
		byte shape = fireworkType(firework.getType());
		return new FireworkExplosion(shape, firework.hasFlicker(), firework.hasTrail());
	}

	public static List<FireworkExplosion> fireworks(List<org.bukkit.FireworkEffect> fireworks) {
		List<FireworkExplosion> list = new ArrayList<FireworkExplosion>();

		for (org.bukkit.FireworkEffect firework : fireworks) {
			list.add(firework(firework));
		}

		return list;
	}

	public static byte fireworkType(org.bukkit.FireworkEffect.Type type) {
		switch (type) {
			case BALL:
				return 0;
			case BALL_LARGE:
				return 1;
			case STAR:
				return 2;
			case CREEPER:
				return 3;
			case BURST:
				return 4;
			default:
				return 0;
		}
	}

	/**
	 * Potion effects
	 */
	public static PotionEffect potionEffect(org.bukkit.potion.PotionEffect potionEffect) {
		return new PotionEffect((byte) potionEffect.getType().getId(), (byte) potionEffect.getAmplifier(), potionEffect.getDuration(), potionEffect.isAmbient());
	}

	public static List<PotionEffect> potionEffects(List<org.bukkit.potion.PotionEffect> potionEffects) {
		List<PotionEffect> list = new ArrayList<PotionEffect>();

		for (org.bukkit.potion.PotionEffect potionEffect : potionEffects) {
			list.add(potionEffect(potionEffect));
		}

		return list;
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

	/**
	 * BlockFace/raw rotation value
	 */
	public static byte rotation(org.bukkit.block.BlockFace rotation) {
		switch (rotation) {
			case NORTH:
				return 0;
			case NORTH_NORTH_EAST:
				return 1;
			case NORTH_EAST:
				return 2;
			case EAST_NORTH_EAST:
				return 3;
			case EAST:
				return 4;
			case EAST_SOUTH_EAST:
				return 5;
			case SOUTH_EAST:
				return 6;
			case SOUTH_SOUTH_EAST:
				return 7;
			case SOUTH:
				return 8;
			case SOUTH_SOUTH_WEST:
				return 9;
			case SOUTH_WEST:
				return 10;
			case WEST_SOUTH_WEST:
				return 11;
			case WEST:
				return 12;
			case WEST_NORTH_WEST:
				return 13;
			case NORTH_WEST:
				return 14;
			case NORTH_NORTH_WEST:
				return 15;
			default:
				return 0;
		}
	}

	public static org.bukkit.block.BlockFace rotation(byte rotation) {
		switch (rotation) {
			case 0:
				return org.bukkit.block.BlockFace.NORTH;
			case 1:
				return org.bukkit.block.BlockFace.NORTH_NORTH_EAST;
			case 2:
				return org.bukkit.block.BlockFace.NORTH_EAST;
			case 3:
				return org.bukkit.block.BlockFace.EAST_NORTH_EAST;
			case 4:
				return org.bukkit.block.BlockFace.EAST;
			case 5:
				return org.bukkit.block.BlockFace.EAST_SOUTH_EAST;
			case 6:
				return org.bukkit.block.BlockFace.SOUTH_EAST;
			case 7:
				return org.bukkit.block.BlockFace.SOUTH_SOUTH_EAST;
			case 8:
				return org.bukkit.block.BlockFace.SOUTH;
			case 9:
				return org.bukkit.block.BlockFace.SOUTH_SOUTH_WEST;
			case 10:
				return org.bukkit.block.BlockFace.SOUTH_WEST;
			case 11:
				return org.bukkit.block.BlockFace.WEST_SOUTH_WEST;
			case 12:
				return org.bukkit.block.BlockFace.WEST;
			case 13:
				return org.bukkit.block.BlockFace.WEST_NORTH_WEST;
			case 14:
				return org.bukkit.block.BlockFace.NORTH_WEST;
			case 15:
				return org.bukkit.block.BlockFace.NORTH_NORTH_WEST;
			default:
				return org.bukkit.block.BlockFace.NORTH;
		}
	}

}
