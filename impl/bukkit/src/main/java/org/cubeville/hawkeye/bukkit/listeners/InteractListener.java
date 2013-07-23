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

package org.cubeville.hawkeye.bukkit.listeners;

import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.event.player.PlayerInteractEvent;

import org.cubeville.hawkeye.Action;
import org.cubeville.hawkeye.DefaultActions;
import org.cubeville.hawkeye.bukkit.Convert;
import org.cubeville.hawkeye.bukkit.HawkEvent;
import org.cubeville.hawkeye.bukkit.HawkEyeListener;
import org.cubeville.hawkeye.bukkit.HawkEyePlugin;
import org.cubeville.hawkeye.model.PlayerInteractEntry;

public class InteractListener extends HawkEyeListener {

	public InteractListener(HawkEyePlugin plugin) {
		super(plugin);
	}

	@HawkEvent(action = {DefaultActions.INTERACT_BUTTON, DefaultActions.INTERACT_DOOR, DefaultActions.INTERACT_LEVER})
	public void onPlayerInteract(PlayerInteractEvent event) {
		if (event.getAction() != org.bukkit.event.block.Action.RIGHT_CLICK_BLOCK) return;

		Action action;

		switch (event.getClickedBlock().getType()) {
			case STONE_BUTTON:
			case WOOD_BUTTON:
				action = DefaultActions.INTERACT_BUTTON;
				break;
			case WOODEN_DOOR:
			case TRAP_DOOR:
			case FENCE_GATE:
				action = DefaultActions.INTERACT_DOOR;
				break;
			case LEVER:
				action = DefaultActions.INTERACT_LEVER;
				break;
			default:
				return;
		}

		Player player = event.getPlayer();
		Location loc = event.getClickedBlock().getLocation();

		log(new PlayerInteractEntry(action, player.getName(), Convert.location(loc)));
	}

}
