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

public class RollbackCommands {

	@Command(command = "hawkeye rollback",
			aliases = {"hawkeye rb", "hawkeye roll"},
			usage = "/hawkeye rollback <parameters>",
			description = "Rolls back changes",
			min = 1)
	public void rollback(CommandSender sender, CommandData data) {

	}

	@Command(command = "hawkeye preview rollback",
			aliases = {"hawkeye preview rb", "hawkeye preview roll"},
			usage = "/hawkeye preview rollback <parameters>",
			description = "Previews rollback changes",
			min = 1)
	public void previewRollback(CommandSender sender, CommandData data) {

	}

	@Command(command = "hawkeye rebuild",
			aliases = {"hawkeye re", "hawkeye build", },
			usage = "/hawkeye rebuild <parameters>",
			description = "Rebuilds changes",
			min = 1)
	public void rebuild(CommandSender sender, CommandData data) {

	}

	@Command(command = "hawkeye preview rebuild",
			aliases = {"hawkeye preview re", "hawkeye preview build"},
			usage = "/hawkeye preview rebuild <parameters>",
			description = "Previews rebuild changes",
			min = 1)
	public void previewRebuild(CommandSender sender, CommandData data) {

	}

	@Command(command = "hawkeye preview apply",
			aliases = {"hawkeye preview go"},
			usage = "/hawkeye preview apply",
			description = "Applies the previewed changes",
			max = 0)
	public void applyRebuild(CommandSender sender, CommandData data) {

	}

	@Command(command = "hawkeye preview cancel",
			aliases = {"hawkeye preview stop"},
			usage = "/hawkeye preview cancel",
			description = "Cancels the previewed changes",
			max = 0)
	public void cancelRebuild(CommandSender sender, CommandData data) {

	}

	@Command(command = "hawkeye undo",
			usage = "/hawkeye undo",
			description = "Undoes the previous rollback/rebuild",
			max = 0)
	public void undo(CommandSender sender, CommandData data) {

	}

}
