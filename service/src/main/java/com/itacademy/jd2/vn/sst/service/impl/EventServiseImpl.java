package com.itacademy.jd2.vn.sst.service.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.itacademy.jd2.vn.sst.dao.api.IEventDao;
import com.itacademy.jd2.vn.sst.dao.api.entity.table.IEvent;
import com.itacademy.jd2.vn.sst.dao.api.filter.EventFilter;
import com.itacademy.jd2.vn.sst.service.IEventServise;

@Service
public class EventServiseImpl implements IEventServise {

	private IEventDao dao;

	@Autowired
	public EventServiseImpl(IEventDao dao) {
		super();
		this.dao = dao;

	}

	@Override
	public IEvent createEntity() {
		return dao.createEntity();
	}

	@Override
	public IEvent get(Integer id) {

		final IEvent entity = dao.get(id);
		return entity;
	}

	@Override
	public List<IEvent> getAll() {

		final List<IEvent> all = dao.selectAll();
		return all;
	}

	@Override
	public void save(IEvent entity) {

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

		final IEvent iEvent = dao.get(id);
		iEvent.getSeasonTicket().clear();
		dao.update(iEvent);
		dao.delete(id);

	}

	@Override
	public void deleteAll() {
		dao.deleteAll();

	}

	@Override
	public IEvent getFullInfo(Integer id) {

		final IEvent entity = dao.getFullInfo(id);
		return entity;
	}

	@Override
	public long getCount(EventFilter filter) {
		return dao.getCount(filter);
	}

	@Override
	public List<IEvent> find(EventFilter filter) {
		return dao.find(filter);
	}

}
