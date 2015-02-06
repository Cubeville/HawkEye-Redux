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

package org.cubeville.hawkeye;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

import org.cubeville.hawkeye.block.BlockData;
import org.cubeville.hawkeye.block.data.CommandBlock;
import org.cubeville.hawkeye.block.data.Sign;
import org.cubeville.hawkeye.block.data.Skull;
import org.cubeville.hawkeye.item.ItemData;
import org.cubeville.hawkeye.item.data.BaseItemData;
import org.cubeville.hawkeye.item.data.BookItemData;
import org.cubeville.hawkeye.item.data.ColoredArmorItemData;
import org.cubeville.hawkeye.item.data.EnchantmentStorageItemData;
import org.cubeville.hawkeye.item.data.FireworkChargeItemData;
import org.cubeville.hawkeye.item.data.FireworkItemData;
import org.cubeville.hawkeye.item.data.MapItemData;
import org.cubeville.hawkeye.item.data.MobHeadItemData;
import org.cubeville.hawkeye.item.data.PotionItemData;
import org.cubeville.hawkeye.model.NBTSerializable;
import org.cubeville.lib.jnbt.CompoundTag;

public enum DefaultItems implements Item {

	/**
	 * BLOCKS
	 */
	AIR(0, "air"),
	STONE(1, "stone"),
	GRASS(2, "grass"),
	DIRT(3, "dirt"),
	COBBLESTONE(4, "cobblestone"),
	WOOD(5, "planks"),
	SAPLING(6, "sapling", Attribute.ATTACHABLE_TOP, Attribute.BREAKS_FALLING),
	BEDROCK(7, "bedrock"),
	WATER(8, "flowing_water", Attribute.FALL_THROUGH),
	STATIONARY_WATER(9, "water", Attribute.FALL_THROUGH),
	LAVA(10, "flowing_lava", Attribute.FALL_THROUGH),
	STATIONARY_LAVA(11, "lava", Attribute.FALL_THROUGH),
	SAND(12, "sand", Attribute.GRAVITY),
	GRAVEL(13, "gravel", Attribute.GRAVITY),
	GOLD_ORE(14, "gold_ore"),
	IRON_ORE(15, "iron_ore"),
	COAL_ORE(16, "coal_ore"),
	LOG(17, "log"),
	LEAVES(18, "leaves"),
	SPONGE(19, "sponge"),
	GLASS(20, "glass"),
	LAPIS_ORE(21, "lapis_ore"),
	LAPIS_BLOCK(22, "lapis_block"),
	DISPENSER(23, "dispenser"),
	SANDSTONE(24, "sandstone"),
	NOTE_BLOCK(25, "noteblock"),
	BED_BLOCK(26, "bed"),
	POWERED_RAIL(27, "golden_rail", Attribute.ATTACHABLE_TOP, Attribute.BREAKS_FALLING),
	DETECTOR_RAIL(28, "detector_rail", Attribute.ATTACHABLE_TOP, Attribute.BREAKS_FALLING),
	PISTON_STICKY_BASE(29, "sticky_piston"),
	WEB(30, "web", Attribute.BREAKS_FALLING),
	LONG_GRASS(31, "tallgrass", Attribute.ATTACHABLE_TOP, Attribute.FALL_THROUGH),
	DEAD_BUSH(32, "deadbush", Attribute.ATTACHABLE_TOP, Attribute.FALL_THROUGH),
	PISTON_BASE(33, "piston"),
	PISTON_EXTENSION(34, "pistonhead"),
	WOOL(35, "wool"),
	PISTON_MOVING_PIECE(36, "piston_extension"),
	YELLOW_FLOWER(37, "yellow_flower", Attribute.ATTACHABLE_TOP, Attribute.BREAKS_FALLING),
	RED_ROSE(38, "red_flower", Attribute.ATTACHABLE_TOP, Attribute.BREAKS_FALLING),
	BROWN_MUSHROOM(39, "brown_mushroom", Attribute.ATTACHABLE_TOP, Attribute.BREAKS_FALLING),
	RED_MUSHROOM(40, "red_mushroom", Attribute.ATTACHABLE_TOP, Attribute.BREAKS_FALLING),
	GOLD_BLOCK(41, "gold_block"),
	IRON_BLOCK(42, "iron_block"),
	DOUBLE_STEP(43, "double_stone_slab"),
	STEP(44, "stone_slab", Attribute.BREAKS_FALLING),
	BRICK(45, "brick_block"),
	TNT(46, "tnt"),
	BOOKSHELF(47, "bookshelf"),
	MOSSY_COBBLESTONE(48, "mossy_cobblestone"),
	OBSIDIAN(49, "obsidian"),
	TORCH(50, "torch", Attribute.ATTACHABLE_SIDE, Attribute.ATTACHABLE_TOP, Attribute.BREAKS_FALLING),
	FIRE(51, "fire", Attribute.FALL_THROUGH),
	MOB_SPAWNER(52, "mob_spawner"),
	WOOD_STAIRS(53, "oak_stairs"),
	CHEST(54, "chest"),
	REDSTONE_WIRE(55, "redstone_wire", Attribute.ATTACHABLE_TOP, Attribute.BREAKS_FALLING),
	DIAMOND_ORE(56, "diamond_ore"),
	DIAMOND_BLOCK(57, "diamond_block"),
	WORKBENCH(58, "crafting_table"),
	CROPS(59, "wheat", Attribute.ATTACHABLE_TOP, Attribute.BREAKS_FALLING),
	SOIL(60, "farmland"),
	FURNACE(61, "furnace"),
	BURNING_FURNACE(62, "lit_furnace"),
	SIGN_POST(63, "standing_sign", Sign.class, Attribute.ATTACHABLE_TOP, Attribute.BREAKS_FALLING),
	WOODEN_DOOR(64, "wooden_door", Attribute.ATTACHABLE_TOP),
	LADDER(65, "ladder", Attribute.ATTACHABLE_SIDE),
	RAILS(66, "rail", Attribute.ATTACHABLE_TOP, Attribute.BREAKS_FALLING),
	COBBLESTONE_STAIRS(67, "stone_stairs"),
	WALL_SIGN(68, "wall_sign", Sign.class, Attribute.ATTACHABLE_SIDE, Attribute.BREAKS_FALLING),
	LEVER(69, "lever", Attribute.ATTACHABLE_SIDE, Attribute.ATTACHABLE_TOP, Attribute.ATTACHABLE_BOTTOM),
	STONE_PLATE(70, "stone_pressure_plate", Attribute.ATTACHABLE_TOP, Attribute.BREAKS_FALLING),
	IRON_DOOR_BLOCK(71, "iron_door", Attribute.ATTACHABLE_TOP),
	WOOD_PLATE(72, "wooden_pressure_plate", Attribute.ATTACHABLE_TOP, Attribute.BREAKS_FALLING),
	REDSTONE_ORE(73, "redstone_ore"),
	GLOWING_REDSTONE_ORE(74, "lit_restone_ore"),
	REDSTONE_TORCH_OFF(75, "unlit_restone_torch", Attribute.ATTACHABLE_SIDE, Attribute.ATTACHABLE_TOP, Attribute.BREAKS_FALLING),
	REDSTONE_TORCH_ON(76, "redstone_torch", Attribute.ATTACHABLE_SIDE, Attribute.ATTACHABLE_TOP, Attribute.BREAKS_FALLING),
	STONE_BUTTON(77, "stone_button", Attribute.ATTACHABLE_SIDE, Attribute.BREAKS_FALLING),
	SNOW(78, "snow_layer", Attribute.ATTACHABLE_TOP, Attribute.FALL_THROUGH),
	ICE(79, "ice"),
	SNOW_BLOCK(80, "snow"),
	CACTUS(81, "cactus", Attribute.ATTACHABLE_TOP),
	CLAY(82, "clay"),
	SUGAR_CANE_BLOCK(83, "reeds", Attribute.ATTACHABLE_TOP, Attribute.BREAKS_FALLING),
	JUKEBOX(84, "jukebox"),
	FENCE(85, "fence"),
	PUMPKIN(86, "pumpkin"),
	NETHERRACK(87, "netherrack"),
	SOUL_SAND(88, "soul_sand"),
	GLOWSTONE(89, "glowstone"),
	PORTAL(90, "portal"),
	JACK_O_LANTERN(91, "lit_pumpkin"),
	CAKE_BLOCK(92, "cake", Attribute.ATTACHABLE_TOP, Attribute.BREAKS_FALLING),
	DIODE_BLOCK_OFF(93, "unpowered_repeater", Attribute.ATTACHABLE_TOP, Attribute.BREAKS_FALLING),
	DIODE_BLOCK_ON(94, "powered_repeater", Attribute.ATTACHABLE_TOP, Attribute.BREAKS_FALLING),
	STAINED_GLASS(95, "stained_glass"),
	TRAP_DOOR(96, "trapdoor", Attribute.ATTACHABLE_SIDE),
	MONSTER_EGGS(97, "monster_egg"),
	SMOOTH_BRICK(98, "stonebrick"),
	HUGE_MUSHROOM_1(99, "brown_mushroom_block"),
	HUGE_MUSHROOM_2(100, "red_mushroom_block"),
	IRON_FENCE(101, "iron_bars"),
	THIN_GLASS(102, "glass_pane"),
	MELON_BLOCK(103, "melon_block"),
	PUMPKIN_STEM(104, "pumpkin_stem", Attribute.ATTACHABLE_TOP, Attribute.BREAKS_FALLING),
	MELON_STEM(105, "melon_stem", Attribute.ATTACHABLE_TOP, Attribute.BREAKS_FALLING),
	VINE(106, "vine", Attribute.FALL_THROUGH),
	FENCE_GATE(107, "fence_gate"),
	BRICK_STAIRS(108, "brick_stairs"),
	SMOOTH_STAIRS(109, "stone_brick_stairs"),
	MYCEL(110, "mycelium"),
	WATER_LILY(111, "waterlily", Attribute.ATTACHABLE_TOP, Attribute.BREAKS_FALLING),
	NETHER_BRICK(112, "nether_brick"),
	NETHER_FENCE(113, "nether_brick_fence"),
	NETHER_BRICK_STAIRS(114, "nether_brick_stairs"),
	NETHER_WARTS(115, "nether_wart"),
	ENCHANTMENT_TABLE(116, "enchanting_table"),
	BREWING_STAND(117, "brewing_stand"),
	CAULDRON(118, "cauldron"),
	ENDER_PORTAL(119, "end_portal"),
	ENDER_PORTAL_FRAME(120, "end_portal_frame"),
	ENDER_STONE(121, "end_stone"),
	DRAGON_EGG(122, "dragon_egg", Attribute.GRAVITY),
	REDSTONE_LAMP_OFF(123, "redstone_lamp"),
	REDSTONE_LAMP_ON(124, "lit_redstone_lamp"),
	WOOD_DOUBLE_STEP(125, "double_wooden_slab"),
	WOOD_STEP(126, "wooden_slab", Attribute.BREAKS_FALLING),
	COCOA(127, "cocoa", Attribute.ATTACHABLE_SIDE),
	STANDSTONE_STAIRS(128, "sandstone_stairs"),
	EMERALD_ORE(129, "emerald_ore"),
	ENDER_CHEST(130, "ender_chest"),
	TRIPWIRE_HOOK(131, "tripwire_hook", Attribute.ATTACHABLE_SIDE, Attribute.BREAKS_FALLING),
	TRIPWIRE(132, "tripwire", Attribute.FALL_THROUGH),
	EMERALD_BLOCK(133, "emerald_block"),
	SPRUCE_WOOD_STAIRS(134, "spruce_stairs"),
	BIRCH_WOOD_STAIRS(135, "birch_stairs"),
	JUNGLE_WOOD_STAIRS(136, "jungle_stairs"),
	COMMAND(137, "command_block", CommandBlock.class),
	BEACON(138, "beacon"),
	COBBLE_WALL(139, "cobblestone_wall"),
	FLOWER_POT(140, "flower_pot", Attribute.ATTACHABLE_TOP, Attribute.BREAKS_FALLING),
	CARROT(141, "carrots", Attribute.ATTACHABLE_TOP, Attribute.BREAKS_FALLING),
	POTATO(142, "potatoes", Attribute.ATTACHABLE_TOP, Attribute.BREAKS_FALLING),
	WOOD_BUTTON(143, "wooden_button", Attribute.ATTACHABLE_SIDE, Attribute.BREAKS_FALLING),
	SKULL(144, "skull", Skull.class),
	ANVIL(145, "anvil", Attribute.GRAVITY),
	TRAPPED_CHEST(146, "trapped_chest"),
	GOLD_PLATE(147, "light_weighted_pressure_plate", Attribute.ATTACHABLE_TOP, Attribute.BREAKS_FALLING),
	IRON_PLATE(148, "heavy_weighted_pressure_plate", Attribute.ATTACHABLE_TOP, Attribute.BREAKS_FALLING),
	REDSTONE_COMPARATOR_ON(149, "unpowered_comparator", Attribute.ATTACHABLE_TOP, Attribute.BREAKS_FALLING),
	REDSTONE_COMPARATOR_OFF(150, "powered_comparator", Attribute.ATTACHABLE_TOP, Attribute.BREAKS_FALLING),
	DAYLIGHT_SENSOR(151, "daylight_detector", Attribute.BREAKS_FALLING),
	REDSTONE_BLOCK(152, "redstone_block"),
	QUARTZ_ORE(153, "quartz_ore"),
	HOPPER(154, "hopper"),
	QUARTZ_BLOCK(155, "quartz_block"),
	QUARTZ_STAIRS(156, "quartz_stairs"),
	ACTIVATOR_RAIL(157, "activator_rail", Attribute.ATTACHABLE_TOP, Attribute.BREAKS_FALLING),
	DROPPER(158, "dropper"),
	STAINED_CLAY(159, "stained_hardened_clay"),
	STAINED_GLASS_PANE(160, "stained_glass_pane"),
	LEAVES_2(161, "leaves2"),
	LOG_2(162, "logs2"),
	ACACIA_STAIRS(163, "acacia_stairs"),
	DARK_OAK_STAIRS(164, "dark_oak_stairs"),
	WHEAT_BLOCK(170, "hay_block"),
	CARPET(171, "carpet", Attribute.ATTACHABLE_TOP, Attribute.BREAKS_FALLING),
	HARDENED_CLAY(172, "hardened_clay"),
	COAL_BLOCK(173, "coal_block"),
	PACKED_ICE(174, "packed_ice"),
	DOUBLE_PLANT(175, "double_plant", Attribute.ATTACHABLE_TOP, Attribute.BREAKS_FALLING),



	/**
	 * ITEMS
	 */
	IRON_SHOVEL(256, "iron_shovel"),
	IRON_PICKAXE(257, "iron_pickaxe"),
	IRON_AXE(258, "iron_axe"),
	FLINT_AND_STEEL(259, "flint_and_steel"),
	APPLE(260, "apple"),
	BOW(261, "bow"),
	ARROW(262, "arrow"),
	COAL(263, "coal"),
	DIAMOND(264, "diamond"),
	IRON_INGOT(265, "iron_ingot"),
	GOLD_INGOT(266, "gold_ingot"),
	IRON_SWORD(267, "iron_sword"),
	WOOD_SWORD(268, "wooden_sword"),
	WOOD_SHOVEL(269, "wooden_shovel"),
	WOOD_PICKAXE(270, "wooden_pickaxe"),
	WOOD_AXE(271, "wooden_axe"),
	STONE_SWORD(272, "stone_sword"),
	STONE_SHOVEL(273, "stone_shovel"),
	STONE_PICKAXE(274, "stone_pickaxe"),
	STONE_AXE(275, "stone_axe"),
	DIAMOND_SWORD(276, "diamond_sword"),
	DIAMOND_SHOVEL(277, "diamond_shovel"),
	DIAMOND_PICKAXE(278, "diamond_pickaxe"),
	DIAMOND_AXE(279, "diamond_axe"),
	STICK(280, "stick"),
	BOWL(281, "bowl"),
	MUSHROOM_SOUP(282, "mushroom_stew"),
	GOLD_SWORD(283, "golden_sword"),
	GOLD_SHOVEL(284, "golden_shovel"),
	GOLD_PICKAXE(285, "golden_pickaxe"),
	GOLD_AXE(286, "golden_axe"),
	STRING(287, "string"),
	FEATHER(288, "feather"),
	SULPHUR(289, "gunpowder"),
	WOOD_HOE(290, "wooden_hoe"),
	STONE_HOE(291, "stone_hoe"),
	IRON_HOE(292, "iron_hoe"),
	DIAMOND_HOE(293, "diamond_hoe"),
	GOLD_HOE(294, "golden_hoe"),
	SEEDS(295, "wheat_seeds"),
	WHEAT(296, "wheat"),
	BREAD(297, "bread"),
	LEATHER_HELMET(298, "leather_helmet", ColoredArmorItemData.class),
	LEATHER_CHESTPLATE(299, "leather_chestplate", ColoredArmorItemData.class),
	LEATHER_PANTS(300, "leather_leggings", ColoredArmorItemData.class),
	LEATHER_BOOTS(301, "leather_boots", ColoredArmorItemData.class),
	CHAIN_HELMET(302, "chainmail_helmet"),
	CHAIN_CHESTPLATE(303, "chainmail_chestplate"),
	CHAIN_PANTS(304, "chainmail_leggings"),
	CHAIN_BOOTS(305, "chainmail_boots"),
	IRON_HELMET(306, "iron_helmet"),
	IRON_CHESTPLATE(307, "iron_chestplate"),
	IRON_PANTS(308, "iron_leggings"),
	IRON_BOOTS(309, "iron_boots"),
	DIAMOND_HELMET(310, "diamond_helmet"),
	DIAMOND_CHESTPLATE(311, "diamond_chestplate"),
	DIAMOND_PANTS(312, "diamond_leggings"),
	DIAMOND_BOOTS(313, "diamond_boots"),
	GOLD_HELMET(314, "golden_helmet"),
	GOLD_CHESTPLATE(315, "golden_chestplate"),
	GOLD_PANTS(316, "golden_pants"),
	GOLD_BOOTS(317, "golden_boots"),
	FLINT(318, "flint"),
	RAW_PORK(319, "porkchop"),
	COOKED_PORK(320, "cooked_porkchop"),
	PAINTING(321, "painting"),
	GOLD_APPLE(322, "golden_apple"),
	SIGN(323, "sign"),
	WOODEN_DOOR_ITEM(324, "wooden_door"),
	BUCKET(325, "bucket"),
	WATER_BUCKET(326, "water_bucket"),
	LAVA_BUCKET(327, "lava_bucket"),
	MINECART(328, "minecart"),
	SADDLE(329, "saddle"),
	IRON_DOOR(330, "iron_door"),
	REDSTONE_DUST(331, "redstone"),
	SNOW_BALL(332, "snowball"),
	BOAT(333, "boat"),
	LEATHER(334, "leather"),
	MILK_BUCKET(335, "milk_bucket"),
	CLAY_BRICK(336, "brick"),
	CLAY_BALL(337, "clay_ball"),
	SUGAR_CANE(338, "reeds"),
	PAPER(339, "paper"),
	BOOK(340, "book"),
	SLIMEBALL(341, "slime_ball"),
	STORAGE_MINECART(342, "chest_minecart"),
	POWERED_MINECART(343, "furnace_minecart"),
	EGG(344, "egg"),
	COMPASS(345, "compass"),
	FISHING_ROD(346, "fishing_rod"),
	CLOCK(347, "clock"),
	GLOWSTONE_DUST(348, "glowstone_dust"),
	RAW_FISH(349, "fish"),
	COOKED_FISH(350, "cooked_fish"),
	INK_SACK(351, "dye"),
	BONE(352, "bone"),
	SUGAR(353, "sugar"),
	CAKE(354, "cake"),
	BED(355, "bed"),
	DIODE(356, "repeater"),
	COOKIE(357, "cookie"),
	MAP(358, "filled_map", MapItemData.class),
	SHEARS(359, "shears"),
	MELON(360, "melon"),
	PUMPKIN_SEEDS(361, "pumpkin_seeds"),
	MELON_SEEDS(362, "melon_seeds"),
	RAW_BEEF(363, "beef"),
	COOKED_BEEF(364, "cooked_beef"),
	RAW_CHICKEN(365, "chicken"),
	COOKED_CHICKEN(366, "cooked_chicken"),
	ROTTEN_FLESH(367, "rotten_flesh"),
	ENDERPEARL(368, "ender_pearl"),
	BLAZE_ROD(369, "blaze_rod"),
	GHAST_TEAR(370, "ghast_tear"),
	GOLD_NUGGET(371, "gold_nugget"),
	NETHER_STALK(372, "nether_wart"),
	POTION(373, "potion", PotionItemData.class),
	GLASS_BOTTLE(374, "glass_bottle"),
	SPIDER_EYE(375, "spider_eye"),
	FERMENTED_SPIDER_EYE(376, "fermented_spider_eye"),
	BLAZE_POWDER(377,  "blaze_powder"),
	MAGMA_CREAM(378, "magma_cream"),
	BREWING_STAND_ITEM(379, "brewing_stand"),
	CAULDRON_ITEM(380, "cauldron"),
	EYE_OF_ENDER(381, "ender_eye"),
	GLISTERING_MELON(382, "speckled_melon"),
	SPAWN_EGG(383, "spawn_egg"),
	EXP_BOTTLE(384, "experience_bottle"),
	FIRE_CHARGE(385, "fire_charge"),
	BOOK_AND_QUILL(386, "writable_book", BookItemData.class),
	WRITTEN_BOOK(387, "written_book", BookItemData.class),
	EMERALD(388, "emerald"),
	ITEM_FRAME(389, "item_frame"),
	FLOWER_POT_ITEM(390, "flower_pot"),
	CARROT_ITEM(391, "carrot"),
	POTATO_ITEM(392, "potato"),
	BAKED_POTATO(393, "baked_potato"),
	POISONOUS_POTATO(394, "poisonous_potato"),
	EMPTY_MAP(395, "map"),
	GOLD_CARROT(396, "golden_carrot"),
	MOB_HEAD(397, "skull", MobHeadItemData.class),
	CARROT_STICK(398, "carrot_on_a_stick"),
	NETHER_STAR(399, "nether_star"),
	PUMPKIN_PIE(400, "pumpkin_pie"),
	FIREWORK(401, "fireworks", FireworkItemData.class),
	FIREWORK_CHARGE(402, "firework_charge", FireworkChargeItemData.class),
	ENCHANTED_BOOK(403, "enchanted_book", EnchantmentStorageItemData.class),
	REDSTONE_COMPARATOR(404, "comparator"),
	NETHER_BRICK_ITEM(405, "netherbrick"),
	QUARTZ_ITEM(406, "quartz"),
	TNT_MINECART(407, "tnt_minecart"),
	HOPPER_MINECART(408, "hopper_minecart"),
	IRON_HORSE_ARMOR(417, "iron_horse_armor"),
	GOLD_HORSE_ARMOR(418, "golden_horse_armor"),
	DIAMOND_HORSE_ARMOR(419, "diamond_horse_armor"),
	LEASH(420, "lead"),
	NAME_TAG(421, "name_tag"),
	COMMAND_MINECART(422, "command_block_minecart"),
	RECORD_13(2256, "record_13"),
	RECORD_CAT(2257, "record_cat"),
	RECORD_BLOCKS(2258, "record_blocks"),
	RECORD_CHIRP(2259, "record_chirp"),
	RECORD_FAR(2260, "record_far"),
	RECORD_MALL(2261, "record_mall"),
	RECORD_MELLOHI(2262, "record_mellohi"),
	RECORD_STAL(2263, "record_stal"),
	RECORD_STRAD(2264, "record_strad"),
	RECORD_WARD(2265, "record_ward"),
	RECORD_11(2266, "record_11"),
	RECORD_WAIT(2267, "record_wait");

	/**
	 * Item id
	 */
	private final short id;

	/**
	 * Item name
	 */
	private final String name;

	/**
	 * Attribute flags
	 */
	private final int flags;

	/**
	 * Custom item data class
	 */
	private final Class<? extends NBTSerializable> dataClass;

	private final Constructor<? extends NBTSerializable> constructor;

	private DefaultItems(int id, String name, Attribute... attributes) {
		this(id, name, null, attributes);
	}

	private DefaultItems(int id, String name, Class<? extends NBTSerializable> dataClass, Attribute... attributes) {
		this.id = (short) id;
		this.name = name;

		int flags = 0;
		for (Attribute attribute : attributes) {
			flags |= attribute.getMask();
		}
		this.flags = flags;

		if (dataClass == null && ItemType.isItem(id)) dataClass = BaseItemData.class;
		this.dataClass = dataClass;

		Constructor<? extends NBTSerializable> tmp = null;

		if (dataClass != null) {
			try {
				tmp = dataClass.getConstructor(CompoundTag.class);
				tmp.setAccessible(true);
			} catch (SecurityException e) {
				HawkEye.getLogger().error("Could not get CompoundTag constructor for " + dataClass.getCanonicalName(), e);
			} catch (NoSuchMethodException e) {
				HawkEye.getLogger().error("Could not get CompoundTag constructor for " + dataClass.getCanonicalName(), e);
			}
		}

		constructor = tmp;
	}

	@Override
	public short getId() {
		return id;
	}

	@Override
	public String getName() {
		return name;
	}

	@Override
	public boolean hasAttribute(Attribute attribute) {
		return (flags & attribute.getMask()) == attribute.getMask();
	}

	@Override
	public boolean hasItemData() {
		return dataClass != null && ItemData.class.isAssignableFrom(dataClass);
	}

	@Override
	public ItemData getItemData(CompoundTag tag) {
		if (!ItemData.class.isAssignableFrom(dataClass)) return null;
		if (constructor == null) return null;

		try {
			return (ItemData) constructor.newInstance(tag);
		} catch (IllegalArgumentException ignore) {
		} catch (InstantiationException ignore) {
		} catch (IllegalAccessException ignore) {
		} catch (InvocationTargetException e) {
			HawkEye.getLogger().error("Error reconstructing item data '" + toString() + "'", e);
		}

		return null;
	}

	@Override
	public boolean hasBlockData() {
		return dataClass != null && BlockData.class.isAssignableFrom(dataClass);
	}

	@Override
	public BlockData getBlockData(CompoundTag tag) {
		if (!BlockData.class.isAssignableFrom(dataClass)) return null;
		if (constructor == null) return null;

		try {
			return (BlockData) constructor.newInstance(tag);
		} catch (IllegalArgumentException ignore) {
		} catch (InstantiationException ignore) {
		} catch (IllegalAccessException ignore) {
		} catch (InvocationTargetException e) {
			HawkEye.getLogger().error("Error reconstructing block data '" + toString() + "'", e);
		}

		return null;
	}

}
