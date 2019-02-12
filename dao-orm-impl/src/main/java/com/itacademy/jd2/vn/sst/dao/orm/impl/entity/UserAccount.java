package com.itacademy.jd2.vn.sst.dao.orm.impl.entity;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;

import com.itacademy.jd2.vn.sst.dao.api.entity.enums.UserType;
import com.itacademy.jd2.vn.sst.dao.api.entity.table.IClub;
import com.itacademy.jd2.vn.sst.dao.api.entity.table.ICountry;
import com.itacademy.jd2.vn.sst.dao.api.entity.table.IFunOrganisation;
import com.itacademy.jd2.vn.sst.dao.api.entity.table.IUserAccount;

@Entity
public class UserAccount extends BaseEntity implements IUserAccount {

	@Column
	private String name;
	@Column
	private String email;
	@Column
	private String password;
	@ManyToOne(fetch = FetchType.LAZY, targetEntity = Country.class)
	private ICountry country;
	@ManyToOne(fetch = FetchType.LAZY, targetEntity = FunOrganisation.class)
	private IFunOrganisation funOrganisation;
	@Column
	private String phone;
	@Column
	private Date birthday;
	@Column
	private UserType role;

	@JoinTable(name = "club_2_owner", joinColumns = { @JoinColumn(name = "user_account_id") }, inverseJoinColumns = {
			@JoinColumn(name = "club_id") })
	@ManyToMany(targetEntity = Club.class, fetch = FetchType.LAZY)
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
	public final Date getBirthday() {
		return birthday;
	}
	@Override
	public final String getPhone() {
		return phone;
	}

	public final void setPhone(String phone) {
		this.phone = phone;
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

	@Override
	public String toString() {
		return "UserAccount [name=" + name + ", getId()=" + getId() + "]";
	}

}
