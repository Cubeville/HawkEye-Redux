package org.cubeville.hawkeye.item.data;

public class SignItemData extends BasicItemData {

	private String[] lines;

	public SignItemData(byte data, String[] lines) {
		super(data);
		this.lines = lines;
	}

	public void setLines(String[] lines) {
		this.lines = lines;
	}

	public String[] getLines() {
		return lines;
	}

	public void setLine(int i, String line) {
		lines[i] = line;
	}

	public String getLine(int i) {
		return lines[i];
	}

}
