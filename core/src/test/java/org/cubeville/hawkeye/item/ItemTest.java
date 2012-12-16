package org.cubeville.hawkeye.item;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.HashMap;
import java.util.Map;

import org.junit.Test;

public class ItemTest {

	@Test
	public void testItemSerialize() {
		TestItem item = new TestItem(17, (byte) 5, (short) 2);
		TestItem newItem = new TestItem(item.serialize());

		assertEquals(item.getId(), newItem.getId());
		assertEquals(item.getAmount(), newItem.getAmount());
		assertEquals(item.getDurability(), newItem.getDurability());
	}

	@Test
	public void testItemEnchantmentsSerialize() {
		Map<Enchantment, Integer> enchants = new HashMap<Enchantment, Integer>();

		int level = 0;
		for (Enchantment enchant : Enchantment.values()) {
			level = (level < 3) ? level + 1 : 1;
			enchants.put(enchant, level);
		}

		TestItem enchanted = new TestItem(278, (byte) 1, (short) 0, enchants);
		TestItem serialized = new TestItem(enchanted.serialize());

		for (Map.Entry<Enchantment, Integer> entry : enchants.entrySet()) {
			assertTrue(serialized.getEnchantments().containsKey(entry.getKey()));
			assertEquals(serialized.getEnchantments().get(entry.getKey()), entry.getValue());
		}
	}

}
