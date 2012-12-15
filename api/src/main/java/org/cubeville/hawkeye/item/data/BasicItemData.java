package org.cubeville.hawkeye.item.data;

public class BasicItemData implements ItemData {

	/**
	 * Holds item data (i.e. dye color)
	 */
	private byte data;

	public BasicItemData(byte data) {
		this.data = data;
	}

	/**
	 * Sets this item's data
	 *
	 * @param data New data value
	 */
	public void setData(byte data) {
		this.data = data;
	}

	/**
	 * Gets this item's data
	 *
	 * @return This item's data
	 */
	public byte getData() {
		return data;
	}

}
