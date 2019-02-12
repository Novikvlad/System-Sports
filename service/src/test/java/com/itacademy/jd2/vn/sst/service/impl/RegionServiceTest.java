package com.itacademy.jd2.vn.sst.service.impl;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

import java.util.List;

import org.junit.jupiter.api.Test;

import com.itacademy.jd2.vn.sst.dao.api.entity.table.IRegion;

public class RegionServiceTest extends AbstractTest {

    @Test
    public void testCreate() {
        final IRegion entity = saveNewRegion();

        final IRegion entityFromDb = regionService.get(entity.getId());

        assertNotNull(entityFromDb);
        assertEquals(entity.getName(), entityFromDb.getName());
        assertNotNull(entityFromDb.getId());
        assertNotNull(entityFromDb.getCreated());
        assertNotNull(entityFromDb.getUpdated());
        assertTrue(entityFromDb.getCreated().equals(entityFromDb.getUpdated()));
    }

    @Test
    public void testCreateMultiple() {
        int initialSize = regionService.getAll().size();

        final IRegion entity1 = regionService.createEntity();
        entity1.setName("region-" + getRandomPrefix());

        try {
            final IRegion entity2 = regionService.createEntity();
            regionService.save(entity1, entity2);
            fail("Region save should fail if name not specified");
        } catch (Exception e) {
            assertEquals(initialSize, regionService.getAll().size());
        }

    }

    @Test
    public void testUpdate() throws InterruptedException {
        final IRegion entity = saveNewRegion();

        String newName = entity.getName() + "_updated";
        entity.setName(newName);
        Thread.sleep(2000);
        regionService.save(entity);

        final IRegion entityFromDb = regionService.get(entity.getId());

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
        final int intialCount = regionService.getAll().size();

        final int randomObjectsCount = getRandomObjectsCount();
        for (int i = 0; i < randomObjectsCount; i++) {
            saveNewRegion();
        }

        final List<IRegion> allEntities = regionService.getAll();

        for (final IRegion entityFromDb : allEntities) {
            assertNotNull(entityFromDb.getName());
            assertNotNull(entityFromDb.getId());
            assertNotNull(entityFromDb.getCreated());
            assertNotNull(entityFromDb.getUpdated());
        }

        assertEquals(randomObjectsCount + intialCount, allEntities.size());
    }

    @Test
    public void testDelete() {
        final IRegion entity = saveNewRegion();
        regionService.delete(entity.getId());
        assertNull(regionService.get(entity.getId()));
    }

    @Test
    public void testDeleteAll() {
        saveNewRegion();
        regionService.deleteAll();
        assertEquals(0, regionService.getAll().size());
    }
}
