package com.itacademy.jd2.vn.sst.jdbc.impl.entity;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import com.itacademy.jd2.vn.sst.dao.api.entity.table.IEvent;
import com.itacademy.jd2.vn.sst.dao.api.entity.table.IStadium;
import com.itacademy.jd2.vn.sst.dao.api.entity.table.ISeasonTicket;

public class Event extends BaseEntity implements IEvent {
	private String name;
	private Date date;
	private IStadium stadium;
	private Set<ISeasonTicket> seasonTicket = new HashSet<>();

	@Override
	public final String getName() {
		return name;
	}

	@Override
	public final void setName(String name) {
		this.name = name;
	}

	@Override
	public final IStadium getStadium() {
		return stadium;
	}

	@Override
	public final void setStadium(IStadium stadium) {
		this.stadium = stadium;
	}

	public final Set<ISeasonTicket> getSeasonTicket() {
		return seasonTicket;
	}

	public final void setSeasonTicket(Set<ISeasonTicket> seasonTicket) {
		this.seasonTicket = seasonTicket;
	}

	@Override
	public Date getDate() {
		return null;
	}

	@Override
	public void setDate(Date date) {

	}

	@Override
	public String toString() {
		return "Event [name=" + name + ", getId()=" + getId() + "]";
	}

		
}
