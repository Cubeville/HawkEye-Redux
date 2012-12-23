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

package org.cubeville.hawkeye.entity;

import java.util.ArrayList;
import java.util.List;

import org.cubeville.hawkeye.location.Location;
import org.cubeville.hawkeye.location.World;

public class TestPlayer extends Player {

	private final List<String> permissions = new ArrayList<String>();

	public TestPlayer(String... permissions) {
		for (String permission : permissions) {
			this.permissions.add(permission);
		}
	}

	@Override
	public void sendMessage(String... message) {
	}

	@Override
	public boolean hasPermission(String permission) {
		return permissions.contains(permission);
	}

	@Override
	public String getName() {
		return "Test";
	}

	@Override
	public String getDisplayName() {
		return "Test";
	}

	@Override
	public Location getPosition() {
		return new Location(null, 0, 0, 0);
	}

	@Override
	public World getWorld() {
		return null;
	}

}
