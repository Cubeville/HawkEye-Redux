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

package org.cubeville.hawkeye;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

import org.cubeville.hawkeye.model.BlockBreakEntry;
import org.cubeville.hawkeye.model.BlockBucketEntry;
import org.cubeville.hawkeye.model.BlockEntityEntry;
import org.cubeville.hawkeye.model.BlockExplosionEntry;
import org.cubeville.hawkeye.model.BlockFireEntry;
import org.cubeville.hawkeye.model.BlockGrowEntry;
import org.cubeville.hawkeye.model.BlockModifyEntry;
import org.cubeville.hawkeye.model.BlockPistonEntry;
import org.cubeville.hawkeye.model.BlockPlaceEntry;
import org.cubeville.hawkeye.model.ChatEntry;
import org.cubeville.hawkeye.model.CommandEntry;
import org.cubeville.hawkeye.model.DatabaseEntry;
import org.cubeville.hawkeye.model.Entry;
import org.cubeville.hawkeye.model.HangingBreakEntry;
import org.cubeville.hawkeye.model.HangingPlaceEntry;
import org.cubeville.hawkeye.model.Modifiable;
import org.cubeville.hawkeye.model.PlayerDeathEntry;
import org.cubeville.hawkeye.model.PlayerInteractEntry;
import org.cubeville.hawkeye.model.PlayerItemEntry;
import org.cubeville.hawkeye.model.PlayerKillEntry;
import org.cubeville.hawkeye.model.PlayerLogEntry;
import org.cubeville.hawkeye.model.PlayerPvpDeathEntry;

public enum DefaultActions implements Action {

	/**
	 * Block placed by player
	 */
	BLOCK_PLACE(BlockPlaceEntry.class, "place"),
	/**
	 * Block broken by player
	 */
	BLOCK_BREAK(BlockBreakEntry.class, "break"),
	/**
	 * Hanging entity (painting, item frame) broken
	 */
	HANGING_BREAK(HangingBreakEntry.class, "painting-break"),
	/**
	 * Hanging entity (painting, item frame) placed
	 */
	HANGING_PLACE(HangingPlaceEntry.class, "painting-place"),
	/**
	 * Player login
	 */
	PLAYER_JOIN(PlayerLogEntry.class, "login", "join"),
	/**
	 * Player logout
	 */
	PLAYER_QUIT(PlayerLogEntry.class, "logout", "quit"),
	/**
	 * Player chat
	 */
	PLAYER_CHAT(ChatEntry.class, "chat"),
	/**
	 * Player executed command
	 */
	PLAYER_COMMAND(CommandEntry.class, "command"),
	/**
	 * Player dropped item
	 */
	PLAYER_ITEM_DROP(PlayerItemEntry.class, "item-drop"),
	/**
	 * Player picked up item
	 */
	PLAYER_ITEM_PICKUP(PlayerItemEntry.class, "item-pickup"),
	/**
	 * Player died in PvP
	 */
	PLAYER_DEATH_PVP(PlayerPvpDeathEntry.class, "pvp-death"),
	/**
	 * Player died from a non-pvp cause
	 */
	PLAYER_DEATH_OTHER(PlayerDeathEntry.class, "death"),
	/**
	 * Items dropped on death
	 */
	PLAYER_DEATH_ITEMS(PlayerItemEntry.class, "item-death"), // TODO
	/**
	 * Player killed a mob
	 */
	PLAYER_MOB_KILL(PlayerKillEntry.class, "kill", "mob-kill", "entity-kill"),
	/**
	 * Player used lava bucket
	 */
	LAVA_BUCKET(BlockBucketEntry.class, "lava-place"), // TODO
	/**
	 * Player used water bucket
	 */
	WATER_BUCKET(BlockBucketEntry.class, "water-place"), // TODO
	/**
	 * Player used flint & steel
	 */
	FLINT_AND_STEEL(BlockFireEntry.class, "lighter", "flint-steel"), // TODO
	/**
	 * Player interacted with door
	 */
	INTERACT_DOOR(PlayerInteractEntry.class, "door"), // TODO
	/**
	 * Player interacted with lever
	 */
	INTERACT_LEVER(PlayerInteractEntry.class, "lever"), // TODO
	/**
	 * Player (or arrow) interacted with button
	 */
	INTERACT_BUTTON(PlayerInteractEntry.class, "button"), // TODO
	/**
	 * Player ate cake
	 */
	CAKE_EAT(PlayerInteractEntry.class, "eat-cake"), // TODO
	/**
	 * Putting item in an inventory (chest, furnace, dispenser, etc)
	 */
	INVENTORY_ADD(PlayerItemEntry.class, "item-deposit"), // TODO
	/**
	 * Taking an item from an inventory
	 */
	INVENTORY_TAKE(PlayerItemEntry.class, "item-take"), // TODO
	/**
	 * Creeper explosion
	 */
	EXPLOSION_CREEPER(BlockExplosionEntry.class, "creeper", "creeper-explode"), // TODO
	/**
	 * TNT explosion
	 */
	EXPLOSION_TNT(BlockExplosionEntry.class, "tnt", "tnt-explode"), // TODO
	/**
	 * Ghast explosion
	 */
	EXPLOSION_GHAST(BlockExplosionEntry.class, "ghast", "ghast-explode"), // TODO
	/**
	 * Miscellaneous explosion
	 */
	EXPLOSION_OTHER(BlockExplosionEntry.class, "explosion"), // TODO
	/**
	 * Block fade (i.e. snow melt, leaf decay, ice melt)
	 */
	BLOCK_FADE(BlockModifyEntry.class, "melt", "decay"), // TODO
	/**
	 * Block form (i.e. snow/ice forming)
	 */
	BLOCK_FORM(BlockModifyEntry.class, "form"), // TODO
	/**
	 * Crop growth
	 */
	BLOCK_GROW(BlockModifyEntry.class, "grow"), // TODO
	/**
	 * Block moved by piston
	 */
	BLOCK_PISTON(BlockPistonEntry.class, "piston"), // TODO
	/**
	 * Block spread (i.e. mushroom growth/fire spread)
	 */
	BLOCK_SPREAD(BlockModifyEntry.class, "spread"), // TODO
	/**
	 * Block changed by entity (i.e. enderman moving blocks, zombie breaking
	 * doors, snow golems leaving snow, wither, etc)
	 */
	ENTITY_BLOCK_MODIFY(BlockEntityEntry.class, "entity-modify", "enderman"), // TODO
	/**
	 * Structure grown (tree/mushroom) naturally
	 */
	STRUCTURE_GROW(BlockGrowEntry.class, "grow"), // TODO
	/**
	 * Structure grown by bonemeal
	 */
	STRUCTURE_BONEMEAL(BlockGrowEntry.class, "bonemeal"); // TODO

	/**
	 * Log entry data class
	 */
	private final Class<? extends Entry> entryClass;

	/**
	 * Action aliases
	 */
	private final String[] aliases;

	/**
	 * Data class constructor
	 */
	private final Constructor<? extends Entry> constructor;

	/**
	 * Default fallback until entry classes are implemented
	 */
	private DefaultActions() {
		this(Entry.class);
	}

	private DefaultActions(Class<? extends Entry> entryClass) {
		this(entryClass, new String[0]);
	}

	private DefaultActions(Class<? extends Entry> entryClass, String... aliases) {
		this.entryClass = entryClass;
		this.aliases = aliases;

		Constructor<? extends Entry> tmp = null;

		try {
			tmp = entryClass.getConstructor(DatabaseEntry.class);
			tmp.setAccessible(true);
		} catch (SecurityException e) {
			HawkEye.getLogger().error("Could not get DatabaseEntry constructor for " + entryClass.getCanonicalName(), e);
		} catch (NoSuchMethodException e) {
			HawkEye.getLogger().error("Could not get DatabaseEntry constructor for " + entryClass.getCanonicalName(), e);
		}

		constructor = tmp;
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
		} catch (IllegalArgumentException ignore) {
		} catch (InstantiationException ignore) {
		} catch (IllegalAccessException ignore) {
		} catch (InvocationTargetException e) {
			HawkEye.getLogger().error("Error reconstructing entry type '" + getDisplayName() + "'", e);
		}

		return null;
	}

	@Override
	public boolean canModify() {
		return Modifiable.class.isAssignableFrom(entryClass);
	}

	@Override
	public boolean isLogged() {
		return HawkEye.getConfig().isLogging(this);
	}

	public String[] getAliases() {
		return aliases;
	}


}
