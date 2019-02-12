package com.itacademy.jd2.vn.sst.web.dto;

import java.util.Date;
import java.util.Set;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.springframework.format.annotation.DateTimeFormat;

public class EventDTO {

	private Integer id;
	@Size(min = 1, max = 50)
	private String name;
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date date;
	@DateTimeFormat(pattern = "hh:mm a")
	private Date time;
	@NotNull
	private Integer stadiumId;
	private String stadiumName;
	private Set<Integer> seasonTicketIds;
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

	public final Date getDate() {
		return date;
	}

	public final void setDate(Date date) {
		this.date = date;
	}

	public final Date getTime() {
		return time;
	}

	public final void setTime(Date time) {
		this.time = time;
	}

	public final Integer getStadiumId() {
		return stadiumId;
	}

	public final void setStadiumId(Integer stadiumId) {
		this.stadiumId = stadiumId;
	}

	public final String getStadiumName() {
		return stadiumName;
	}

	public final void setStadiumName(String stadiumName) {
		this.stadiumName = stadiumName;
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

	public final Set<Integer> getSeasonTicketIds() {
		return seasonTicketIds;
	}

	public final void setSeasonTicketIds(Set<Integer> seasonTicketIds) {
		this.seasonTicketIds = seasonTicketIds;
	}

}
