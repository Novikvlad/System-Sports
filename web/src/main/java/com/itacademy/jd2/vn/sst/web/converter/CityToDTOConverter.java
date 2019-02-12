package com.itacademy.jd2.vn.sst.web.converter;

import java.util.function.Function;

import org.springframework.stereotype.Component;

import com.itacademy.jd2.vn.sst.dao.api.entity.table.ICity;
import com.itacademy.jd2.vn.sst.dao.api.entity.table.ICountry;
import com.itacademy.jd2.vn.sst.web.dto.CityDTO;

@Component
public class CityToDTOConverter implements Function<ICity, CityDTO> {

	@Override
	public CityDTO apply(final ICity entity) {
		final CityDTO cityDto = new CityDTO();
		cityDto.setId(entity.getId());
		cityDto.setName(entity.getName());
		cityDto.setCreated(entity.getCreated());
		cityDto.setUpdated(entity.getUpdated());
		
		final ICountry country = entity.getCountry();
		if (country != null) {
			cityDto.setCountryId(country.getId());
			cityDto.setCountryName(country.getName());
		}
		return cityDto;
	}
}
