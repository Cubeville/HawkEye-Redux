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

package org.cubeville.hawkeye.block.data;

import java.util.Map;

import org.cubeville.hawkeye.NBT;
import org.cubeville.lib.jnbt.CompoundTag;
import org.cubeville.lib.jnbt.StringTag;
import org.cubeville.lib.jnbt.Tag;

public class CommandBlock extends Renamable {

	private final String command;

	/**
	 * Deserialization constructor
	 *
	 * @param tag Tag to deserialize from
	 */
	public CommandBlock(CompoundTag tag) {
		super(tag);
		Map<String, Tag> data = tag.getValue();
		if (data.containsKey(NBT.BLOCK.COMMAND_BLOCK.COMMAND)) {
			command = ((StringTag) data.get(NBT.BLOCK.COMMAND_BLOCK.COMMAND)).getValue();
		} else {
			command = "";
		}
	}

	public CommandBlock(String name, String command) {
		super(name);
		this.command = command;
	}

	public CommandBlock(String command) {
		this(null, command);
	}

	/**
	 * Gets the command run by this command block
	 */
	public String getCommand() {
		return command;
	}

	@Override
	public void serialize(Map<String, Tag> map) {
		super.serialize(map);
		map.put(NBT.BLOCK.COMMAND_BLOCK.COMMAND, new StringTag(NBT.BLOCK.COMMAND_BLOCK.COMMAND, command));
	}

}
