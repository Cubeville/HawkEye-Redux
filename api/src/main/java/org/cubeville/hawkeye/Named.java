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

public interface Named {

	/**
	 * Gets this object's name
	 *
	 * The object name is immutable and thus should be used for operations such
	 * as persistent data storage.
	 *
	 * @return This object's name
	 */
	String getName();

	/**
	 * Gets this object's display name
	 *
	 * The display name is mutable and thus cannot be relied on for fetching
	 * data. The display name should be used in most text output cases.
	 *
	 * @return This object's display name
	 */
	String getDisplayName();

}
