/*
 * HawkEye redux
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

import java.util.HashMap;
import java.util.Map;

import org.cubeville.hawkeye.model.BlockData;
import org.cubeville.lib.jnbt.CompoundTag;
import org.cubeville.lib.jnbt.Tag;

/**
 * ItemData implementation to store the text written on signs
 */
public class Sign implements BlockData {

	private String[] lines;

	public Sign(String[] lines) {
		this.lines = lines;
	}

	public void setLines(String[] lines) {
		this.lines = lines;
	}

	public String[] getLines() {
		return lines;
	}

	public void setLine(int i, String line) {
		lines[i] = line;
	}

	public String getLine(int i) {
		return lines[i];
	}

	@Override
	public CompoundTag serialize() {
		Map<String, Tag> data = new HashMap<String, Tag>();

		return new CompoundTag("", data);
	}

}
