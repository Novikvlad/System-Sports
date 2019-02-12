package com.itacademy.jd2.vn.sst.web.converter;

import java.util.function.Function;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.itacademy.jd2.vn.sst.dao.api.entity.table.ICountry;
import com.itacademy.jd2.vn.sst.dao.api.entity.table.IRegion;
import com.itacademy.jd2.vn.sst.service.ICountryService;
import com.itacademy.jd2.vn.sst.service.IRegionService;
import com.itacademy.jd2.vn.sst.web.dto.CountryDTO;

@Component
public class CountryFromDTOConverter implements Function<CountryDTO, ICountry> {

	@Autowired
	private ICountryService countryService;
	@Autowired
	private IRegionService regionService;

	@Override
	public ICountry apply(final CountryDTO dto) {

		final ICountry entity = countryService.createEntity();
		entity.setId(dto.getId());
		entity.setName(dto.getName());

		final IRegion region = regionService.createEntity();
		region.setId(dto.getRegionId());
		entity.setRegion(region);
		return entity;
	}

}
