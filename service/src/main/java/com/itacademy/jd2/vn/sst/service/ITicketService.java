package com.itacademy.jd2.vn.sst.service;

import java.util.List;

import javax.transaction.Transactional;

import com.itacademy.jd2.vn.sst.dao.api.entity.table.ITicket;
import com.itacademy.jd2.vn.sst.dao.api.filter.TicketFilter;

public interface ITicketService {
	
	ITicket createEntity();

	ITicket get(Integer id);

	@Transactional
	void save(ITicket entity);

	@Transactional
	void delete(Integer id);

	@Transactional
	void deleteAll();

	List<ITicket> getAll();

	ITicket getFullInfo(final Integer id);

	List<ITicket> find(TicketFilter filter);

	long getCount(TicketFilter filter);
}
