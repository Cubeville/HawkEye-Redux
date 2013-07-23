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
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import org.cubeville.hawkeye.NBT;
import org.cubeville.hawkeye.item.ItemData;
import org.cubeville.lib.jnbt.CompoundTag;
import org.cubeville.lib.jnbt.ListTag;
import org.cubeville.lib.jnbt.StringTag;
import org.cubeville.lib.jnbt.Tag;

public class BaseItemData implements ItemData {

	protected final String name;
	protected final List<String> lore;

	protected final List<Enchantment> enchantments;

	/**
	 * Deserialization constructor
	 *
	 * @param tag Tag to deserialize from
	 */
	public BaseItemData(CompoundTag tag) {
		lore = new LinkedList<String>();
		enchantments = new ArrayList<Enchantment>();

		Map<String, Tag> data = tag.getValue();

		// Name and lore are nested in the display tag
		if (data.containsKey(NBT.ITEM.DISPLAY.TAG)) {
			Map<String, Tag> display = ((CompoundTag) data.get(NBT.ITEM.DISPLAY.TAG)).getValue();

			if (display.containsKey(NBT.ITEM.DISPLAY.NAME)) {
				name = ((StringTag) display.get(NBT.ITEM.DISPLAY.NAME)).getValue();
			} else {
				name = null;
			}

			if (display.containsKey(NBT.ITEM.DISPLAY.LORE)) {
				List<Tag> list = ((ListTag) display.get(NBT.ITEM.DISPLAY.LORE)).getValue();

				for (Tag t : list) {
					lore.add(((StringTag) t).getValue());
				}
			}
		} else {
			name = null;
		}

		if (data.containsKey(NBT.ITEM.ENCHANTMENTS)) {
			List<Tag> enchants = ((ListTag) data.get(NBT.ITEM.ENCHANTMENTS)).getValue();
			for (Tag t : enchants) {
				Enchantment enchant = new Enchantment((CompoundTag) t);
				enchantments.add(enchant);
			}
		}
	}

	public BaseItemData(String name, List<String> lore, List<Enchantment> enchantments) {
		this.name = name;

		if (lore == null) lore = Collections.emptyList();
		this.lore = Collections.unmodifiableList(lore);

		if (enchantments == null) enchantments = Collections.emptyList();
		this.enchantments = Collections.unmodifiableList(enchantments);
	}

	/**
	 * Gets whether or not this item has a custom name
	 */
	public boolean hasName() {
		return name != null;
	}

	/**
	 * Gets whether or not this item has lore
	 */
	public boolean hasLore() {
		return !lore.isEmpty();
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
		Map<String, Tag> display;

		// Don't want to overwrite the display tag if it's already there
		if (map.containsKey(NBT.ITEM.DISPLAY.TAG)) {
			display = ((CompoundTag) map.get(NBT.ITEM.DISPLAY.TAG)).getValue();
		} else {
			display = new HashMap<String, Tag>();
		}

		if (hasName()) {
			display.put(NBT.ITEM.DISPLAY.NAME, new StringTag(NBT.ITEM.DISPLAY.NAME, name));
		}

		if (hasLore()) {
			List<Tag> list = new LinkedList<Tag>();

			for (String line : lore) {
				list.add(new StringTag("", line));
			}

			display.put(NBT.ITEM.DISPLAY.LORE, new ListTag(NBT.ITEM.DISPLAY.LORE, StringTag.class, list));
		}

		map.put(NBT.ITEM.DISPLAY.TAG, new CompoundTag(NBT.ITEM.DISPLAY.TAG, display));

		if (hasEnchantments()) {
			List<Tag> enchants = new ArrayList<Tag>();

			for (Enchantment enchantment : enchantments) {
				enchants.add(enchantment.serialize());
			}

			map.put(NBT.ITEM.ENCHANTMENTS, new ListTag(NBT.ITEM.ENCHANTMENTS, CompoundTag.class, enchants));
		}
	}

}
