package com.itacademy.jd2.vn.sst.service;

import java.util.List;

import javax.transaction.Transactional;

import com.itacademy.jd2.vn.sst.dao.api.entity.table.IPartnerContract;
import com.itacademy.jd2.vn.sst.dao.api.filter.PartnerContractFilter;

public interface IPartnerContractService {

	IPartnerContract get(Integer id);

	@Transactional
	void save(IPartnerContract entity);

	@Transactional
	void delete(Integer id);

	@Transactional
	void deleteAll();

	IPartnerContract createEntity();

	List<IPartnerContract> find(PartnerContractFilter filter);

	long getCount(PartnerContractFilter filter);

	IPartnerContract getFullInfo(Integer id);

	List<IPartnerContract> getAll();
}
