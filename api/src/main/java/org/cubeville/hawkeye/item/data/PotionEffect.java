/*
 * HawkEye Redux
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

import org.cubeville.hawkeye.item.PotionEffects;
import org.cubeville.hawkeye.model.NBTSerializable;
import org.cubeville.lib.jnbt.Tag;

public class PotionEffect implements NBTSerializable {

	private byte effectId;
	private byte amplifier;
	private int duration;
	private boolean splash;
	private boolean ambient;

	/**
	 * Deserialization constructor
	 *
	 * @param data Tag to deserialize from
	 */
	public PotionEffect(Tag data) {

	}

	public PotionEffects getEffect() {
		return PotionEffects.getById(effectId);
	}

	public byte getEffectId() {
		return effectId;
	}

	public byte getAmplifier() {
		return amplifier;
	}

	public int getDuration() {
		return duration;
	}

	public boolean isSplash() {
		return splash;
	}

	public boolean isAmbient() {
		return ambient;
	}

	@Override
	public Tag serialize() {
		// TODO Auto-generated method stub
		return null;
	}

}
