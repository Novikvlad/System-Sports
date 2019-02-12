package com.itacademy.jd2.vn.sst.dao.api;

import java.util.List;

import com.itacademy.jd2.vn.sst.dao.api.entity.table.ICountry;
import com.itacademy.jd2.vn.sst.dao.api.filter.CountryFilter;

public interface ICountryDao extends IDao<ICountry, Integer> {
	ICountry getFullInfo(final Integer id);

	List<ICountry> find(CountryFilter filter);

	long getCount(CountryFilter filter);
}
