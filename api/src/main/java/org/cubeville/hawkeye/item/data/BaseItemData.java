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

package org.cubeville.hawkeye.item.data;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.cubeville.hawkeye.NBT;
import org.cubeville.hawkeye.item.ItemData;
import org.cubeville.lib.jnbt.CompoundTag;
import org.cubeville.lib.jnbt.ListTag;
import org.cubeville.lib.jnbt.Tag;

public class BaseItemData implements ItemData {

	private final List<Enchantment> enchantments;

	/**
	 * Deserialization constructor
	 *
	 * @param tag Tag to deserialize from
	 */
	public BaseItemData(CompoundTag tag) {
		enchantments = new ArrayList<Enchantment>();
		Map<String, Tag> data = tag.getValue();

		if (data.containsKey(NBT.ITEM.ENCHANTMENTS)) {
			List<Tag> enchants = ((ListTag) data.get(NBT.ITEM.ENCHANTMENTS)).getValue();
			for (Tag t : enchants) {
				Enchantment enchant = new Enchantment((CompoundTag) t);
				enchantments.add(enchant);
			}
		}
	}

	public BaseItemData() {
		enchantments = new ArrayList<Enchantment>();
		// TODO Get enchantments
	}

	/**
	 * Gets whether or not this item has enchantments on it
	 */
	public boolean hasEnchantments() {
		return !enchantments.isEmpty();
	}

	@Override
	public final CompoundTag serialize() {
		Map<String, Tag> data = new HashMap<String, Tag>();
		serialize(data);
		return new CompoundTag(NBT.ITEM.DATA, data);
	}

	protected void serialize(Map<String, Tag> map) {
		List<Tag> data = new ArrayList<Tag>();

		if (hasEnchantments()) {
			for (Enchantment enchantment : enchantments) {
				data.add(enchantment.serialize());
			}
		}

		map.put(NBT.ITEM.ENCHANTMENTS, new ListTag(NBT.ITEM.ENCHANTMENTS, CompoundTag.class, data));
	}

}
