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

package org.cubeville.hawkeye.search.parsers;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.cubeville.hawkeye.Action;
import org.cubeville.hawkeye.HawkEye;
import org.cubeville.hawkeye.Item;
import org.cubeville.hawkeye.block.BlockState;
import org.cubeville.hawkeye.command.CommandException;
import org.cubeville.hawkeye.command.CommandSender;
import org.cubeville.hawkeye.item.ItemStack;
import org.cubeville.hawkeye.model.BlockEntry;
import org.cubeville.hawkeye.model.Entry;
import org.cubeville.hawkeye.model.ItemEntry;
import org.cubeville.hawkeye.search.PostParameterParser;
import org.cubeville.hawkeye.search.PreParameterParser;
import org.cubeville.util.Pair;
import org.cubeville.util.StringUtil;

public class BlockParser implements PreParameterParser, PostParameterParser {

	@Override
	public void validate(List<String> parameters) throws CommandException {
		for (int i = 0; i < parameters.size(); i++) {
			String[] value = parameters.get(i).split(":", 2);

			if (Item.match(value[0]) == null) throw new CommandException("Invalid item specified: &7" + value[0]);
		}
	}

	@Override
	public Pair<String, Map<String, Object>> process(List<String> parameters, CommandSender sender) throws CommandException {
		List<String> blockActions = new ArrayList<String>();
		for (Action action : HawkEye.getDataManager().getActions()) {
			if (BlockEntry.class.isAssignableFrom(action.getClass()) || ItemEntry.class.isAssignableFrom(action.getClass())) {
				blockActions.add(action.getName());
			}
		}

		List<String> conditions = new ArrayList<String>();
		Map<String, Object> binds = new HashMap<String, Object>();

		for (int i = 0; i < parameters.size(); i++) {
			String name = "block" + i;
			String[] value = parameters.get(i).split(":", 2);

			Item item = Item.match(value[0]);

			String id = String.valueOf(item.getId());
			if (value.length == 2) {
				id += ":" + value[1];
			}

			conditions.add("(`data` LIKE :" + name + ")");
			binds.put(name, "%" + id + "%");
		}

		String sql = "";
		sql += "`action` IN ('" + StringUtil.buildString(blockActions, "','") + "')";

		return Pair.of(sql, null);
	}

	@Override
	public void process(List<String> parameters, List<Entry> results) {
		List<ItemStack> items = new ArrayList<ItemStack>();

		for (int i = 0; i < parameters.size(); i++) {
			String[] value = parameters.get(i).split(":", 2);

			Item item = Item.match(value[0]);
			short data = -1;

			if (value.length == 2) {
				try {
					data = Short.parseShort(value[1]);
				} catch (NumberFormatException ignore) {
				}
			}

			items.add(new ItemStack(item, (byte) 1, data));
		}

		Iterator<Entry> itr = results.iterator();

		outer:
		while (itr.hasNext()) {
			Entry entry = itr.next();

			if (entry instanceof BlockEntry) {
				BlockState s1 = ((BlockEntry) entry).getOldBlockState();
				BlockState s2 = ((BlockEntry) entry).getNewBlockState();

				for (ItemStack item : items) {
					// Check if either state matches a query parameter
					if (compareBlockState(item, s1) || compareBlockState(item, s2)) {
						continue outer;
					}
				}
			}

			if (entry instanceof ItemEntry) {
				ItemStack i = ((ItemEntry) entry).getItem();

				for (ItemStack item : items) {
					// Check if the item matches a query parameter
					if (compareItemStack(item, i)) {
						continue outer;
					}
				}
			}

			// At this point no matches were found, kill the entry
			itr.remove();
		}
	}

	private boolean compareBlockState(ItemStack item, BlockState state) {
		if (item.getTypeId() != state.getTypeId()) return false;
		if (item.getDurability() != -1 && item.getDurability() != state.getData()) return false;

		return true;
	}

	private boolean compareItemStack(ItemStack item, ItemStack test) {
		if (item.getTypeId() != test.getTypeId()) return false;
		if (item.getDurability() != -1 && item.getDurability() != test.getDurability()) return false;

		return true;
	}

}
