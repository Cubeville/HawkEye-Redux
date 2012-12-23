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

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

public class SimpleDataManager implements DataManager {

	/**
	 * Map containing action database names and their corresponding actions
	 */
	private final Map<String, Action> actions = new HashMap<String, Action>();

	public SimpleDataManager() {
		// Register all actions logged by this plugin
		registerActions(DefaultActions.values());
	}

	@Override
	public Collection<Action> getActions() {
		return actions.values();
	}

	@Override
	public boolean registerAction(Action action) {
		String name = action.getName().toLowerCase();
		if (actions.containsKey(name)) return false;

		actions.put(name, action);
		return true;
	}

	private void registerActions(Action[] actions) {
		for (Action action : actions) {
			registerAction(action);
		}
	}

}
