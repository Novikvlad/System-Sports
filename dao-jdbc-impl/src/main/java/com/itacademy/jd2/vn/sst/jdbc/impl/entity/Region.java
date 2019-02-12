package com.itacademy.jd2.vn.sst.jdbc.impl.entity;

import com.itacademy.jd2.vn.sst.dao.api.entity.table.IRegion;

public class Region extends BaseEntity implements IRegion {

	private String name;

	@Override
	public final String getName() {
		return name;
	}

	@Override
	public final void setName(String name) {
		this.name = name;
	}

	@Override
	public String toString() {
		return "Region [name=" + name + ", getId()=" + getId() + ", getCreated()=" + getCreated() + ", getUpdated()="
				+ getUpdated() + "]";
	}
}
