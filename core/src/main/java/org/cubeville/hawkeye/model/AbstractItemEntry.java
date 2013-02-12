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

package org.cubeville.hawkeye.model;

import org.cubeville.hawkeye.Action;
import org.cubeville.hawkeye.item.ItemStack;
import org.cubeville.hawkeye.location.Location;
import org.cubeville.lib.jnbt.NBTUtils;

public class AbstractItemEntry extends AbstractEntry implements ItemEntry {

	private final ItemStack item;
	private byte[] cache = null;

	public AbstractItemEntry(Action action, DatabaseEntry entry) {
		super(action, entry);

		item = new ItemStack(entry.getData(), entry.getNbt());
	}

	public AbstractItemEntry(Action action, String player, Location location, ItemStack item) {
		super(action, player, location);

		this.item = item;
	}

	@Override
	public ItemStack getItem() {
		return item;
	}

	@Override
	public String getData() {
		return item.toString();
	}

	@Override
	public byte[] getNbt() {
		if (cache != null) return cache;
		cache = NBTUtils.toByteArray(item.getData().serialize());
		return cache;
	}

}
