package com.itacademy.jd2.vn.sst.web.dto;

import java.util.Date;
import java.util.Set;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.springframework.format.annotation.DateTimeFormat;

import com.itacademy.jd2.vn.sst.dao.api.entity.enums.UserType;

public class UserAccountDTO {

	private Integer id;
	@Size(min = 1, max = 50)
	private String name;
	@Size(min = 1, max = 50)
	private String email;
	@Size(min = 1, max = 50)
	private String password;
	@NotNull
	private Integer countryId;
	private String countryName;
	@NotNull
	private Integer funOrganisationId;
	private String funOrganisationName;
	@Size(min = 1, max = 50)
	private String phone;
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date birthday;
	private UserType role;
	private Set<Integer> clubIds;
	private Date created;
	private Date updated;

	public final Integer getId() {
		return id;
	}

	public final void setId(Integer id) {
		this.id = id;
	}

	public final String getName() {
		return name;
	}

	public final void setName(String name) {
		this.name = name;
	}

	public final String getEmail() {
		return email;
	}

	public final void setEmail(String email) {
		this.email = email;
	}

	public final String getPassword() {
		return password;
	}

	public final void setPassword(String password) {
		this.password = password;
	}

	public final Integer getCountryId() {
		return countryId;
	}

	public final void setCountryId(Integer countryId) {
		this.countryId = countryId;
	}

	public final String getCountryName() {
		return countryName;
	}

	public final void setCountryName(String countryName) {
		this.countryName = countryName;
	}

	public final Integer getFunOrganisationId() {
		return funOrganisationId;
	}

	public final void setFunOrganisationId(Integer funOrganisationId) {
		this.funOrganisationId = funOrganisationId;
	}

	public final String getFunOrganisationName() {
		return funOrganisationName;
	}

	public final void setFunOrganisationName(String funOrganisationName) {
		this.funOrganisationName = funOrganisationName;
	}

	public final String getPhone() {
		return phone;
	}

	public final void setPhone(String phone) {
		this.phone = phone;
	}

	public final Date getBirthday() {
		return birthday;
	}

	public final void setBirthday(Date birthday) {
		this.birthday = birthday;
	}

	public final UserType getRole() {
		return role;
	}

	public final void setRole(UserType role) {
		this.role = role;
	}

	public final Set<Integer> getClubIds() {
		return clubIds;
	}

	public final void setClubIds(Set<Integer> clubIds) {
		this.clubIds = clubIds;
	}

	public final Date getCreated() {
		return created;
	}

	public final void setCreated(Date created) {
		this.created = created;
	}

	public final Date getUpdated() {
		return updated;
	}

	public final void setUpdated(Date updated) {
		this.updated = updated;
	}

}
