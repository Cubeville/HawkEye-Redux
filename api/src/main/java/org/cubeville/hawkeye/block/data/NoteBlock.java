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
import org.cubeville.lib.jnbt.ByteTag;
import org.cubeville.lib.jnbt.CompoundTag;
import org.cubeville.lib.jnbt.Tag;

public class NoteBlock extends BaseBlockData {

	private final byte note;

	/**
	 * Deserialization constructor
	 *
	 * @param tag Tag to deserialize from
	 */
	public NoteBlock(CompoundTag tag) {
		Map<String, Tag> data = tag.getValue();

		note = ((ByteTag) data.get(NBT.BLOCK.NOTE_BLOCK.NOTE)).getValue();
	}

	public NoteBlock(byte note) {
		this.note = note;
	}

	/**
	 * Gets the note this note block plays
	 */
	public byte getNote() {
		return note;
	}

	@Override
	protected void serialize(Map<String, Tag> map) {
		super.serialize(map);
		map.put(NBT.BLOCK.NOTE_BLOCK.NOTE, new ByteTag(NBT.BLOCK.NOTE_BLOCK.NOTE, note));
	}

}
