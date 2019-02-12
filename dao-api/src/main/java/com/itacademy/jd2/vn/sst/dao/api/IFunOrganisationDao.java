package com.itacademy.jd2.vn.sst.dao.api;

import java.util.List;

import com.itacademy.jd2.vn.sst.dao.api.entity.table.IFunOrganisation;
import com.itacademy.jd2.vn.sst.dao.api.filter.FunOrganisationFilter;

public interface IFunOrganisationDao extends IDao<IFunOrganisation, Integer>{
	
	IFunOrganisation getFulInfo(final Integer id);

	List<IFunOrganisation> find(FunOrganisationFilter filter);

	long getCount(FunOrganisationFilter filter);
}
