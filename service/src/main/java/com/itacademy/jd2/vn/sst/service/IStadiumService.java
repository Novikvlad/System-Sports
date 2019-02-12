package com.itacademy.jd2.vn.sst.service;

import java.util.List;

import javax.transaction.Transactional;

import com.itacademy.jd2.vn.sst.dao.api.entity.table.IStadium;
import com.itacademy.jd2.vn.sst.dao.api.filter.StadiumFilter;

public interface IStadiumService {

	IStadium createEntity();

	IStadium get(Integer id);

	@Transactional
	void save(IStadium entity);

	@Transactional
	void delete(Integer id);

	@Transactional
	void deleteAll();

	List<IStadium> getAll();

	IStadium getFullInfo(final Integer id);

	List<IStadium> find(StadiumFilter filter);

	long getCount(StadiumFilter filter);
}
