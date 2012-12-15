/*
 * HawkEye
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

package org.cubeville.hawkeye.command;

public interface CommandSender {

	/**
	 * Gets this command sender's name
	 *
	 * @return This command sender's name
	 */
	String getName();

	/**
	 * Gets this command sender's display name
	 *
	 * @return This command sender's display name
	 */
	String getDisplayName();

	/**
	 * Sends a message to this command sender
	 *
	 * @param message Message(s) to send
	 */
	void sendMessage(String... message);

	/**
	 * Checks if this command sender has a permission node
	 *
	 * @param permission Permission node to check for
	 * @return True if this command sender has permission, false if not
	 */
	boolean hasPermission(String permission);

	/**
	 * Checks if this command sender is a player
	 *
	 * @return True if command sender is a player, false if not
	 */
	boolean isPlayer();

}
