package com.itacademy.jd2.vn.sst.jdbc.impl.entity;

import java.math.BigDecimal;
import java.util.Date;

import com.itacademy.jd2.vn.sst.dao.api.entity.table.IClub;
import com.itacademy.jd2.vn.sst.dao.api.entity.table.IPartner;
import com.itacademy.jd2.vn.sst.dao.api.entity.table.IPartnerContract;

public class PartnerContract extends BaseEntity implements IPartnerContract {

	private IClub club;
	private IPartner partner;
	private Date dateSigning;
	private Date contractTerm;
	private BigDecimal contractValue;

	public final IClub getClub() {
		return club;
	}

	public final void setClub(IClub club) {
		this.club = club;
	}

	public final IPartner getPartner() {
		return partner;
	}

	public final void setPartner(IPartner partner) {
		this.partner = partner;
	}

	public final Date getDateSigning() {
		return dateSigning;
	}

	public final void setDateSigning(Date dateSigning) {
		this.dateSigning = dateSigning;
	}

	public final Date getContractTerm() {
		return contractTerm;
	}

	public final void setContractTerm(Date contractTerm) {
		this.contractTerm = contractTerm;
	}

	public final BigDecimal getContractValue() {
		return contractValue;
	}

	public final void setContractValue(BigDecimal contractValue) {
		this.contractValue = contractValue;
	}

}
