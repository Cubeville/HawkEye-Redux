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

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import org.cubeville.util.Triplet;

public class SimpleCommandManager implements CommandManager {

	/**
	 * Mapping of commands to their execution info
	 */
	private final Map<String, Triplet<Command, Method, Object>> commands;

	/**
	 * Mapping of aliases to the actual command
	 */
	private final Map<String, String> aliases;

	/**
	 * Nested commands
	 */
	private final Map<String, Set<String>> nested;

	public SimpleCommandManager() {
		commands = new HashMap<String, Triplet<Command, Method, Object>>();
		aliases = new HashMap<String, String>();
		nested = new HashMap<String, Set<String>>();
	}

	@Override
	public void registerCommands(Object obj) {
		// Get the object's methods via reflection
		Class<?> clazz = obj.getClass();

		for (Method method : clazz.getMethods()) {
			// Check if it's a command handler
			if (!method.isAnnotationPresent(Command.class)) continue;

			// Make sure method can handle commands
			if (!validateMethod(method)) continue;

			// Make sure command info is valid
			Command info = method.getAnnotation(Command.class);
			if (!validateCommand(info)) continue;

			// Make sure method is accessible
			method.setAccessible(true);

			// Register command execution data
			Triplet<Command, Method, Object> command = Triplet.of(info, method, obj);

			// Process nested commands
			String base = "";
			String[] parts = info.command().split(" ");
			for (int i = 0; i < parts.length; i++) {
				if (i > 0) base += " ";
				base += parts[i];

				if ((i + 1) == parts.length) break;

				Set<String> nest = nested.get(base);
				if (nest == null) nest = new HashSet<String>();

				nest.add(parts[i + 1]);
				nested.put(base, nest);
			}

			commands.put(info.command(), command);

			// Register aliases
			// No existing alias check is done so they may be overridden
			for (String alias : info.aliases()) {
				aliases.put(alias, info.command());
			}
		}
	}

	@Override
	public void execute(String command, CommandSender sender) throws CommandException {
		// Remove leading slash
		if (command.startsWith("/")) command = command.substring(1);

		String base = "";
		String[] args = new String[0];

		// Check what command is being run (loop to find nested commands)
		String[] parts = command.split(" ");
		for (int i = 0; i < parts.length; i++) {
			if (i > 0) base += " ";
			base += parts[i];

			Set<String> nest = nested.get(base);
			String next = ((i + 1) == parts.length) ? "" : parts[i + 1];

			// No more nested commands
			if (nest == null || !nest.contains(next)) {
				args = new String[parts.length - (i + 1)];
				System.arraycopy(parts, i + 1, args, 0, parts.length - (i + 1));
				break;
			}

			// Command not registered
			if (!commands.containsKey(base)) return;

			CommandData data = new CommandData(base, args);
			Triplet<Command, Method, Object> info = commands.get(base);
			Command cmd = info.getLeft();
			Method method = info.getMiddle();

			// Check permission nodes
			if (!hasPermission(method, sender)) {
				// TODO Throw exception
			}

			// Check number of arguments
			if (data.length() < cmd.min()) {
				// TODO Throw exception
			}

			if (cmd.max() != -1 && data.length() > cmd.max()) {
				// TODO Throw exception
			}

			// Check flags
			Set<Character> validFlags = new HashSet<Character>();
			for (char flag : cmd.flags().toCharArray()) {
				validFlags.add(flag);
			}

			for (char flag : data.getFlags()) {
				if (validFlags.contains(flag)) {
					// TODO Throw exception
				}
			}

			// Execute command
			try {
				method.invoke(info.getRight(), sender, data);
			} catch (IllegalArgumentException e) {
				e.printStackTrace();
			} catch (IllegalAccessException e) {
				e.printStackTrace();
			} catch (InvocationTargetException e) {
				if (e.getCause() instanceof CommandException) {
					throw (CommandException) e.getCause();
				} else {
					throw new CommandException(e.getCause());
				}
			}
		}
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

	/**
	 * Checks if a sender has permission to execute a command
	 *
	 * @param method Command method to run permission check on
	 * @param sender Command sender to check permission for
	 * @return True if sender can execute command, false if not
	 */
	private boolean hasPermission(Method method, CommandSender sender) {
		// No permission specified default true
		if (!method.isAnnotationPresent(CommandPermission.class)) return true;

		// True if sender has one or more of the specified nodes
		for (String node : method.getAnnotation(CommandPermission.class).value()) {
			if (sender.hasPermission(node)) return true;
		}

		return false;
	}

}