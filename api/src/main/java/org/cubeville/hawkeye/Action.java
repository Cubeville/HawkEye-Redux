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

import org.cubeville.hawkeye.model.DatabaseEntry;
import org.cubeville.hawkeye.model.Entry;

public interface Action extends Named {

	/**
	 * Returns the entry class this action uses to store data
	 *
	 * @return Class used by this action to store database entries
	 */
	Class<? extends Entry> getEntryClass();

	/**
	 * Constructs an entry representing this action based on a database entry
	 *
	 * @param entry Database entry
	 * @return Entry implementation representing this action
	 */
	Entry getEntry(DatabaseEntry entry);

	/**
	 * Gets whether or not this entry can be modified (i.e. rolled back)
	 *
	 * @return True if the entry is able to be modified
	 */
	boolean canModify();

}
