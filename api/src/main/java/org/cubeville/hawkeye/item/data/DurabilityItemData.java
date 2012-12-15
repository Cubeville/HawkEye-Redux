package org.cubeville.hawkeye.item.data;

/**
 * ItemData implementation to store the durability of tools/armor
 */
public class DurabilityItemData implements ItemData {

	/**
	 * Holds item durability (i.e. for tools/armor)
	 */
	private short durability;

	public DurabilityItemData(short durability) {
		this.durability = durability;
	}

	/**
	 * Sets this item's durability
	 *
	 * @param durability Durability level
	 */
	public void setDurability(short durability) {
		this.durability = durability;
	}

	/**
	 * Gets this item's durability
	 *
	 * @return This item's durability level
	 */
	public short getDurability() {
		return durability;
	}

}
