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
 * ItemData implementation to store basic item data (i.e. rotation, wool color)
 */
public class BasicItemData implements ItemData {

	/**
	 * Holds item data (i.e. dye color)
	 */
	private byte data;

	public BasicItemData(byte data) {
		this.data = data;
	}

	/**
	 * Sets this item's data
	 *
	 * @param data New data value
	 */
	public void setData(byte data) {
		this.data = data;
	}

	/**
	 * Gets this item's data
	 *
	 * @return This item's data
	 */
	public byte getData() {
		return data;
	}

}
