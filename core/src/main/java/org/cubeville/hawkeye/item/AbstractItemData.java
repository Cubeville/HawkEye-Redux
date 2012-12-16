package org.cubeville.hawkeye.item;

import java.util.HashMap;
import java.util.Map;

public abstract class AbstractItemData implements ItemData {

	public AbstractItemData(String data) {
		String[] pieces = data.split("\\^");

		for (String piece : pieces) {
			String[] parts = piece.split(":");
			if (parts.length != 2) continue;

			String name = parts[0].toLowerCase();
			String value = parts[1];

			processData(name, value);
		}
	}

	/**
	 * Can be overridden to process custom data
	 *
	 * @param name Data name
	 * @param value Data value
	 */
	protected void processData(String name, String value) {
		if (name.equals("i")) {
			// Item ID
			try {
				setId(Integer.valueOf(value));
			} catch (NumberFormatException ex) {
				setId(0);
			}
		} else if (name.equals("a")) {
			// Amount
			try {
				setAmount(Byte.valueOf(value));
			} catch (NumberFormatException ex) {
				setAmount((byte) 1);
			}
		} else if (name.equals("d")) {
			// Durability
			try {
				setDurability(Short.valueOf(value));
			} catch (NumberFormatException ex) {
				setDurability((short) 0);
			}
		} else if (name.equals("e")) {
			processEnchantments(value);
		}
	}

	private void processEnchantments(String value) {
		String[] pieces = value.split(";");

		Map<Enchantment, Integer> enchantments = new HashMap<Enchantment, Integer>();

		for (int i = 0; i < pieces.length; i++) {
			String[] data = pieces[i].split(",");
			if (data.length != 2) continue;

			try {
				Enchantment enchant = Enchantment.getById(Integer.valueOf(data[0]));
				int level = Integer.valueOf(data[1]);
				enchantments.put(enchant, level);
			} catch (NumberFormatException ex) {
				continue;
			}
		}

		setEnchantments(enchantments);
	}

	protected abstract int getId();

	protected abstract void setId(int id);

	protected abstract byte getAmount();

	protected abstract void setAmount(byte amount);

	protected abstract short getDurability();

	protected abstract void setDurability(short durability);

	protected abstract Map<Enchantment, Integer> getEnchantments();

	protected abstract void setEnchantments(Map<Enchantment, Integer> enchantments);

	@Override
	public String serialize() {
		StringBuilder output = new StringBuilder();

		output.append("i:").append(getId());
		output.append("^").append("a:").append(getAmount());
		output.append("^").append("d:").append(getDurability());

		Map<Enchantment, Integer> enchantments = getEnchantments();
		if (!enchantments.isEmpty()) {
			output.append("^").append("e:");
			String delimit = "";
			for (Map.Entry<Enchantment, Integer> enchantment : enchantments.entrySet()) {
				output.append(delimit);
				delimit = ";";
				output.append(enchantment.getKey().getId()).append(",").append(enchantment.getValue());
			}
		}

		return output.toString();
	}

}
