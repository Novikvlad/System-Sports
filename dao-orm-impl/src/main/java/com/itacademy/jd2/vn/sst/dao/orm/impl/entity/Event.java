package com.itacademy.jd2.vn.sst.dao.orm.impl.entity;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;

import com.itacademy.jd2.vn.sst.dao.api.entity.table.IEvent;
import com.itacademy.jd2.vn.sst.dao.api.entity.table.IStadium;
import com.itacademy.jd2.vn.sst.dao.api.entity.table.ISeasonTicket;

@Entity
public class Event extends BaseEntity implements IEvent {
	@Column
	private String name;
	@Column
	private Date date;
	@ManyToOne(fetch = FetchType.LAZY, targetEntity = Stadium.class)
	private IStadium stadium;
	@JoinTable(name = "event_2_season_ticket", joinColumns = { @JoinColumn(name = "event_id") }, inverseJoinColumns = {
			@JoinColumn(name = "season_ticket_id") })
	@ManyToMany(targetEntity = SeasonTicket.class, fetch = FetchType.LAZY)
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

	@Override
	public final Set<ISeasonTicket> getSeasonTicket() {
		return seasonTicket;
	}

	@Override
	public final void setSeasonTicket(Set<ISeasonTicket> seasonTicket) {
		this.seasonTicket = seasonTicket;
	}

	@Override
	public final Date getDate() {
		return date;
	}

	@Override
	public final void setDate(Date date) {
		this.date = date;
	}

}
