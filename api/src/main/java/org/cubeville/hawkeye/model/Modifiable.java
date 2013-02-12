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

package org.cubeville.hawkeye.model;

import org.cubeville.hawkeye.entity.Player;

/**
 * Represents an entry that can be modified, i.e. rolled back or rebuilt
 */
public interface Modifiable {

	/**
	 * Performs a rollback on the data in this entry
	 *
	 * @return Whether or not the entry was rolled back
	 */
	boolean rollback();

	/**
	 * Performs a local rollback on the data in this entry
	 *
	 * Local rollbacks are only visible to the player specified, for use in a
	 * rollback preview
	 *
	 * @param player Player to send the local rollback to
	 * @return Whether or not the entry was rolled back
	 */
	boolean localRollback(Player player);

	/**
	 * Performs a rebuilt on the data in this entry (opposite of rollback)
	 *
	 * @return Whether or not the entry was rebuilt
	 */
	boolean rebuild();

	/**
	 * Performs a local rebuild on the data in this entry
	 *
	 * Local rebuild are only visible to the player specified, for use in a
	 * rebuild preview
	 *
	 * @param player Player to send the local rollback to
	 * @return Whether or not the entry was rebuilt
	 */
	boolean localRebuild(Player player);

}
