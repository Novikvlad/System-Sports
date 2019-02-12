package com.itacademy.jd2.vn.sst.jdbc.impl.entity;

import com.itacademy.jd2.vn.sst.dao.api.entity.table.IPartner;

public class Partner extends BaseEntity implements IPartner {

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
		return "Partner [name=" + name + ", getId()=" + getId() + ", getCreated()=" + getCreated() + ", getUpdated()="
				+ getUpdated() + "]";
	}

}
