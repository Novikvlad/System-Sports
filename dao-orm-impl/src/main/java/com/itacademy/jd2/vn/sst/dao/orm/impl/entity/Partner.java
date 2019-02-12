package com.itacademy.jd2.vn.sst.dao.orm.impl.entity;

import javax.persistence.Column;
import javax.persistence.Entity;

import com.itacademy.jd2.vn.sst.dao.api.entity.table.IPartner;

@Entity
public class Partner extends BaseEntity implements IPartner {

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
		return "Partner [name=" + name + ", getId()=" + getId() + "]";
	}

}
