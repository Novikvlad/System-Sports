package com.itacademy.jd2.vn.sst.dao.orm.impl.entity;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;

import com.itacademy.jd2.vn.sst.dao.api.entity.table.ICity;
import com.itacademy.jd2.vn.sst.dao.api.entity.table.IClub;
import com.itacademy.jd2.vn.sst.dao.api.entity.table.IFunOrganisation;

@Entity
public class FunOrganisation extends BaseEntity implements IFunOrganisation {

	@Column
	private String name;
	@ManyToOne(fetch = FetchType.LAZY, targetEntity = Club.class)
	private IClub club;
	@ManyToOne(fetch = FetchType.LAZY, targetEntity = City.class)
	private ICity city;
	@Column
	private BigDecimal deposit;

	@Override
	public final String getName() {
		return name;
	}

	@Override
	public final void setName(String name) {
		this.name = name;
	}

	@Override
	public final IClub getClub() {
		return club;
	}

	@Override
	public final void setClub(IClub club) {
		this.club = club;
	}

	@Override
	public final ICity getCity() {
		return city;
	}

	@Override
	public final void setCity(ICity city) {
		this.city = city;
	}

	@Override
	public final BigDecimal getDeposit() {
		return deposit;
	}

	@Override
	public final void setDeposit(BigDecimal deposit) {
		this.deposit = deposit;
	}

	@Override
	public String toString() {
		return "FunOrganisation [name=" + name + ", club=" + club + ", city=" + city + ", getId()=" + getId() + "]";
	}

}
