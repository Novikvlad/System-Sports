package com.itacademy.jd2.vn.sst.service;

import java.util.List;

import javax.transaction.Transactional;

import com.itacademy.jd2.vn.sst.dao.api.entity.table.IRegion;
import com.itacademy.jd2.vn.sst.dao.api.filter.RegionFilter;

public interface IRegionService {

	IRegion createEntity();

	IRegion get(Integer id);

	List<IRegion> getAll();

	@Transactional
	void save(IRegion entity);

	@Transactional
	void save(IRegion... entity);

	@Transactional
	void delete(Integer id);

	@Transactional
	void deleteAll();

	List<IRegion> find(RegionFilter filter);

	long getCount(RegionFilter filter);
}
