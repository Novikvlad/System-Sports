package com.itacademy.jd2.vn.sst.service.impl;

import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.itacademy.jd2.vn.sst.dao.api.ITicketDao;
import com.itacademy.jd2.vn.sst.dao.api.entity.table.ITicket;
import com.itacademy.jd2.vn.sst.dao.api.filter.TicketFilter;
import com.itacademy.jd2.vn.sst.service.ITicketService;

@Service
public class TicketServiceImpl implements ITicketService {
	private static final Logger LOGGER = LoggerFactory.getLogger(EventServiseImpl.class);

	private ITicketDao dao;

	@Autowired
	public TicketServiceImpl(ITicketDao dao) {
		super();
		this.dao = dao;
	}

	@Override
	public ITicket createEntity() {
		return dao.createEntity();
	}

	@Override
	public ITicket get(final Integer id) {
		final ITicket entity = dao.get(id);
		return entity;
	}

	@Override
	public void save(final ITicket entity) {
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
	public void delete(final Integer id) {
		LOGGER.info("delete in DB:{}", id);
		dao.delete(id);
	}

	@Override
	public void deleteAll() {
		dao.deleteAll();
	}

	@Override
	public List<ITicket> getAll() {
		final List<ITicket> all = dao.selectAll();
		return all;
	}

	@Override
	public ITicket getFullInfo(Integer id) {
		return dao.getFullInfo(id);
	}

	@Override
	public List<ITicket> find(final TicketFilter filter) {
		return dao.find(filter);
	}

	@Override
	public long getCount(TicketFilter filter) {
		return dao.getCount(filter);
	}

}
