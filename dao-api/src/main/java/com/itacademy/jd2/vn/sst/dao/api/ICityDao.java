package com.itacademy.jd2.vn.sst.dao.api;

import java.util.List;

import com.itacademy.jd2.vn.sst.dao.api.entity.table.ICity;
import com.itacademy.jd2.vn.sst.dao.api.filter.CityFilter;

public interface ICityDao extends IDao<ICity, Integer> {
	ICity getFullInfo(final Integer id);

	List<ICity> find(CityFilter filter);

	long getCount(CityFilter filter);
}
