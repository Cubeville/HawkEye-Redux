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

import org.cubeville.hawkeye.model.Entry;

public interface Consumer extends Runnable {

	/**
	 * Adds an entry to the queue
	 *
	 * @param entry Entry to add
	 */
	void addEntry(Entry entry);

	/**
	 * Gets the number of queued entries
	 *
	 * @return Entry queue size
	 */
	int size();

	/**
	 * Dumps queued entries to a file
	 *
	 * Should be used if entries cannot be written to the database for some
	 * reason (i.e. server shutdown)
	 */
	void dumpToFile();

}
