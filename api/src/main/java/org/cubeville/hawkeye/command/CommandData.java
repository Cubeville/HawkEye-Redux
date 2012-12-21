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

import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

import org.cubeville.util.StringUtil;

public class CommandData {

	private final String command;
	private final List<String> processedArgs;
	private final Set<Character> flags;

	public CommandData(String command, String args) {
		this(command, args.split(" "));
	}

	public CommandData(String command, String[] args) {
		processedArgs = new LinkedList<String>();
		flags = new HashSet<Character>();

		// Remove leading slash
		this.command = command.startsWith("/") ? command.substring(1) : command;

		for (int i = 0; i < args.length; i++) {
			String arg = args[i];
			if (arg.length() == 0) continue;

			// Arguments starting with '-' are assumed to be flags
			if (arg.charAt(0) == '-' && arg.length() >= 2) {
				for (int j = 1; j < arg.length(); j++) {
					flags.add(arg.charAt(j));
				}

				continue;
			}

			// Not a flag, regular arg
			processedArgs.add(arg);
		}
	}

	/**
	 * Gets the number of arguments that this command data holds
	 *
	 * @return Number of arguments (not including flags)
	 */
	public int length() {
		return processedArgs.size();
	}

	/**
	 * Gets the command that this object represents
	 *
	 * @return Base command
	 */
	public String getCommand() {
		return command;
	}

	/**
	 * Gets a command argument as a string
	 *
	 * @param index Index of the argument
	 * @return The argument
	 */
	public String getString(int index) {
		return processedArgs.get(index);
	}

	/**
	 * Gets several arguments joined into a full string
	 *
	 * @param startIndex Starting index
	 * @return String of all arguments at and after start index
	 */
	public String getFullString(int startIndex) {
		return StringUtil.buildString(processedArgs, " ", startIndex);
	}

	/**
	 * Gets an argument as an integer
	 *
	 * @param index Index of the argument
	 * @return int
	 * @throws NumberFormatException If the argument is not a number
	 */
	public int getInt(int index) throws NumberFormatException {
		return Integer.parseInt(processedArgs.get(index));
	}

	/**
	 * Gets an argument as a double
	 *
	 * @param index Index of the argument
	 * @return double
	 * @throws NumberFormatException If the argument is not a number
	 */
	public double getDouble(int index) throws NumberFormatException {
		return Double.parseDouble(processedArgs.get(index));
	}

	/**
	 * Checks if a flag was defined
	 *
	 * @param flag Flag to check for
	 * @return True if flag was defined, false if not
	 */
	public boolean hasFlag(char flag) {
		return flags.contains(flag);
	}

}
