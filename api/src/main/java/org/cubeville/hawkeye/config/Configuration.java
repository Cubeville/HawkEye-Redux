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

import java.util.List;

/**
 * Basic configuration interface
 */
public interface Configuration {

	/**
	 * Clears all saved config data
	 */
	void clear();

	/**
	 * Gets an object at the specified node
	 *
	 * @param node Path to node (in dot notation)
	 * @return Value at the node, or null if it doesn't exist
	 */
	Object get(String path);

	/**
	 * Sets a value at the specified node
	 *
	 * @param node Path to node (in dot notation)
	 * @param value Value to set at node
	 */
	void set(String path, Object value);

	/**
	 * Gets a string at the specified node
	 *
	 * @param path Path to node
	 * @return String or null if there is no value at path
	 */
	String getString(String path);

	/**
	 * Gets a string at the specified node
	 *
	 * @param path Path to node
	 * @param def Default value to return if string is null
	 * @return String
	 */
	String getString(String path, String def);

	/**
	 * Gets an integer at the specified node
	 *
	 * @param path Path to node
	 * @return Integer or null
	 */
	Integer getInt(String path);

	/**
	 * Gets an integer at the specified node
	 *
	 * @param path Path to node
	 * @param def Default value to return if integer is null
	 * @return int
	 */
	int getInt(String path, int def);

	/**
	 * Gets a double at the specified node
	 *
	 * @param path Path to node
	 * @return Double or null
	 */
	Double getDouble(String path);

	/**
	 * Gets a double at the specified node
	 *
	 * @param path Path to node
	 * @param def Default value to return if double is null
	 * @return double
	 */
	double getDouble(String path, double def);

	/**
	 * Gets a boolean at the specified node
	 *
	 * @param path Path to node
	 * @return Boolean or null
	 */
	Boolean getBoolean(String path);

	/**
	 * Gets a boolean at the specified node
	 *
	 * @param path Path to node
	 * @param def Default value to return if boolean is null
	 * @return boolean
	 */
	boolean getBoolean(String path, boolean def);

	/**
	 * Gets a string list at the specified node
	 *
	 * @param path Path to node
	 * @return String list
	 */
	List<String> getStringList(String path);

	/**
	 * Gets a string list at the specified node
	 *
	 * @param path Path to node
	 * @param def Default value to return if list is null
	 * @return String list
	 */
	List<String> getStringList(String path, List<String> def);

	/**
	 * Gets an integer list at the specified node
	 *
	 * @param path Path to node
	 * @return Integer list
	 */
	List<Integer> getIntList(String path);

	/**
	 * Gets an integer list at the specified node
	 *
	 * @param path Path to node
	 * @param def Default value to return if list is null
	 * @return Integer list
	 */
	List<Integer> getIntList(String path, List<Integer> def);

	/**
	 * Gets a double list at the specified node
	 *
	 * @param path Path to node
	 * @return Double list
	 */
	List<Double> getDoubleList(String path);

	/**
	 * Gets a double list at the specified node
	 *
	 * @param path Path to node
	 * @param def Default value to return if list is null
	 * @return Double list
	 */
	List<Double> getDoubleList(String path, List<Double> def);

	/**
	 * Whether or not to save the default values for nodes that are null
	 *
	 * @return True if defaults are written, false if not
	 */
	boolean writeDefaults();

	/**
	 * Sets whether or not to save the default values
	 *
	 * @param writeDefaults Whether or not to write defaults
	 */
	void setWriteDefaults(boolean writeDefaults);

}
