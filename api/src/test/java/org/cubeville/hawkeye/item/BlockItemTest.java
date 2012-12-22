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
		for (Block block : Block.values()) {
			assertEquals(Block.getById(block.getId()), block);
		}
	}

	@Test
	public void getItemById() {
		for (Item item : Item.values()) {
			assertEquals(Item.getById(item.getId()), item);
		}
	}

	@Test
	public void testItemTypes() {
		for (Block block : Block.values()) {
			assertTrue(ItemType.isBlock(block.getId()));
			assertThat(ItemType.getType(block.getId()), is(ItemType.BLOCK));
		}

		for (Item item : Item.values()) {
			assertTrue(ItemType.isItem(item.getId()));
			assertThat(ItemType.getType(item.getId()), is(ItemType.ITEM));
		}
	}

	@Test
	public void testItemExistence() {
		for (Block block : Block.values()) {
			assertTrue(ItemType.exists(block.getId()));
		}

		for (Item item : Item.values()) {
			assertTrue(ItemType.exists(item.getId()));
		}

		assertFalse(ItemType.exists(-1));
		assertFalse(ItemType.exists(255));
		assertFalse(ItemType.exists(Integer.MAX_VALUE));
	}

}
