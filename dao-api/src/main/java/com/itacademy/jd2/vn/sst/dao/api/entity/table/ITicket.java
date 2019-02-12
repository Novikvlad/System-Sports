package com.itacademy.jd2.vn.sst.dao.api.entity.table;

import java.math.BigDecimal;

import com.itacademy.jd2.vn.sst.dao.api.entity.enums.PresenceType;

public interface ITicket extends IBaseEntity {

	String getName();

	void setName(String name);

	String getSector();

	void setSector(String sector);

	String getRow();

	void setRow(String row);

	String getSeat();

	void setSeat(String seat);

	BigDecimal getPrice();

	void setPrice(BigDecimal price);

	PresenceType getPresence();

	void setPresence(PresenceType presence);

	IEvent getEvent();

	void setEvent(IEvent event);

}
