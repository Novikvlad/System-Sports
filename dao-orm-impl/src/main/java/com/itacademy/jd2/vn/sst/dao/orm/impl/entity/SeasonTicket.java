package com.itacademy.jd2.vn.sst.dao.orm.impl.entity;

import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;

import com.itacademy.jd2.vn.sst.dao.api.entity.enums.PresenceType;
import com.itacademy.jd2.vn.sst.dao.api.entity.table.ISeasonTicket;

@Entity
public class SeasonTicket extends BaseEntity implements ISeasonTicket {

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
	private Date date;
	@Column
	private PresenceType presence;

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
	public final Date getDate() {
		return date;
	}

	@Override
	public final void setDate(Date date) {
		this.date = date;
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
	public String toString() {
		return "SeasonTicket [name=" + name + ", getId()=" + getId() + "]";
	}

	

}
