package org.cubeville.hawkeye.item.data;

public class MobHeadItemData extends BasicItemData {

	/**
	 * Owner of this mob head
	 */
	private String owner;

	public MobHeadItemData(byte data, String owner) {
		super(data);
		this.owner = owner;
	}

	/**
	 * Sets the owner of this mob head
	 *
	 * @param owner New owner
	 */
	public void setOwner(String owner) {
		this.owner = owner;
	}

	/**
	 * Gets the owner of this mob head
	 * @return This mob head's owner
	 */
	public String getOwner() {
		return owner;
	}

}
