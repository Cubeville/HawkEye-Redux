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

package org.cubeville.hawkeye.session;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.cubeville.hawkeye.command.CommandSender;
import org.cubeville.hawkeye.entity.Player;

public class SimpleSessionManager implements SessionManager {

	private final SimpleSessionFactory factory;
	private final Map<String, SimpleSession> sessions = new ConcurrentHashMap<String, SimpleSession>();

	public SimpleSessionManager(SimpleSessionFactory factory) {
		this.factory = factory;
	}

	@Override
	public SimpleSession getSession(CommandSender owner) {
		SimpleSession session = sessions.get(owner.getName());

		if (session == null) {
			session = factory.createSession(owner);
			sessions.put(owner.getName(), session);
		}

		return session;
	}

	@Override
	public void handleDisconnect(String player) {
		if (sessions.containsKey(player)) {
			sessions.get(player).handleDisconnect();
		}
	}

	@Override
	public void handleReconnect(Player player) {
		if (sessions.containsKey(player.getName())) {
			sessions.get(player.getName()).handleReconnect(player);
		}
	}

}
