package com.itacademy.jd2.vn.sst.service.impl;

import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.itacademy.jd2.vn.sst.dao.api.ISeasonTicketDao;
import com.itacademy.jd2.vn.sst.dao.api.entity.table.ISeasonTicket;
import com.itacademy.jd2.vn.sst.dao.api.filter.SeasonTicketFilter;
import com.itacademy.jd2.vn.sst.service.ISeasonTicketService;

@Service
public class SeasonTicketServiceImpl implements ISeasonTicketService {
	private static final Logger LOGGER = LoggerFactory.getLogger(SeasonTicketServiceImpl.class);
	private ISeasonTicketDao dao;

	@Autowired
	public SeasonTicketServiceImpl(ISeasonTicketDao dao) {
		super();
		this.dao = dao;
	}

	@Override
	public ISeasonTicket createEntity() {
		return dao.createEntity();
	}

	@Override
	public ISeasonTicket get(Integer id) {
		final ISeasonTicket entity = dao.get(id);
		LOGGER.debug("entityById: {}", entity);
		return entity;
	}

	@Override
	public List<ISeasonTicket> getAll() {

		final List<ISeasonTicket> all = dao.selectAll();
		LOGGER.debug("total count in DB: {}", all.size());
		return all;
	}

	@Override
	public void save(ISeasonTicket entity) {

		final Date modifedOn = new Date();
		entity.setUpdated(modifedOn);
		if (entity.getId() == null) {
			entity.setCreated(modifedOn);
			dao.insert(entity);
			LOGGER.info("new saved seasonTicket: {} ", entity);
		} else {
			dao.update(entity);
		}
	}

	@Override
	public void delete(final Integer id) {
		LOGGER.info("dalete entity: {}", id);
		dao.delete(id);
	}

	@Override
	public void deleteAll() {
		dao.deleteAll();
	}

	@Override
	public List<ISeasonTicket> find(SeasonTicketFilter filter) {
		return dao.find(filter);
	}

	@Override
	public long getCount(SeasonTicketFilter filter) {
		return dao.getCount(filter);
	}

}
