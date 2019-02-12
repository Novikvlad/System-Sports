package com.itacademy.jd2.vn.sst.service;

import java.util.List;

import javax.transaction.Transactional;

import com.itacademy.jd2.vn.sst.dao.api.entity.table.IUserAccount;
import com.itacademy.jd2.vn.sst.dao.api.filter.UserAccountFilter;

public interface IUserAccountService {

	IUserAccount createEntity();

	IUserAccount get(Integer id);

	List<IUserAccount> getAll();

	@Transactional
	void save(IUserAccount entity);

	@Transactional
	void delete(Integer id);

	@Transactional
	void deleteAll();


	IUserAccount getFullInfo(final Integer id);

	long getCount(UserAccountFilter filter);

	List<IUserAccount> find(UserAccountFilter filter);

	IUserAccount getByEmail(String username);
}
