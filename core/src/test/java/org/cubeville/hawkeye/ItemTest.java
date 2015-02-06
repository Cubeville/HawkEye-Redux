package org.cubeville.hawkeye;


import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

public class ItemTest {

	@Test
	public void testTypes() {
		for (Item item : DefaultItems.values()) {
			if (item.getId() <= 255) {
				assertTrue(ItemType.isBlock(item.getId()));
				assertThat(ItemType.getType(item.getId()), is(ItemType.BLOCK));
			} else {
				assertTrue(ItemType.isItem(item.getId()));
				assertThat(ItemType.getType(item.getId()), is(ItemType.ITEM));
			}
		}
	}

}
