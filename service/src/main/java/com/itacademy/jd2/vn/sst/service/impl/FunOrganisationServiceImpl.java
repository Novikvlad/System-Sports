package com.itacademy.jd2.vn.sst.service.impl;

import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.itacademy.jd2.vn.sst.dao.api.IFunOrganisationDao;
import com.itacademy.jd2.vn.sst.dao.api.entity.table.IFunOrganisation;
import com.itacademy.jd2.vn.sst.dao.api.filter.FunOrganisationFilter;
import com.itacademy.jd2.vn.sst.service.IFunOrganisationService;

@Service
public class FunOrganisationServiceImpl implements IFunOrganisationService {
	private static final Logger LOGGER = LoggerFactory.getLogger(FunOrganisationServiceImpl.class);

	private IFunOrganisationDao dao;

	@Autowired
	public FunOrganisationServiceImpl(IFunOrganisationDao dao) {
		super();
		this.dao = dao;

	}

	@Override
	public IFunOrganisation get(Integer id) {
		final IFunOrganisation entity = dao.get(id);
		return entity;
	}

	@Override
	public List<IFunOrganisation> getAll() {

		final List<IFunOrganisation> all = dao.selectAll();
		return all;
	}

	@Override
	public void save(IFunOrganisation entity) {
		final Date modifedOn = new Date();
		entity.setUpdated(modifedOn);
		if (entity.getId() == null) {
			entity.setCreated(modifedOn);
			dao.insert(entity);
		} else {
			dao.update(entity);
		}
	}

	@Override
	public void delete(Integer id) {
		LOGGER.info("delete in DB:{}", id);
		dao.delete(id);
	}

	@Override
	public void deleteAll() {
		dao.deleteAll();
	}

	@Override
	public IFunOrganisation createEntity() {
		return dao.createEntity();
	}

	@Override
	public IFunOrganisation getFullInfo(Integer id) {
		return dao.getFulInfo(id);
	}

	@Override
	public List<IFunOrganisation> find(FunOrganisationFilter filter) {
		return dao.find(filter);
	}

	@Override
	public long getCount(FunOrganisationFilter filter) {
		return dao.getCount(filter);
	}

}
