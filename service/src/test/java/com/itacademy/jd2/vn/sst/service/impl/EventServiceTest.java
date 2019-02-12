package com.itacademy.jd2.vn.sst.service.impl;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Set;

import org.junit.jupiter.api.Test;

import com.itacademy.jd2.vn.sst.dao.api.entity.table.IEvent;
import com.itacademy.jd2.vn.sst.dao.api.entity.table.ISeasonTicket;

public class EventServiceTest extends AbstractTest {

	@Test
	public void createTestEvent() {
		final IEvent entity = eventServise.createEntity();
		entity.setName("event-" + getRandomPrefix());
		entity.setDate(new Date());
		entity.setStadium(saveNewStadium());
		eventServise.save(entity);

		final IEvent entityFromDb = eventServise.getFullInfo(entity.getId());

		assertEquals(entity.getName(), entityFromDb.getName());
		assertEquals(entity.getStadium().getId(), entityFromDb.getStadium().getId());
		assertNotNull(entityFromDb.getId());
		assertNotNull(entityFromDb.getCreated());
		assertNotNull(entityFromDb.getUpdated());
	}

	@Test
	public void createSeasonTicketWithEventTest() {
		final IEvent entity = eventServise.createEntity();
		entity.setName("event-" + getRandomPrefix());
		entity.setStadium(saveNewStadium());

		final int randomObjectsCount = getRandomObjectsCount();
		final List<ISeasonTicket> seasonTickets = new ArrayList<>();
		for (int i = 0; i < randomObjectsCount; i++) {
			seasonTickets.add(saveNewSeasonTicket());
		}
		entity.getSeasonTicket().addAll(seasonTickets);

		eventServise.save(entity);

		final IEvent entityFromDb = eventServise.getFullInfo(entity.getId());
		final Set<ISeasonTicket> enginesFromDb = entityFromDb.getSeasonTicket();
		assertEquals(seasonTickets.size(), enginesFromDb.size());

		// check that correct (by id) engines were returned
		for (final ISeasonTicket seasonTicket : seasonTickets) {
			boolean found = false;
			for (final ISeasonTicket dbEngine : enginesFromDb) {
				if (seasonTicket.getId().equals(dbEngine.getId())) {
					found = true;
					break;
				}

			}
			assertTrue(found, "can't find entity:" + seasonTicket);
		}
	}

}
