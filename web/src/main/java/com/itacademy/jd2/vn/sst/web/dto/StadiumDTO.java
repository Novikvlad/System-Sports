package com.itacademy.jd2.vn.sst.web.dto;

import java.util.Date;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.springframework.format.annotation.DateTimeFormat;

public class StadiumDTO {

	private Integer id;
	@Size(min = 1, max = 50)
	private String name;
	private Integer capacity;
	private String address;

	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date based;
	@NotNull
	private Integer cityId;
	private String cityName;
	private Integer ticketId;
	private String ticketName;
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

	public final Integer getCapacity() {
		return capacity;
	}

	public final void setCapacity(Integer capacity) {
		this.capacity = capacity;
	}

	public final String getAddress() {
		return address;
	}

	public final void setAddress(String address) {
		this.address = address;
	}

	public final Date getBased() {
		return based;
	}

	public final void setBased(Date based) {
		this.based = based;
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

	public final Integer getTicketId() {
		return ticketId;
	}

	public final void setTicketId(Integer ticketId) {
		this.ticketId = ticketId;
	}

	public final String getTicketName() {
		return ticketName;
	}

	public final void setTicketName(String ticketName) {
		this.ticketName = ticketName;
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
