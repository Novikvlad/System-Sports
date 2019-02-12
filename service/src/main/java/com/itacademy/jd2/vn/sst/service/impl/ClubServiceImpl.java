package com.itacademy.jd2.vn.sst.service.impl;

import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.itacademy.jd2.vn.sst.dao.api.IClubDao;
import com.itacademy.jd2.vn.sst.dao.api.entity.table.IClub;
import com.itacademy.jd2.vn.sst.dao.api.filter.ClubFilter;
import com.itacademy.jd2.vn.sst.service.IClubService;

@Service
public class ClubServiceImpl implements IClubService {

	private static final Logger LOGGER = LoggerFactory.getLogger(EventServiseImpl.class);
	private IClubDao dao;

	@Autowired
	public ClubServiceImpl(IClubDao dao) {
		super();
		this.dao = dao;
	}

	@Override
	public IClub createEntity() {
		return dao.createEntity();
	}

	@Override
	public IClub get(Integer id) {
		
		final IClub entity = dao.get(id);
		LOGGER.debug("new saved stadiums", entity);
		return entity;
	}

	@Override
	public void save(IClub entity) {
		final Date modifedOn = new Date();
		entity.setUpdated(modifedOn);
		if (entity.getId() == null) {
			entity.setCreated(modifedOn);
			dao.insert(entity);
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
	public List<IClub> getAll() {
		final List<IClub> all = dao.selectAll();
		return all;
	}

	@Override
	public IClub getFullInfo(Integer id) {
		return dao.getFullInfo(id);
	}

	@Override
	public List<IClub> find(ClubFilter filter) {
		return dao.find(filter);
	}

	@Override
	public long getCount(ClubFilter filter) {
		return dao.getCount(filter);
	}

}
