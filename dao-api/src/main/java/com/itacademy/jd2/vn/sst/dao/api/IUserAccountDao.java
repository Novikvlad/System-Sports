package com.itacademy.jd2.vn.sst.dao.api;

import java.util.List;

import com.itacademy.jd2.vn.sst.dao.api.entity.table.IUserAccount;
import com.itacademy.jd2.vn.sst.dao.api.filter.UserAccountFilter;

public interface IUserAccountDao extends IDao<IUserAccount, Integer> {
	IUserAccount getFullInfo(final Integer id);

	List<IUserAccount> find(UserAccountFilter filter);

	long getCount(UserAccountFilter filter);

	public IUserAccount getByEmail(String email);
}
