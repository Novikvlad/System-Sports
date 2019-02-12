package com.itacademy.jd2.vn.sst.dao.api.entity.table;

public interface ICountry extends IBaseEntity {

	String getName();

	void setName(String name);

	IRegion getRegion();

	void setRegion(IRegion region);

}
