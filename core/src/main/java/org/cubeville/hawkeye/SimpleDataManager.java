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

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

public class SimpleDataManager implements DataManager {

	/**
	 * Action list
	 */
	private final List<Action> actions = new ArrayList<Action>();

	public SimpleDataManager() {
		// Register all actions logged by this plugin
		registerActions(DefaultActions.values());
	}

	@Override
	public List<Action> getActions() {
		return actions;
	}

	@Override
	public void registerAction(Action action) {
		actions.add(action);
	}

	@Override
	public void registerActions(Action[] list) {
		registerActions(Arrays.asList(list));
	}

	@Override
	public void registerActions(Collection<Action> list) {
		actions.addAll(list);
	}

}
