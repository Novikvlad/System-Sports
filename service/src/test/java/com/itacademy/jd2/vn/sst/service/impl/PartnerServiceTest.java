package com.itacademy.jd2.vn.sst.service.impl;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

import java.util.List;

import org.junit.jupiter.api.Test;

import com.itacademy.jd2.vn.sst.dao.api.entity.table.IPartner;


public class PartnerServiceTest extends AbstractTest {
	  @Test
	    public void testCreate() {
	        final IPartner entity = saveNewPartner();

	        final IPartner entityFromDb = partnerService.get(entity.getId());

	        assertNotNull(entityFromDb);
	        assertEquals(entity.getName(), entityFromDb.getName());
	        assertNotNull(entityFromDb.getId());
	        assertNotNull(entityFromDb.getCreated());
	        assertNotNull(entityFromDb.getUpdated());
	        assertTrue(entityFromDb.getCreated().equals(entityFromDb.getUpdated()));
	    }

	    @Test
	    public void testCreateMultiple() {
	        int initialSize = partnerService.getAll().size();

	        final IPartner entity1 = partnerService.createEntity();
	        entity1.setName("partner-" + getRandomPrefix());

	        try {
	            final IPartner entity2 = partnerService.createEntity();
	            partnerService.save(entity1, entity2);
	            fail("Partner save should fail if name not specified");
	        } catch (Exception e) {
	            assertEquals(initialSize, partnerService.getAll().size());
	        }

	    }

	    @Test
	    public void testUpdate() throws InterruptedException {
	        final IPartner entity = saveNewPartner();

	        String newName = entity.getName() + "_updated";
	        entity.setName(newName);
	        Thread.sleep(2000);
	        partnerService.save(entity);

	        final IPartner entityFromDb = partnerService.get(entity.getId());

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
	        final int intialCount = partnerService.getAll().size();

	        final int randomObjectsCount = getRandomObjectsCount();
	        for (int i = 0; i < randomObjectsCount; i++) {
	        	saveNewPartner();
	        }

	        final List<IPartner> allEntities = partnerService.getAll();

	        for (final IPartner entityFromDb : allEntities) {
	            assertNotNull(entityFromDb.getName());
	            assertNotNull(entityFromDb.getId());
	            assertNotNull(entityFromDb.getCreated());
	            assertNotNull(entityFromDb.getUpdated());
	        }

	        assertEquals(randomObjectsCount + intialCount, allEntities.size());
	    }

	    @Test
	    public void testDelete() {
	        final IPartner entity = saveNewPartner();
	        partnerService.delete(entity.getId());
	        assertNull(partnerService.get(entity.getId()));
	    }

	    @Test
	    public void testDeleteAll() {
	        saveNewRegion();
	        partnerService.deleteAll();
	        assertEquals(0, partnerService.getAll().size());
	    }
}
