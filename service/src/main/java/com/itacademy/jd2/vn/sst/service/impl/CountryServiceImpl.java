package com.itacademy.jd2.vn.sst.service.impl;

import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.itacademy.jd2.vn.sst.dao.api.ICountryDao;
import com.itacademy.jd2.vn.sst.dao.api.entity.table.ICountry;
import com.itacademy.jd2.vn.sst.dao.api.filter.CountryFilter;
import com.itacademy.jd2.vn.sst.service.ICountryService;

@Service
public class CountryServiceImpl implements ICountryService {

	private static final Logger LOGGER = LoggerFactory.getLogger(CountryServiceImpl.class);

	private ICountryDao dao;

	@Autowired
	public CountryServiceImpl(ICountryDao dao) {
		super();
		this.dao = dao;

	}

	@Override
	public ICountry createEntity() {
		return dao.createEntity();
	}

	@Override
	public void save(final ICountry entity) {
		final Date modifedOn = new Date();
		entity.setUpdated(modifedOn);
		if (entity.getId() == null) {
			entity.setCreated(modifedOn);
			dao.insert(entity);
			LOGGER.info("new saved countries:{}", entity);
		} else {
			dao.update(entity);
		}
	}

	@Override
	public ICountry get(final Integer id) {
		final ICountry entity = dao.get(id);
		LOGGER.debug("entityById: {}", entity);
		return entity;
	}

	@Override
	public void delete(final Integer id) {
		LOGGER.info("delete in DB:{}", id);
		dao.delete(id);
	}

	@Override
	public void deleteAll() {
		dao.deleteAll();
	}

	@Override
	public List<ICountry> getAll() {
		final List<ICountry> all = dao.selectAll();
		LOGGER.debug("total count in DB:{}", all.size());
		return all;
	}

	@Override
	public List<ICountry> find(CountryFilter filter) {
		return dao.find(filter);
	}

	@Override
	public long getCount(final CountryFilter filter) {
		return dao.getCount(filter);
	}

	@Override
	public ICountry getFullInfo(Integer id) {
		return dao.getFullInfo(id);
	}

	

}
