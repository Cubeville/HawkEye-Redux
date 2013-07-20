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

package org.cubeville.hawkeye.bukkit;

import java.lang.reflect.Method;

import org.bukkit.event.Event;
import org.bukkit.event.EventException;
import org.bukkit.event.Listener;
import org.bukkit.plugin.EventExecutor;
import org.bukkit.plugin.PluginManager;

import org.cubeville.hawkeye.DefaultActions;
import org.cubeville.hawkeye.HawkEye;

public abstract class HawkEyeListener implements Listener {

	protected final HawkEyePlugin plugin;

	public HawkEyeListener(HawkEyePlugin plugin) {
		this.plugin = plugin;
	}

	public final void registerEvents() {
		PluginManager pm = plugin.getServer().getPluginManager();

		Method[] methods = getClass().getDeclaredMethods();

		for (int i = 0; i < methods.length; i++) {
			final Method method = methods[i];
			final HawkEvent event = method.getAnnotation(HawkEvent.class);

			// Not a listener
			if (event == null) continue;

			// Loop through the actions specified to determine if this listener
			// needs to be registered
			boolean register = false;

			for (DefaultActions action : event.action()) {
				if (HawkEye.getConfig().isLogging(action)) {
					register = true;
					break;
				}
			}

			if (!register) continue;

			// Make sure it only accepts an event as a parameter
			Class<?>[] params = method.getParameterTypes();
			if (params.length != 1 || !Event.class.isAssignableFrom(params[0])) {
				continue;
			}

			final Class<? extends Event> eventClass = params[0].asSubclass(Event.class);
			method.setAccessible(true);

			EventExecutor executor = new EventExecutor() {
				@Override
				public void execute(Listener listener, Event event) throws EventException {
					try {
						if (!eventClass.isAssignableFrom(event.getClass())) return;
						method.invoke(listener, event);
					} catch (Exception e) {
						throw new EventException(e.getCause());
					}
				}
			};

			pm.registerEvent(eventClass, this, event.priority(), executor, plugin, event.ignoreCancelled());
		}
	}

}
