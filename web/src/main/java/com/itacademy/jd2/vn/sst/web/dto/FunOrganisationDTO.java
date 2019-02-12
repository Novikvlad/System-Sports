package com.itacademy.jd2.vn.sst.web.dto;

import java.math.BigDecimal;
import java.util.Date;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class FunOrganisationDTO {
	private Integer id;
	@Size(min = 1, max = 50)
	private String name;
	@NotNull
	private Integer clubId;
	@Size(min = 1, max = 50)
	private String clubName;
	@NotNull
	private Integer cityId;
	@Size(min = 1, max = 50)
	private String cityName;
	private BigDecimal deposit;
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

	public final Integer getClubId() {
		return clubId;
	}

	public final void setClubId(Integer clubId) {
		this.clubId = clubId;
	}

	public final String getClubName() {
		return clubName;
	}

	public final void setClubName(String clubName) {
		this.clubName = clubName;
	}

	public final Integer getCityId() {
		return cityId;
	}

	public final void setCityId(Integer cityId) {
		this.cityId = cityId;
	}

	public final String getCityName() {
		return cityName;
	}

	public final void setCityName(String cityName) {
		this.cityName = cityName;
	}

	public final BigDecimal getDeposit() {
		return deposit;
	}

	public final void setDeposit(BigDecimal deposit) {
		this.deposit = deposit;
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
