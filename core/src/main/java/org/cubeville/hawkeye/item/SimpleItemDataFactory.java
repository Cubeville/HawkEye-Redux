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

import java.util.Map;

import org.cubeville.lib.jnbt.CompoundTag;
import org.cubeville.lib.jnbt.Tag;

public class SimpleItemDataFactory implements ItemDataFactory {

	@Override
	public ItemData getItemData(CompoundTag tag) {
		Map<String, Tag> data = tag.getValue();
		if (!data.containsKey("id")) return null;

		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ItemData getItemData(ItemStack stack) {
		// TODO Auto-generated method stub
		return null;
	}

}
