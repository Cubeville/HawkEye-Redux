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
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.entity.Projectile;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.EntityDeathEvent;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.bukkit.event.player.PlayerCommandPreprocessEvent;
import org.bukkit.event.player.PlayerDropItemEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerPickupItemEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.inventory.ItemStack;

import org.cubeville.hawkeye.DefaultActions;
import org.cubeville.hawkeye.bukkit.Convert;
import org.cubeville.hawkeye.bukkit.HawkEvent;
import org.cubeville.hawkeye.bukkit.HawkEyeListener;
import org.cubeville.hawkeye.bukkit.HawkEyePlugin;
import org.cubeville.hawkeye.model.ChatEntry;
import org.cubeville.hawkeye.model.CommandEntry;
import org.cubeville.hawkeye.model.PlayerDeathEntry;
import org.cubeville.hawkeye.model.PlayerItemEntry;
import org.cubeville.hawkeye.model.PlayerKillEntry;
import org.cubeville.hawkeye.model.PlayerLogEntry;
import org.cubeville.hawkeye.model.PlayerPvpDeathEntry;

public class PlayerListener extends HawkEyeListener {

	public PlayerListener(HawkEyePlugin plugin) {
		super(plugin);
	}

	@HawkEvent(action = DefaultActions.PLAYER_JOIN)
	public void onPlayerJoin(PlayerJoinEvent event) {
		Player player = event.getPlayer();
		Location loc = player.getLocation();
		String ip = player.getAddress().getAddress().getHostAddress();

		log(new PlayerLogEntry(DefaultActions.PLAYER_JOIN, player.getName(), Convert.location(loc), ip));
	}

	@HawkEvent(action = DefaultActions.PLAYER_QUIT)
	public void onPlayerQuit(PlayerQuitEvent event) {
		Player player = event.getPlayer();
		Location loc = player.getLocation();
		String ip = player.getAddress().getAddress().getHostAddress();

		log(new PlayerLogEntry(DefaultActions.PLAYER_QUIT, player.getName(), Convert.location(loc), ip));
	}

	@HawkEvent(action = DefaultActions.PLAYER_CHAT)
	public void onPlayerChat(AsyncPlayerChatEvent event) {
		Player player = event.getPlayer();
		Location loc = player.getLocation();

		log(new ChatEntry(player.getName(), Convert.location(loc), event.getMessage()));
	}

	@HawkEvent(action = DefaultActions.PLAYER_COMMAND, ignoreCancelled = false)
	public void onPlayerCommand(PlayerCommandPreprocessEvent event) {
		Player player = event.getPlayer();
		Location loc = player.getLocation();

		log(new CommandEntry(player.getName(), Convert.location(loc), event.getMessage()));
	}

	@HawkEvent(action = {DefaultActions.PLAYER_DEATH_PVP, DefaultActions.PLAYER_DEATH_OTHER})
	public void onPlayerDeath(PlayerDeathEvent event) {
		Player player = event.getEntity();
		Entity killer = resolveDamager(player.getLastDamageCause());
		Location loc = player.getLocation();

		if (killer instanceof Player) {
			// PVP Death
			log(new PlayerPvpDeathEntry(player.getName(), Convert.location(loc), Convert.player((Player) killer)));
		} else if (killer != null) {
			// Other death
			log(new PlayerDeathEntry(DefaultActions.PLAYER_DEATH_OTHER, player.getName(), Convert.location(loc), Convert.entity(killer)));
		} else {
			// TODO Non-entity kills
			// Should be PlayerDeathEntry but I apparently made that an
			// EntityEntry for some reason
		}
	}

	@HawkEvent(action = DefaultActions.PLAYER_MOB_KILL)
	public void onEntityDeath(EntityDeathEvent event) {
		if (event.getEntityType() == EntityType.PLAYER) return;

		Entity dead = event.getEntity();
		Entity killer = resolveDamager(dead.getLastDamageCause());

		if (killer instanceof Player) {
			Player player = (Player) killer;
			Location loc = player.getLocation();

			log(new PlayerKillEntry(player.getName(), Convert.location(loc), Convert.entity(dead)));
		}
	}

	private Entity resolveDamager(EntityDamageEvent event) {
		Entity damager = null;

		if (event instanceof EntityDamageByEntityEvent) {
			damager = ((EntityDamageByEntityEvent) event).getDamager();
		}

		if (damager instanceof Projectile) {
			damager = ((Projectile) damager).getShooter();
		}

		return damager;
	}

	@HawkEvent(action = DefaultActions.PLAYER_ITEM_DROP)
	public void onPlayerDropItem(PlayerDropItemEvent event) {
		Player player = event.getPlayer();
		Location loc = player.getLocation();
		ItemStack item = event.getItemDrop().getItemStack();

		log(new PlayerItemEntry(DefaultActions.PLAYER_ITEM_DROP, player.getName(), Convert.location(loc), Convert.itemStack(item)));
	}

	@HawkEvent(action = DefaultActions.PLAYER_ITEM_PICKUP)
	public void onPlayerPickupItem(PlayerPickupItemEvent event) {
		Player player = event.getPlayer();
		Location loc = player.getLocation();
		ItemStack item = event.getItem().getItemStack();

		log(new PlayerItemEntry(DefaultActions.PLAYER_ITEM_PICKUP, player.getName(), Convert.location(loc), Convert.itemStack(item)));
	}

}
