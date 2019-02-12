package com.itacademy.jd2.vn.sst.web.converter;

import java.util.function.Function;

import org.springframework.stereotype.Component;

import com.itacademy.jd2.vn.sst.dao.api.entity.table.ICity;
import com.itacademy.jd2.vn.sst.dao.api.entity.table.IClub;
import com.itacademy.jd2.vn.sst.dao.api.entity.table.IFunOrganisation;
import com.itacademy.jd2.vn.sst.web.dto.FunOrganisationDTO;

@Component
public class FunOrganisationToDTOConverter implements Function<IFunOrganisation, FunOrganisationDTO> {


	@Override
	public FunOrganisationDTO apply(IFunOrganisation entity) {

		final FunOrganisationDTO funOrganisationDTO = new FunOrganisationDTO();
		funOrganisationDTO.setId(entity.getId());
		funOrganisationDTO.setName(entity.getName());
		funOrganisationDTO.setDeposit(entity.getDeposit());
		funOrganisationDTO.setCreated(entity.getCreated());
		funOrganisationDTO.setUpdated(entity.getUpdated());

		final IClub club = entity.getClub();
		if (club != null) {
			funOrganisationDTO.setClubId(club.getId());
			funOrganisationDTO.setClubName(club.getName());

		}
		final ICity city = entity.getCity();
		if (city != null) {
			funOrganisationDTO.setCityId(city.getId());
			funOrganisationDTO.setCityName(city.getName());
		}
		return funOrganisationDTO;

	}

}
