package org.cubeville.hawkeye.item;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

public class BlockItemTest {

	@Test
	public void getBlockById() {
		for (Blocks block : Blocks.values()) {
			assertEquals(Blocks.getById(block.getId()), block);
		}
	}

	@Test
	public void getItemById() {
		for (Items item : Items.values()) {
			assertEquals(Items.getById(item.getId()), item);
		}
	}

	@Test
	public void testItemTypes() {
		for (Blocks block : Blocks.values()) {
			assertTrue(ItemType.isBlock(block.getId()));
			assertThat(ItemType.getType(block.getId()), is(ItemType.BLOCK));
		}

		for (Items item : Items.values()) {
			assertTrue(ItemType.isItem(item.getId()));
			assertThat(ItemType.getType(item.getId()), is(ItemType.ITEM));
		}
	}

	@Test
	public void testItemExistence() {
		for (Blocks block : Blocks.values()) {
			assertTrue(ItemType.exists(block.getId()));
		}

		for (Items item : Items.values()) {
			assertTrue(ItemType.exists(item.getId()));
		}

		assertFalse(ItemType.exists(-1));
		assertFalse(ItemType.exists(255));
		assertFalse(ItemType.exists(Integer.MAX_VALUE));
	}

}
