package com.itacademy.jd2.vn.sst.dao.orm.impl.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;

import com.itacademy.jd2.vn.sst.dao.api.entity.table.ICity;
import com.itacademy.jd2.vn.sst.dao.api.entity.table.IStadium;

@Entity
public class Stadium extends BaseEntity implements IStadium {

	@Column
	private String name;
	@Column
	private Integer capacity;
	@Column
	private String address;
	@Column
	private Date based;
	@ManyToOne(fetch = FetchType.LAZY, targetEntity = City.class)
	private ICity city;

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

	public ICity getCity() {
		return city;
	}

	public void setCity(ICity city) {
		this.city = city;
	}
}
