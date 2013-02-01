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
import org.cubeville.hawkeye.item.PotionEffects;
import org.cubeville.hawkeye.model.NBTSerializable;
import org.cubeville.lib.jnbt.ByteTag;
import org.cubeville.lib.jnbt.CompoundTag;
import org.cubeville.lib.jnbt.IntTag;
import org.cubeville.lib.jnbt.Tag;

public class PotionEffect implements NBTSerializable {

	private final byte effectId;
	private final byte amplifier;
	private final int duration;
	private final boolean ambient;

	/**
	 * Deserialization constructor
	 *
	 * @param tag Tag to deserialize from
	 */
	public PotionEffect(CompoundTag tag) {
		Map<String, Tag> data = tag.getValue();
		effectId = ((ByteTag) data.get(NBT.POTION_EFFECT.ID)).getValue();
		amplifier = ((ByteTag) data.get(NBT.POTION_EFFECT.LEVEL)).getValue();
		duration = ((IntTag) data.get(NBT.POTION_EFFECT.DURATION)).getValue();
		ambient = (((ByteTag) data.get(NBT.POTION_EFFECT.AMBIENT)).getValue() == 1);
	}

	/**
	 * Gets the type of effect this potion effect has
	 */
	public PotionEffects getEffect() {
		return PotionEffects.getById(effectId);
	}

	/**
	 * Gets the id of the type of effect this potion effect has
	 */
	public byte getEffectId() {
		return effectId;
	}

	/**
	 * Gets this potion effect's level amplifier
	 */
	public byte getAmplifier() {
		return amplifier;
	}

	/**
	 * Gets this potion effect's duration (in game ticks)
	 */
	public int getDuration() {
		return duration;
	}

	/**
	 * Gets whether or not this potion effect is ambient
	 *
	 * Ambient potion effects have a reduced amount of visual particles around
	 * the player.
	 */
	public boolean isAmbient() {
		return ambient;
	}

	@Override
	public CompoundTag serialize() {
		Map<String, Tag> data = new HashMap<String, Tag>();
		data.put(NBT.POTION_EFFECT.ID, new ByteTag(NBT.POTION_EFFECT.ID, effectId));
		data.put(NBT.POTION_EFFECT.LEVEL, new ByteTag(NBT.POTION_EFFECT.LEVEL, amplifier));
		data.put(NBT.POTION_EFFECT.DURATION, new IntTag(NBT.POTION_EFFECT.DURATION, duration));
		data.put(NBT.POTION_EFFECT.AMBIENT, new ByteTag(NBT.POTION_EFFECT.AMBIENT, (byte) (ambient ? 1 : 0)));
		return new CompoundTag("", data);
	}

}
