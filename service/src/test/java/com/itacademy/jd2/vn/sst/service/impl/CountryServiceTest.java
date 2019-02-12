package com.itacademy.jd2.vn.sst.service.impl;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

import java.util.List;

import org.junit.jupiter.api.Test;

import com.itacademy.jd2.vn.sst.dao.api.entity.table.ICountry;

public class CountryServiceTest extends AbstractTest {

	@Test
	public void cteateTest() {

		final ICountry entity = countryService.createEntity();
		entity.setName("country-" + getRandomPrefix());
		entity.setRegion(saveNewRegion());
		countryService.save(entity);

		final ICountry entityFromDB = countryService.getFullInfo(entity.getId());

		assertNotNull(entityFromDB);;
		assertEquals(entity.getName(), entityFromDB.getName());
		assertEquals(entity.getRegion().getId(), entityFromDB.getRegion().getId());
		assertNotNull(entityFromDB.getCreated());
		assertNotNull(entityFromDB.getUpdated());
	}

	@Test
	public void testCreateMultiple() {
		int initialSize = countryService.getAll().size();

		final ICountry entity1 = countryService.createEntity();
		entity1.setName("country-" + getRandomPrefix());

		try {
			final ICountry entity2 = countryService.createEntity();
			countryService.save(entity2);
			fail("Country save should fail if name not specified");
		} catch (Exception e) {
			assertEquals(initialSize, countryService.getAll().size());
		}

	}

	@Test
	public void testUpdate() throws InterruptedException {
		final ICountry entity = saveNewCountry();

		String newName = entity.getName() + "_updated";
		entity.setName(newName);
		Thread.sleep(2000);
		countryService.save(entity);

		final ICountry entityFromDb = countryService.get(entity.getId());

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
		final int intialCount = countryService.getAll().size();

		final int randomObjectsCount = getRandomObjectsCount();
		for (int i = 0; i < randomObjectsCount; i++) {
			saveNewCountry();
		}

		final List<ICountry> allEntities = countryService.getAll();

		for (final ICountry entityFromDb : allEntities) {
			assertNotNull(entityFromDb.getName());
			assertNotNull(entityFromDb.getId());
			assertNotNull(entityFromDb.getUpdated());
			assertNotNull(entityFromDb.getUpdated());
		}

		assertEquals(randomObjectsCount + intialCount, allEntities.size());
	}

	@Test
	public void testDelete() {
		final ICountry entity = saveNewCountry();
		countryService.delete(entity.getId());
		assertNull(countryService.get(entity.getId()));
	}

	@Test
	public void testDeleteAll() {
		saveNewCountry();
		countryService.deleteAll();
		assertEquals(0, countryService.getAll().size());
	}
}
