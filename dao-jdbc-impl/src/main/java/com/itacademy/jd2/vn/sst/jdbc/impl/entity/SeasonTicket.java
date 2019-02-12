package com.itacademy.jd2.vn.sst.jdbc.impl.entity;

import java.math.BigDecimal;
import java.util.Date;

import com.itacademy.jd2.vn.sst.dao.api.entity.enums.PresenceType;
import com.itacademy.jd2.vn.sst.dao.api.entity.table.ISeasonTicket;

public class SeasonTicket extends BaseEntity implements ISeasonTicket {
	private String name;
	private String sector;
	private String row;
	private String seat;
	private BigDecimal price;
	private Date date;

	public final String getName() {
		return name;
	}

	public final void setName(String name) {
		this.name = name;
	}

	public final String getSector() {
		return sector;
	}

	public final void setSector(String sector) {
		this.sector = sector;
	}

	public final String getRow() {
		return row;
	}

	public final void setRow(String row) {
		this.row = row;
	}

	public final String getSeat() {
		return seat;
	}

	public final void setSeat(String seat) {
		this.seat = seat;
	}

	public final BigDecimal getPrice() {
		return price;
	}

	public final void setPrice(BigDecimal price) {
		this.price = price;
	}

	public final Date getDate() {
		return date;
	}

	public final void setDate(Date date) {
		this.date = date;
	}



	@Override
	public String toString() {
		return "SeasonTicket [name=" + name + ", getId()=" + getId() + "]";
	}

	@Override
	public PresenceType getPresence() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void setPresence(PresenceType presence) {
		// TODO Auto-generated method stub
		
	}

	

}
