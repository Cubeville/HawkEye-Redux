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

package org.cubeville.hawkeye;

import java.util.logging.Logger;

import org.cubeville.hawkeye.command.CommandException;
import org.cubeville.hawkeye.command.CommandManager;
import org.cubeville.hawkeye.command.CommandPermissionException;
import org.cubeville.hawkeye.command.CommandPlayerException;
import org.cubeville.hawkeye.command.CommandSender;
import org.cubeville.hawkeye.command.CommandUsageException;
import org.cubeville.hawkeye.command.ConsoleCommandSender;
import org.cubeville.hawkeye.command.SimpleCommandManager;
import org.cubeville.hawkeye.commands.BaseCommands;
import org.cubeville.hawkeye.commands.RollbackCommands;
import org.cubeville.hawkeye.commands.SearchCommands;
import org.cubeville.hawkeye.commands.ToolCommands;
import org.cubeville.hawkeye.config.Configuration.Variable;
import org.cubeville.hawkeye.config.PluginConfig;
import org.cubeville.hawkeye.editor.WorldEditor;
import org.cubeville.hawkeye.entity.Player;
import org.cubeville.hawkeye.location.World;
import org.cubeville.hawkeye.model.Entry;
import org.cubeville.hawkeye.search.QueryManager;
import org.cubeville.hawkeye.search.SimpleQueryManager;
import org.cubeville.hawkeye.search.SimpleSearchQuery;
import org.cubeville.hawkeye.search.parsers.RadiusParser;
import org.cubeville.hawkeye.session.Session;
import org.cubeville.hawkeye.session.SessionManager;
import org.cubeville.hawkeye.session.SimpleSessionFactory;
import org.cubeville.hawkeye.session.SimpleSessionManager;
import org.cubeville.hawkeye.sql.Database;
import org.cubeville.hawkeye.sql.DatabaseException;
import org.cubeville.hawkeye.sql.MySqlDatabase;
import org.cubeville.hawkeye.util.HawkEyeLogger;

public class HawkEyeEngine implements PluginEngine {

	/**
	 * Ticks per second
	 */
	private static final long TPS = 20;

	/**
	 * Logger
	 */
	private final Logger logger;

	/**
	 * Server compatibility layer
	 */
	private final ServerInterface server;

	/**
	 * Plugin configuration
	 */
	private final PluginConfig config;

	/**
	 * Database
	 */
	private final Database database;

	/**
	 * Entry consumer
	 */
	private final Consumer consumer;

	/**
	 * Session manager
	 */
	private final SessionManager sessionManager;

	/**
	 * Data manager
	 */
	private final DataManager dataManager;

	/**
	 * Query manager
	 */
	private final QueryManager queryManager;

	/**
	 * Command manager
	 */
	private final CommandManager commandManager;

	/**
	 * Display managers
	 */
	private final DisplayManager displayManager;

	public HawkEyeEngine(ServerInterface server, PluginConfig config) {
		HawkEye.setEngine(this);
		logger = new HawkEyeLogger(this);
		this.server = server;
		this.config = config;

		database = new MySqlDatabase(config.getString(Config.DB_PREFIX));
		consumer = new SimpleConsumer();

		try {
			database.connect(
				config.getString(Config.DB_HOST),
				config.getString(Config.DB_PORT),
				config.getString(Config.DB_NAME),
				config.getString(Config.DB_USER),
				config.getString(Config.DB_PASS)
			);
		} catch (DatabaseException e) {
			// Tell the consumer to log to file if the database fails
			((SimpleConsumer) consumer).disableDatabase();
			logger.severe("Could not connect to database");
			e.printStackTrace();
		}

		sessionManager = new SimpleSessionManager(new SimpleSessionFactory());
		dataManager = new SimpleDataManager();
		queryManager = new SimpleQueryManager();
		commandManager = new SimpleCommandManager();

		displayManager = new AbstractDisplayManager(6) {
			@Override
			public void displayEntry(Session session, Entry entry) {
				session.sendMessage(entry.getOutput());
			}
		};

		server.loadExistingData();

		try {
			((SimpleCommandManager) commandManager).registerCommands(
				new BaseCommands(),
				new SearchCommands(),
				new ToolCommands(),
				new RollbackCommands()
			);
		} catch (CommandException e) {
			logger.severe("Unable to register commands");
			e.printStackTrace();
		}

		server.scheduleAsyncRepeatingTask(1L, config.getInt(Config.UPDATE_INTERVAL, 10) * TPS, consumer);

		if (((SimpleConsumer) consumer).hasDatabase()) {
			server.scheduleAsyncTask(1L, new EntryImporter());
		}

		// Load limits from config
		SimpleSearchQuery.setLimit(config.getInt(Config.LIMIT_SEARCH_RESULTS, -1));
		RadiusParser.setMaxRadius(config.getInt(Config.LIMIT_SEARCH_RADIUS, -1));
		WorldEditor.setLimit(config.getInt(Config.LIMIT_ROLLBACK_ENTRIES, -1));
		WorldEditor.setTickLimit(config.getInt(Config.LIMIT_ROLLBACK_PER_TICK, 500));
	}

	@Override
	public String getVersion() {
		// TODO Get version automagically
		return "2.0.0-SNAPSHOT";
	}

	@Override
	public Logger getLogger() {
		return logger;
	}

	@Override
	public ServerInterface getServerInterface() {
		return server;
	}

	@Override
	public PluginConfig getConfig() {
		return config;
	}

	@Override
	public Database getDatabase() {
		return database;
	}

	@Override
	public Consumer getConsumer() {
		return consumer;
	}

	@Override
	public SessionManager getSessionManager() {
		return sessionManager;
	}

	@Override
	public DataManager getDataManager() {
		return dataManager;
	}

	@Override
	public QueryManager getQueryManager() {
		return queryManager;
	}

	@Override
	public CommandManager getCommandManager() {
		return commandManager;
	}

	@Override
	public DisplayManager getDisplayManager() {
		return displayManager;
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

	@Override
	public void handleCommand(CommandSender sender, String command) {
		try {
			commandManager.execute(command, sender);
		} catch (CommandPermissionException e) {
			sender.sendMessage("You do not have permission to do that.");
		} catch (CommandPlayerException e) {
			sender.sendMessage("That command can only be used by ingame players.");
		} catch (CommandUsageException e) {
			sender.sendMessage(new String[] { e.getMessage(), e.getUsage() });
		} catch (CommandException e) {
			sender.sendMessage(e.getMessage());
		}
	}

	/**
	 * Config file variables
	 */
	public enum Config implements Variable {
		DB_PREFIX("database.prefix"),
		DB_HOST("database.hostname"),
		DB_PORT("database.port"),
		DB_NAME("database.database"),
		DB_USER("database.username"),
		DB_PASS("database.password"),

		LIMIT_SEARCH_RESULTS("limits.search-results"),
		LIMIT_SEARCH_RADIUS("limits.max-radius"),
		LIMIT_DATABASE_ENTRIES("limits.database.max-entries"),
		LIMIT_DATABASE_AGE("limits.database.entry-age"),
		LIMIT_ROLLBACK_ENTRIES("limits.rollback.max-entries"),
		LIMIT_ROLLBACK_PER_TICK("limits.rollback.per-tick"),

		UPDATE_INTERVAL("general.update-interval"),
		DELETE_ON_ROLLBACK("general.delete-on-rollback"),
		DEFAULT_HERE_RADIUS("general.here-radius"),

		IGNORED_WORLDS("general.ignored.worlds"),
		IGNORED_PLAYERS("general.ignored.players"),
		IGNORED_COMMANDS("general.ignored.commands");

		private final String path;

		private Config(String path) {
			this.path = path;
		}

		@Override
		public String getPath() {
			return path;
		}
	}

}
