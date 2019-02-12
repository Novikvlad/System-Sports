package com.itacademy.jd2.vn.sst.dao.api.entity.table;

import java.util.Date;
import java.util.Set;

public interface IClub extends IBaseEntity {

	String getName();

	void setName(String name);

	Date getBased();

	void setBased(Date based);

	IStadium getStadium();

	void setStadium(IStadium stadium);

	Set<IPartnerContract> getPartnerContract();

	void setPartnerContract(Set<IPartnerContract> partnerContract);

}
