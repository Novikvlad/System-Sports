package com.itacademy.jd2.vn.sst.dao.orm.impl.entity;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import com.itacademy.jd2.vn.sst.dao.api.entity.table.IClub;
import com.itacademy.jd2.vn.sst.dao.api.entity.table.IPartnerContract;
import com.itacademy.jd2.vn.sst.dao.api.entity.table.IStadium;

@Entity
public class Club extends BaseEntity implements IClub {

	@Column
	private String name;
	@Column
	private Date based;
	@ManyToOne(fetch = FetchType.LAZY, targetEntity = Stadium.class)
	private IStadium stadium;
	@OneToMany(fetch = FetchType.LAZY,targetEntity = PartnerContract.class)
	@JoinColumn(name = "partner_contract")
	private Set<IPartnerContract> partnerContract = new HashSet<IPartnerContract>();

	@Override
	public final String getName() {
		return name;
	}

	@Override
	public final void setName(String name) {
		this.name = name;
	}

	@Override
	public final Date getBased() {
		return based;
	}

	@Override
	public final void setBased(Date based) {
		this.based = based;
	}

	@Override
	public final IStadium getStadium() {
		return stadium;
	}

	@Override
	public final void setStadium(IStadium stadium) {
		this.stadium = stadium;
	}
	@Override
	public final Set<IPartnerContract> getPartnerContract() {
		return partnerContract;
	}
	@Override
	public final void setPartnerContract(Set<IPartnerContract> partnerContract) {
		this.partnerContract = partnerContract;
	}
	

}
