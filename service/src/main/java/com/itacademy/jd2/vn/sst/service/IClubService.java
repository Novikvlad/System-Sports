package com.itacademy.jd2.vn.sst.service;

import java.util.List;

import javax.transaction.Transactional;

import com.itacademy.jd2.vn.sst.dao.api.entity.table.IClub;
import com.itacademy.jd2.vn.sst.dao.api.filter.ClubFilter;

public interface IClubService {

	IClub createEntity();

	IClub get(Integer id);

	@Transactional
	void save(IClub entity);

	@Transactional
	void delete(Integer id);

	@Transactional
	void deleteAll();

	List<IClub> getAll();

	IClub getFullInfo(Integer id);

	List<IClub> find(ClubFilter filter);

	long getCount(ClubFilter filter);
}
