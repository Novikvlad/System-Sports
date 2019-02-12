package com.itacademy.jd2.vn.sst.jdbc.impl.entity;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;
import com.itacademy.jd2.vn.sst.jdbc.impl.entity.BaseEntity;
import com.itacademy.jd2.vn.sst.dao.api.entity.enums.UserType;
import com.itacademy.jd2.vn.sst.dao.api.entity.table.IClub;
import com.itacademy.jd2.vn.sst.dao.api.entity.table.ICountry;
import com.itacademy.jd2.vn.sst.dao.api.entity.table.IFunOrganisation;
import com.itacademy.jd2.vn.sst.dao.api.entity.table.IUserAccount;

public class UserAccount extends BaseEntity implements IUserAccount {

	private String name;
	private String email;
	private String password;
	private ICountry country;
	private IFunOrganisation funOrganisation;
	private String phone;
	private Date birthday;
	private UserType role;
	private Set<IClub> clubs = new HashSet<>();

	@Override
	public final String getName() {
		return name;
	}

	@Override
	public final void setName(String name) {
		this.name = name;
	}

	@Override
	public final String getEmail() {
		return email;
	}

	@Override
	public final void setEmail(String email) {
		this.email = email;
	}

	@Override
	public final String getPassword() {
		return password;
	}

	@Override
	public final void setPassword(String password) {
		this.password = password;
	}

	@Override
	public final ICountry getCountry() {
		return country;
	}

	@Override
	public final void setCountry(ICountry country) {
		this.country = country;
	}

	@Override
	public final IFunOrganisation getFunOrganisation() {
		return funOrganisation;
	}

	@Override
	public final void setFunOrganisation(IFunOrganisation funOrganisation) {
		this.funOrganisation = funOrganisation;
	}

	@Override
	public final String getPhone() {
		return phone;
	}

	@Override
	public final void setPhone(String phone) {
		this.phone = phone;
	}

	@Override
	public final Date getBirthday() {
		return birthday;
	}

	@Override
	public final void setBirthday(Date birthday) {
		this.birthday = birthday;
	}

	@Override
	public final UserType getRole() {
		return role;
	}

	@Override
	public final void setRole(UserType role) {
		this.role = role;
	}

	@Override
	public final Set<IClub> getClubs() {
		return clubs;
	}

	@Override
	public final void setClubs(Set<IClub> clubs) {
		this.clubs = clubs;
	}

}
