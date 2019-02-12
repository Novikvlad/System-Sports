package com.itacademy.jd2.vn.sst.service;

import java.util.List;

import javax.transaction.Transactional;

import com.itacademy.jd2.vn.sst.dao.api.entity.table.ICity;
import com.itacademy.jd2.vn.sst.dao.api.filter.CityFilter;

public interface ICityService {

	ICity createEntity();

	ICity get(Integer id);

	@Transactional
	void save(ICity entity);

	@Transactional
	void delete(Integer id);

	@Transactional
	void deleteAll();

	List<ICity> getAll();

	ICity getFullInfo(Integer id);

	List<ICity> find(CityFilter filter);

	long getCount(CityFilter filter);
}
