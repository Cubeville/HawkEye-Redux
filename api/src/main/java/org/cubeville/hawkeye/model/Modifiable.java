package org.cubeville.hawkeye.model;

import org.cubeville.hawkeye.entity.Player;

/**
 * Represents an entry that can be modified, i.e. rolled back or rebuilt
 */
public interface Modifiable {

	/**
	 * Performs a rollback on the data in this entry
	 */
	void rollback();

	/**
	 * Performs a local rollback on the data in this entry
	 *
	 * Local rollbacks are only visible to the player specified, for use in a
	 * rollback preview
	 *
	 * @param player Player to send the local rollback to
	 */
	void localRollback(Player player);

	/**
	 * Performs a rebuilt on the data in this entry (opposite of rollback)
	 */
	void rebuild();

	/**
	 * Performs a local rebuild on the data in this entry
	 *
	 * Local rebuild are only visible to the player specified, for use in a
	 * rebuild preview
	 *
	 * @param player Player to send the local rollback to
	 */
	void localRebuild(Player player);

}
