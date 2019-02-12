package com.itacademy.jd2.vn.sst.dao.api.entity.table;

public interface ICity extends IBaseEntity {

	String getName();

	void setName(String name);

	ICountry getCountry();

	void setCountry(ICountry country);
}
