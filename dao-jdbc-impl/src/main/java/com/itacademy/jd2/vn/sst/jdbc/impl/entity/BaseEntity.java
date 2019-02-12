package com.itacademy.jd2.vn.sst.jdbc.impl.entity;

import java.util.Date;

public abstract class BaseEntity {
	private Integer id;
	private Date created;
	private Date updated;
	
	public final Integer getId() {
		return id;
	}

	public final void setId(Integer id) {
		this.id = id;
	}

	public final Date getCreated() {
		return created;
	}

	public final void setCreated(Date created) {
		this.created = created;
	}

	public final Date getUpdated() {
		return updated;
	}

	public final void setUpdated(Date updated) {
		this.updated = updated;
	}

}
