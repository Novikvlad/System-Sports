package com.itacademy.jd2.vn.sst.dao.api.entity.table;

import java.util.Date;
import java.util.Set;

public interface IEvent extends IBaseEntity {
	String getName();

	void setName(String name);

	Date getDate();

	void setDate(Date date);

	IStadium getStadium();

	void setStadium(IStadium stadium);

	Set<ISeasonTicket> getSeasonTicket();

	void setSeasonTicket(Set<ISeasonTicket> seasonTicket);
}
