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

package org.cubeville.hawkeye.item.data;

/**
 * ItemData implementation to store the durability of tools/armor
 */
public class DurabilityItemData implements ItemData {

	/**
	 * Holds item durability (i.e. for tools/armor)
	 */
	private short durability;

	public DurabilityItemData(short durability) {
		this.durability = durability;
	}

	/**
	 * Sets this item's durability
	 *
	 * @param durability Durability level
	 */
	public void setDurability(short durability) {
		this.durability = durability;
	}

	/**
	 * Gets this item's durability
	 *
	 * @return This item's durability level
	 */
	public short getDurability() {
		return durability;
	}

}
