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

package org.cubeville.hawkeye.model;

import org.cubeville.hawkeye.Action;
import org.cubeville.hawkeye.Item;
import org.cubeville.hawkeye.block.BlockData;
import org.cubeville.hawkeye.block.BlockState;
import org.cubeville.hawkeye.entity.Player;
import org.cubeville.hawkeye.location.Block;
import org.cubeville.hawkeye.location.Location;
import org.cubeville.lib.jnbt.NBTUtils;

public abstract class AbstractBlockEntry extends AbstractEntry implements BlockEntry, Modifiable {

	private final Block block;
	private final BlockState oldState;
	private final BlockState newState;
	private final byte[] nbt;

	public AbstractBlockEntry(Action action, DatabaseEntry entry) {
		super(action, entry);

		block = getLocation().toBlock();

		String before;
		String after;

		String[] parts = entry.getData().split(DELIMITER_SPLITTER);
		before = parts[0];
		after = parts.length > 1 ? parts[1] : "0";

		nbt = entry.getNbt();
		oldState = after.equals("0") ? new BlockState(before, nbt) : new BlockState(before);
		newState = after.equals("0") ? new BlockState(after) : new BlockState(after, nbt);
	}

	public AbstractBlockEntry(Action action, String player, Location location, BlockState oldState, BlockState newState) {
		super(action, player, location);
		block = location.toBlock();
		this.oldState = oldState;
		this.newState = newState;

		BlockData data = newState.getType() == Item.AIR ? oldState.getBlockData() : newState.getBlockData();
		nbt = NBTUtils.toByteArray(data);
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
	public String getData() {
		return oldState.toString() + DELIMITER + newState.toString();
	}

	@Override
	public byte[] getNbt() {
		return nbt;
	}

	@Override
	public boolean rollback() {
		if (block.getState().equals(newState)) {
			block.setState(oldState);
			return true;
		}

		return false;
	}

	@Override
	public boolean localRollback(Player player) {
		if (block.getState().equals(newState)) {
			block.setLocalState(player, oldState);
			return true;
		}

		return false;
	}

	@Override
	public boolean rebuild() {
		if (block.getState().equals(oldState)) {
			block.setState(newState);
			return true;
		}

		return false;
	}

	@Override
	public boolean localRebuild(Player player) {
		if (block.getState().equals(oldState)) {
			block.setLocalState(player, newState);
			return true;
		}

		return false;
	}

}
