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

package org.cubeville.hawkeye.commands;

import org.cubeville.hawkeye.command.Command;
import org.cubeville.hawkeye.command.CommandData;
import org.cubeville.hawkeye.command.CommandSender;

public class ToolCommands {

	@Command(command = "hawkeye tool",
			aliases = {"hawkeye t"},
			description = "Toggles the search tool")
	public void tool(CommandSender sender, CommandData data) {

	}

	@Command(command = "hawkeye tool bind",
			aliases = {"hawkeye t bind"},
			description = "Binds custom search parameters to your tool")
	public void bind(CommandSender sender, CommandData data) {

	}

	@Command(command = "hawkeye tool reset",
			aliases = {"hawkeye t reset"},
			description = "Resets any custom parameters on your tool")
	public void reset(CommandSender sender, CommandData data) {

	}

}
