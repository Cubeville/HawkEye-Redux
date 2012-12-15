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

package org.cubeville.hawkeye;

import org.cubeville.hawkeye.model.Entry;
import org.cubeville.hawkeye.model.Modifiable;

public enum Action {

	/**
	 * Block placed by player
	 */
	BLOCK_PLACE,
	/**
	 * Block broken by player
	 */
	BLOCK_BREAK,
	/**
	 * Sign placed by player
	 */
	SIGN_PLACE,
	/**
	 * Sign broken by player
	 */
	SIGN_BREAK,
	/**
	 * Hanging entity (painting, item frame) broken
	 */
	HANGING_BREAK,
	/**
	 * Hanging entity (painting, item frame) placed
	 */
	HANGING_PLACE,
	/**
	 * Player login
	 */
	PLAYER_JOIN,
	/**
	 * Player logout
	 */
	PLAYER_QUIT,
	/**
	 * Player chat
	 */
	PLAYER_CHAT,
	/**
	 * Player executed command
	 */
	PLAYER_COMMAND,
	/**
	 * Player dropped item
	 */
	PLAYER_ITEM_DROP,
	/**
	 * Player picked up item
	 */
	PLAYER_ITEM_PICKUP,
	/**
	 * Player died in PvP
	 */
	PLAYER_DEATH_PVP,
	/**
	 * Player died from a non-pvp cause
	 */
	PLAYER_DEATH_OTHER,
	/**
	 * Items dropped on death
	 */
	PLAYER_DEATH_ITEMS,
	/**
	 * Player killed a mob
	 */
	PLAYER_MOB_KILL,
	/**
	 * Player used lava bucket
	 */
	LAVA_BUCKET,
	/**
	 * Player used water bucket
	 */
	WATER_BUCKET,
	/**
	 * Player used flint & steel
	 */
	FLINT_AND_STEEL,
	/**
	 * Player interacted with door
	 */
	INTERACT_DOOR,
	/**
	 * Player interacted with lever
	 */
	INTERACT_LEVER,
	/**
	 * Player (or arrow) interacted with button
	 */
	INTERACT_BUTTON,
	/**
	 * Player ate cake
	 */
	CAKE_EAT,
	/**
	 * Putting item in an inventory (chest, furnace, dispenser, etc)
	 */
	INVENTORY_ADD,
	/**
	 * Taking an item from an inventory
	 */
	INVENTORY_TAKE,
	/**
	 * Creeper explosion
	 */
	EXPLOSION_CREEPER,
	/**
	 * TNT explosion
	 */
	EXPLOSION_TNT,
	/**
	 * Ghast explosion
	 */
	EXPLOSION_GHAST,
	/**
	 * Miscellaneous explosion
	 */
	EXPLOSION_OTHER,
	/**
	 * Block fade (i.e. snow melt, leaf decay, ice melt)
	 */
	BLOCK_FADE,
	/**
	 * Block form (i.e. snow/ice forming)
	 */
	BLOCK_FORM,
	/**
	 * Crop growth
	 */
	BLOCK_GROW,
	/**
	 * Block moved by piston
	 */
	BLOCK_PISTON,
	/**
	 * Block spread (i.e. mushroom growth/fire spread)
	 */
	BLOCK_SPREAD,
	/**
	 * Block changed by entity (i.e. enderman moving blocks, zombie breaking
	 * doors, snow golems leaving snow, wither, etc)
	 */
	ENTITY_BLOCK_MODIFY,
	/**
	 * Structure grown (tree/mushroom) naturally
	 */
	STRUCTURE_GROW,
	/**
	 * Structure grown by bonemeal
	 */
	STRUCTURE_BONEMEAL,
	/**
	 * Custom action for other plugins to log custom data under
	 */
	CUSTOM;

	/**
	 * Log entry data class
	 */
	private final Class<? extends Entry> dataClass;

	/**
	 * Default fallback until entry classes are implemented
	 */
	private Action() {
		this(Entry.class);
	}

	private Action(Class<? extends Entry> dataClass) {
		this.dataClass = dataClass;
	}

	/**
	 * Gets the simple name for this action for use in config and database
	 *
	 * @return Simple name for the action
	 */
	public String getSimpleName() {
		return toString().toLowerCase().replace("_", "-");
	}

	/**
	 * Gets whether or not this entry can be modified (i.e. rolled back)
	 *
	 * @return True if the entry is able to be modified
	 */
	public boolean canModify() {
		return Modifiable.class.isAssignableFrom(dataClass);
	}

}
