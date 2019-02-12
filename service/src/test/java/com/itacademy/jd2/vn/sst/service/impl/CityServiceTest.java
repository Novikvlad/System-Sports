package com.itacademy.jd2.vn.sst.service.impl;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

import java.util.List;

import org.junit.jupiter.api.Test;

import com.itacademy.jd2.vn.sst.dao.api.entity.table.ICity;

public class CityServiceTest extends AbstractTest {

	@Test
	public void createTestCity() {

		final ICity entity = cityService.createEntity();
		entity.setName("city-" + getRandomPrefix());
		entity.setCountry(saveNewCountry());
		cityService.save(entity);

		final ICity entityFromDB = cityService.getFullInfo(entity.getId());

		assertNotNull(entityFromDB.getId());
		assertEquals(entity.getName(), entityFromDB.getName());
		assertEquals(entity.getCountry().getId(), entityFromDB.getCountry().getId());
		assertNotNull(entityFromDB.getCreated());
		assertNotNull(entityFromDB.getUpdated());
	}

	@Test
	public void testCreateMultiple() {
		int initialSize = cityService.getAll().size();

		final ICity entity1 = cityService.createEntity();
		entity1.setName("country-" + getRandomPrefix());

		try {
			final ICity entity2 = cityService.createEntity();
			cityService.save(entity2);
			fail("Country save should fail if name not specified");
		} catch (Exception e) {
			assertEquals(initialSize, cityService.getAll().size());
		}

	}

	@Test
	public void testUpdate() throws InterruptedException {
		final ICity entity = saveNewCity();

		String newName = entity.getName() + "_updated";
		entity.setName(newName);
		Thread.sleep(2000);
		cityService.save(entity);

		final ICity entityFromDb = cityService.get(entity.getId());

		assertNotNull(entityFromDb);
		assertEquals(newName, entityFromDb.getName());
		assertNotNull(entityFromDb.getId());
		assertNotNull(entityFromDb.getCreated());
		assertNotNull(entityFromDb.getUpdated());
		assertEquals(entity.getCreated(), entityFromDb.getCreated());
		assertTrue(entityFromDb.getUpdated().after(entity.getCreated()));
	}

	@Test
	public void testGetAll() {
		final int intialCount = cityService.getAll().size();

		final int randomObjectsCount = getRandomObjectsCount();
		for (int i = 0; i < randomObjectsCount; i++) {
			saveNewCountry();
		}

		final List<ICity> allEntities = cityService.getAll();

		for (final ICity entityFromDb : allEntities) {
			assertNotNull(entityFromDb.getName());
			assertNotNull(entityFromDb.getId());
			assertNotNull(entityFromDb.getCreated());
			assertNotNull(entityFromDb.getUpdated());
		}

		assertEquals(randomObjectsCount + intialCount, allEntities.size());
	}

	@Test
	public void testDelete() {
		final ICity entity = saveNewCity();
		cityService.delete(entity.getId());
		assertNull(cityService.get(entity.getId()));
	}

	@Test
	public void testDeleteAll() {
		saveNewCity();
		cityService.deleteAll();
		assertEquals(0, cityService.getAll().size());
	}
}
