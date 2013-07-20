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
import org.cubeville.lib.jnbt.CompoundTag;
import org.cubeville.lib.jnbt.IntTag;
import org.cubeville.lib.jnbt.Tag;

/**
 * ItemData implementation to store the color of dyed armor
 */
public class ColoredArmorItemData extends BaseItemData {

	/**
	 * Default color for leather armor
	 */
	private static final int defaultColor = 0xA06540;

	// TODO Utility class for managing colors?
	private final int color;

	public ColoredArmorItemData(CompoundTag tag) {
		super(tag);

		Map<String, Tag> data = tag.getValue();

		// Color tag is nested in the display tag
		if (data.containsKey(NBT.ITEM.DISPLAY_TAG)) {
			Map<String, Tag> display = ((CompoundTag) data.get(NBT.ITEM.DISPLAY_TAG)).getValue();

			if (display.containsKey(NBT.ITEM.DISPLAY.COLOR)) {
				color = ((IntTag) display.get(NBT.ITEM.DISPLAY.COLOR)).getValue();
			} else {
				color = defaultColor;
			}
		} else {
			color = defaultColor;
		}
	}

	/**
	 * Gets whether or not this armor has a color set on it
	 */
	public boolean hasColor() {
		return color != defaultColor;
	}

	@Override
	protected void serialize(Map<String, Tag> map) {
		super.serialize(map);

		if (!hasColor()) return;

		Map<String, Tag> display;

		// Don't want to overwrite the display tag if it's already there
		if (map.containsKey(NBT.ITEM.DISPLAY_TAG)) {
			display = ((CompoundTag) map.get(NBT.ITEM.DISPLAY_TAG)).getValue();
		} else {
			display = new HashMap<String, Tag>();
		}

		display.put(NBT.ITEM.DISPLAY.COLOR, new IntTag(NBT.ITEM.DISPLAY.COLOR, color));

		map.put(NBT.ITEM.DISPLAY_TAG, new CompoundTag(NBT.ITEM.DISPLAY_TAG, display));
	}


}
