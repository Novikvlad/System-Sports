package com.itacademy.jd2.vn.sst.service;

import java.util.List;

import javax.transaction.Transactional;

import com.itacademy.jd2.vn.sst.dao.api.entity.table.IPartner;
import com.itacademy.jd2.vn.sst.dao.api.filter.PartnerFilter;

public interface IPartnerService {

	IPartner createEntity();

	IPartner get(Integer id);

	List<IPartner> getAll();

	@Transactional
	void save(IPartner entity);

	@Transactional
	void save(IPartner... entity);

	@Transactional
	void delete(Integer id);

	@Transactional
	void deleteAll();

	List<IPartner> find(PartnerFilter filter);

	long getCount(PartnerFilter filter);

}
