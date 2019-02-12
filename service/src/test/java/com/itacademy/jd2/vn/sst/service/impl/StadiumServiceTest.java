package com.itacademy.jd2.vn.sst.service.impl;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

import java.util.Date;
import java.util.List;

import org.junit.jupiter.api.Test;

import com.itacademy.jd2.vn.sst.dao.api.entity.table.IStadium;

public class StadiumServiceTest extends AbstractTest {

	@Test
	public void cteateTestStadium() {

		final IStadium entity = stadiumService.createEntity();
		entity.setName("stadium-" + getRandomPrefix());
		entity.setCapacity(getRandomObjectsCount());
		entity.setAddress("address-" + getRandomPrefix());
		entity.setBased(new Date());
		entity.setCity(saveNewCity());
		stadiumService.save(entity);

		final IStadium entityFromDB = stadiumService.getFullInfo(entity.getId());

		assertNotNull(entityFromDB.getId());
		assertEquals(entity.getName(), entityFromDB.getName());
		assertEquals(entity.getCapacity(), entityFromDB.getCapacity());
		assertEquals(entity.getCity().getId(), entityFromDB.getCity().getId());
		assertNotNull(entityFromDB.getCreated());
		assertNotNull(entityFromDB.getUpdated());
	}

	@Test
	public void testCreateMultiple() {
		int initialSize = stadiumService.getAll().size();

		final IStadium entity1 = stadiumService.createEntity();
		entity1.setName("stadium-" + getRandomPrefix());

		try {
			final IStadium entity2 = stadiumService.createEntity();
			stadiumService.save(entity2);
			fail("Stadium save should fail if name not specified");
		} catch (Exception e) {
			assertEquals(initialSize, countryService.getAll().size());
		}

	}

	@Test
	public void testUpdate() throws InterruptedException {
		final IStadium entity = saveNewStadium();

		String newName = entity.getName() + "_updated";
		entity.setName(newName);
		Thread.sleep(2000);
		stadiumService.save(entity);

		final IStadium entityFromDb = stadiumService.get(entity.getId());

		assertNotNull(entityFromDb);
		assertEquals(newName, entityFromDb.getName());
		assertNotNull(entityFromDb.getId());
		assertNotNull(entityFromDb.getAddress());
		assertNotNull(entityFromDb.getCapacity());
		assertNotNull(entityFromDb.getBased());
		assertNotNull(entityFromDb.getCreated());
		assertNotNull(entityFromDb.getUpdated());
		assertEquals(entity.getCreated(), entityFromDb.getCreated());
		assertTrue(entityFromDb.getUpdated().after(entity.getCreated()));
	}

	@Test
	public void testGetAll() {
		final int intialCount = stadiumService.getAll().size();

		final int randomObjectsCount = getRandomObjectsCount();
		for (int i = 0; i < randomObjectsCount; i++) {
			saveNewCountry();
		}

		final List<IStadium> allEntities = stadiumService.getAll();

		for (final IStadium entityFromDb : allEntities) {
			assertNotNull(entityFromDb.getName());
			assertNotNull(entityFromDb.getId());
			assertNotNull(entityFromDb.getCapacity());
			assertNotNull(entityFromDb.getAddress());
			assertNotNull(entityFromDb.getBased());
			assertNotNull(entityFromDb.getCreated());
			assertNotNull(entityFromDb.getUpdated());
		}

		assertEquals(randomObjectsCount + intialCount, allEntities.size());
	}

	@Test
	public void testDelete() {
		final IStadium entity = saveNewStadium();
		stadiumService.delete(entity.getId());
		assertNull(stadiumService.get(entity.getId()));
	}

	@Test
	public void testDeleteAll() {
		saveNewStadium();
		stadiumService.deleteAll();
		assertEquals(0, stadiumService.getAll().size());
	}

}
