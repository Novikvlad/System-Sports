package com.itacademy.jd2.vn.sst.service.impl;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

import java.util.List;

import org.junit.jupiter.api.Test;

import com.itacademy.jd2.vn.sst.dao.api.entity.table.ITicket;

public class TicketServiceTest extends AbstractTest {
	@Test
	public void cteateTestTicket() {

		final ITicket entity = ticketService.createEntity();
		entity.setName("ticket-" + getRandomPrefix());
		entity.setSector("sector-" + getRandomPrefix());
		entity.setRow("row-" + getRandomPrefix());
		entity.setSeat("seat-" + getRandomPrefix());
		entity.setPrice(getRandomDecimal());
		entity.setPresence(getRandomPresenceType());
		entity.setEvent(saveNewEvent());
		ticketService.save(entity);

		final ITicket entityFromDB = ticketService.getFullInfo(entity.getId());

		assertNotNull(entityFromDB.getId());
		assertEquals(entity.getName(), entityFromDB.getName());
		assertEquals(entity.getSector(), entityFromDB.getSector());
		assertEquals(entity.getRow(), entityFromDB.getRow());
		assertEquals(entity.getSeat(), entityFromDB.getSeat());
		assertEquals(entity.getPrice(), entityFromDB.getPrice());
		assertEquals(entity.getPresence(), entityFromDB.getPresence());
		assertEquals(entity.getEvent().getId(), entityFromDB.getEvent().getId());
		assertNotNull(entityFromDB.getCreated());
		assertNotNull(entityFromDB.getUpdated());
	}

	@Test
	public void testCreateMultiple() {
		int initialSize = ticketService.getAll().size();

		final ITicket entity1 = ticketService.createEntity();
		entity1.setName("ticket-" + getRandomPrefix());

		try {
			final ITicket entity2 = ticketService.createEntity();
			ticketService.save(entity2);
			fail("Stadium save should fail if name not specified");
		} catch (Exception e) {
			assertEquals(initialSize, eventServise.getAll().size());
		}

	}

	@Test
	public void testUpdate() throws InterruptedException {
		final ITicket entity = saveNewTicket();

		String newName = entity.getName() + "_updated";
		entity.setName(newName);
		Thread.sleep(2000);
		ticketService.save(entity);

		final ITicket entityFromDb = ticketService.get(entity.getId());

		assertNotNull(entityFromDb);
		assertEquals(newName, entityFromDb.getName());
		assertNotNull(entityFromDb.getId());
		assertNotNull(entityFromDb.getSector());
		assertNotNull(entityFromDb.getRow());
		assertNotNull(entityFromDb.getSeat());
		assertNotNull(entityFromDb.getPrice());
		assertNotNull(entityFromDb.getPresence());
		assertNotNull(entityFromDb.getCreated());
		assertNotNull(entityFromDb.getUpdated());
		assertEquals(entity.getCreated(), entityFromDb.getCreated());
		assertTrue(entityFromDb.getUpdated().after(entity.getCreated()));
	}

	@Test
	public void testGetAll() {
		final int intialCount = ticketService.getAll().size();

		final int randomObjectsCount = getRandomObjectsCount();
		for (int i = 0; i < randomObjectsCount; i++) {
			saveNewEvent();
		}

		final List<ITicket> allEntities = ticketService.getAll();

		for (final ITicket entityFromDb : allEntities) {
			assertNotNull(entityFromDb.getName());
			assertNotNull(entityFromDb.getId());
			assertNotNull(entityFromDb.getSector());
			assertNotNull(entityFromDb.getRow());
			assertNotNull(entityFromDb.getSeat());
			assertNotNull(entityFromDb.getPrice());
			assertNotNull(entityFromDb.getPresence());
			assertNotNull(entityFromDb.getCreated());
			assertNotNull(entityFromDb.getUpdated());
		}

		assertEquals(randomObjectsCount + intialCount, allEntities.size());
	}

	@Test
	public void testDelete() {
		final ITicket entity = saveNewTicket();
		stadiumService.delete(entity.getId());
		assertNull(stadiumService.get(entity.getId()));
	}

	@Test
	public void testDeleteAll() {
		saveNewTicket();
		stadiumService.deleteAll();
		assertEquals(0, stadiumService.getAll().size());
	}

}
