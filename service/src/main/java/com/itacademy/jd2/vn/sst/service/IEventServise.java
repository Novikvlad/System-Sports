package com.itacademy.jd2.vn.sst.service;

import java.util.List;

import javax.transaction.Transactional;

import com.itacademy.jd2.vn.sst.dao.api.entity.table.IClub;
import com.itacademy.jd2.vn.sst.dao.api.entity.table.IEvent;
import com.itacademy.jd2.vn.sst.dao.api.filter.EventFilter;

public interface IEventServise {
	
	
	IEvent get(Integer id);

	List<IEvent> getAll();

	@Transactional
	void save(IEvent entity);

	@Transactional
	void delete(Integer id);

	@Transactional
	void deleteAll();

	IEvent createEntity();

	IEvent getFullInfo(final Integer id);

	List<IEvent> find(EventFilter filter);

	long getCount(EventFilter filter);
}
