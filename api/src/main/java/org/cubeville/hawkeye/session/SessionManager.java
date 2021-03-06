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

package org.cubeville.hawkeye.session;

import org.cubeville.hawkeye.command.CommandSender;
import org.cubeville.hawkeye.entity.Player;

public interface SessionManager {

	/**
	 * Gets a user's session
	 *
	 * @param owner Session owner
	 * @return Owner's session
	 */
	Session getSession(CommandSender owner);

	/**
	 * Called when a user disconnects from the server
	 *
	 * @param player Name of the player that disconnected
	 */
	void handleDisconnect(String player);

	/**
	 * Called when a player (re)connects to the server
	 *
	 * @param player The player who connected
	 */
	void handleReconnect(Player player);

}
