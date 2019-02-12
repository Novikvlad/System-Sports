package com.itacademy.jd2.vn.sst.dao.api;

import java.util.List;

import com.itacademy.jd2.vn.sst.dao.api.entity.table.IClub;
import com.itacademy.jd2.vn.sst.dao.api.filter.ClubFilter;

public interface IClubDao extends IDao<IClub, Integer> {

	IClub getFullInfo(final Integer id);

	List<IClub> find(ClubFilter filter);

	long getCount(ClubFilter filter);

	
	
}
