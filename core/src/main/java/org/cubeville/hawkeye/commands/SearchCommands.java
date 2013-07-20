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

import org.cubeville.hawkeye.HawkEye;
import org.cubeville.hawkeye.command.Command;
import org.cubeville.hawkeye.command.CommandData;
import org.cubeville.hawkeye.command.CommandException;
import org.cubeville.hawkeye.command.CommandSender;
import org.cubeville.hawkeye.search.SearchQuery;
import org.cubeville.hawkeye.search.callbacks.SearchCallback;
import org.cubeville.hawkeye.session.Session;

public class SearchCommands {

	@Command(command = "hawkeye search",
			aliases = {"hawkeye s"},
			description = "Search the HawkEye database")
	public void search(CommandSender sender, CommandData data) throws CommandException {
		Session session = HawkEye.getSessionManager().getSession(sender);
		String params = data.getFullString(0);

		SearchQuery query = HawkEye.getQueryManager().createQuery(sender, params, new SearchCallback(session));
		HawkEye.getServerInterface().scheduleAsyncTask(1L, query);

		sender.sendMessage("&cSearching database...");
	}

	@Command(command = "hawkeye page",
			aliases = {"hawkeye pg"},
			description = "Display a separate page of your results",
			max = 1)
	public void page(CommandSender sender, CommandData data) throws CommandException {
		Session session = HawkEye.getSessionManager().getSession(sender);
		int page = data.getInt(0);
		HawkEye.getDisplayManager().displayResults(session, page);
	}

	@Command(command = "hawkeye tpto",
			aliases = {"hawkeye tp", "hawkeye teleport"},
			description = "Teleports to a specified record")
	public void tpto(CommandSender sender, CommandData data) {

	}

	@Command(command = "hawkeye here",
			aliases = {"hawkeye near"},
			description = "Alias for a radius search")
	public void here(CommandSender sender, CommandData data) {

	}

}
