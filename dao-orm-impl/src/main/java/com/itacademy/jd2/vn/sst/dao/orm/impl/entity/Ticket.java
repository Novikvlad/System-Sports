package com.itacademy.jd2.vn.sst.dao.orm.impl.entity;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;
import javax.persistence.Version;

import com.itacademy.jd2.vn.sst.dao.api.entity.enums.PresenceType;
import com.itacademy.jd2.vn.sst.dao.api.entity.table.IEvent;
import com.itacademy.jd2.vn.sst.dao.api.entity.table.ITicket;

@Entity
public class Ticket extends BaseEntity implements ITicket {

	@Column
	private String name;
	@Column
	private String sector;
	@Column
	private String row;
	@Column
	private String seat;
	@Column
	private BigDecimal price;
	@Column
	private PresenceType presence;
	@ManyToOne(fetch = FetchType.LAZY, targetEntity = Event.class)
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
	public final void setPrice(BigDecimal price) {
		this.price = price;
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
	public final IEvent getEvent() {
		return event;
	}

	@Override
	public final void setEvent(IEvent event) {
		this.event = event;
	}

}
