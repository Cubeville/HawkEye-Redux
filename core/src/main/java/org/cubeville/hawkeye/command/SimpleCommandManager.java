/*
 * HawkEye
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

package org.cubeville.hawkeye.command;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

import org.cubeville.util.Triplet;

public class SimpleCommandManager implements CommandManager {

	/**
	 * Mapping of commands to their execution info
	 */
	private final Map<String, Triplet<Command, Method, Object>> commands;

	public SimpleCommandManager() {
		commands = new HashMap<String, Triplet<Command, Method, Object>>();
	}

	@Override
	public void registerCommands(Object obj) {
		Class<?> clazz = obj.getClass();

		for (Method method : clazz.getMethods()) {
			// Check if it's a command handler
			if (!method.isAnnotationPresent(Command.class)) continue;

			// Make sure method can handle commands
			if (!validateMethod(method)) continue;

			// Make sure command info is valid
			Command info = method.getAnnotation(Command.class);
			if (!validateCommand(info)) continue;

			// Register command execution data
			Triplet<Command, Method, Object> command = Triplet.of(info, method, obj);
			commands.put(info.command(), command);
		}
	}

	@Override
	public void execute(String command, String[] args, CommandSender sender) throws CommandException {
		// TODO Auto-generated method stub
	}

	/**
	 * Checks if a method is a valid command handler
	 *
	 * @param method Method to check
	 * @return True if method can handle commands
	 */
	private boolean validateMethod(Method method) {
		// Make sure it has the right parameters (CommandSender, CommandData)
		Class<?>[] params = method.getParameterTypes();
		if (params.length != 2) return false;
		if (!CommandSender.class.isAssignableFrom(params[0])) return false;
		if (!CommandData.class.isAssignableFrom(params[1])) return false;

		return true;
	}

	/**
	 * Checks if a command specification is valid
	 *
	 * @param command Command annotation to check
	 * @return True if command is a valid specification
	 */
	private boolean validateCommand(Command command) {
		// Make sure it isn't already registered
		if (commands.containsKey(command.command())) return false;

		if (command.min() < 0) return false;
		if (command.max() < -1) return false;

		// Make sure max args > min args
		if (command.max() != -1 && command.min() > command.max()) return false;

		return true;
	}

}
