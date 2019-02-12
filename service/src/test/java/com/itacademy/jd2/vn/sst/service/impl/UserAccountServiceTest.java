package com.itacademy.jd2.vn.sst.service.impl;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Set;

import org.junit.jupiter.api.Test;

import com.itacademy.jd2.vn.sst.dao.api.entity.table.IClub;
import com.itacademy.jd2.vn.sst.dao.api.entity.table.IUserAccount;

public class UserAccountServiceTest extends AbstractTest {
	@Test
	public void createTestEvent() {
		final IUserAccount entity = userAccountService.createEntity();
		entity.setName("ticket-" + getRandomPrefix());
		entity.setEmail("email-" + getRandomPrefix());
		entity.setPassword("password-" + getRandomPrefix());
		entity.setFunOrganisation(saveNewFunOrganisation());
		entity.setCountry(saveNewCountry());
		entity.setPhone("email-"+getRandomPrefix());
		entity.setBirthday(new Date());
		entity.setRole(getRandomUserType());

		final IUserAccount entityFromDb = userAccountService.getFullInfo(entity.getId());

		assertEquals(entity.getName(), entityFromDb.getName());
		assertEquals(entity.getEmail(), entityFromDb.getEmail());
		assertEquals(entity.getPassword(), entityFromDb.getPassword());
		assertEquals(entity.getCountry().getId(), entityFromDb.getCountry().getId());
		assertEquals(entity.getFunOrganisation().getId(), entityFromDb.getFunOrganisation().getId());
		assertNotNull(entityFromDb.getId());
		assertNotNull(entityFromDb.getCreated());
		assertNotNull(entityFromDb.getUpdated());
		assertTrue(entityFromDb.getCreated().equals(entityFromDb.getUpdated()));
	}

	@Test
	public void testCreateMultiple() {
		int initialSize = userAccountService.getAll().size();

		final IUserAccount entity1 = userAccountService.createEntity();
		entity1.setName("stadium-" + getRandomPrefix());

		try {
			final IUserAccount entity2 = userAccountService.createEntity();
			userAccountService.save(entity2);
			fail("User Account save should fail if name not specified");
		} catch (Exception e) {
			assertEquals(initialSize, countryService.getAll().size());
		}
		try {
			final IUserAccount entity2 = userAccountService.createEntity();
			userAccountService.save(entity2);
			fail("User Account save should fail if name not specified");
		} catch (Exception e) {
			assertEquals(initialSize, funOrganisationService.getAll().size());
		}
	}

	@Test
	public void testUpdate() throws InterruptedException {
		final IUserAccount entity = saveNewUserAccount();

		String newName = entity.getName() + "_updated";
		entity.setName(newName);
		Thread.sleep(2000);
		userAccountService.save(entity);

		final IUserAccount entityFromDb = userAccountService.get(entity.getId());

		assertNotNull(entityFromDb);
		assertEquals(newName, entityFromDb.getName());
		assertNotNull(entityFromDb.getId());
		assertNotNull(entityFromDb.getEmail());
		assertNotNull(entityFromDb.getPassword());
		assertNotNull(entityFromDb.getCountry().getId());
		assertNotNull(entityFromDb.getFunOrganisation().getId());
		assertNotNull(entityFromDb.getPhone());
		assertNotNull(entityFromDb.getBirthday());
		assertNotNull(entityFromDb.getRole());
		assertNotNull(entityFromDb.getCreated());
		assertNotNull(entityFromDb.getUpdated());
		assertEquals(entity.getCreated(), entityFromDb.getCreated());
		assertTrue(entityFromDb.getUpdated().after(entity.getCreated()));
	}

	@Test
	public void testGetAll() {
		final int intialCount = userAccountService.getAll().size();

		final int randomObjectsCount = getRandomObjectsCount();
		for (int i = 0; i < randomObjectsCount; i++) {
			saveNewCountry();
		}
		for (int i = 0; i < randomObjectsCount; i++) {
			saveNewFunOrganisation();
		}
		final List<IUserAccount> allEntities = userAccountService.getAll();

		for (final IUserAccount entityFromDb : allEntities) {
			assertNotNull(entityFromDb.getName());
			assertNotNull(entityFromDb.getId());
			assertNotNull(entityFromDb.getEmail());
			assertNotNull(entityFromDb.getPassword());
			assertNotNull(entityFromDb.getCountry().getId());
			assertNotNull(entityFromDb.getFunOrganisation().getId());
			assertNotNull(entityFromDb.getPhone());
			assertNotNull(entityFromDb.getBirthday());
			assertNotNull(entityFromDb.getRole());
			assertNotNull(entityFromDb.getCreated());
			assertNotNull(entityFromDb.getUpdated());
		}

		assertEquals(randomObjectsCount + intialCount, allEntities.size());
	}

	@Test
	public void testDelete() {
		final IUserAccount entity = saveNewUserAccount();
		userAccountService.delete(entity.getId());
		assertNull(userAccountService.get(entity.getId()));
	}

	@Test
	public void testDeleteAll() {
		saveNewUserAccount();
		userAccountService.deleteAll();
		assertEquals(0, userAccountService.getAll().size());
	}

	@Test
	public void createClubWithUserAccountTest() {
		final IUserAccount entity = userAccountService.createEntity();
		entity.setName("userAccount-" + getRandomPrefix());
		entity.setCountry(saveNewCountry());
		entity.setFunOrganisation(saveNewFunOrganisation());

		final int randomObjectsCount = getRandomObjectsCount();
		final List<IClub> clubs = new ArrayList<>();
		for (int i = 0; i < randomObjectsCount; i++) {
			clubs.add(saveNewClub());
		}
		entity.getClubs().addAll(clubs);

		userAccountService.save(entity);

		final IUserAccount entityFromDb = userAccountService.getFullInfo(entity.getId());
		final Set<IClub> clubsFromDb = entityFromDb.getClubs();
		assertEquals(clubs.size(), clubsFromDb.size());

		for (final IClub club : clubs) {
			boolean found = false;
			for (final IClub dbEngine : clubsFromDb) {
				if (club.getId().equals(dbEngine.getId())) {
					found = true;
					break;
				}

			}
			assertTrue(found, "can't find entity:" + club);
		}
	}
}
