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

import org.cubeville.hawkeye.block.BlockData;
import org.cubeville.hawkeye.item.ItemData;
import org.cubeville.lib.jnbt.CompoundTag;

public interface Item {

	/**
	 * Gets the id of this item
	 *
	 * @return This item's id
	 */
	short getId();

	/**
	 * Gets the name of this item
	 *
	 * @return This item's name
	 */
	String getName();

	/**
	 * Gets whether or not this item has an attribute
	 */
	boolean hasAttribute(Attribute attribute);

	/**
	 * Gets whether or not this item stores custom item data
	 *
	 * @return True if item has custom data to store, false if not
	 */
	boolean hasItemData();

	/**
	 * Constructs item data from a serialized NBT tag
	 *
	 * @param tag NBT Tag to reconstruct from
	 * @return ItemData or null if unable to reconstruct
	 */
	ItemData getItemData(CompoundTag tag);

	/**
	 * Gets whether or not this item stores custom block data
	 *
	 * @return True if block has custom data to store, false if not
	 */
	boolean hasBlockData();

	/**
	 * Constructs block data from a serialized NBT tag
	 *
	 * @param tag NBT Tag to reconstruct from
	 * @return BlockData or null if unable to reconstruct
	 */
	BlockData getBlockData(CompoundTag tag);

	/**
	 * Block/item attributes
	 */
	public enum Attribute {

		/**
		 * Blocks that are attachable to the side of a block
		 */
		ATTACHABLE_SIDE(1),
		/**
		 * Blocks that attach on top of a block
		 */
		ATTACHABLE_TOP(2),
		/**
		 * Blocks that attach underneath a block (only levers?)
		 */
		ATTACHABLE_BOTTOM(4),
		/**
		 * Blocks that are affected by gravity
		 */
		GRAVITY(8),
		/**
		 * Blocks that break falling blocks (i.e. torch breaks falling sand)
		 */
		BREAKS_FALLING(16),
		/**
		 * Blocks that falling blocks can fall through
		 */
		FALL_THROUGH(32);

		private final int mask;

		private Attribute(int mask) {
			this.mask = mask;
		}

		public final int getMask() {
			return mask;
		}

	}

}
