package com.itacademy.jd2.vn.sst.web.dto;

import java.util.Date;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.springframework.format.annotation.DateTimeFormat;

public class ClubDTO {

	private Integer id;
	@Size(min = 1, max = 50)
	private String name;
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date based;
	@NotNull
	private Integer stadiumId;
	@Size(min = 1, max = 50)
	private String stadiumName;
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

	public final Date getBased() {
		return based;
	}

	public final void setBased(Date based) {
		this.based = based;
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

}
