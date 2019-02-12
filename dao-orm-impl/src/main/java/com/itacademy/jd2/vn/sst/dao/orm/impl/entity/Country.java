package com.itacademy.jd2.vn.sst.dao.orm.impl.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;

import com.itacademy.jd2.vn.sst.dao.api.entity.table.ICountry;
import com.itacademy.jd2.vn.sst.dao.api.entity.table.IRegion;


@Entity
public class Country extends BaseEntity implements ICountry {

	@Column
	private String name;
	@ManyToOne(fetch = FetchType.LAZY, targetEntity = Region.class)
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
