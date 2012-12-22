package org.cubeville.hawkeye.item;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class EnchantmentTest {

	@Test
	public void getEnchantmentById() {
		for (Enchantment enchantment : Enchantment.values()) {
			assertEquals(Enchantment.getById(enchantment.getId()), enchantment);
		}
	}

}
