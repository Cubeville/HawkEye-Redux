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
	 * @return This item's id
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
	 * @return Enchamtment with the specified id or null if it doesn't exist
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
