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

package org.cubeville.hawkeye;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

import org.cubeville.hawkeye.model.BlockBreakEntry;
import org.cubeville.hawkeye.model.BlockExplosionEntry;
import org.cubeville.hawkeye.model.BlockModifyEntry;
import org.cubeville.hawkeye.model.BlockPlaceEntry;
import org.cubeville.hawkeye.model.ChatEntry;
import org.cubeville.hawkeye.model.CommandEntry;
import org.cubeville.hawkeye.model.DatabaseEntry;
import org.cubeville.hawkeye.model.Entry;
import org.cubeville.hawkeye.model.Modifiable;
import org.cubeville.hawkeye.model.PlayerInteractEntry;
import org.cubeville.hawkeye.model.SignEntry;

public enum DefaultActions implements Action {

	/**
	 * Block placed by player
	 */
	BLOCK_PLACE(BlockPlaceEntry.class),
	/**
	 * Block broken by player
	 */
	BLOCK_BREAK(BlockBreakEntry.class),
	/**
	 * Sign placed by player
	 */
	SIGN_PLACE(SignEntry.class),
	/**
	 * Sign broken by player
	 */
	SIGN_BREAK(SignEntry.class),
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
	PLAYER_CHAT(ChatEntry.class),
	/**
	 * Player executed command
	 */
	PLAYER_COMMAND(CommandEntry.class),
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
	INTERACT_DOOR(PlayerInteractEntry.class),
	/**
	 * Player interacted with lever
	 */
	INTERACT_LEVER(PlayerInteractEntry.class),
	/**
	 * Player (or arrow) interacted with button
	 */
	INTERACT_BUTTON(PlayerInteractEntry.class),
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
	EXPLOSION_CREEPER(BlockExplosionEntry.class),
	/**
	 * TNT explosion
	 */
	EXPLOSION_TNT(BlockExplosionEntry.class),
	/**
	 * Ghast explosion
	 */
	EXPLOSION_GHAST(BlockExplosionEntry.class),
	/**
	 * Miscellaneous explosion
	 */
	EXPLOSION_OTHER(BlockExplosionEntry.class),
	/**
	 * Block fade (i.e. snow melt, leaf decay, ice melt)
	 */
	BLOCK_FADE(BlockModifyEntry.class),
	/**
	 * Block form (i.e. snow/ice forming)
	 */
	BLOCK_FORM(BlockModifyEntry.class),
	/**
	 * Crop growth
	 */
	BLOCK_GROW(BlockModifyEntry.class),
	/**
	 * Block moved by piston
	 */
	BLOCK_PISTON,
	/**
	 * Block spread (i.e. mushroom growth/fire spread)
	 */
	BLOCK_SPREAD(BlockModifyEntry.class),
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
	STRUCTURE_BONEMEAL;

	/**
	 * Log entry data class
	 */
	private final Class<? extends Entry> entryClass;

	/**
	 * Data class constructor
	 */
	private Constructor<? extends Entry> constructor;

	/**
	 * Default fallback until entry classes are implemented
	 */
	private DefaultActions() {
		this(Entry.class);
	}

	private DefaultActions(Class<? extends Entry> entryClass) {
		this.entryClass = entryClass;
		constructor = null;

		try {
			constructor = entryClass.getConstructor(DatabaseEntry.class);
			constructor.setAccessible(true);
		} catch (SecurityException e) {
			HawkEye.getLogger().warning("Could not get DatabaseEntry constructor for " + entryClass.getCanonicalName());
			e.printStackTrace();
		} catch (NoSuchMethodException e) {
			HawkEye.getLogger().warning("Could not get DatabaseEntry constructor for " + entryClass.getCanonicalName());
			e.printStackTrace();
		}
	}

	@Override
	public String getName() {
		return toString().toLowerCase().replace("_", "-");
	}

	@Override
	public String getDisplayName() {
		return toString().toLowerCase().replace("_", " ");
	}

	@Override
	public Class<? extends Entry> getEntryClass() {
		return entryClass;
	}

	@Override
	public Entry getEntry(DatabaseEntry entry) {
		if (constructor == null) return null;

		try {
			return constructor.newInstance(entry);
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		} catch (InstantiationException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		}

		return null;
	}

	@Override
	public boolean canModify() {
		return Modifiable.class.isAssignableFrom(entryClass);
	}

}
