package org.cubeville.hawkeye.entity;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class EntityTest {

	@Test
	public void getEntityById() {
		for (EntityType entity : EntityType.values()) {
			if (entity.getId() == -1) continue;
			assertEquals(entity.toString(), EntityType.getById(entity.getId()), entity);
		}
	}

}
