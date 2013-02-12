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

import java.sql.Timestamp;

import org.cubeville.hawkeye.Action;
import org.cubeville.hawkeye.location.Location;

public abstract class AbstractEntry implements Entry {

	/**
	 * String used to delimit multiple parts of an entry in the database
	 */
	protected static final String DELIMITER = "|@|";
	protected static final String DELIMITER_SPLITTER = "\\|@\\|";

	private final Action action;
	private final String player;
	private final Location location;
	private final Timestamp time;
	private final byte[] nbt;

	public AbstractEntry(Action action, DatabaseEntry entry) {
		this.action = action;
		player = entry.getPlayer();
		location = entry.getLocation();
		time = entry.getTimestamp();
		nbt = entry.getNbt();
	}

	public AbstractEntry(Action action, String player, Location location) {
		this.action = action;
		this.player = player;
		this.location = location;
		time = new Timestamp(System.currentTimeMillis());
		nbt = new byte[0];
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
	public Timestamp getTime() {
		return time;
	}

	@Override
	public byte[] getNbt() {
		return nbt;
	}

	@Override
	public String getOutput() {
		// TODO Auto-generated method stub
		return null;
	}

}
