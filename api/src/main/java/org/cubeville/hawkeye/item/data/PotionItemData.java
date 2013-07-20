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

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.cubeville.hawkeye.NBT;
import org.cubeville.lib.jnbt.CompoundTag;
import org.cubeville.lib.jnbt.ListTag;
import org.cubeville.lib.jnbt.Tag;

public class PotionItemData extends BaseItemData {

	private final List<PotionEffect> customEffects;

	/**
	 * Deserialization constructor
	 *
	 * @param tag Tag to deserialize from
	 */
	public PotionItemData(CompoundTag tag) {
		super(tag);

		customEffects = new ArrayList<PotionEffect>();
		Map<String, Tag> data = tag.getValue();

		if (data.containsKey(NBT.ITEM.POTION.EFFECTS)) {
			List<Tag> effects = ((ListTag) data.get(NBT.ITEM.POTION.EFFECTS)).getValue();
			for (Tag t : effects) {
				PotionEffect effect = new PotionEffect((CompoundTag) t);
				customEffects.add(effect);
			}
		}
	}

	/**
	 * Gets whether or not this potion has custom effects added to it
	 */
	public boolean hasCustomEffects() {
		return !customEffects.isEmpty();
	}

	@Override
	protected void serialize(Map<String, Tag> map) {
		super.serialize(map);

		List<Tag> data = new ArrayList<Tag>();

		if (hasCustomEffects()) {
			for (PotionEffect effect : customEffects) {
				data.add(effect.serialize());
			}
		}

		map.put(NBT.ITEM.POTION.EFFECTS, new ListTag(NBT.ITEM.POTION.EFFECTS, CompoundTag.class, data));
	}

}
