/*
 * HawkEye Redux
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

package org.cubeville.hawkeye.model;

import org.cubeville.hawkeye.Action;
import org.cubeville.hawkeye.entity.Player;
import org.cubeville.hawkeye.location.Block;

public abstract class AbstractBlockEntry extends AbstractEntry implements BlockEntry, Modifiable {

	private final Block block;
	private BlockState oldState;
	private BlockState newState;

	public AbstractBlockEntry(Action action, DatabaseEntry entry) {
		super(action, entry);

		block = getLocation().toBlock();
		// TODO Parse entry data to get block states
	}

	@Override
	public Block getBlock() {
		return block;
	}

	@Override
	public BlockState getOldBlockState() {
		return oldState;
	}

	@Override
	public BlockState getNewBlockState() {
		return newState;
	}

	@Override
	public void rollback() {
		if (block.getState().equals(newState)) block.setState(oldState);
	}

	@Override
	public void localRollback(Player player) {
		if (block.getState().equals(newState)) block.setLocalState(player, oldState);
	}

	@Override
	public void rebuild() {
		if (block.getState().equals(oldState)) block.setState(newState);
	}

	@Override
	public void localRebuild(Player player) {
		if (block.getState().equals(oldState)) block.setLocalState(player, newState);
	}

}
