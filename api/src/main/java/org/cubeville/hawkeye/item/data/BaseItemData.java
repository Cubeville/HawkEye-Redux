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

import java.util.HashMap;
import java.util.Map;

import org.cubeville.hawkeye.model.ItemData;
import org.cubeville.lib.jnbt.ByteTag;
import org.cubeville.lib.jnbt.CompoundTag;
import org.cubeville.lib.jnbt.ShortTag;
import org.cubeville.lib.jnbt.Tag;

public class BaseItemData implements ItemData {

	private short itemId;
	private byte amount;
	private short durability;

	@Override
	public final Tag serialize() {
		Map<String, Tag> data = new HashMap<String, Tag>();
		data.put("id", new ShortTag("id", itemId));
		data.put("Count", new ByteTag("Count", amount));
		data.put("Damage", new ShortTag("Damage", durability));

		Map<String, Tag> tagData = new HashMap<String, Tag>();
		serialize(tagData);
		if (!tagData.isEmpty()) data.put("tag", new CompoundTag("tag", tagData));

		return new CompoundTag("", data);
	}

	public void serialize(Map<String, Tag> map) {

	}

}
