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

package org.cubeville.hawkeye.item;

import java.util.HashMap;
import java.util.Map;

import org.cubeville.hawkeye.block.data.Sign;
import org.cubeville.hawkeye.block.data.Skull;
import org.cubeville.hawkeye.item.data.BookItemData;
import org.cubeville.hawkeye.item.data.ColoredArmorItemData;
import org.cubeville.hawkeye.item.data.EnchantmentStorageItemData;
import org.cubeville.hawkeye.item.data.FireworkChargeItemData;
import org.cubeville.hawkeye.item.data.FireworkItemData;
import org.cubeville.hawkeye.item.data.MapItemData;
import org.cubeville.hawkeye.item.data.MobHeadItemData;
import org.cubeville.hawkeye.item.data.PotionItemData;
import org.cubeville.hawkeye.model.NBTSerializable;

public enum Items {

	/**
	 * BLOCKS
	 */
	AIR(0),
	STONE(1),
	GRASS(2),
	DIRT(3),
	COBBLESTONE(4),
	WOOD(5),
	SAPLING(6, Attribute.ATTACHABLE_TOP | Attribute.BREAKS_FALLING),
	BEDROCK(7),
	WATER(8, Attribute.FALL_THROUGH),
	STATIONARY_WATER(9, Attribute.FALL_THROUGH),
	LAVA(10, Attribute.FALL_THROUGH),
	STATIONARY_LAVA(11, Attribute.FALL_THROUGH),
	SAND(12, Attribute.GRAVITY),
	GRAVEL(13, Attribute.GRAVITY),
	GOLD_ORE(14),
	IRON_ORE(15),
	COAL_ORE(16),
	LOG(17),
	LEAVES(18),
	SPONGE(19),
	GLASS(20),
	LAPIS_ORE(21),
	LAPIS_BLOCK(22),
	DISPENSER(23),
	SANDSTONE(24),
	NOTE_BLOCK(25),
	BED_BLOCK(26),
	POWERED_RAIL(27, Attribute.ATTACHABLE_TOP | Attribute.BREAKS_FALLING),
	DETECTOR_RAIL(28, Attribute.ATTACHABLE_TOP | Attribute.BREAKS_FALLING),
	PISTON_STICKY_BASE(29),
	WEB(30, Attribute.BREAKS_FALLING),
	LONG_GRASS(31, Attribute.ATTACHABLE_TOP | Attribute.FALL_THROUGH),
	DEAD_BUSH(32, Attribute.ATTACHABLE_TOP | Attribute.FALL_THROUGH),
	PISTON_BASE(33),
	PISTON_EXTENSION(34),
	WOOL(35),
	PISTON_MOVING_PIECE(36),
	YELLOW_FLOWER(37, Attribute.ATTACHABLE_TOP | Attribute.BREAKS_FALLING),
	RED_ROSE(38, Attribute.ATTACHABLE_TOP | Attribute.BREAKS_FALLING),
	BROWN_MUSHROOM(39, Attribute.ATTACHABLE_TOP | Attribute.BREAKS_FALLING),
	RED_MUSHROOM(40, Attribute.ATTACHABLE_TOP | Attribute.BREAKS_FALLING),
	GOLD_BLOCK(41),
	IRON_BLOCK(42),
	DOUBLE_STEP(43),
	STEP(44, Attribute.BREAKS_FALLING),
	BRICK(45),
	TNT(46),
	BOOKSHELF(47),
	MOSSY_COBBLESTONE(48),
	OBSIDIAN(49),
	TORCH(50, Attribute.ATTACHABLE_SIDE | Attribute.ATTACHABLE_TOP | Attribute.BREAKS_FALLING),
	FIRE(51, Attribute.FALL_THROUGH),
	MOB_SPAWNER(52),
	WOOD_STAIRS(53),
	CHEST(54),
	REDSTONE_WIRE(55, Attribute.ATTACHABLE_TOP | Attribute.BREAKS_FALLING),
	DIAMOND_ORE(56),
	DIAMOND_BLOCK(57),
	WORKBENCH(58),
	CROPS(59, Attribute.ATTACHABLE_TOP | Attribute.BREAKS_FALLING),
	SOIL(60),
	FURNACE(61),
	BURNING_FURNACE(62),
	SIGN_POST(63, Attribute.ATTACHABLE_TOP | Attribute.BREAKS_FALLING, Sign.class),
	WOODEN_DOOR(64, Attribute.ATTACHABLE_TOP),
	LADDER(65, Attribute.ATTACHABLE_SIDE),
	RAILS(66, Attribute.ATTACHABLE_TOP | Attribute.BREAKS_FALLING),
	COBBLESTONE_STAIRS(67),
	WALL_SIGN(68, Attribute.ATTACHABLE_SIDE | Attribute.BREAKS_FALLING, Sign.class),
	LEVER(69, Attribute.ATTACHABLE_SIDE | Attribute.ATTACHABLE_TOP | Attribute.ATTACHABLE_BOTTOM),
	STONE_PLATE(70, Attribute.ATTACHABLE_TOP | Attribute.BREAKS_FALLING),
	IRON_DOOR_BLOCK(71, Attribute.ATTACHABLE_TOP),
	WOOD_PLATE(72, Attribute.ATTACHABLE_TOP | Attribute.BREAKS_FALLING),
	REDSTONE_ORE(73),
	GLOWING_REDSTONE_ORE(74),
	REDSTONE_TORCH_OFF(75, Attribute.ATTACHABLE_SIDE | Attribute.ATTACHABLE_TOP | Attribute.BREAKS_FALLING),
	REDSTONE_TORCH_ON(76, Attribute.ATTACHABLE_SIDE | Attribute.ATTACHABLE_TOP | Attribute.BREAKS_FALLING),
	STONE_BUTTON(77, Attribute.ATTACHABLE_SIDE | Attribute.BREAKS_FALLING),
	SNOW(78, Attribute.ATTACHABLE_TOP | Attribute.FALL_THROUGH),
	ICE(79),
	SNOW_BLOCK(80),
	CACTUS(81, Attribute.ATTACHABLE_TOP),
	CLAY(82),
	SUGAR_CANE_BLOCK(83, Attribute.ATTACHABLE_TOP | Attribute.BREAKS_FALLING),
	JUKEBOX(84),
	FENCE(85),
	PUMPKIN(86),
	NETHERRACK(87),
	SOUL_SAND(88),
	GLOWSTONE(89),
	PORTAL(90),
	JACK_O_LANTERN(91),
	CAKE_BLOCK(92, Attribute.ATTACHABLE_TOP | Attribute.BREAKS_FALLING),
	DIODE_BLOCK_OFF(93, Attribute.ATTACHABLE_TOP | Attribute.BREAKS_FALLING),
	DIODE_BLOCK_ON(94, Attribute.ATTACHABLE_TOP | Attribute.BREAKS_FALLING),
	LOCKED_CHEST(95),
	TRAP_DOOR(96, Attribute.ATTACHABLE_SIDE),
	MONSTER_EGGS(97),
	SMOOTH_BRICK(98),
	HUGE_MUSHROOM_1(99),
	HUGE_MUSHROOM_2(100),
	IRON_FENCE(101),
	THIN_GLASS(102),
	MELON_BLOCK(103),
	PUMPKIN_STEM(104, Attribute.ATTACHABLE_TOP | Attribute.BREAKS_FALLING),
	MELON_STEM(105, Attribute.ATTACHABLE_TOP | Attribute.BREAKS_FALLING),
	VINE(106, Attribute.FALL_THROUGH),
	FENCE_GATE(107),
	BRICK_STAIRS(108),
	SMOOTH_STAIRS(109),
	MYCEL(110),
	WATER_LILY(111, Attribute.ATTACHABLE_TOP | Attribute.BREAKS_FALLING),
	NETHER_BRICK(112),
	NETHER_FENCE(113),
	NETHER_BRICK_STAIRS(114),
	NETHER_WARTS(115),
	ENCHANTMENT_TABLE(116),
	BREWING_STAND(117),
	CAULDRON(118),
	ENDER_PORTAL(119),
	ENDER_PORTAL_FRAME(120),
	ENDER_STONE(121),
	DRAGON_EGG(122, Attribute.GRAVITY),
	REDSTONE_LAMP_OFF(123),
	REDSTONE_LAMP_ON(124),
	WOOD_DOUBLE_STEP(125),
	WOOD_STEP(126, Attribute.BREAKS_FALLING),
	COCOA(127, Attribute.ATTACHABLE_SIDE),
	STANDSTONE_STAIRS(128),
	EMERALD_ORE(129),
	ENDER_CHEST(130),
	TRIPWIRE_HOOK(131, Attribute.ATTACHABLE_SIDE | Attribute.BREAKS_FALLING),
	TRIPWIRE(132, Attribute.FALL_THROUGH),
	EMERALD_BLOCK(133),
	SPRUCE_WOOD_STAIRS(134),
	BIRCH_WOOD_STAIRS(135),
	JUNGLE_WOOD_STAIRS(136),
	COMMAND(137),
	BEACON(138),
	COBBLE_WALL(139),
	FLOWER_POT(140, Attribute.ATTACHABLE_TOP | Attribute.BREAKS_FALLING),
	CARROT(141, Attribute.ATTACHABLE_TOP | Attribute.BREAKS_FALLING),
	POTATO(142, Attribute.ATTACHABLE_TOP | Attribute.BREAKS_FALLING),
	WOOD_BUTTON(143, Attribute.ATTACHABLE_SIDE | Attribute.BREAKS_FALLING),
	SKULL(144, Skull.class),
	ANVIL(145, Attribute.GRAVITY),



	/**
	 * ITEMS
	 */
	IRON_SHOVEL(256),
	IRON_PICKAXE(257),
	IRON_AXE(258),
	FLINT_AND_STEEL(259),
	APPLE(260),
	BOW(261),
	ARROW(262),
	COAL(263),
	DIAMOND(264),
	IRON_INGOT(265),
	GOLD_INGOT(266),
	IRON_SWORD(267),
	WOOD_SWORD(268),
	WOOD_SHOVEL(269),
	WOOD_PICKAXE(270),
	WOOD_AXE(271),
	STONE_SWORD(272),
	STONE_SHOVEL(273),
	STONE_PICKAXE(274),
	STONE_AXE(275),
	DIAMOND_SWORD(276),
	DIAMOND_SHOVEL(277),
	DIAMOND_PICKAXE(278),
	DIAMOND_AXE(279),
	STICK(280),
	BOWL(281),
	MUSHROOM_SOUP(282),
	GOLD_SWORD(283),
	GOLD_SHOVEL(284),
	GOLD_PICKAXE(285),
	GOLD_AXE(286),
	STRING(287),
	FEATHER(288),
	SULPHUR(289),
	WOOD_HOE(290),
	STONE_HOE(291),
	IRON_HOE(292),
	DIAMOND_HOE(293),
	GOLD_HOE(294),
	SEEDS(295),
	WHEAT(296),
	BREAD(297),
	LEATHER_HELMET(298, ColoredArmorItemData.class),
	LEATHER_CHESTPLATE(299, ColoredArmorItemData.class),
	LEATHER_PANTS(300, ColoredArmorItemData.class),
	LEATHER_BOOTS(301, ColoredArmorItemData.class),
	CHAIN_HELMET(302),
	CHAIN_CHESTPLATE(303),
	CHAIN_PANTS(304),
	CHAIN_BOOTS(305),
	IRON_HELMET(306),
	IRON_CHESTPLATE(307),
	IRON_PANTS(308),
	IRON_BOOTS(309),
	DIAMOND_HELMET(310),
	DIAMOND_CHESTPLATE(311),
	DIAMOND_PANTS(312),
	DIAMOND_BOOTS(313),
	GOLD_HELMET(314),
	GOLD_CHESTPLATE(315),
	GOLD_PANTS(316),
	GOLD_BOOTS(317),
	FLINT(318),
	RAW_PORK(319),
	COOKED_PORK(320),
	PAINTING(321),
	GOLD_APPLE(322),
	SIGN(323),
	WOODEN_DOOR_ITEM(324),
	BUCKET(325),
	WATER_BUCKET(326),
	LAVA_BUCKET(327),
	MINECART(328),
	SADDLE(329),
	IRON_DOOR(330),
	REDSTONE_DUST(331),
	SNOW_BALL(332),
	BOAT(333),
	LEATHER(334),
	MILK_BUCKET(335),
	CLAY_BRICK(336),
	CLAY_BALL(337),
	SUGAR_CANE(338),
	PAPER(339),
	BOOK(340),
	SLIMEBALL(341),
	STORAGE_MINECART(342),
	POWERED_MINECART(343),
	EGG(344),
	COMPASS(345),
	FISHING_ROD(346),
	CLOCK(347),
	GLOWSTONE_DUST(348),
	RAW_FISH(349),
	COOKED_FISH(350),
	INK_SACK(351),
	BONE(352),
	SUGAR(353),
	CAKE(354),
	BED(355),
	DIODE(356),
	COOKIE(357),
	MAP(358, MapItemData.class),
	SHEARS(359),
	MELON(360),
	PUMPKIN_SEEDS(361),
	MELON_SEEDS(362),
	RAW_BEEF(363),
	COOKED_BEEF(364),
	RAW_CHICKEN(365),
	COOKED_CHICKEN(366),
	ROTTEN_FLESH(367),
	ENDERPEARL(368),
	BLAZE_ROD(369),
	GHAST_TEAR(370),
	GOLD_NUGGET(371),
	NETHER_STALK(372),
	POTION(373, PotionItemData.class),
	GLASS_BOTTLE(374),
	SPIDER_EYE(375),
	FERMENTED_SPIDER_EYE(376),
	BLAZE_POWDER(377),
	MAGMA_CREAM(378),
	BREWING_STAND_ITEM(379),
	CAULDRON_ITEM(380),
	EYE_OF_ENDER(381),
	GLISTERING_MELON(382),
	SPAWN_EGG(383),
	EXP_BOTTLE(384),
	FIRE_CHARGE(385),
	BOOK_AND_QUILL(386, BookItemData.class),
	WRITTEN_BOOK(387, BookItemData.class),
	EMERALD(388),
	ITEM_FRAME(389),
	FLOWER_POT_ITEM(390),
	CARROT_ITEM(391),
	POTATO_ITEM(392),
	BAKED_POTATO(393),
	POISONOUS_POTATO(394),
	EMPTY_MAP(395),
	GOLD_CARROT(396),
	MOB_HEAD(397, MobHeadItemData.class),
	CARROT_STICK(398),
	NETHER_STAR(399),
	PUMPKIN_PIE(400),
	FIREWORK(401, FireworkItemData.class),
	FIREWORK_CHARGE(402, FireworkChargeItemData.class),
	ENCHANTED_BOOK(403, EnchantmentStorageItemData.class),
	RECORD_13(2256),
	RECORD_CAT(2257),
	RECORD_BLOCKS(2258),
	RECORD_CHIRP(2259),
	RECORD_FAR(2260),
	RECORD_MALL(2261),
	RECORD_MELLOHI(2262),
	RECORD_STAL(2263),
	RECORD_STRAD(2264),
	RECORD_WARD(2265),
	RECORD_11(2266),
	RECORD_WAIT(2267);

	/**
	 * Item id
	 */
	private final short id;

	/**
	 * Attribute flags
	 */
	private final int flags;

	/**
	 * Custom item data class
	 */
	private final Class<? extends NBTSerializable> dataClass;

	/**
	 * Mapping of ids to item for quick access
	 */
	private static final Map<Short, Items> idMap = new HashMap<Short, Items>(values().length);

	private Items(int id) {
		this(id, 0, null);
	}

	private Items(int id, int flags) {
		this(id, flags, null);
	}

	private Items(int id, Class<? extends NBTSerializable> dataClass) {
		this(id, 0, dataClass);
	}

	private Items(int id, int flags, Class<? extends NBTSerializable> dataClass) {
		this.id = (short) id;
		this.flags = flags;
		this.dataClass = dataClass;
	}

	/**
	 * Gets the id of this item
	 *
	 * @return This item's id
	 */
	public short getId() {
		return id;
	}

	/**
	 * Gets whether or not this item stores custom item data
	 *
	 * @return True if item has custom data to store, false if not
	 */
	public boolean hasItemData() {
		return dataClass != null;
	}

	/**
	 * Gets whether or not this item has an attribute
	 */
	public boolean hasAttribute(int attribute) {
		return (flags & attribute) == attribute;
	}

	/**
	 * Gets an item by its id
	 *
	 * @param id Id of item to get
	 * @return Item with the specified id or null if it doesn't exist
	 */
	public static Items getById(int id) {
		return idMap.containsKey(id) ? idMap.get(id) : null;
	}

	static {
		for (Items item : values()) {
			idMap.put(item.id, item);
		}
	}

	public class Attribute {
		/**
		 * Blocks that are attachable to the side of a block
		 */
		public static final int ATTACHABLE_SIDE = 1;
		/**
		 * Blocks that attach on top of a block
		 */
		public static final int ATTACHABLE_TOP = 2;
		/**
		 * Blocks that attach underneath a block (only levers?)
		 */
		public static final int ATTACHABLE_BOTTOM = 4;
		/**
		 * Blocks that are affected by gravity
		 */
		public static final int GRAVITY = 8;
		/**
		 * Blocks that break falling blocks (i.e. torch breaks falling sand)
		 */
		public static final int BREAKS_FALLING = 16;
		/**
		 * Blocks that falling blocks can fall through
		 */
		public static final int FALL_THROUGH = 32;
	}

}
