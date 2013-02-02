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

package org.cubeville.hawkeye.command;

public interface CommandManager {

	/**
	 * Registers all annotated command methods in the specified object
	 *
	 * @param obj Object to register commands of
	 * @throws CommandException If a command method cannot be processed
	 */
	void registerCommands(Object obj) throws CommandException;

	/**
	 * Registers an alias for a root command
	 *
	 * @param command Command to register alias for
	 * @param alias Alias for the command
	 * @throws CommandException If the alias is already taken
	 */
	void registerRootAlias(String command, String alias) throws CommandException;

	/**
	 * Attempts to execute a command
	 *
	 * @param command Command to execute
	 * @param sender Who sent the command
	 * @throws CommandException If the command could not be handled properly
	 */
	void execute(String command, CommandSender sender) throws CommandException;

}
