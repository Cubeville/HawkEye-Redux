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
import org.cubeville.hawkeye.config.HawkEyeConfig.Var;
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
import org.cubeville.hawkeye.sql.BoneCPDatabase;
import org.cubeville.hawkeye.sql.Database;
import org.cubeville.hawkeye.sql.DatabaseException;
import org.cubeville.hawkeye.util.HawkEyeLogger;
import org.cubeville.util.Chat;

public class HawkEyeEngine implements PluginEngine {

	/**
	 * Ticks per second
	 */
	private static final long TPS = 20;

	/**
	 * Logger
	 */
	private final HawkEyeLogger logger;

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
		this.server = server;
		this.config = config;
		logger = new HawkEyeLogger(this);

		database = new BoneCPDatabase(config.getString(Var.DB_PREFIX, "hawkeye"));
		consumer = new SimpleConsumer();

		try {
			database.connect(
				config.getString(Var.DB_HOST, "localhost"),
				config.getString(Var.DB_PORT, "3306"),
				config.getString(Var.DB_NAME, "hawkeye"),
				config.getString(Var.DB_USER, "username"),
				config.getString(Var.DB_PASS, "password")
			);
		} catch (DatabaseException e) {
			logger.error("Could not connect to database", e);
		}

		sessionManager = new SimpleSessionManager(new SimpleSessionFactory());
		dataManager = new SimpleDataManager();
		queryManager = new SimpleQueryManager();
		commandManager = new SimpleCommandManager();

		// TODO Different allow users to choose from different display manager
		// implementations with different output formats
		displayManager = new AbstractDisplayManager(6) {
			@Override
			public void displayEntry(Session session, Entry entry) {
				StringBuilder info = new StringBuilder();
				info.append(Chat.RED).append("id:").append(entry.getId()).append(" ");
				info.append(Chat.GRAY).append(entry.getTime()).append(" ");
				info.append(Chat.RED).append(entry.getPlayer()).append(" ");
				info.append(Chat.GRAY).append(entry.getAction().getDisplayName()).append(" ");

				StringBuilder data = new StringBuilder().append("   ");
				data.append(Chat.RED).append("Loc: ").append(Chat.GRAY).append(entry.getLocation()).append(" ");
				data.append(Chat.RED).append("Data: ").append(Chat.GRAY).append(entry.getOutput());

				session.sendMessage(info.toString(), data.toString());
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
			logger.error("Unable to register commands", e);
		}

		server.scheduleAsyncRepeatingTask(1L, config.getInt(Var.UPDATE_INTERVAL, 10) * TPS, consumer);

		// Import entries from file if we have a connection
		if (HawkEye.getDatabase().hasConnection()) {
			server.scheduleAsyncTask(1L, new EntryImporter());
		}

		// Load limits from config
		SimpleSearchQuery.setLimit(config.getInt(Var.LIMIT_SEARCH_RESULTS, -1));
		RadiusParser.setMaxRadius(config.getInt(Var.LIMIT_SEARCH_RADIUS, -1));
		WorldEditor.setLimit(config.getInt(Var.LIMIT_ROLLBACK_ENTRIES, -1));
		WorldEditor.setTickLimit(config.getInt(Var.LIMIT_ROLLBACK_PER_TICK, 500));
	}

	@Override
	public void shutdown() {
		consumer.shutdown();
		server.cancelAllTasks();
		database.close();
	}

	@Override
	public String getVersion() {
		// TODO Get version automagically
		return "2.0.0-SNAPSHOT";
	}

	@Override
	public HawkEyeLogger getLogger() {
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
			sender.sendMessage(Chat.RED + "You do not have permission to do that.");
		} catch (CommandPlayerException e) {
			sender.sendMessage(Chat.RED + "That command can only be used by ingame players.");
		} catch (CommandUsageException e) {
			sender.sendMessage(Chat.RED + e.getMessage(), Chat.RED + e.getUsage());
		} catch (CommandException e) {
			sender.sendMessage(Chat.RED + e.getMessage());
		}
	}

}
