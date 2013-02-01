/*
 * HawkEye redux
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
import org.cubeville.lib.jnbt.StringTag;
import org.cubeville.lib.jnbt.Tag;

/**
 * ItemData implementation to store the text written on signs
 */
public class Sign extends BaseBlockData {

	private final String[] lines;

	/**
	 * Deserialization constructor
	 *
	 * @param data Tag to deserialize from
	 */
	public Sign(CompoundTag tag) {
		lines = new String[] { "", "", "", "" };

		Map<String, Tag> data = tag.getValue();

		if (data.containsKey(NBT.BLOCK.SIGN.LINE1)) {
			lines[0] = ((StringTag) data.get(NBT.BLOCK.SIGN.LINE1)).getValue();
		}
		if (data.containsKey(NBT.BLOCK.SIGN.LINE2)) {
			lines[1] = ((StringTag) data.get(NBT.BLOCK.SIGN.LINE2)).getValue();
		}
		if (data.containsKey(NBT.BLOCK.SIGN.LINE3)) {
			lines[2] = ((StringTag) data.get(NBT.BLOCK.SIGN.LINE3)).getValue();
		}
		if (data.containsKey(NBT.BLOCK.SIGN.LINE4)) {
			lines[3] = ((StringTag) data.get(NBT.BLOCK.SIGN.LINE4)).getValue();
		}
	}

	public Sign(String[] lines) {
		this.lines = lines;
	}

	/**
	 * Returns the lines of text on this sign
	 */
	public String[] getLines() {
		return lines;
	}

	/**
	 * Gets the specified line of text on this sign
	 *
	 * @param i Line number to get
	 * @return Text on line number
	 */
	public String getLine(int i) {
		return lines[i];
	}

	@Override
	public void serialize(Map<String, Tag> map) {
		map.put(NBT.BLOCK.SIGN.LINE1, new StringTag(NBT.BLOCK.SIGN.LINE1, lines[0]));
		map.put(NBT.BLOCK.SIGN.LINE2, new StringTag(NBT.BLOCK.SIGN.LINE2, lines[1]));
		map.put(NBT.BLOCK.SIGN.LINE3, new StringTag(NBT.BLOCK.SIGN.LINE3, lines[2]));
		map.put(NBT.BLOCK.SIGN.LINE4, new StringTag(NBT.BLOCK.SIGN.LINE4, lines[3]));
	}

}
