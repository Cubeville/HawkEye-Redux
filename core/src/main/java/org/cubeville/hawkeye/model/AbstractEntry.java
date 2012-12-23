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

public abstract class AbstractEntry implements Entry {

	private final Action action;
	private final String player;
	private final Location location;
	private final Date time;

	public AbstractEntry(Action action, DatabaseEntry entry) {
		this.action = action;
		player = entry.getPlayer();
		location = entry.getLocation();
		time = entry.getTimestamp();
	}

	@Override
	public Action getAction() {
		return action;
	}

	@Override
	public String getPlayer() {
		return player;
	}

	@Override
	public Location getLocation() {
		return location;
	}

	@Override
	public Date getTime() {
		return time;
	}

	@Override
	public String getOutput() {
		// TODO Auto-generated method stub
		return null;
	}

}
