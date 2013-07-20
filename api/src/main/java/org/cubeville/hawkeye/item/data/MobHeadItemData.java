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
import org.cubeville.lib.jnbt.CompoundTag;
import org.cubeville.lib.jnbt.StringTag;
import org.cubeville.lib.jnbt.Tag;

/**
 * ItemData implementation to store the owner of player skulls
 */
public class MobHeadItemData extends BaseItemData {

	/**
	 * Owner of this mob head
	 */
	private final String owner;

	/**
	 * Deserialization constructor
	 *
	 * @param data Tag to deserialize from
	 */
	public MobHeadItemData(CompoundTag tag) {
		super(tag);

		Map<String, Tag> data = tag.getValue();

		if (data.containsKey(NBT.ITEM.SKULL.OWNER)) {
			owner = ((StringTag) data.get(NBT.ITEM.SKULL.OWNER)).getValue();
		} else {
			owner = null;
		}
	}

	public MobHeadItemData(String owner) {
		this.owner = owner;
	}

	/**
	 * Gets whether or not this mob head has a custom owner
	 */
	public boolean hasOwner() {
		return owner != null;
	}

	/**
	 * Gets the owner of this mob head
	 */
	public String getOwner() {
		return owner;
	}

	@Override
	protected void serialize(Map<String, Tag> map) {
		super.serialize(map);

		if (hasOwner()) {
			map.put(NBT.ITEM.SKULL.OWNER, new StringTag(NBT.ITEM.SKULL.OWNER, owner));
		}
	}

}
