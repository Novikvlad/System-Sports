package com.itacademy.jd2.vn.sst.web.converter;

import java.util.function.Function;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.itacademy.jd2.vn.sst.dao.api.entity.table.ICity;
import com.itacademy.jd2.vn.sst.dao.api.entity.table.IClub;
import com.itacademy.jd2.vn.sst.dao.api.entity.table.IFunOrganisation;
import com.itacademy.jd2.vn.sst.service.ICityService;
import com.itacademy.jd2.vn.sst.service.IClubService;
import com.itacademy.jd2.vn.sst.service.IFunOrganisationService;
import com.itacademy.jd2.vn.sst.web.dto.FunOrganisationDTO;

@Component
public class FunOrganisationFromDTOConverter implements Function<FunOrganisationDTO, IFunOrganisation> {

	@Autowired
	private IFunOrganisationService funOrganisationService;
	@Autowired
	private IClubService clubService;
	@Autowired
	private ICityService cityService;

	@Override
	public IFunOrganisation apply(FunOrganisationDTO dto) {

		final IFunOrganisation entity = funOrganisationService.createEntity();
		entity.setId(dto.getId());
		entity.setName(dto.getName());
		entity.setDeposit(dto.getDeposit());

		final IClub club = clubService.createEntity();
		club.setId(dto.getClubId());
		entity.setClub(club);

		final ICity city = cityService.createEntity();
		city.setId(dto.getCityId());
		entity.setCity(city);
		return entity;
	}

}
