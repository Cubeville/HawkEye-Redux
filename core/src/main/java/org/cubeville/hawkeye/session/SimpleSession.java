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

import java.util.HashMap;
import java.util.Map;

import org.cubeville.hawkeye.command.CommandSender;

public class SimpleSession implements Session {

	/**
	 * The owner of this session
	 */
	private CommandSender owner;

	/**
	 * Attribute store
	 */
	private final Map<String, Object> attributes = new HashMap<String, Object>();

	protected SimpleSession(CommandSender owner) {
		this.owner = owner;
	}

	@Override
	public CommandSender getOwner() {
		return owner;
	}

	@Override
	public Object getAttribute(String name) {
		return attributes.containsKey(name) ? attributes.get(name) : null;
	}

	@Override
	public void setAttribute(String name, Object value) {
		attributes.put(name, value);
	}

	@Override
	public void removeAttribute(String name) {
		attributes.remove(name);
	}

	@Override
	public void clearAttributes() {
		attributes.clear();
	}

	protected void handleDisconnect() {
		owner = null;
	}

	protected void handleReconnect(CommandSender sender) {
		owner = sender;
	}

}
