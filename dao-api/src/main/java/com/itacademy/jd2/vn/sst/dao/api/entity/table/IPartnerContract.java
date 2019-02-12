package com.itacademy.jd2.vn.sst.dao.api.entity.table;

import java.math.BigDecimal;
import java.util.Date;

public interface IPartnerContract extends IBaseEntity {

	IClub getClub();

	void setClub(IClub club);

	IPartner getPartner();

	void setPartner(IPartner partner);

	Date getDateSigning();

	void setDateSigning(Date dateSigning);

	Date getContractTerm();

	void setContractTerm(Date contractTerm);

	BigDecimal getContractValue();

	void setContractValue(BigDecimal contractValue);
}
