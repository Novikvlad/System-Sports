package com.itacademy.jd2.vn.sst.service.impl;

import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.itacademy.jd2.vn.sst.dao.api.IPartnerContractDao;
import com.itacademy.jd2.vn.sst.dao.api.entity.table.IPartnerContract;
import com.itacademy.jd2.vn.sst.dao.api.filter.PartnerContractFilter;
import com.itacademy.jd2.vn.sst.service.IPartnerContractService;

@Service
public class PartnerContractServiceImpl implements IPartnerContractService {
	private static final Logger LOGGER = LoggerFactory.getLogger(CountryServiceImpl.class);
	private IPartnerContractDao dao;

	@Autowired
	public PartnerContractServiceImpl(IPartnerContractDao dao) {
		super();
		this.dao = dao;

	}

	@Override
	public IPartnerContract createEntity() {
		return dao.createEntity();
	}

	@Override
	public IPartnerContract get(final Integer id) {
		final IPartnerContract entity = dao.get(id);
		LOGGER.debug("entityById:{}", entity);
		return entity;
	}

	@Override
	public void save(final IPartnerContract entity) {
		final Date modifedOn = new Date();
		entity.setUpdated(modifedOn);
		if (entity.getId() == null) {
			entity.setCreated(modifedOn);
			dao.insert(entity);
			LOGGER.info("new saved countries:{}", entity);
		} else {
			dao.update(entity);
		}
	}

	@Override
	public void delete(final Integer id) {
		LOGGER.info("delete in DB:{}", id);
		dao.delete(id);
	}

	@Override
	public void deleteAll() {
		dao.deleteAll();
	}


	@Override
	public List<IPartnerContract> find(final PartnerContractFilter filter) {
		return dao.find(filter);
	}

	@Override
	public long getCount(final PartnerContractFilter filter) {
		return dao.getCount(filter);
	}

	@Override
	public IPartnerContract getFullInfo(Integer id) {
		return dao.getFulInfo(id);
	}

	@Override
	public List<IPartnerContract> getAll() {
		final List<IPartnerContract> all = dao.selectAll();
		return all;
	}

}
