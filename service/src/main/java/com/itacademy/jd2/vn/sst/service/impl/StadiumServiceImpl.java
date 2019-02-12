package com.itacademy.jd2.vn.sst.service.impl;

import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.itacademy.jd2.vn.sst.dao.api.IStadiumDao;
import com.itacademy.jd2.vn.sst.dao.api.entity.table.IStadium;
import com.itacademy.jd2.vn.sst.dao.api.filter.StadiumFilter;
import com.itacademy.jd2.vn.sst.service.IStadiumService;

@Service
public class StadiumServiceImpl implements IStadiumService {

	private static final Logger LOGGER = LoggerFactory.getLogger(StadiumServiceImpl.class);
	private IStadiumDao dao;

	@Autowired
	public StadiumServiceImpl(IStadiumDao dao) {
		super();
		this.dao = dao;
	}

	@Override
	public IStadium createEntity() {
		return dao.createEntity();
	}

	@Override
	public IStadium get(Integer id) {

		final IStadium entity = dao.get(id);
		LOGGER.debug("new saved stadiums", entity);
		return entity;
	}

	@Override
	public void save(IStadium entity) {
		final Date modifedOn = new Date();
		entity.setUpdated(modifedOn);
		if (entity.getId() == null) {
			entity.setCreated(modifedOn);
			dao.insert(entity);
			LOGGER.info("new saved stadiums:{}", entity);
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
	public List<IStadium> getAll() {
		final List<IStadium> all = dao.selectAll();
		return all;
	}

	@Override
	public IStadium getFullInfo(Integer id) {
		return dao.getFullInfo(id);
	}

	@Override
	public List<IStadium> find(StadiumFilter filter) {
		return dao.find(filter);
	}

	@Override
	public long getCount(StadiumFilter filter) {
		return dao.getCount(filter);
	}

}
