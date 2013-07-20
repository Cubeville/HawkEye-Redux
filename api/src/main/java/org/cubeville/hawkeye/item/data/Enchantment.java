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

import java.util.HashMap;
import java.util.Map;

import org.cubeville.hawkeye.NBT;
import org.cubeville.hawkeye.item.Enchantments;
import org.cubeville.hawkeye.model.NBTSerializable;
import org.cubeville.lib.jnbt.CompoundTag;
import org.cubeville.lib.jnbt.ShortTag;
import org.cubeville.lib.jnbt.Tag;

public class Enchantment implements NBTSerializable {

	private final short enchantmentId;
	private final short level;

	/**
	 * Deserialization constructor
	 *
	 * @param tag Tag to deserialize from
	 */
	public Enchantment(CompoundTag tag) {
		Map<String, Tag> data = tag.getValue();
		enchantmentId = ((ShortTag) data.get(NBT.ENCHANTMENT.ID)).getValue();
		level = ((ShortTag) data.get(NBT.ENCHANTMENT.LEVEL)).getValue();
	}

	/**
	 * Gets the type of enchantment this is
	 */
	public Enchantments getEnchantment() {
		return Enchantments.getById(enchantmentId);
	}

	/**
	 * Gets the id of the enchantment this is
	 */
	public short getEnchantmentId() {
		return enchantmentId;
	}

	/**
	 * Gets the enchantment's level
	 */
	public short getLevel() {
		return level;
	}

	@Override
	public CompoundTag serialize() {
		Map<String, Tag> data = new HashMap<String, Tag>();
		data.put(NBT.ENCHANTMENT.ID, new ShortTag(NBT.ENCHANTMENT.ID, enchantmentId));
		data.put(NBT.ENCHANTMENT.LEVEL, new ShortTag(NBT.ENCHANTMENT.LEVEL, level));
		return new CompoundTag("", data);
	}

}
