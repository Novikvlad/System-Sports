package com.itacademy.jd2.vn.sst.dao.api.entity.table;

import java.util.Date;
import java.util.Set;

import com.itacademy.jd2.vn.sst.dao.api.entity.enums.UserType;

public interface IUserAccount extends IBaseEntity {

	String getName();

	void setName(String name);

	String getEmail();

	void setEmail(String email);

	String getPassword();

	void setPassword(String password);

	ICountry getCountry();

	void setCountry(ICountry country);

	IFunOrganisation getFunOrganisation();

	void setFunOrganisation(IFunOrganisation funOrganisation);

	String getPhone();

	void setPhone(String phone);

	Date getBirthday();

	void setBirthday(Date birthday);

	UserType getRole();

	void setRole(UserType role);

	Set<IClub> getClubs();

	void setClubs(Set<IClub> clubs);


}
