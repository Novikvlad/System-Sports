package com.itacademy.jd2.vn.sst.jdbc.impl.entity;

import com.itacademy.jd2.vn.sst.dao.api.entity.table.ICity;
import com.itacademy.jd2.vn.sst.dao.api.entity.table.ICountry;

public class City extends BaseEntity implements ICity {
	private String name;
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

	@Override
	public String toString() {
		return "City [name=" + name + ", getId()=" + getId() + "]";
	}

}
