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

package org.cubeville.hawkeye.session;

import java.util.List;

import org.cubeville.hawkeye.command.CommandSender;
import org.cubeville.hawkeye.model.Entry;

public interface Session {

	/**
	 * Gets the owner of this session
	 *
	 * @return Session owner
	 */
	CommandSender getOwner();

	/**
	 * Sends a message to the owner of this session
	 *
	 * @param message Message(s) to send
	 */
	void sendMessage(String... message);

	/**
	 * Gets the latest search results from this session
	 *
	 * @return Results from this session's last search
	 */
	List<Entry> getSearchResults();

	/**
	 * Stores search results in this session
	 *
	 * @param results Search results to store
	 */
	void setSearchResults(List<Entry> results);

	/**
	 * Gets an attribute stored in this session
	 *
	 * @param name Name of attribute to get
	 * @return Stored attribute or null if not found
	 */
	Object getAttribute(String name);

	/**
	 * Stores an attribute in this session
	 *
	 * @param name Name of attribute to set
	 * @param value Value to set
	 */
	void setAttribute(String name, Object value);

	/**
	 * Removes an attribute stored in this session
	 *
	 * @param name Name of attribute to clear
	 */
	void removeAttribute(String name);

	/**
	 * Clears all attributes stored in this session
	 */
	void clearAttributes();

	/**
	 * Native session attributes
	 */
	public static class Attribute {
		/**
		 * Attribute for whether or not the session is running a search
		 */
		public static final String SEARCHING = "searching";
		/**
		 * Attribute for whether or not the session is currently processing a
		 * rollback/rebuild
		 */
		public static final String WORKING = "working";
	}

}
