package com.itacademy.jd2.vn.sst.jdbc.impl.entity;

import java.math.BigDecimal;

import com.itacademy.jd2.vn.sst.dao.api.entity.enums.PresenceType;
import com.itacademy.jd2.vn.sst.dao.api.entity.table.IEvent;
import com.itacademy.jd2.vn.sst.dao.api.entity.table.ITicket;

public class Ticket extends BaseEntity implements ITicket {

	private String name;
	private String sector;
	private String row;
	private String seat;
	private BigDecimal price;
	private PresenceType presence;
	private IEvent event;

	@Override
	public final String getName() {
		return name;
	}

	@Override
	public final void setName(String name) {
		this.name = name;
	}

	@Override
	public final String getSector() {
		return sector;
	}

	@Override
	public final void setSector(String sector) {
		this.sector = sector;
	}

	@Override
	public final String getRow() {
		return row;
	}

	@Override
	public final void setRow(String row) {
		this.row = row;
	}

	@Override
	public final String getSeat() {
		return seat;
	}

	@Override
	public final void setSeat(String seat) {
		this.seat = seat;
	}

	@Override
	public final BigDecimal getPrice() {
		return price;
	}

	@Override
	public final IEvent getEvent() {
		return event;
	}

	@Override
	public final void setEvent(IEvent event) {
		this.event = event;
	}

	@Override

	public final PresenceType getPresence() {
		return presence;
	}

	@Override

	public final void setPresence(PresenceType presence) {
		this.presence = presence;
	}

	@Override
	public final void setPrice(BigDecimal price) {
		this.price = price;
	}

	@Override
	public String toString() {
		return "Ticket [name=" + name + ", sector=" + sector + ", row=" + row + ", seat=" + seat + ", getId()="
				+ getId() + "]";

	}

}
