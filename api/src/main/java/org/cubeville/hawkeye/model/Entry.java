package org.cubeville.hawkeye.model;

import org.cubeville.hawkeye.Action;

/**
 * Represents a log entry that can be stored in the database
 */
public interface Entry {

	/**
	 * Gets the type of action stored in this entry
	 *
	 * @return Entry action
	 */
	Action getAction();

}
