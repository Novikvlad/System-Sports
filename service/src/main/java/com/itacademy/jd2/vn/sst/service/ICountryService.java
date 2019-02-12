package com.itacademy.jd2.vn.sst.service;

import java.util.List;

import javax.transaction.Transactional;

import com.itacademy.jd2.vn.sst.dao.api.entity.table.ICountry;
import com.itacademy.jd2.vn.sst.dao.api.filter.CountryFilter;

public interface ICountryService {
	
	ICountry createEntity();
	ICountry get(Integer id);

	@Transactional
	void save(ICountry entity);

	@Transactional
	void delete(Integer id);

	@Transactional
	void deleteAll();

	

	List<ICountry> getAll();

	List<ICountry> find(CountryFilter filter);

	long getCount(CountryFilter filter);

	ICountry getFullInfo(Integer id);
}
