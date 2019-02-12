package com.itacademy.jd2.vn.sst.dao.api;

import java.util.List;

import com.itacademy.jd2.vn.sst.dao.api.entity.table.IStadium;
import com.itacademy.jd2.vn.sst.dao.api.filter.StadiumFilter;


public interface IStadiumDao extends IDao<IStadium, Integer> {
	
	IStadium getFullInfo(final Integer id);

	List<IStadium> find(StadiumFilter filter);

	long getCount(StadiumFilter filter);

	
}
