package com.itacademy.jd2.vn.sst.dao.orm.impl.entity;

import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;

import com.itacademy.jd2.vn.sst.dao.api.entity.table.IClub;
import com.itacademy.jd2.vn.sst.dao.api.entity.table.IPartner;
import com.itacademy.jd2.vn.sst.dao.api.entity.table.IPartnerContract;

@Entity
public class PartnerContract extends BaseEntity implements IPartnerContract {

	
	@ManyToOne(fetch = FetchType.LAZY, targetEntity = Partner.class)
	private IPartner partner;
	@ManyToOne(fetch = FetchType.LAZY, targetEntity = Club.class)
	private IClub club;
	@Column
	private Date dateSigning;
	@Column
	private Date contractTerm;
	@Column
	private BigDecimal contractValue;

	@Override
	public final IClub getClub() {
		return club;
	}

	@Override
	public final void setClub(IClub club) {
		this.club = club;
	}

	@Override
	public final IPartner getPartner() {
		return partner;
	}

	@Override
	public final void setPartner(IPartner partner) {
		this.partner = partner;
	}

	@Override
	public final Date getDateSigning() {
		return dateSigning;
	}

	@Override
	public final void setDateSigning(Date dateSigning) {
		this.dateSigning = dateSigning;
	}

	@Override
	public final Date getContractTerm() {
		return contractTerm;
	}

	@Override
	public final void setContractTerm(Date contractTerm) {
		this.contractTerm = contractTerm;
	}

	@Override
	public final BigDecimal getContractValue() {
		return contractValue;
	}

	@Override
	public final void setContractValue(BigDecimal contractValue) {
		this.contractValue = contractValue;
	}

	@Override
	public String toString() {
		return "PartnerContract [club=" + club + ", partner=" + partner + ", getId()=" + getId() + "]";
	}

}
