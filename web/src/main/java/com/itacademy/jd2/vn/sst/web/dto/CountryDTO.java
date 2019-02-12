package com.itacademy.jd2.vn.sst.web.dto;

import java.util.Date;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class CountryDTO {

	private Integer id;

	@Size(min = 1, max = 50)
	private String name;

	@NotNull
	private Integer regionId;

	private String regionName;

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

	public final Integer getRegionId() {
		return regionId;
	}

	public final void setRegionId(Integer regionId) {
		this.regionId = regionId;
	}

	public final String getRegionName() {
		return regionName;
	}

	public final void setRegionName(String regionName) {
		this.regionName = regionName;
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
