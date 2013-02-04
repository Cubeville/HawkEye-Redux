package org.cubeville.hawkeye.item;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import org.cubeville.hawkeye.ItemType;
import org.cubeville.hawkeye.Item;

public class BlockItemTest {

	@Test
	public void getById() {
		for (Item item : Item.values()) {
			assertEquals(Item.getById(item.getId()), item);
		}
	}

	@Test
	public void testTypes() {
		for (Item item : Item.values()) {
			if (item.getId() <= 255) {
				assertTrue(ItemType.isBlock(item.getId()));
				assertThat(ItemType.getType(item.getId()), is(ItemType.BLOCK));
			} else {
				assertTrue(ItemType.isItem(item.getId()));
				assertThat(ItemType.getType(item.getId()), is(ItemType.ITEM));
			}
		}
	}

	@Test
	public void testExistence() {
		for (Item item : Item.values()) {
			assertTrue(ItemType.exists(item.getId()));
		}

		assertFalse(ItemType.exists(-1));
		assertFalse(ItemType.exists(255));
		assertFalse(ItemType.exists(Integer.MAX_VALUE));
	}

}
