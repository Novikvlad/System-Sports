package com.itacademy.jd2.vn.sst.jdbc.impl.entity;

import com.itacademy.jd2.vn.sst.dao.api.entity.table.ICountry;
import com.itacademy.jd2.vn.sst.dao.api.entity.table.IRegion;

public class Country extends BaseEntity implements ICountry {

	private String name;
	private IRegion region;

	@Override
	public final String getName() {
		return name;
	}

	@Override
	public final void setName(String name) {
		this.name = name;
	}

	@Override
	public final IRegion getRegion() {
		return region;
	}

	@Override
	public final void setRegion(IRegion region) {
		this.region = region;
	}

	@Override
	public String toString() {
		return "Country [name=" + name + ", getId()=" + getId() + "]";
	}

}
