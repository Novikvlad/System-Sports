package com.itacademy.jd2.vn.sst.dao.api;

import java.util.List;

import com.itacademy.jd2.vn.sst.dao.api.entity.table.IRegion;
import com.itacademy.jd2.vn.sst.dao.api.filter.RegionFilter;

public interface IRegionDao extends IDao<IRegion, Integer> {

	void save(IRegion... entities); 

	List<IRegion> find(RegionFilter filter);

	long getCount(RegionFilter filter); 
}
