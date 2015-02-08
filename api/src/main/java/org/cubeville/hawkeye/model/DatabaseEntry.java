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
import java.util.UUID;

import org.cubeville.hawkeye.Action;
import org.cubeville.hawkeye.HawkEye;
import org.cubeville.hawkeye.location.Location;
import org.cubeville.hawkeye.location.World;

public class DatabaseEntry {

	private final int id;
	private final UUID player;
	private final Action action;
	private final Timestamp time;
	private final World world;
	private final Location location;
	private final String data;
	private final byte[] nbt;

	public DatabaseEntry(int id, int player, String action, Timestamp time, int worldId, double x, double y, double z, String data, byte[] nbt) {
		this.id = id;
		this.player = HawkEye.getDataManager().getPlayer(player);
		this.action = HawkEye.getDataManager().getAction(action);
		this.time = time;
		world = HawkEye.getDataManager().getWorld(worldId);
		location = world.getLocationAt(x, y, z);
		this.data = data;
		this.nbt = nbt;
	}

	/**
	 * Gets this entry's id in the database
	 *
	 * @return Entry id
	 */
	public int getId() {
		return id;
	}

	/**
	 * Gets the player who performed this action
	 *
	 * @return Player name
	 */
	public UUID getPlayer() {
		return player;
	}

	/**
	 * Gets the action that was performed
	 *
	 * @return Action
	 */
	public Action getAction() {
		return action;
	}

	/**
	 * Gets the time this action was performed
	 *
	 * @return Timestamp
	 */
	public Timestamp getTimestamp() {
		return time;
	}

	/**
	 * Gets the world this action was performed in
	 *
	 * @return World
	 */
	public World getWorld() {
		return world;
	}

	/**
	 * Gets the location this action was performed at
	 *
	 * @return Location
	 */
	public Location getLocation() {
		return location;
	}

	/**
	 * Gets the data associated with this action
	 *
	 * @return Action data
	 */
	public String getData() {
		return data;
	}

	/**
	 * Returns the byte array stored in this entry's nbt column
	 *
	 * @return NBT data byte array
	 */
	public byte[] getNbt() {
		return nbt;
	}

	/**
	 * Attempts to convert this object to its action's entry implementation
	 */
	public Entry toEntry() {
		return action.getEntry(this);
	}

}
