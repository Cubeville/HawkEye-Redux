/*
 * HawkEye
 * Copyright (C) 2012 Cubeville <http://www.cubeville.org> and contributors
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

import org.cubeville.hawkeye.item.data.BasicItemData;
import org.cubeville.hawkeye.item.data.BookItemData;
import org.cubeville.hawkeye.item.data.DurabilityItemData;
import org.cubeville.hawkeye.item.data.EnchantmentItemData;
import org.cubeville.hawkeye.item.data.ItemData;
import org.cubeville.hawkeye.item.data.MobHeadItemData;

public enum Item {

	IRON_SHOVEL(256, EnchantmentItemData.class),
	IRON_PICKAXE(257, EnchantmentItemData.class),
	IRON_AXE(258, EnchantmentItemData.class),
	FLINT_AND_STEEL(259, DurabilityItemData.class),
	APPLE(260),
	BOW(261, EnchantmentItemData.class),
	ARROW(262),
	COAL(263, BasicItemData.class),
	DIAMOND(264),
	IRON_INGOT(265),
	GOLD_INGOT(266),
	IRON_SWORD(267, EnchantmentItemData.class),
	WOOD_SWORD(268, EnchantmentItemData.class),
	WOOD_SHOVEL(269, EnchantmentItemData.class),
	WOOD_PICKAXE(270, EnchantmentItemData.class),
	WOOD_AXE(271, EnchantmentItemData.class),
	STONE_SWORD(272, EnchantmentItemData.class),
	STONE_SHOVEL(273, EnchantmentItemData.class),
	STONE_PICKAXE(274, EnchantmentItemData.class),
	STONE_AXE(275, EnchantmentItemData.class),
	DIAMOND_SWORD(276, EnchantmentItemData.class),
	DIAMOND_SHOVEL(277, EnchantmentItemData.class),
	DIAMOND_PICKAXE(278, EnchantmentItemData.class),
	DIAMOND_AXE(279, EnchantmentItemData.class),
	STICK(280),
	BOWL(281),
	MUSHROOM_SOUP(282),
	GOLD_SWORD(283, EnchantmentItemData.class),
	GOLD_SHOVEL(284, EnchantmentItemData.class),
	GOLD_PICKAXE(285, EnchantmentItemData.class),
	GOLD_AXE(286, EnchantmentItemData.class),
	STRING(287),
	FEATHER(288),
	SULPHUR(289),
	WOOD_HOE(290, DurabilityItemData.class),
	STONE_HOE(291, DurabilityItemData.class),
	IRON_HOE(292, DurabilityItemData.class),
	DIAMOND_HOE(293, DurabilityItemData.class),
	GOLD_HOE(294, DurabilityItemData.class),
	SEEDS(295),
	WHEAT(296),
	BREAD(297),
	LEATHER_HELMET(298, EnchantmentItemData.class),
	LEATHER_CHESTPLATE(299, EnchantmentItemData.class),
	LEATHER_PANTS(300, EnchantmentItemData.class),
	LEATHER_BOOTS(301, EnchantmentItemData.class),
	CHAIN_HELMET(302, EnchantmentItemData.class),
	CHAIN_CHESTPLATE(303, EnchantmentItemData.class),
	CHAIN_PANTS(304, EnchantmentItemData.class),
	CHAIN_BOOTS(305, EnchantmentItemData.class),
	IRON_HELMET(306, EnchantmentItemData.class),
	IRON_CHESTPLATE(307, EnchantmentItemData.class),
	IRON_PANTS(308, EnchantmentItemData.class),
	IRON_BOOTS(309, EnchantmentItemData.class),
	DIAMOND_HELMET(310, EnchantmentItemData.class),
	DIAMOND_CHESTPLATE(311, EnchantmentItemData.class),
	DIAMOND_PANTS(312, EnchantmentItemData.class),
	DIAMOND_BOOTS(313, EnchantmentItemData.class),
	GOLD_HELMET(314, EnchantmentItemData.class),
	GOLD_CHESTPLATE(315, EnchantmentItemData.class),
	GOLD_PANTS(316, EnchantmentItemData.class),
	GOLD_BOOTS(317, EnchantmentItemData.class),
	FLINT(318),
	RAW_PORK(319),
	COOKED_PORK(320),
	PAINTING(321),
	GOLD_APPLE(322),
	SIGN(323),
	WOODEN_DOOR(324),
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
	FISHING_ROD(346, DurabilityItemData.class),
	CLOCK(347),
	GLOWSTONE_DUST(348),
	RAW_FISH(349),
	COOKED_FISH(350),
	INK_SACK(351, BasicItemData.class),
	BONE(352),
	SUGAR(353),
	CAKE(354),
	BED(355),
	DIODE(356),
	COOKIE(357),
	MAP(358, BasicItemData.class),
	SHEARS(359, DurabilityItemData.class),
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
	POTION(373, BasicItemData.class),
	GLASS_BOTTLE(374),
	SPIDER_EYE(375),
	FERMENTED_SPIDER_EYE(376),
	BLAZE_POWDER(377),
	MAGMA_CREAM(378),
	BREWING_STAND(379),
	CAULDRON(380),
	EYE_OF_ENDER(381),
	GLISTERING_MELON(382),
	SPAWN_EGG(383, BasicItemData.class),
	EXP_BOTTLE(384),
	FIRE_CHARGE(385),
	BOOK_AND_QUILL(386, BookItemData.class),
	WRITTEN_BOOK(387, BookItemData.class),
	EMERALD(388),
	ITEM_FRAME(389),
	FLOWER_POT(390),
	CARROT(391),
	POTATO(392),
	BAKED_POTATO(393),
	POISONOUS_POTATO(394),
	EMPTY_MAP(395),
	GOLD_CARROT(396),
	MOB_HEAD(397, MobHeadItemData.class),
	CARROT_STICK(398),
	NETHER_STAR(399),
	PUMPKIN_PIE(400),
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
	private final int id;

	/**
	 * Custom item data class
	 */
	private final Class<? extends ItemData> dataClass;

	/**
	 * Mapping of ids to item for quick access
	 */
	private static final Map<Integer, Item> idMap = new HashMap<Integer, Item>(values().length);

	private Item(int id) {
		this(id, null);
	}

	private Item(int id, Class<? extends ItemData> dataClass) {
		this.id = id;
		this.dataClass = dataClass;
	}

	/**
	 * Gets the id of this item
	 *
	 * @return This item's id
	 */
	public int getId() {
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
	 * Gets an item by its id
	 *
	 * @param id Id of item to get
	 * @return Item with the specified id or null if it doesn't exist
	 */
	public static Item getById(int id) {
		return idMap.containsKey(id) ? idMap.get(id) : null;
	}

	static {
		for (Item item : values()) {
			idMap.put(item.id, item);
		}
	}

}
