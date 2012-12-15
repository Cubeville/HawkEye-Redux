package org.cubeville.hawkeye.item;

public enum ItemType {

	/**
	 * Block that can be placed in the world
	 */
	BLOCK,
	/**
	 * Regular item
	 */
	ITEM;

	/**
	 * Gets the type of a specified item id
	 *
	 * @param id Item id to check
	 * @return Item's type
	 */
	public static ItemType getType(int id) {
		return isBlock(id) ? BLOCK : (isItem(id) ? ITEM : null);
	}

	/**
	 * Checks if the specified item id is a block
	 *
	 * @param id Item id to check
	 * @return True if specified id is a block id, false if not
	 */
	public static boolean isBlock(int id) {
		return id >= 0 && id <= 255;
	}

	/**
	 * Checks if the specified item id is an item
	 *
	 * @param id Item id to check
	 * @return True if specified id is an item id, false if not
	 */
	public static boolean isItem(int id) {
		return id > 255;
	}

	/**
	 * Checks if the specified item is an existing item (in vanilla minecraft)
	 *
	 * @param id Item id to check
	 * @return True if specified id is a vanilla minecraft item, false if not
	 */
	public static boolean exists(int id) {
		Block block = Block.getById(id);
		if (block != null) return true;

		Item item = Item.getById(id);
		return item != null;
	}

}
