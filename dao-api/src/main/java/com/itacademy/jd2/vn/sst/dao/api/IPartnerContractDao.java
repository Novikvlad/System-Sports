package com.itacademy.jd2.vn.sst.dao.api;

import java.util.List;

import com.itacademy.jd2.vn.sst.dao.api.entity.table.IPartnerContract;
import com.itacademy.jd2.vn.sst.dao.api.filter.PartnerContractFilter;

public interface IPartnerContractDao extends IDao<IPartnerContract, Integer> {

	IPartnerContract getFulInfo(final Integer id);

	List<IPartnerContract> find(PartnerContractFilter filter);

	long getCount(PartnerContractFilter filter);
}
