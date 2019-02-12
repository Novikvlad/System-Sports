package com.itacademy.jd2.vn.sst.web.dto;

import java.util.Date;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class CityDTO {

	private Integer id;

	@Size(min = 1, max = 50)
	private String name;

	@NotNull
	private Integer countryId;

	private String countryName;

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

	public final Integer getCountryId() {
		return countryId;
	}

	public final void setCountryId(Integer countryId) {
		this.countryId = countryId;
	}

	public final String getCountryName() {
		return countryName;
	}

	public final void setCountryName(String countryName) {
		this.countryName = countryName;
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
