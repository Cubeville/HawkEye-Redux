package org.cubeville.hawkeye.entity;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class EntityTest {

	@Test
	public void getEntityById() {
		for (Entity entity : Entity.values()) {
			if (entity.getId() == -1) continue;
			assertEquals(Entity.getById(entity.getId()), entity);
		}
	}

}
