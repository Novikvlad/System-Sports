package com.itacademy.jd2.vn.sst.web.converter;

import java.util.function.Function;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.itacademy.jd2.vn.sst.dao.api.entity.table.ICity;
import com.itacademy.jd2.vn.sst.dao.api.entity.table.ICountry;
import com.itacademy.jd2.vn.sst.service.ICityService;
import com.itacademy.jd2.vn.sst.service.ICountryService;
import com.itacademy.jd2.vn.sst.web.dto.CityDTO;

@Component
public class CityFromDTOConverter implements Function<CityDTO, ICity>{
	@Autowired
	private ICityService cityServise;
	@Autowired
	private ICountryService countryService;

	@Override
	public ICity apply(final CityDTO dto) {
		final ICity entity = cityServise.createEntity();
		entity.setId(dto.getId());
		entity.setName(dto.getName());

		final ICountry country = countryService.createEntity();
		country.setId(dto.getCountryId());
		entity.setCountry(country);

		return entity;
	}

}
