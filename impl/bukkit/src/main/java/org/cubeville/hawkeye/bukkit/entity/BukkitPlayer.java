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

package org.cubeville.hawkeye.bukkit.entity;

import java.util.UUID;

import org.cubeville.hawkeye.bukkit.Convert;
import org.cubeville.hawkeye.entity.Player;
import org.cubeville.hawkeye.location.Location;
import org.cubeville.hawkeye.location.World;
import org.cubeville.util.Chat;

public class BukkitPlayer extends Player {

	private final org.bukkit.entity.Player player;

	public BukkitPlayer(org.bukkit.entity.Player player) {
		this.player = player;
	}

	public org.bukkit.entity.Player getBukkitPlayer() {
		return player;
	}

	@Override
	public void sendMessage(String... message) {
		for (String s : message) player.sendMessage(Chat.format(s));
	}

	@Override
	public boolean hasPermission(String permission) {
		return player.hasPermission(permission);
	}

	@Override
	public UUID getUUID() {
		return player.getUniqueId();
	}

	@Override
	public String getName() {
		return player.getName();
	}

	@Override
	public String getDisplayName() {
		return player.getDisplayName();
	}

	@Override
	public Location getPosition() {
		return Convert.location(player.getLocation());
	}

	@Override
	public World getWorld() {
		return Convert.world(player.getWorld());
	}

}
