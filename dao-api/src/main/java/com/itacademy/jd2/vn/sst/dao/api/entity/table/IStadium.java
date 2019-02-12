package com.itacademy.jd2.vn.sst.dao.api.entity.table;

import java.util.Date;

public interface IStadium extends IBaseEntity {

	String getName();

	void setName(String name);

	Integer getCapacity();

	void setCapacity(Integer capacity);

	String getAddress();

	void setAddress(String address);

	Date getBased();

	void setBased(Date based);

	ICity getCity();

	void setCity(ICity iCity);
}
