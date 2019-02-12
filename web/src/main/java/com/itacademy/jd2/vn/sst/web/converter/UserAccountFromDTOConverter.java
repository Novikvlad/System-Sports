package com.itacademy.jd2.vn.sst.web.converter;

import java.util.Set;
import java.util.function.Function;
import java.util.stream.Collectors;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.itacademy.jd2.vn.sst.dao.api.entity.table.IClub;
import com.itacademy.jd2.vn.sst.dao.api.entity.table.ICountry;
import com.itacademy.jd2.vn.sst.dao.api.entity.table.IFunOrganisation;
import com.itacademy.jd2.vn.sst.dao.api.entity.table.IUserAccount;
import com.itacademy.jd2.vn.sst.service.IClubService;
import com.itacademy.jd2.vn.sst.service.ICountryService;
import com.itacademy.jd2.vn.sst.service.IFunOrganisationService;
import com.itacademy.jd2.vn.sst.service.IUserAccountService;
import com.itacademy.jd2.vn.sst.web.dto.UserAccountDTO;

@Component
public class UserAccountFromDTOConverter implements Function<UserAccountDTO, IUserAccount> {

	@Autowired
	private IUserAccountService userAccountService;
	@Autowired
	private ICountryService countryService;
	@Autowired
	private IFunOrganisationService funOrganisationServicel;
	@Autowired
	private IClubService clubService;

	@Override
	public IUserAccount apply(UserAccountDTO dto) {
		final IUserAccount entity = userAccountService.createEntity();
		entity.setId(dto.getId());
		entity.setName(dto.getName());
		entity.setEmail(dto.getEmail());
		entity.setPassword(dto.getPassword());
		entity.setPhone(dto.getPhone());
		entity.setBirthday(dto.getBirthday());
		entity.setRole(dto.getRole());

		final ICountry country = countryService.createEntity();
		country.setId(dto.getCountryId());
		entity.setCountry(country);

		final IFunOrganisation funOrganisation = funOrganisationServicel.createEntity();
		funOrganisation.setId(dto.getFunOrganisationId());
		entity.setFunOrganisation(funOrganisation);

		final Set<Integer> clubIds = dto.getClubIds();
		if (CollectionUtils.isNotEmpty(clubIds)) {
			entity.setClubs(clubIds.stream().map((id) -> {
				final IClub club = clubService.createEntity();
				club.setId(id);
				return club;
			}).collect(Collectors.toSet()));
		}

		return entity;
	}

}
