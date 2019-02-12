package com.itacademy.jd2.vn.sst.service.impl;

import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.itacademy.jd2.vn.sst.dao.api.ICityDao;
import com.itacademy.jd2.vn.sst.dao.api.entity.table.ICity;
import com.itacademy.jd2.vn.sst.dao.api.filter.CityFilter;
import com.itacademy.jd2.vn.sst.service.ICityService;

@Service
public class CityServiceImpl implements ICityService {
	private static final Logger LOGGER = LoggerFactory.getLogger(CityServiceImpl.class);
	private ICityDao dao;
	@Autowired
	public CityServiceImpl(ICityDao dao) {
		super();
		this.dao = dao;
	}

	@Override
	public ICity createEntity() {
		return dao.createEntity();
	}

	@Override
	public ICity get(Integer id) {
		final ICity entity = dao.get(id);
		LOGGER.debug("new saved cities", entity);
		return entity;
	}

	@Override
	public void save(ICity entity) {
		final Date modifedOn = new Date();
		entity.setUpdated(modifedOn);
		if (entity.getId() == null) {
			entity.setCreated(modifedOn);
			dao.insert(entity);
			LOGGER.info("new saved cities:{}", entity);
		} else {
			dao.update(entity);
		}
	}

	@Override
	public void delete(Integer id) {
		LOGGER.info("delete in DB:{}", id);
		dao.delete(id);
	}

	@Override
	public void deleteAll() {
		dao.deleteAll();
	}

	@Override
	public List<ICity> getAll() {
		final List<ICity> all = dao.selectAll();
		LOGGER.debug("total count in DB:{}", all.size());
		return all;
	}

	@Override
	public ICity getFullInfo(Integer id) {
		return dao.getFullInfo(id);
	}

	@Override
	public List<ICity> find(CityFilter filter) {
		return dao.find(filter);
	}

	@Override
	public long getCount(CityFilter filter) {
		return dao.getCount(filter);
	}

}
