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

package org.cubeville.hawkeye.item.data;

import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import org.cubeville.hawkeye.NBT;
import org.cubeville.lib.jnbt.CompoundTag;
import org.cubeville.lib.jnbt.ListTag;
import org.cubeville.lib.jnbt.StringTag;
import org.cubeville.lib.jnbt.Tag;

/**
 * ItemData implementation to store the contents of written books
 */
public class BookItemData extends BaseItemData {

	private final String title;
	private final String author;
	private final List<String> pages;

	/**
	 * Deserialization constructor
	 *
	 * @param tag Tag to deserialize from
	 */
	public BookItemData(CompoundTag tag) {
		super(tag);

		pages = new LinkedList<String>();
		Map<String, Tag> data = tag.getValue();

		if (data.containsKey(NBT.ITEM.BOOK.TITLE)) {
			title = ((StringTag) data.get(NBT.ITEM.BOOK.TITLE)).getValue();
		} else {
			title = null;
		}

		if (data.containsKey(NBT.ITEM.BOOK.AUTHOR)) {
			author = ((StringTag) data.get(NBT.ITEM.BOOK.AUTHOR)).getValue();
		} else {
			author = null;
		}

		List<Tag> list = ((ListTag) data.get(NBT.ITEM.BOOK.PAGES)).getValue();
		for (Tag t : list) {
			pages.add(((StringTag) t).getValue());
		}
	}

	public BookItemData(List<String> pages) {
		this(null, null, pages);
	}

	public BookItemData(String title, String author, List<String> pages) {
		this.title = title;
		this.author = author;
		this.pages = pages;
	}

	/**
	 * Gets whether or not this book has a title
	 */
	public boolean hasTitle() {
		return title != null;
	}

	/**
	 * Gets whether or not this book has an author
	 */
	public boolean hasAuthor() {
		return author != null;
	}

	@Override
	protected void serialize(Map<String, Tag> map) {
		super.serialize(map);

		if (hasTitle()) {
			map.put(NBT.ITEM.BOOK.TITLE, new StringTag(NBT.ITEM.BOOK.TITLE, title));
		}

		if (hasAuthor()) {
			map.put(NBT.ITEM.BOOK.AUTHOR, new StringTag(NBT.ITEM.BOOK.AUTHOR, author));
		}

		List<Tag> data = new LinkedList<Tag>();
		for (String page : pages) {
			data.add(new StringTag("", page));
		}

		map.put(NBT.ITEM.BOOK.PAGES, new ListTag(NBT.ITEM.BOOK.PAGES, StringTag.class, data));
	}

}
