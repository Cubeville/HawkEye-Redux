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
	public boolean isPlayer() {
		return true;
	}

	@Override
	public boolean equals(Object obj) {
		if (!(obj instanceof Player)) return false;

		return getName().equals(((Player) obj).getName());
	}

}
