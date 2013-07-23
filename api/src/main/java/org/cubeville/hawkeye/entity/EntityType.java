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

package org.cubeville.hawkeye.entity;

import java.util.HashMap;
import java.util.Map;

public enum EntityType {

	DROPPED_ITEM(1, "item"),
	EXPERIENCE_ORB(2, "xporb", "exporb", "experience"),
	PAINTING(9, "picture"),
	ARROW(10),
	SNOWBALL(11),
	FIREBALL(12),
	SMALL_FIREBALL(13),
	ENDERPEARL(14, "pearl"),
	ENDER_SIGNAL(15, "endereye", "eyeofender"),
	THROWN_EXP_BOTTLE(17, "xpbottle", "expbottle"),
	ITEM_FRAME(18, "frame"),
	WITHER_SKULL(19),
	PRIMED_TNT(20, "tnt"),
	FALLING_BLOCK(21, "fallingsand", "fallinggravel"),
	FIREWORKS(22, "firework", "rocket"),
	BOAT(41),
	MINECART(42, "cart"),
	CHEST_MINECART(43, "chestcart"),
	FURNACE_MINECART(44, "furnacecart"),
	TNT_MINECART(45, "tntcart"),
	HOPPER_MINECART(46, "hoppercart"),
	SPAWNER_MINECART(47, "spawnercart"),
	CREEPER(50),
	SKELETON(51),
	SPIDER(52),
	GIANT(53, "giantzombie"),
	ZOMBIE(54),
	SLIME(55),
	GHAST(56),
	ZOMBIE_PIGMAN(57, "pigman", "pigzombie", "zombiepig"),
	ENDERMAN(58),
	CAVE_SPIDER(59),
	SILVERFISH(60),
	BLAZE(61),
	MAGMA_CUBE(62, "lavaslime", "magmaslime"),
	ENDERDRAGON(63, "dragon"),
	WITHER(64),
	BAT(65),
	WITCH(66),
	PIG(90),
	SHEEP(91),
	COW(92),
	CHICKEN(93),
	SQUID(94),
	WOLF(95, "dog"),
	MOOSHROOM(96, "mushroomcow"),
	SNOW_GOLEM(97, "snowman"),
	OCELOT(98, "cat"),
	IRON_GOLEM(99, "golem"),
	HORSE(100),
	VILLAGER(120),
	ENDER_CRYSTAL(200, "crystal"),
	SPLASH_POTION(-1, "potion", "splashpot"),
	EGG(-1),
	FISHING_HOOK(-1, "bobber", "fishingrod"),
	LIGHTNING(-1),
	WEATHER(-1, "rain"),
	PLAYER(-1),
	COMPLEX_PART(-1);

	/**
	 * Entity id
	 */
	private final int id;

	/**
	 * Aliases
	 */
	private final String[] aliases;

	/**
	 * Mapping of ids to blocks for quick access
	 */
	private static final Map<Integer, EntityType> idMap = new HashMap<Integer, EntityType>(values().length - 7);

	/**
	 * Name mapping
	 */
	private static final Map<String, EntityType> nameMap = new HashMap<String, EntityType>();

	private EntityType(int id) {
		this(id, new String[0]);
	}

	private EntityType(int id, String... aliases) {
		this.id = id;
		this.aliases = aliases;
	}

	public int getId() {
		return id;
	}

	/**
	 * Gets an entity type by its id
	 *
	 * @param id Id of entity type to get
	 * @return Entity type with the specified id or null if it doesn't exist
	 */
	public static EntityType getById(int id) {
		return idMap.get(id);
	}

	/**
	 * Attempts to match an entity by name or user input
	 *
	 * @param input Input to match against
	 * @return EntityType or null if no match was found
	 */
	public static EntityType match(String input) {
		try {
			return getById(Integer.parseInt(input));
		} catch (NumberFormatException e) {
			input = input.trim().toLowerCase().replaceAll("[ -_]", "");
			return nameMap.containsKey(input) ? nameMap.get(input) : null;
		}
	}

	static {
		for (EntityType entity : values()) {
			if (entity.id >= 1) {
				idMap.put(entity.id, entity);
			}

			nameMap.put(entity.toString().toLowerCase().replace("_", ""), entity);
			for (String alias : entity.aliases) {
				nameMap.put(alias.toLowerCase(), entity);
			}
		}
	}

}
