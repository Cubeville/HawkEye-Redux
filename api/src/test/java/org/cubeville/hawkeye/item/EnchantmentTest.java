package org.cubeville.hawkeye.item;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class EnchantmentTest {

	@Test
	public void getEnchantmentById() {
		for (Enchantments enchantment : Enchantments.values()) {
			assertEquals(Enchantments.getById(enchantment.getId()), enchantment);
		}
	}

}
