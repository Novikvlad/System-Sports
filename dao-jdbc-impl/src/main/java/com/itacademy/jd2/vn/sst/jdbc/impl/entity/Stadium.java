package com.itacademy.jd2.vn.sst.jdbc.impl.entity;

import java.util.Date;

import com.itacademy.jd2.vn.sst.dao.api.entity.table.ICity;
import com.itacademy.jd2.vn.sst.dao.api.entity.table.IStadium;

public class Stadium extends BaseEntity implements IStadium {

	private String name;
	private Integer capacity;
	private String address;
	private Date based;
	private ICity сity;

	@Override
	public final String getName() {
		return name;
	}

	@Override
	public final void setName(String name) {
		this.name = name;
	}

	@Override
	public final Integer getCapacity() {
		return capacity;
	}

	@Override
	public final void setCapacity(Integer capacity) {
		this.capacity = capacity;
	}

	@Override
	public final String getAddress() {
		return address;
	}

	@Override
	public final void setAddress(String address) {
		this.address = address;
	}

	@Override
	public final Date getBased() {
		return based;
	}

	@Override
	public final void setBased(Date based) {
		this.based = based;
	}

	@Override
	public final ICity getCity() {
		return сity;
	}

	@Override
	public final void setCity(ICity сity) {
		this.сity = сity;
	}

	@Override
	public String toString() {
		return "Stadium [name=" + name + ", getId()=" + getId() + "]";
	}

}
