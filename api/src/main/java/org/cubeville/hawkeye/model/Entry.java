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

package org.cubeville.hawkeye.model;

import java.util.Date;

import org.cubeville.hawkeye.Action;
import org.cubeville.hawkeye.location.Location;

/**
 * Represents a log entry that can be stored in the database
 *
 * To be recognized as a valid entry, classes implementing this interface
 * must also implement a constructor that accepts a {@link DatabaseEntry}.
 *
 * The entry should store its important data via {@link #getData()} and can
 * recover this data in its constructor via {@link DatabaseEntry#getData()}.
 */
public interface Entry {

	/**
	 * Gets the type of action stored in this entry
	 *
	 * @return Entry action
	 */
	Action getAction();

	/**
	 * Gets the player who performed this action
	 *
	 * @return Player's name
	 */
	String getPlayer();

	/**
	 * Gets the location this action was performed at
	 *
	 * @return Location action occurred at
	 */
	Location getLocation();

	/**
	 * Gets the time this action was performed at
	 *
	 * @return Time action occurred at
	 */
	Date getTime();

	/**
	 * Gets a string that can be into the database's data column
	 *
	 * The entry should be able to restore all the data it needs when it
	 * receives this same string back through {@link DatabaseEntry#getData()}.
	 *
	 * The data column in the default database can hold 4096 bytes of data.
	 *
	 * @return String to put in the database `data` column
	 */
	String getData();

	/**
	 * Returns a formatted string to be displayed in search results
	 *
	 * @return Formatted string with important entry data
	 */
	String getOutput();

}
