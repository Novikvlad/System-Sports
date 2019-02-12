package com.itacademy.jd2.vn.sst.dao.api;

import java.util.List;

import com.itacademy.jd2.vn.sst.dao.api.entity.table.IEvent;
import com.itacademy.jd2.vn.sst.dao.api.filter.EventFilter;

public interface IEventDao extends IDao<IEvent, Integer> {
	
	IEvent getFullInfo(final Integer id);

	long getCount(EventFilter filter);

	List<IEvent> find(EventFilter filter);
}
