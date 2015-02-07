package org.cubeville.hawkeye.entity;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.cubeville.hawkeye.location.Location;
import org.cubeville.hawkeye.location.World;

public class TestPlayer extends Player {

	private final UUID uuid = UUID.randomUUID();
	private final List<String> permissions = new ArrayList<String>();

	public TestPlayer(String... permissions) {
		for (String permission : permissions) {
			this.permissions.add(permission);
		}
	}

	@Override
	public void sendMessage(String... message) {
	}

	@Override
	public boolean hasPermission(String permission) {
		return permissions.contains(permission);
	}

	@Override
	public UUID getUUID() {
		return uuid;
	}

	@Override
	public String getName() {
		return "Test";
	}

	@Override
	public String getDisplayName() {
		return "Test";
	}

	@Override
	public Location getPosition() {
		return new Location(null, 0, 0, 0);
	}

	@Override
	public World getWorld() {
		return null;
	}

}
