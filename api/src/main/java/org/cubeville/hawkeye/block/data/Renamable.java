/*
 * HawkEye Redux
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

package org.cubeville.hawkeye.block.data;

import java.util.Map;

import org.cubeville.hawkeye.NBT;
import org.cubeville.lib.jnbt.CompoundTag;
import org.cubeville.lib.jnbt.StringTag;
import org.cubeville.lib.jnbt.Tag;

public abstract class Renamable extends BaseBlockData {

	private final String name;

	/**
	 * Deserialization constructor
	 *
	 * @param data Tag to deserialize from
	 */
	public Renamable(CompoundTag tag) {
		Map<String, Tag> data = tag.getValue();
		if (data.containsKey(NBT.BLOCK.NAME)) {
			name = ((StringTag) data.get(NBT.BLOCK.NAME)).getValue();
		} else {
			name = null;
		}
	}

	public Renamable(String name) {
		this.name = name;
	}

	/**
	 * Returns whether or not this block has a custom name
	 */
	public boolean hasName() {
		return !(name == null || name.isEmpty());
	}

	/**
	 * Gets the custom name associated with this block
	 */
	public String getName() {
		return name;
	}

	@Override
	public void serialize(Map<String, Tag> map) {
		if (hasName()) {
			map.put(NBT.BLOCK.NAME, new StringTag(NBT.BLOCK.NAME, name));
		}
	}

}
