/*
 * HawkEye Redux
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

package org.cubeville.hawkeye.entity;

import java.util.HashMap;
import java.util.Map;

public enum Entity {

	DROPPED_ITEM(1),
	EXPERIENCE_ORB(2),
	PAINTING(9),
	ARROW(10),
	SNOWBALL(11),
	FIREBALL(12),
	SMALL_FIREBALL(13),
	ENDERPEARL(14),
	ENDER_SIGNAL(15),
	THROWN_EXP_BOTTLE(17),
	ITEM_FRAME(18),
	WITHER_SKULL(19),
	PRIMED_TNT(20),
	FALLING_BLOCK(21),
	FIREWORKS(22),
	MINECART(40),
	BOAT(41),
	CREEPER(50),
	SKELETON(51),
	SPIDER(52),
	GIANT(53),
	ZOMBIE(54),
	SLIME(55),
	GHAST(56),
	ZOMBIE_PIGMAN(57),
	ENDERMAN(58),
	CAVE_SPIDER(59),
	SILVERFISH(60),
	BLAZE(61),
	MAGMA_CUBE(62),
	ENDERDRAGON(63),
	WITHER(64),
	BAT(65),
	WITCH(66),
	PIG(90),
	SHEEP(91),
	COW(92),
	CHICKEN(93),
	SQUID(94),
	WOLF(95),
	MOOSHROOM(96),
	SNOW_GOLEM(97),
	OCELOT(98),
	IRON_GOLEM(99),
	VILLAGER(120),
	ENDER_CRYSTAL(200),
	SLASH_POTION(-1),
	EGG(-1),
	FISHING_HOOK(-1),
	LIGHTNING(-1),
	WEATHER(-1),
	PLAYER(-1),
	COMPLEX_PART(-1);


	/**
	 * Entity id
	 */
	private final int id;

	/**
	 * Mapping of ids to blocks for quick access
	 */
	private static final Map<Integer, Entity> idMap = new HashMap<Integer, Entity>(values().length - 7);

	private Entity(int id) {
		this.id = id;
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
	public static Entity getById(int id) {
		return idMap.containsKey(id) ? idMap.get(id) : null;
	}

	static {
		for (Entity entity : values()) {
			if (entity.id < 1) continue;
			idMap.put(entity.id, entity);
		}
	}

}
