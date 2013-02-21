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

import java.util.List;

import org.cubeville.hawkeye.model.Entry;
import org.cubeville.hawkeye.session.Session;
import org.cubeville.util.Chat;

public abstract class AbstractDisplayManager implements DisplayManager {

	/**
	 * Maximum number of 5 pixel characters that fit on a line
	 */
	private static final int MAX_CHAT_WIDTH = 54;
	/**
	 * Footer displayed after search results
	 */
	private static final String FOOTER;

	private final int recordsPerPage;

	public AbstractDisplayManager(int recordsPerPage) {
		this.recordsPerPage = recordsPerPage;
	}

	@Override
	public void displayResults(Session session, int page) {
		List<Entry> results = session.getSearchResults();
		int pages = getTotalPages(session);
		if (page < 1 || page > pages) return;

		int start = (page - 1) * recordsPerPage;
		int end = start + recordsPerPage;

		String header = "&7Page &c" + page + "&7/&c" + pages;
		int paddingLength = (MAX_CHAT_WIDTH - Chat.stripFormatting(header).length()) / 2;
		String padding = "&8";
		for (int i = 0; i < paddingLength; i++) {
			padding += "-";
		}

		message(session, pad(header, padding));

		for (int i = start; i < end; i++) {
			if (start == results.size()) break;

			Entry entry = results.get(i);
			displayEntry(session, entry);
		}

		message(session, FOOTER);
	}

	public abstract void displayEntry(Session session, Entry entry);

	public int getTotalPages(Session session) {
		double records = session.getSearchResults().size();
		return (int) Math.ceil(records / recordsPerPage);
	}

	private void message(Session session, String... message) {
		session.getOwner().sendMessage(message);
	}

	private String pad(String string, String pad) {
		return pad + string + pad;
	}

	static {
		String footer = "&8";
		for (int i = 0; i < MAX_CHAT_WIDTH; i++) {
			footer += "-";
		}
		FOOTER = footer;
	}

}
