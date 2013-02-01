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

package org.cubeville.hawkeye.entity;

import org.cubeville.hawkeye.command.CommandSender;
import org.cubeville.hawkeye.location.Location;
import org.cubeville.hawkeye.location.World;

public abstract class Player implements CommandSender {

	/**
	 * Gets this player's position
	 *
	 * @return Player's position
	 */
	public abstract Location getPosition();

	/**
	 * Gets this player's world
	 *
	 * @return Player's world
	 */
	public abstract World getWorld();

	/**
	 * {@inheritDoc}
	 */
	@Override
	public final boolean isPlayer() {
		return true;
	}

}
