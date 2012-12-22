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

import org.cubeville.hawkeye.command.ConsoleCommandSender;
import org.cubeville.hawkeye.config.Configuration;
import org.cubeville.hawkeye.entity.Player;
import org.cubeville.hawkeye.location.World;
import org.cubeville.hawkeye.search.SearchManager;
import org.cubeville.hawkeye.search.SimpleSearchManager;
import org.cubeville.hawkeye.session.SessionManager;
import org.cubeville.hawkeye.session.SimpleSessionFactory;
import org.cubeville.hawkeye.session.SimpleSessionManager;
import org.cubeville.hawkeye.sql.Database;
import org.cubeville.hawkeye.sql.DatabaseException;
import org.cubeville.hawkeye.sql.MySqlDatabase;

public class HawkEyeEngine implements PluginEngine {

	/**
	 * Server compatibility layer
	 */
	private final ServerInterface server;

	/**
	 * Plugin configuration
	 */
	private final Configuration config;

	/**
	 * Database
	 */
	private final Database database;

	/**
	 * Session manager
	 */
	private final SessionManager sessionManager;

	/**
	 * Search manager
	 */
	private final SearchManager searchManager;

	public HawkEyeEngine(ServerInterface server, Configuration config) {
		HawkEye.setEngine(this);
		this.server = server;
		this.config = config;

		database = new MySqlDatabase();
		sessionManager = new SimpleSessionManager(new SimpleSessionFactory());
		searchManager = new SimpleSearchManager();

		// TODO Run this in a thread
		try {
			database.connect(
				config.getString("database.hostname"),
				config.getString("database.port"),
				config.getString("database.database"),
				config.getString("database.username"),
				config.getString("database.password"),
				config.getString("database.prefix")
			);
		} catch (DatabaseException ex) {
			// TODO Handle this better
			ex.printStackTrace();
		}
	}

	@Override
	public String getVersion() {
		// TODO Get version automagically
		return "2.0.0-SNAPSHOT";
	}

	@Override
	public ServerInterface getServerInterface() {
		return server;
	}

	@Override
	public Configuration getConfig() {
		return config;
	}

	@Override
	public Database getDatabase() {
		return database;
	}

	@Override
	public SessionManager getSessionManager() {
		return sessionManager;
	}

	@Override
	public SearchManager getSearchManager() {
		return searchManager;
	}

	@Override
	public ConsoleCommandSender getConsoleSender() {
		return server.getConsoleSender();
	}

	@Override
	public Player getPlayer(String name) {
		return server.getPlayer(name);
	}

	@Override
	public World getWorld(String name) {
		return server.getWorld(name);
	}

}
