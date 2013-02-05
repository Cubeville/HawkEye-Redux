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

package org.cubeville.hawkeye;

import java.util.LinkedHashMap;
import java.util.Map;

import org.cubeville.hawkeye.model.NBTSerializable;

public class SimpleNBTManager implements NBTManager {

	private final DataCache cache;

	public SimpleNBTManager() {
		cache = new DataCache(100);
	}

	@Override
	public NBTSerializable getData(int id) {
		if (cache.containsKey(id)) {
			return cache.get(id);
		} else {
			NBTSerializable data = null;
			// TODO Get from database

			cache.put(id, data);
			return data;
		}
	}

	private class DataCache extends LinkedHashMap<Integer, NBTSerializable> {

		private static final long serialVersionUID = -9188497816333315862L;

		private final int limit;

		public DataCache(int limit) {
			super(limit, 0.75f, true);
			this.limit = limit;
		}

		@Override
		protected boolean removeEldestEntry(Map.Entry<Integer, NBTSerializable> entry) {
			return size() > limit;
		}

	}

}
