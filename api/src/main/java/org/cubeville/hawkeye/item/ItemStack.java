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

import org.cubeville.hawkeye.Item;
import org.cubeville.hawkeye.item.data.BaseItemData;

/**
 * Represents a stack of items
 */
public class ItemStack {

	private final short id;
	private final byte amount;
	private final short durability;

	/**
	 * Custom data associated with the item
	 */
	private final ItemData data;

	public ItemStack(Item type) {
		this(type.getId());
	}

	public ItemStack(Item type, ItemData data) {
		this(type.getId(), data);
	}

	public ItemStack(short id) {
		this(id, (byte) 1, (short) 0);
	}

	public ItemStack(short id, ItemData data) {
		this(id, (byte) 1, (short) 0, data);
	}

	public ItemStack(Item type, byte amount) {
		this(type.getId(), amount);
	}

	public ItemStack(Item type, byte amount, ItemData data) {
		this(type.getId(), amount, data);
	}

	public ItemStack(short id, byte amount) {
		this(id, amount, (short) 0);
	}

	public ItemStack(short id, byte amount, ItemData data) {
		this(id, amount, (short) 0, data);
	}

	public ItemStack(Item type, byte amount, short durability) {
		this(type.getId(), amount, durability);
	}

	public ItemStack(Item type, byte amount, short durability, ItemData data) {
		this(type.getId(), amount, durability, data);
	}

	public ItemStack(short id, byte amount, short durability) {
		this(id, amount, durability, null);
	}

	public ItemStack(short id, byte amount, short durability, ItemData data) {
		this.id = id;
		this.amount = amount;
		this.durability = durability;
		this.data = data;
	}

	/**
	 * Constructs an item stack from a database serialized string
	 *
	 * @param str Database string
	 * @param nbt Item nbt data byte array
	 */
	public ItemStack(String str, byte[] nbt) {
		short id;
		byte amount;
		short durability;
		String[] parts = str.split("@");
		String[] data = parts[0].split(":");

		try {
			id = Short.parseShort(data[0]);
		} catch (NumberFormatException e) {
			id = 1;
		}

		try  {
			durability = data.length > 1 ? Short.parseShort(data[1]) : 0;
		} catch (NumberFormatException e) {
			durability = 0;
		}

		try {
			amount = parts.length > 1 ? Byte.parseByte(parts[1]) : 1;
		} catch (NumberFormatException e) {
			amount = 1;
		}

		this.id = id;
		this.amount = amount;
		this.durability = durability;

		if (nbt != null && nbt.length > 0) {
			// TODO Get real item data
			this.data = new BaseItemData(null, null, null);
		} else {
			this.data = new BaseItemData(null, null, null);
		}
	}

	public ItemStack(String str) {
		this(str, null);
	}

	/**
	 * Gets the type of item this stack is
	 *
	 * @return Item type
	 */
	public Item getType() {
		return Item.getById(id);
	}

	/**
	 * Gets the id of this stack's item type
	 *
	 * @return Item type id
	 */
	public short getTypeId() {
		return id;
	}

	/**
	 * Gets the amount the items in this stack
	 *
	 * @return Item stack amount
	 */
	public byte getAmount() {
		return amount;
	}

	/**
	 * Gets the durability of this item stack
	 *
	 * @return Item durability
	 */
	public short getDurability() {
		return durability;
	}

	/**
	 * Gets any custom data associated with this item stack
	 *
	 * @return Item data
	 */
	public ItemData getData() {
		return data;
	}

	@Override
	public String toString() {
		String ret = String.valueOf(id);
		if (durability != 0) ret += ":" + String.valueOf(durability);
		if (amount != 0) ret += "@" + String.valueOf(amount);
		return ret;
	}

}
