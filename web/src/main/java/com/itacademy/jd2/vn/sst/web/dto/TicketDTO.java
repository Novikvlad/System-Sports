package com.itacademy.jd2.vn.sst.web.dto;

import java.math.BigDecimal;
import java.util.Date;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.itacademy.jd2.vn.sst.dao.api.entity.enums.PresenceType;

public class TicketDTO {

	private Integer id;
	@Size(min = 1, max = 50)
	private String name;
	@Size(min = 1, max = 4)
	private String Sector;
	@Size(min = 1, max = 50)
	private String row;
	@Size(min = 1, max = 50)
	private String seat;
	private BigDecimal price;
	private PresenceType presence;
	@NotNull
	private Integer eventId;
	private String eventName;
	private Date created;
	private Date updated;

	public final Integer getId() {
		return id;
	}

	public final void setId(Integer id) {
		this.id = id;
	}

	public final String getName() {
		return name;
	}

	public final void setName(String name) {
		this.name = name;
	}

	public final String getSector() {
		return Sector;
	}

	public final void setSector(String sector) {
		Sector = sector;
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

	public final PresenceType getPresence() {
		return presence;
	}

	public final void setPresence(PresenceType presence) {
		this.presence = presence;
	}

	public final Integer getEventId() {
		return eventId;
	}

	public final void setEventId(Integer eventId) {
		this.eventId = eventId;
	}

	public final String getEventName() {
		return eventName;
	}

	public final void setEventName(String eventName) {
		this.eventName = eventName;
	}

	public final Date getCreated() {
		return created;
	}

	public final void setCreated(Date created) {
		this.created = created;
	}

	public final Date getUpdated() {
		return updated;
	}

	public final void setUpdated(Date updated) {
		this.updated = updated;
	}

}
