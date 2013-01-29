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

package org.cubeville.hawkeye;

/**
 * NBT Tag name constants
 */
public class NBT {

	public class ITEM {
		public static final String SLOT = "Slot";
		public static final String ID = "id";
		public static final String AMOUNT = "Count";
		public static final String DURABILITY = "Damage";
		public static final String DATA = "tag";
		public static final String ENCHANTMENTS = "ench";
		public static final String STORED_ENCHANTMENTS = "StoredEnchantments";
		public static final String REPAIR_COST = "RepairCost";
		public static final String DISPLAY = "display";

		public class DISPLAY {
			public static final String NAME = "Name";
			public static final String DESCRIPTION = "Lore";
			public static final String COLOR = "color";
		}

		public class BOOK {
			public static final String TITLE = "title";
			public static final String AUTHOR = "author";
			public static final String PAGES = "pages";
		}

		public class POTION {
			public static final String EFFECTS = "CustomPotionEffects";
		}

		public class SKULL {
			public static final String OWNER = "SkullOwner";
		}

		public class FIREWORK {
			public static final String POWER = "Flight";
			public static final String EFFECT = "Explosion";
			public static final String EFFECTS = "Explosions";
		}

		public class MAP {
			public static final String SCALING = "map_is_scaling";
		}
	}

	public class ENCHANTMENT {
		public static final String ID = "id";
		public static final String LEVEL = "lvl";
	}

	public class POTION_EFFECT {
		public static final String ID = "Id";
		public static final String LEVEL = "Amplifier";
		public static final String DURATION = "Duration";
		public static final String AMBIENT = "Ambient";
	}

	public class FIREWORK_EFFECT {
		public static final String SHAPE = "Type";
		public static final String FLICKER = "Flicker";
		public static final String TRAIL = "Trail";
		public static final String COLORS = "Colors";
		public static final String FADE_COLORS = "FadeColors";
	}

}
