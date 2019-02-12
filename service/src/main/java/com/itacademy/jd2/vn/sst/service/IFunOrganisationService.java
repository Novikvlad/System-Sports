package com.itacademy.jd2.vn.sst.service;

import java.util.List;

import javax.transaction.Transactional;

import com.itacademy.jd2.vn.sst.dao.api.entity.table.IFunOrganisation;
import com.itacademy.jd2.vn.sst.dao.api.filter.FunOrganisationFilter;

public interface IFunOrganisationService {
	IFunOrganisation get(Integer id);

	List<IFunOrganisation> getAll();

	@Transactional
	void save(IFunOrganisation entity);

	@Transactional
	void delete(Integer id);

	@Transactional
	void deleteAll();

	IFunOrganisation createEntity();

	IFunOrganisation getFullInfo(final Integer id);

	List<IFunOrganisation> find(FunOrganisationFilter filter);

	long getCount(FunOrganisationFilter filter);
}
