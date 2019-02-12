package com.itacademy.jd2.vn.sst.dao.api;

import java.util.List;

import com.itacademy.jd2.vn.sst.dao.api.entity.table.ITicket;
import com.itacademy.jd2.vn.sst.dao.api.filter.TicketFilter;


public interface ITicketDao extends IDao<ITicket, Integer>{
	
	ITicket getFullInfo(final Integer id);

	List<ITicket> find(TicketFilter filter);

	long getCount(TicketFilter filter);
}
