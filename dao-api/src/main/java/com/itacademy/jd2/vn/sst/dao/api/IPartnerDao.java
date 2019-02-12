package com.itacademy.jd2.vn.sst.dao.api;

import java.util.List;

import com.itacademy.jd2.vn.sst.dao.api.entity.table.IPartner;
import com.itacademy.jd2.vn.sst.dao.api.filter.PartnerFilter;

public interface IPartnerDao extends IDao<IPartner, Integer> {

	void save(IPartner... entities);

	List<IPartner> find(PartnerFilter filter);

	long getCount(PartnerFilter filter);
}
