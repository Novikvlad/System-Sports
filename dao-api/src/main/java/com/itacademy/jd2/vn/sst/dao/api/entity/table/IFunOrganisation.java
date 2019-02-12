package com.itacademy.jd2.vn.sst.dao.api.entity.table;

import java.math.BigDecimal;

public interface IFunOrganisation extends IBaseEntity {
	
	String getName();

	void setName(String name);

	IClub getClub();

	void setClub(IClub club);

	ICity getCity();

	void setCity(ICity city);

	BigDecimal getDeposit();

	void setDeposit(BigDecimal deposit);

}
