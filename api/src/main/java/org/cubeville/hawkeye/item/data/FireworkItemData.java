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
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.cubeville.hawkeye.NBT;
import org.cubeville.lib.jnbt.ByteTag;
import org.cubeville.lib.jnbt.CompoundTag;
import org.cubeville.lib.jnbt.ListTag;
import org.cubeville.lib.jnbt.Tag;

/**
 * ItemData implementation to store the effects of a firework
 */
public class FireworkItemData extends BaseItemData {

	private final byte power;
	private final List<FireworkExplosion> explosions;

	/**
	 * Deserialization constructor
	 *
	 * @param tag Tag to deserialize from
	 */
	public FireworkItemData(CompoundTag tag) {
		super(tag);

		explosions = new ArrayList<FireworkExplosion>();

		Map<String, Tag> data = tag.getValue();

		// Firework data is nested in the fireworks tag
		if (data.containsKey(NBT.ITEM.FIREWORKS)) {
			Map<String, Tag> fireworks = ((CompoundTag) data.get(NBT.ITEM.FIREWORKS)).getValue();

			if (fireworks.containsKey(NBT.ITEM.FIREWORK.POWER)) {
				power = ((ByteTag) fireworks.get(NBT.ITEM.FIREWORK.POWER)).getValue();
			} else {
				power = 0;
			}

			if (fireworks.containsKey(NBT.ITEM.FIREWORK.EFFECTS)) {
				List<Tag> list = ((ListTag) fireworks.get(NBT.ITEM.FIREWORK.EFFECTS)).getValue();
				for (Tag t : list) {
					FireworkExplosion explosion = new FireworkExplosion((CompoundTag) t);
					explosions.add(explosion);
				}
			}
		} else {
			power = 0;
		}
	}

	/**
	 * Gets whether or not this firework has any explosions attached to it
	 */
	public boolean hasExplosions() {
		return !(explosions == null || explosions.isEmpty());
	}

	/**
	 * Gets whether or not this firework has a power level
	 */
	public boolean hasPower() {
		return power != 0;
	}

	@Override
	protected void serialize(Map<String, Tag> map) {
		super.serialize(map);

		Map<String, Tag> fireworks;

		// Don't overwrite the firework tag if it already exists
		if (map.containsKey(NBT.ITEM.FIREWORKS)) {
			fireworks = ((CompoundTag) map.get(NBT.ITEM.FIREWORKS)).getValue();
		} else {
			fireworks = new HashMap<String, Tag>();
		}

		if (hasPower()) {
			fireworks.put(NBT.ITEM.FIREWORK.POWER, new ByteTag(NBT.ITEM.FIREWORK.POWER, power));
		}

		List<Tag> data = new ArrayList<Tag>();

		if (hasExplosions()) {
			for (FireworkExplosion explosion : explosions) {
				data.add(explosion.serialize());
			}
		}

		fireworks.put(NBT.ITEM.FIREWORK.EFFECTS, new ListTag(NBT.ITEM.FIREWORK.EFFECTS, CompoundTag.class, data));

		map.put(NBT.ITEM.FIREWORKS, new CompoundTag(NBT.ITEM.FIREWORKS, fireworks));
	}


}
