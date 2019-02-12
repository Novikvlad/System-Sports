package com.itacademy.jd2.vn.sst.service.impl;

import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.itacademy.jd2.vn.sst.dao.api.IRegionDao;
import com.itacademy.jd2.vn.sst.dao.api.entity.table.IRegion;
import com.itacademy.jd2.vn.sst.dao.api.filter.RegionFilter;
import com.itacademy.jd2.vn.sst.service.IRegionService;

@Service
public class RegionServiceImpl implements IRegionService {

	private static final Logger LOGGER = LoggerFactory.getLogger(RegionServiceImpl.class);

	private IRegionDao dao;

	@Autowired
	public RegionServiceImpl(IRegionDao dao) {
		super();
		this.dao = dao;
	}

	@Override
	public IRegion createEntity() {
		return dao.createEntity();
	}

	@Override
	public void save(final IRegion entity) {
		final Date modifedOn = new Date();
		entity.setUpdated(modifedOn);
		if (entity.getId() == null) {
			LOGGER.info("new region created: {}", entity);
			entity.setCreated(modifedOn);
			dao.insert(entity);
		} else {
			LOGGER.debug("region updated: {}", entity);
			dao.update(entity);
		}
	}

	@Override
	public IRegion get(final Integer id) {
		final IRegion entity = dao.get(id);
		return entity;
	}

	@Override
	public void delete(final Integer id) {
		dao.delete(id);
	}

	@Override
	public void deleteAll() {
		LOGGER.info("delete all regions");
		dao.deleteAll();
	}

	@Override
	public List<IRegion> getAll() {
		final List<IRegion> all = dao.selectAll();
		return all;
	}

	@Override
	public void save(IRegion... entities) {
		Date modified = new Date();
		for (IRegion iRegion : entities) {

			iRegion.setUpdated(modified);
			iRegion.setCreated(modified);

		}

		dao.save(entities);
	}

	@Override
	public List<IRegion> find(RegionFilter filter) {
		return dao.find(filter);
	}

	@Override
	public long getCount(RegionFilter filter) {
		return dao.getCount(filter);
	}

}
