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

package org.cubeville.hawkeye.model;

import org.cubeville.hawkeye.Action;
import org.cubeville.hawkeye.entity.Entity;
import org.cubeville.hawkeye.location.Location;

public abstract class AbstractEntityEntry extends AbstractEntry implements EntityEntry {

	private final Entity entity;

	public AbstractEntityEntry(Action action, DatabaseEntry entry) {
		super(action, entry);

		int id = -1;

		try {
			id = Integer.parseInt(entry.getData());
		} catch (NumberFormatException ignore) { }

		entity = Entity.getById(id);
	}

	public AbstractEntityEntry(Action action, String player, Location location, Entity entity) {
		super(action, player, location);

		this.entity = entity;
	}

	@Override
	public Entity getEntity() {
		return entity;
	}

	@Override
	public String getData() {
		return String.valueOf(entity.getId());
	}

}
