package com.itacademy.jd2.vn.sst.web.converter;

import java.util.function.Function;

import org.springframework.stereotype.Component;

import com.itacademy.jd2.vn.sst.dao.api.entity.table.ICountry;
import com.itacademy.jd2.vn.sst.dao.api.entity.table.IFunOrganisation;
import com.itacademy.jd2.vn.sst.dao.api.entity.table.IUserAccount;
import com.itacademy.jd2.vn.sst.web.dto.UserAccountDTO;

@Component
public class UserAccountToDTOConverter implements Function<IUserAccount, UserAccountDTO> {


	@Override
	public UserAccountDTO apply(IUserAccount entity) {

		final UserAccountDTO userAccountDTO = new UserAccountDTO();
		userAccountDTO.setId(entity.getId());
		userAccountDTO.setName(entity.getName());
		userAccountDTO.setEmail(entity.getEmail());
		userAccountDTO.setPassword(entity.getPassword());
		userAccountDTO.setPhone(entity.getPhone());
		userAccountDTO.setBirthday(entity.getBirthday());
		userAccountDTO.setRole(entity.getRole());
		userAccountDTO.setCreated(entity.getCreated());
		userAccountDTO.setUpdated(entity.getUpdated());
		
		final ICountry country = entity.getCountry();
		if (country != null) {
			userAccountDTO.setCountryId(country.getId());
			userAccountDTO.setCountryName(country.getName());
		}
		final IFunOrganisation funOrganisation = entity.getFunOrganisation();
		if (funOrganisation != null) {
			userAccountDTO.setFunOrganisationId(funOrganisation.getId());
			userAccountDTO.setFunOrganisationName(funOrganisation.getName());
		}

		return userAccountDTO;
	}

}
