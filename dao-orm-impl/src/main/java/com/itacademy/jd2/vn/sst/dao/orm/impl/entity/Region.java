package com.itacademy.jd2.vn.sst.dao.orm.impl.entity;

import javax.persistence.Column;
import javax.persistence.Entity;

import com.itacademy.jd2.vn.sst.dao.api.entity.table.IRegion;

@Entity
public class Region extends BaseEntity implements IRegion {

	@Column
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
		return "Region [name=" + name + ", getId()=" + getId() + "]";
	}

}
