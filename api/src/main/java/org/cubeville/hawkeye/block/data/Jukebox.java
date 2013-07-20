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

package org.cubeville.hawkeye.block.data;

import java.util.Map;

import org.cubeville.hawkeye.NBT;
import org.cubeville.lib.jnbt.CompoundTag;
import org.cubeville.lib.jnbt.IntTag;
import org.cubeville.lib.jnbt.Tag;

public class Jukebox extends BaseBlockData {

	// TODO Store the record item
	private final int record;

	/**
	 * Deserialization constructor
	 *
	 * @param tag Tag to deserialize from
	 */
	public Jukebox(CompoundTag tag) {
		Map<String, Tag> data = tag.getValue();

		if (data.containsKey(NBT.BLOCK.JUKEBOX.RECORD_ID)) {
			record = ((IntTag) data.get(NBT.BLOCK.JUKEBOX.RECORD_ID)).getValue();
		} else {
			record = 0;
		}
	}

	public Jukebox(int record) {
		this.record = record;
	}

	/**
	 * Gets whether or not this jukebox has a record
	 */
	public boolean hasRecord() {
		return record != 0;
	}

	/**
	 * Gets the id of the record this jukebox is playing
	 */
	public int getRecordId() {
		return record;
	}

	@Override
	protected void serialize(Map<String, Tag> map) {
		super.serialize(map);
		if (hasRecord()) {
			map.put(NBT.BLOCK.JUKEBOX.RECORD_ID, new IntTag(NBT.BLOCK.JUKEBOX.RECORD_ID, record));
		}
	}

}
