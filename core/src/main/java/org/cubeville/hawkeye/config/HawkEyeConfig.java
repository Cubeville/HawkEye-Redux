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

package org.cubeville.hawkeye.config;

import java.io.File;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import org.cubeville.hawkeye.Action;
import org.cubeville.hawkeye.DefaultActions;

public class HawkEyeConfig extends YamlConfiguration implements PluginConfig {

	public HawkEyeConfig(File file) {
		super(file, true);
		load();
	}

	private final Map<String, Boolean> actions = new HashMap<String, Boolean>();

	/**
	 * Loads data from the config
	 *
	 * @param config Plugin configuration
	 */
	@Override
	public void load() {
		super.load();

		for (Action action : DefaultActions.values()) {
			process(action);
		}

		// Attempt to access each variable to save them if writeDefaults is on
		for (Var var : Var.values()) {
			get(var, var.getDefault());
		}
	}

	@Override
	public boolean isLogging(Action action) {
		String name = action.getName();
		if (!actions.containsKey(name)) process(action);
		return actions.get(name);
	}

	private void process(Action action) {
		String name = action.getName();
		actions.put(name, getBoolean("logging." + name, true));
	}

	/**
	 * Config file variables
	 */
	public enum Var implements Variable {
		DB_PREFIX("database.prefix", "hawkeye"),
		DB_HOST("database.hostname", "localhost"),
		DB_PORT("database.port", 3306),
		DB_NAME("database.database", "minecraft"),
		DB_USER("database.username", "username"),
		DB_PASS("database.password", "password"),

		LIMIT_SEARCH_RESULTS("limits.search-results", -1),
		LIMIT_SEARCH_RADIUS("limits.max-radius", -1),
		LIMIT_DATABASE_ENTRIES("limits.database.max-entries", -1),
		LIMIT_DATABASE_AGE("limits.database.entry-age", "60d"),
		LIMIT_ROLLBACK_ENTRIES("limits.rollback.max-entries", -1),
		LIMIT_ROLLBACK_PER_TICK("limits.rollback.per-tick", 500),

		UPDATE_INTERVAL("general.update-interval", 10),
		DELETE_ON_ROLLBACK("general.delete-on-rollback", false),
		DEFAULT_HERE_RADIUS("general.here-radius", 5),

		IGNORED_WORLDS("general.ignored.worlds", Arrays.asList(new String[]{"ignored-world", "also-ignored"})),
		IGNORED_PLAYERS("general.ignored.players", Collections.emptySet()),
		IGNORED_COMMANDS("general.ignored.commands", Arrays.asList(new String[]{"ignoredcmd", "ignoreme"}));

		private final String path;
		private final Object def;

		private Var(String path, Object def) {
			this.path = path;
			this.def = def;
		}

		@Override
		public String getPath() {
			return path;
		}

		@Override
		public Object getDefault() {
			return def;
		}
	}

}
