package com.itacademy.jd2.vn.sst.dao.api;

import java.util.List;
import java.util.Set;

import com.itacademy.jd2.vn.sst.dao.api.entity.table.ISeasonTicket;
import com.itacademy.jd2.vn.sst.dao.api.filter.SeasonTicketFilter;

public interface ISeasonTicketDao extends IDao<ISeasonTicket, Integer> {

	Set<ISeasonTicket> getByEvent(Integer id);

	List<ISeasonTicket> find(SeasonTicketFilter filter);

	long getCount(SeasonTicketFilter filter);

}
