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

import java.io.File;

public interface FileConfiguration extends Configuration {

	/**
	 * Loads the configuration
	 */
	void load();

	/**
	 * Loads the configuration from a specified file
	 *
	 * @param file File to load from
	 */
	void load(File file);

	/**
	 * Saves the configuration
	 */
	void save();

	/**
	 * Saves the configuration to a specified file
	 *
	 * @param file File to save to
	 */
	void save(File file);

	/**
	 * Reloads the configuration from file
	 */
	void reload();

}
