package org.cubeville.hawkeye.item;

import java.util.HashMap;
import java.util.Map;

public class TestItem extends AbstractItemData {

	private int id;
	private byte amount;
	private short durability;
	private Map<Enchantment, Integer> enchants;

	public TestItem(String data) {
		super(data);
	}

	public TestItem(int id) {
		this(id, (byte) 1);
	}

	public TestItem(int id, byte amount) {
		this(id, amount, (short) 0);
	}

	public TestItem(int id, byte amount, short durability) {
		super();
		this.id = id;
		this.amount = amount;
		this.durability = durability;
		enchants = new HashMap<Enchantment, Integer>();
	}

	public TestItem(int id, byte amount, short durability, Map<Enchantment, Integer> enchantments) {
		this(id, amount, durability);
		addEnchantments(enchantments);
	}

	@Override
	protected int getId() {
		return id;
	}

	@Override
	protected void setId(int id) {
		this.id = id;
	}

	@Override
	protected byte getAmount() {
		return amount;
	}

	@Override
	protected void setAmount(byte amount) {
		this.amount = amount;
	}

	@Override
	protected short getDurability() {
		return durability;
	}

	@Override
	protected void setDurability(short durability) {
		this.durability = durability;
	}

	@Override
	protected Map<Enchantment, Integer> getEnchantments() {
		return enchants;
	}

	@Override
	protected void addEnchantment(Enchantment enchantment, int level) {
		if (enchants == null) enchants = new HashMap<Enchantment, Integer>();
		enchants.put(enchantment, level);
	}

	@Override
	protected void removeEnchantment(Enchantment enchantment) {
		enchants.remove(enchantment);
	}

	private void addEnchantments(Map<Enchantment, Integer> enchantments) {
		for (Map.Entry<Enchantment, Integer> entry : enchantments.entrySet()) {
			addEnchantment(entry.getKey(), entry.getValue());
		}
	}

}
