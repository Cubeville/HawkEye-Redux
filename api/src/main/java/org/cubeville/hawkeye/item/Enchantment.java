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

public enum Enchantment {

	PROTECTION(0),
	FIRE_PROTECTION(1),
	FEATHER_FALLING(2),
	BLAST_PROTECTION(3),
	PROJECTILE_PROTECTION(4),
	RESPIRATION(5),
	AQUA_AFFINITY(6),
	THORNS(7),
	SHARPNESS(16),
	SMITE(17),
	BANE_OF_ARTHROPODS(18),
	KNOCKBACK(19),
	FIRE_ASPECT(20),
	LOOTING(21),
	EFFICIENCY(32),
	SILK_TOUCH(33),
	UNBREAKING(34),
	FORTUNE(35),
	POWER(48),
	PUNCH(49),
	FLAME(50),
	INFINITY(51);

	private final int id;

	/**
	 * Mapping of ids to enchantment for quick access
	 */
	private static final Map<Integer, Enchantment> idMap = new HashMap<Integer, Enchantment>(values().length);

	private Enchantment(int id) {
		this.id = id;
	}

	/**
	 * Gets the id of this enchantment
	 *
	 * @return This enchantment's id
	 */
	public int getId() {
		return id;
	}

	@Override
	public String toString() {
		return super.toString().replace("_", " ").toLowerCase();
	}

	/**
	 * Gets an enchantment by its id
	 *
	 * @param id Id of enchantment to get
	 * @return Enchantment with the specified id or null if it doesn't exist
	 */
	public static Enchantment getById(int id) {
		return idMap.containsKey(id) ? idMap.get(id) : null;
	}

	static {
		for (Enchantment enchantment : values()) {
			idMap.put(enchantment.id, enchantment);
		}
	}

}
