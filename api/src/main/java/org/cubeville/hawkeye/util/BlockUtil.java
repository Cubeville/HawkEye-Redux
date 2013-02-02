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

package org.cubeville.hawkeye.util;

import java.util.HashSet;
import java.util.Set;

import org.cubeville.hawkeye.item.Items;

public class BlockUtil {

	/**
	 * List of blocks that attach to the side of a block
	 */
	private static final Set<Items> attachable;

	/**
	 * List of items that attach on top of a block
	 */
	private static final Set<Items> attachableTop;

	static {
		attachable = new HashSet<Items>(11);
		attachable.add(Items.TORCH);
		attachable.add(Items.LADDER);
		attachable.add(Items.WALL_SIGN);
		attachable.add(Items.LEVER);
		attachable.add(Items.REDSTONE_TORCH_OFF);
		attachable.add(Items.REDSTONE_TORCH_ON);
		attachable.add(Items.STONE_BUTTON);
		attachable.add(Items.TRAP_DOOR);
		attachable.add(Items.COCOA);
		attachable.add(Items.TRIPWIRE_HOOK);
		attachable.add(Items.WOOD_BUTTON);

		attachableTop = new HashSet<Items>(20);
		attachableTop.add(Items.SAPLING);
		attachableTop.add(Items.LONG_GRASS);
		attachableTop.add(Items.DEAD_BUSH);
		attachableTop.add(Items.YELLOW_FLOWER);
		attachableTop.add(Items.RED_ROSE);
		attachableTop.add(Items.BROWN_MUSHROOM);
		attachableTop.add(Items.RED_ROSE);
	}

}
