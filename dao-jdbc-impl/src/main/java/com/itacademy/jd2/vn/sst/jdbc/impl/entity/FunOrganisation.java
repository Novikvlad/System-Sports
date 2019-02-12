package com.itacademy.jd2.vn.sst.jdbc.impl.entity;

import java.math.BigDecimal;

import com.itacademy.jd2.vn.sst.dao.api.entity.table.ICity;
import com.itacademy.jd2.vn.sst.dao.api.entity.table.IClub;
import com.itacademy.jd2.vn.sst.dao.api.entity.table.IFunOrganisation;

public class FunOrganisation extends BaseEntity implements IFunOrganisation {

	private String name;
	private IClub club;
	private ICity city;
	private BigDecimal deposit;

	public final String getName() {
		return name;
	}

	public final void setName(String name) {
		this.name = name;
	}

	public final IClub getClub() {
		return club;
	}

	public final void setClub(IClub club) {
		this.club = club;
	}

	public final ICity getCity() {
		return city;
	}

	public final void setCity(ICity city) {
		this.city = city;
	}

	public final BigDecimal getDeposit() {
		return deposit;
	}

	public final void setDeposit(BigDecimal deposit) {
		this.deposit = deposit;
	}

	@Override
	public String toString() {
		return "FunOrganisation [name=" + name + ", club=" + club + ", city=" + city + ", getId()=" + getId() + "]";
	}

}
