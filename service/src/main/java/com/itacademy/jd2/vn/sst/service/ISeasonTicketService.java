package com.itacademy.jd2.vn.sst.service;

import java.util.List;

import javax.transaction.Transactional;

import com.itacademy.jd2.vn.sst.dao.api.entity.table.ISeasonTicket;
import com.itacademy.jd2.vn.sst.dao.api.filter.SeasonTicketFilter;

public interface ISeasonTicketService {

	ISeasonTicket createEntity();

	
	@Transactional
	void save(ISeasonTicket entity);

	@Transactional
	void delete(Integer id);

	@Transactional
	void deleteAll();

	ISeasonTicket get(Integer id);

	List<ISeasonTicket> getAll();

	List<ISeasonTicket> find(SeasonTicketFilter filter);

	long getCount(SeasonTicketFilter filter);
}
