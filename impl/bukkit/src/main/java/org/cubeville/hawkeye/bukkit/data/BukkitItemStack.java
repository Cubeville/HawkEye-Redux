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

package org.cubeville.hawkeye.bukkit.data;

import org.cubeville.hawkeye.bukkit.Convert;
import org.cubeville.hawkeye.item.ItemStack;

public class BukkitItemStack extends ItemStack {

	private final org.bukkit.inventory.ItemStack itemStack;

	public BukkitItemStack(org.bukkit.inventory.ItemStack itemStack) {
		super((short) itemStack.getTypeId(), (byte) itemStack.getAmount(), itemStack.getDurability(), Convert.itemData(itemStack.getItemMeta()));
		this.itemStack = itemStack;
	}

	public org.bukkit.inventory.ItemStack getBukkitItemStack() {
		return itemStack;
	}

}
