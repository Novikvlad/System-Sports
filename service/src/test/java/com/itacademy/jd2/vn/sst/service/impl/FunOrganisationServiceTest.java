package com.itacademy.jd2.vn.sst.service.impl;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

import java.util.List;

import org.junit.jupiter.api.Test;

import com.itacademy.jd2.vn.sst.dao.api.entity.table.IFunOrganisation;

public class FunOrganisationServiceTest extends AbstractTest {
	@Test
	public void cteateTestStadium() {

		final IFunOrganisation entity = funOrganisationService.createEntity();
		entity.setName("funOrganisation-" + getRandomPrefix());
		entity.setCity(saveNewCity());
		entity.setClub(saveNewClub());
		entity.setDeposit(getRandomDecimal());
		funOrganisationService.save(entity);

		final IFunOrganisation entityFromDB = funOrganisationService.getFullInfo(entity.getId());

		assertNotNull(entityFromDB.getId());
		assertEquals(entity.getName(), entityFromDB.getName());
		assertEquals(entity.getClub().getId(), entityFromDB.getClub().getId());
		assertEquals(entity.getCity().getId(), entityFromDB.getCity().getId());
		assertNotNull(entityFromDB.getDeposit());
		assertNotNull(entityFromDB.getCreated());
		assertNotNull(entityFromDB.getUpdated());
	}

	@Test
	public void testCreateMultiple() {
		int initialSize = funOrganisationService.getAll().size();

		final IFunOrganisation entity1 = funOrganisationService.createEntity();
		entity1.setName("funOrganisation-" + getRandomPrefix());

		try {
			final IFunOrganisation entity2 = funOrganisationService.createEntity();
			funOrganisationService.save(entity2);
			fail("FunOrganisation save should fail if name not specified");
		} catch (Exception e) {
			assertEquals(initialSize, clubServise.getAll().size());
		}
		try {
			final IFunOrganisation entity3 = funOrganisationService.createEntity();
			funOrganisationService.save(entity3);
			fail("FunOrganisation save should fail if name not specified");
		} catch (Exception e) {
			assertEquals(initialSize, clubServise.getAll().size());
		}

	}

	@Test
	public void testUpdate() throws InterruptedException {
		final IFunOrganisation entity = saveNewFunOrganisation();

		String newName = entity.getName() + "_updated";
		entity.setName(newName);
		Thread.sleep(2000);
		funOrganisationService.save(entity);

		final IFunOrganisation entityFromDb = funOrganisationService.get(entity.getId());

		assertNotNull(entityFromDb);
		assertEquals(newName, entityFromDb.getName());
		assertNotNull(entityFromDb.getId());
		assertNotNull(entity.getClub().getId());
		assertNotNull(entity.getCity().getId());
		assertNotNull(entity.getDeposit());
		assertNotNull(entityFromDb.getCreated());
		assertNotNull(entityFromDb.getUpdated());
		assertEquals(entity.getCreated(), entityFromDb.getCreated());
		assertTrue(entityFromDb.getUpdated().after(entity.getCreated()));
	}

	@Test
	public void testGetAll() {
		final int intialCount = funOrganisationService.getAll().size();

		final int randomObjectsCount = getRandomObjectsCount();
		for (int i = 0; i < randomObjectsCount; i++) {
			saveNewCity();
		}
		for (int i = 0; i < randomObjectsCount; i++) {
			saveNewClub();
		}
		final List<IFunOrganisation> allEntities = funOrganisationService.getAll();

		for (final IFunOrganisation entityFromDb : allEntities) {
			assertNotNull(entityFromDb.getName());
			assertNotNull(entityFromDb.getId());
			assertNotNull(entityFromDb.getCity().getId());
			assertNotNull(entityFromDb.getClub().getId());
			assertNotNull(entityFromDb.getDeposit());
			assertNotNull(entityFromDb.getCreated());
			assertNotNull(entityFromDb.getUpdated());
		}

		assertEquals(randomObjectsCount + intialCount, allEntities.size());
	}

	@Test
	public void testDelete() {
		final IFunOrganisation entity = saveNewFunOrganisation();
		funOrganisationService.delete(entity.getId());
		assertNull(funOrganisationService.get(entity.getId()));
	}

	@Test
	public void testDeleteAll() {
		saveNewFunOrganisation();
		funOrganisationService.deleteAll();
		assertEquals(0, funOrganisationService.getAll().size());
	}
}
