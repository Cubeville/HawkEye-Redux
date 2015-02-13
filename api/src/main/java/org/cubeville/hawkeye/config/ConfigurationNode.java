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

package org.cubeville.hawkeye.config;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Configuration implementation backed by a nested map
 */
public class ConfigurationNode implements Configuration {

	protected Map<String, Object> root;
	private boolean writeDefaults;

	public ConfigurationNode(Map<String, Object> root) {
		this(root, false);
	}

	public ConfigurationNode(Map<String, Object> root, boolean writeDefaults) {
		this.root = root;
		this.writeDefaults = writeDefaults;
	}

	/**
	 * Gets the configuration's backing map
	 *
	 * @return Map of keys and values
	 */
	public Map<String, Object> getRoot() {
		return root;
	}

	@Override
	public void clear() {
		root.clear();
	}

	@SuppressWarnings("unchecked")
	@Override
	public Object get(String node) {
		// Process dot notation
		String[] path = node.split("\\.");

		Object val = null;
		Map<String, Object> tmp = root;

		// Loop through map to get nested values
		for (int i = 0; i < path.length; i++) {
			val = tmp.get(path[i]);

			// Path doesn't exist
			if (val == null) return null;

			// Last piece of path
			if (i == path.length - 1) break;

			try {
				// Get next level of nested map
				tmp = (Map<String, Object>) val;
			} catch (ClassCastException ex) {
				// Nested map doesn't exist
				return null;
			}
		}

		return val;
	}

	@Override
	public Object get(String path, Object def) {
		Object val = get(path);
		if (val == null) {
			if (writeDefaults) set(path, def);
			val = def;
		}
		return val;
	}

	@SuppressWarnings("unchecked")
	@Override
	public void set(String node, Object value) {
		// Process dot notation
		String[] path = node.split("\\.");

		Map<String, Object> tmp = root;

		for (int i = 0; i < path.length; i++) {
			// Last level of nesting reached
			if (i == path.length - 1) {
				tmp.put(path[i], value);
				return;
			}

			Object val = tmp.get(path[i]);

			if (val == null || !(val instanceof Map)) {
				// Create a map if it isn't already there
				val = new HashMap<String, Object>();
				tmp.put(path[i], val);
			}

			tmp = (Map<String, Object>) val;
		}
	}

	@Override
	public String getString(String path) {
		Object val = get(path);

		return val == null ? null : val.toString();
	}

	@Override
	public String getString(String path, String def) {
		String val = getString(path);
		if (val == null) {
			if (writeDefaults) set(path, def);
			val = def;
		}
		return val;
	}

	@Override
	public Integer getInt(String path) {
		Object val = get(path);

		if (val instanceof Number) {
			return ((Number) val).intValue();
		} else {
			return null;
		}
	}

	@Override
	public int getInt(String path, int def) {
		Integer val = getInt(path);
		if (val == null) {
			if (writeDefaults) set(path, def);
			val = def;
		}
		return val;
	}

	@Override
	public Double getDouble(String path) {
		Object val = get(path);

		if (val instanceof Number) {
			return ((Number) val).doubleValue();
		} else {
			return null;
		}
	}

	@Override
	public double getDouble(String path, double def) {
		Double val = getDouble(path);
		if (val == null) {
			if (writeDefaults) set(path, def);
			val = def;
		}
		return val;
	}

	@Override
	public Boolean getBoolean(String path) {
		Object val = get(path);

		if (val instanceof Boolean) {
			return (Boolean) val;
		} else {
			return null;
		}
	}

	@Override
	public boolean getBoolean(String path, boolean def) {
		Boolean val = getBoolean(path);
		if (val == null) {
			if (writeDefaults) set(path, def);
			val = def;
		}
		return val;
	}

	@Override
	public List<String> getStringList(String path) {
		Object val = get(path);
		List<String> list = new ArrayList<String>();

		if (!(val instanceof List)) {
			return list;
		}

		@SuppressWarnings("unchecked")
		List<Object> raw = (List<Object>) val;

		for (Object obj : raw) {
			if (obj != null) list.add(obj.toString());
		}

		return list;
	}

	@Override
	public List<String> getStringList(String path, List<String> def) {
		List<String> val = getStringList(path);
		if (val == null) {
			if (writeDefaults) set(path, def);
			val = def;
		}
		return val;
	}

	@Override
	public List<Integer> getIntList(String path) {
		Object val = get(path);
		List<Integer> list = new ArrayList<Integer>();

		if (!(val instanceof List)) {
			return list;
		}

		@SuppressWarnings("unchecked")
		List<Object> raw = (List<Object>) val;

		for (Object obj : raw) {
			if (obj instanceof Number) list.add(((Number) obj).intValue());
		}

		return list;
	}

	@Override
	public List<Integer> getIntList(String path, List<Integer> def) {
		List<Integer> val = getIntList(path);
		if (val == null) {
			if (writeDefaults) set(path, def);
			val = def;
		}
		return val;
	}

	@Override
	public List<Double> getDoubleList(String path) {
		Object val = get(path);
		List<Double> list = new ArrayList<Double>();

		if (!(val instanceof List)) {
			return list;
		}

		@SuppressWarnings("unchecked")
		List<Object> raw = (List<Object>) val;

		for (Object obj : raw) {
			if (obj instanceof Number) list.add(((Number) obj).doubleValue());
		}

		return list;
	}

	@Override
	public List<Double> getDoubleList(String path, List<Double> def) {
		List<Double> val = getDoubleList(path);
		if (val == null) {
			if (writeDefaults) set(path, def);
			val = def;
		}
		return val;
	}

	@Override
	public boolean writeDefaults() {
		return writeDefaults;
	}

	@Override
	public void setWriteDefaults(boolean writeDefaults) {
		this.writeDefaults = writeDefaults;
	}

	@Override public Object get(Variable path) { return get(path.getPath()); }
	@Override public Object get(Variable path, Object def) { return get(path.getPath(), def); }

	@Override public void set(Variable path, Object value) { set(path.getPath(), value); }

	@Override public String getString(Variable path) { return getString(path.getPath()); }
	@Override public String getString(Variable path, String def) { return getString(path.getPath(), def); }

	@Override public Integer getInt(Variable path) { return getInt(path.getPath()); }
	@Override public int getInt(Variable path, int def) { return getInt(path.getPath(), def); }

	@Override public Double getDouble(Variable path) { return getDouble(path.getPath()); }
	@Override public double getDouble(Variable path, double def) { return getDouble(path.getPath(), def); }

	@Override public Boolean getBoolean(Variable path) { return getBoolean(path.getPath()); }
	@Override public boolean getBoolean(Variable path, boolean def) { return getBoolean(path.getPath(), def); }

	@Override public List<String> getStringList(Variable path) { return getStringList(path.getPath()); }
	@Override public List<String> getStringList(Variable path, List<String> def) { return getStringList(path.getPath(), def); }

	@Override public List<Integer> getIntList(Variable path) { return getIntList(path.getPath()); }
	@Override public List<Integer> getIntList(Variable path, List<Integer> def) { return getIntList(path.getPath(), def); }

	@Override public List<Double> getDoubleList(Variable path) { return getDoubleList(path.getPath()); }
	@Override public List<Double> getDoubleList(Variable path, List<Double> def) { return getDoubleList(path.getPath(), def); }

}
