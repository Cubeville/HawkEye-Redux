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

import org.cubeville.hawkeye.model.ItemData;

/**
 * Represents a stack of items
 */
public class ItemStack {

	private final short id;
	private final byte amount;
	private final short durability;

	public ItemStack(short id) {
		this(id, (byte) 1, (short) 0);
	}

	public ItemStack(short id, byte amount) {
		this(id, amount, (short) 0);
	}

	public ItemStack(short id, byte amount, short durability) {
		this.id = id;
		this.amount = amount;
		this.durability = durability;
	}

	/**
	 * Gets the type of item this stack is
	 */
	public Items getType() {
		return Items.getById(id);
	}

	/**
	 * Gets the id of this stack's item type
	 */
	public short getTypeId() {
		return id;
	}

	/**
	 * Gets the amount the items in this stack
	 */
	public byte getAmount() {
		return amount;
	}

	/**
	 * Gets the durability of this item stack
	 */
	public short getDurability() {
		return durability;
	}

	/**
	 * Gets any custom data associated with this item stack
	 */
	public ItemData getData() {
		return null;
	}

}
