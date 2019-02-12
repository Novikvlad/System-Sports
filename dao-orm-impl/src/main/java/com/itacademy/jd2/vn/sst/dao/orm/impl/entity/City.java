package com.itacademy.jd2.vn.sst.dao.orm.impl.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;

import com.itacademy.jd2.vn.sst.dao.api.entity.table.ICity;
import com.itacademy.jd2.vn.sst.dao.api.entity.table.ICountry;

@Entity
public class City extends BaseEntity implements ICity {

	
	@Column
	private String name;
	@ManyToOne(fetch = FetchType.LAZY, targetEntity = Country.class)
	private ICountry country;

	@Override
	public final String getName() {
		return name;
	}

	@Override
	public final void setName(String name) {
		this.name = name;
	}

	@Override
	public final ICountry getCountry() {
		return country;
	}

	@Override
	public final void setCountry(ICountry country) {
		this.country = country;
	}

}
