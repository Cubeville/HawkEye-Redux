/*
 * HawkEye
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

package org.cubeville.hawkeye.item.data;

/**
 * ItemData implementation to store the text written on signs
 */
public class SignItemData extends BasicItemData {

	private String[] lines;

	public SignItemData(byte data, String[] lines) {
		super(data);
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

}
