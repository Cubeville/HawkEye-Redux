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

public enum PotionEffects {

	SPEED(1),
	SLOWNESS(2),
	HASTE(3),
	MINING_FATIGUE(4),
	STRENGTH(5),
	HEALING(6),
	HARMING(7),
	JUMP_BOOST(8),
	NAUSEA(9),
	REGENERATION(10),
	RESISTANCE(11),
	FIRE_RESISTANCE(12),
	WATER_BREATHING(13),
	INVISIBILITY(14),
	BLINDNESS(15),
	NIGHT_VISION(16),
	HUNGER(17),
	WEAKNESS(18),
	POISON(19),
	WITHER(20);

	private final int id;

	/**
	 * Mapping of ids to effects for quick access
	 */
	private static final Map<Integer, PotionEffects> idMap = new HashMap<Integer, PotionEffects>(values().length);

	private PotionEffects(int id) {
		this.id = id;
	}

	/**
	 * Gets the id of this effect
	 *
	 * @return This effect's id
	 */
	public int getId() {
		return id;
	}

	@Override
	public String toString() {
		return super.toString().replace("_", " ").toLowerCase();
	}

	/**
	 * Gets an effect by its id
	 *
	 * @param id Id of effect to get
	 * @return Potion effect with the specified id or null if it doesn't exist
	 */
	public static PotionEffects getById(int id) {
		return idMap.containsKey(id) ? idMap.get(id) : null;
	}

	static {
		for (PotionEffects effect : values()) {
			idMap.put(effect.id, effect);
		}
	}

}
