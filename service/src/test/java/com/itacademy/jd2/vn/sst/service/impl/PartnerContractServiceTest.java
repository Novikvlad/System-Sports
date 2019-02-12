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
import com.itacademy.jd2.vn.sst.dao.api.entity.table.IPartnerContract;

public class PartnerContractServiceTest extends AbstractTest {
	@Test
	public void cteateTestPartnerContract() {

		final IPartnerContract entity = partnerContractService.createEntity();
		entity.setClub(saveNewClub());
		entity.setPartner(saveNewPartner());
		entity.setDateSigning(new Date());
		entity.setContractTerm(new Date());
		partnerContractService.save(entity);

		final IPartnerContract entityFromDB = partnerContractService.getFullInfo(entity.getId());

		assertNotNull(entityFromDB.getId());
		assertEquals(entity.getPartner().getId(), entityFromDB.getPartner().getId());
		assertEquals(entity.getClub().getId(), entityFromDB.getClub().getId());
		assertNotNull(entityFromDB.getDateSigning());
		assertNotNull(entityFromDB.getContractTerm());
		assertNotNull(entityFromDB.getContractValue());
		assertNotNull(entityFromDB.getCreated());
		assertNotNull(entityFromDB.getUpdated());
	}

	@Test
	public void testCreateMultiple() {
		int initialSize = partnerContractService.getAll().size();


		try {
			final IPartnerContract entity1 = partnerContractService.createEntity();
			partnerContractService.save(entity1);
			fail("PartnerContract save should fail if name not specified");
		} catch (Exception e) {
			assertEquals(initialSize, clubServise.getAll().size());
		}
		try {
			final IPartnerContract entity2 = partnerContractService.createEntity();
			partnerContractService.save(entity2);
			fail("PartnerContract save should fail if name not specified");
		} catch (Exception e) {
			assertEquals(initialSize, partnerService.getAll().size());
		}
	}

	@Test
	public void testUpdate() throws InterruptedException {
		final IPartnerContract entity = saveNewPartnerContract();

		Thread.sleep(2000);
		partnerContractService.save(entity);

		final IPartnerContract entityFromDb = partnerContractService.get(entity.getId());

		assertNotNull(entityFromDb);
		assertNotNull(entityFromDb.getId());
		assertNotNull(entityFromDb.getDateSigning());
		assertNotNull(entityFromDb.getContractTerm());
		assertNotNull(entityFromDb.getContractValue());
		assertNotNull(entityFromDb.getCreated());
		assertNotNull(entityFromDb.getUpdated());
		assertEquals(entity.getCreated(), entityFromDb.getCreated());
		assertTrue(entityFromDb.getUpdated().after(entity.getCreated()));
	}

	@Test
	public void testGetAll() {
		final int intialCount = partnerContractService.getAll().size();

		final int randomObjectsCount = getRandomObjectsCount();
		for (int i = 0; i < randomObjectsCount; i++) {
			saveNewClub();
		}
		for (int i = 0; i < randomObjectsCount; i++) {
			saveNewPartner();
		}
		final List<IPartnerContract> allEntities = partnerContractService.getAll();

		for (final IPartnerContract entityFromDb : allEntities) {
			assertNotNull(entityFromDb.getId());
			assertNotNull(entityFromDb.getDateSigning());
			assertNotNull(entityFromDb.getContractTerm());
			assertNotNull(entityFromDb.getContractValue());
			assertNotNull(entityFromDb.getCreated());
			assertNotNull(entityFromDb.getUpdated());
		}

		assertEquals(randomObjectsCount + intialCount, allEntities.size());
	}

	@Test
	public void testDelete() {
		final IClub entity = saveNewClub();
		partnerContractService.delete(entity.getId());
		assertNull(partnerContractService.get(entity.getId()));
	}

	@Test
	public void testDeleteAll() {
		saveNewClub();
		partnerContractService.deleteAll();
		assertEquals(0, partnerContractService.getAll().size());
	}
}
