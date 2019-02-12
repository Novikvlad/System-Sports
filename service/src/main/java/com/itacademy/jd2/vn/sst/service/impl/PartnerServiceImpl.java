package com.itacademy.jd2.vn.sst.service.impl;

import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.itacademy.jd2.vn.sst.dao.api.IPartnerDao;
import com.itacademy.jd2.vn.sst.dao.api.entity.table.IPartner;
import com.itacademy.jd2.vn.sst.dao.api.filter.PartnerFilter;
import com.itacademy.jd2.vn.sst.service.IPartnerService;

@Service
public class PartnerServiceImpl implements IPartnerService {

	private static final Logger LOGGER = LoggerFactory.getLogger(PartnerServiceImpl.class);

	private IPartnerDao dao;

	@Autowired
	public PartnerServiceImpl(IPartnerDao dao) {
		super();
		this.dao = dao;
	}

	@Override
	public IPartner createEntity() {
		return dao.createEntity();
	}

	@Override
	public void save(final IPartner entity) {
		final Date modifedOn = new Date();
		entity.setUpdated(modifedOn);
		if (entity.getId() == null) {
			LOGGER.info("new partner created: {}", entity);
			entity.setCreated(modifedOn);
			dao.insert(entity);

		} else {
			LOGGER.debug("partner updated: {}", entity);
			dao.update(entity);
		}
	}

	@Override
	public IPartner get(final Integer id) {
		final IPartner entity = dao.get(id);
		return entity;
	}

	@Override
	public void delete(final Integer id) {
		dao.delete(id);
	}

	@Override
	public void deleteAll() {
		LOGGER.info("delete all partners");
		dao.deleteAll();
	}

	@Override
	public List<IPartner> getAll() {
		final List<IPartner> all = dao.selectAll();
		return all;
	}

	@Override
	public void save(IPartner... entities) {
		Date modified = new Date();
		for (IPartner iPartner : entities) {

			iPartner.setUpdated(modified);
			iPartner.setCreated(modified);
		}

		dao.save(entities);
	}

	@Override
	public List<IPartner> find(PartnerFilter filter) {
		return dao.find(filter);
	}

	@Override
	public long getCount(PartnerFilter filter) {
		return dao.getCount(filter);
	}
}
