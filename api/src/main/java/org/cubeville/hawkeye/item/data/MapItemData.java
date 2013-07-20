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

import java.util.Map;

import org.cubeville.hawkeye.NBT;
import org.cubeville.lib.jnbt.ByteTag;
import org.cubeville.lib.jnbt.CompoundTag;
import org.cubeville.lib.jnbt.Tag;

/**
 * ItemData implementation to store the details of a map
 */
public class MapItemData extends BaseItemData {

	private final byte scaling;

	/**
	 * Deserialization constructor
	 *
	 * @param tag Tag to deserialize from
	 */
	public MapItemData(CompoundTag tag) {
		super(tag);

		Map<String, Tag> data = tag.getValue();
		if (data.containsKey(NBT.ITEM.MAP.SCALING)) {
			scaling = ((ByteTag) data.get(NBT.ITEM.MAP.SCALING)).getValue();
		} else {
			scaling = 0;
		}
	}

	/**
	 * Gets whether or not this map has custom scaling set
	 */
	public boolean hasScaling() {
		return scaling != 0;
	}

	@Override
	protected void serialize(Map<String, Tag> map) {
		super.serialize(map);

		if (hasScaling()) {
			map.put(NBT.ITEM.MAP.SCALING, new ByteTag(NBT.ITEM.MAP.SCALING, scaling));
		}
	}


}
