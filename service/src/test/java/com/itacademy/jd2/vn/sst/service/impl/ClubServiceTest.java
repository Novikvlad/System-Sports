package com.itacademy.jd2.vn.sst.service.impl;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

import java.util.Date;
import java.util.List;

import org.junit.jupiter.api.Test;

import com.itacademy.jd2.vn.sst.dao.api.entity.table.IClub;

public class ClubServiceTest extends AbstractTest {
	@Test
	public void cteateTestClub() {

		final IClub entity = clubServise.createEntity();
		entity.setName("club-" + getRandomPrefix());
		entity.setBased(new Date());
		entity.setStadium(saveNewStadium());
		clubServise.save(entity);

		final IClub entityFromDB = clubServise.getFullInfo(entity.getId());

		assertNotNull(entityFromDB.getId());
		assertEquals(entity.getName(), entityFromDB.getName());
		assertEquals(entity.getStadium().getId(), entityFromDB.getStadium().getId());
		assertNotNull(entityFromDB.getCreated());
		assertNotNull(entityFromDB.getUpdated());
	}

	@Test
	public void testCreateMultiple() {
		int initialSize = clubServise.getAll().size();

		final IClub entity1 = clubServise.createEntity();
		entity1.setName("club-" + getRandomPrefix());

		try {
			final IClub entity2 = clubServise.createEntity();
			clubServise.save(entity2);
			fail("Club save should fail if name not specified");
		} catch (Exception e) {
			assertEquals(initialSize, stadiumService.getAll().size());
		}

	}

	@Test
	public void testUpdate() throws InterruptedException {
		final IClub entity = saveNewClub();

		String newName = entity.getName() + "_updated";
		entity.setName(newName);
		Thread.sleep(2000);
		clubServise.save(entity);

		final IClub entityFromDb = clubServise.get(entity.getId());

		assertNotNull(entityFromDb);
		assertEquals(newName, entityFromDb.getName());
		assertNotNull(entityFromDb.getId());
		assertNotNull(entityFromDb.getBased());
		assertNotNull(entityFromDb.getCreated());
		assertNotNull(entityFromDb.getUpdated());
		assertEquals(entity.getCreated(), entityFromDb.getCreated());
		assertTrue(entityFromDb.getUpdated().after(entity.getCreated()));
	}

	@Test
	public void testGetAll() {
		final int intialCount = clubServise.getAll().size();

		final int randomObjectsCount = getRandomObjectsCount();
		for (int i = 0; i < randomObjectsCount; i++) {
			saveNewStadium();
		}

		final List<IClub> allEntities = clubServise.getAll();

		for (final IClub entityFromDb : allEntities) {
			assertNotNull(entityFromDb.getName());
			assertNotNull(entityFromDb.getId());
			assertNotNull(entityFromDb.getBased());
			assertNotNull(entityFromDb.getCreated());
			assertNotNull(entityFromDb.getUpdated());
		}

		assertEquals(randomObjectsCount + intialCount, allEntities.size());
	}

	@Test
	public void testDelete() {
		final IClub entity = saveNewClub();
		clubServise.delete(entity.getId());
		assertNull(clubServise.get(entity.getId()));
	}

	@Test
	public void testDeleteAll() {
		saveNewClub();
		clubServise.deleteAll();
		assertEquals(0, clubServise.getAll().size());
	}
}
