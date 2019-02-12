package com.itacademy.jd2.vn.sst.jdbc.impl.entity;

import java.util.Date;
import java.util.Set;

import com.itacademy.jd2.vn.sst.dao.api.entity.table.IClub;
import com.itacademy.jd2.vn.sst.dao.api.entity.table.IPartnerContract;
import com.itacademy.jd2.vn.sst.dao.api.entity.table.IStadium;

public class Club extends BaseEntity implements IClub {

	private String name;
	private Date based;
	private IStadium stadium;

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
	public String toString() {
		return "Club [name=" + name + ", getId()=" + getId() + "]";
	}

	@Override
	public Set<IPartnerContract> getPartnerContract() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void setPartnerContract(Set<IPartnerContract> partnerContract) {
		// TODO Auto-generated method stub
		
	}

}
