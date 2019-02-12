package com.itacademy.jd2.vn.sst.web.converter;

import java.util.function.Function;

import org.springframework.stereotype.Component;

import com.itacademy.jd2.vn.sst.dao.api.entity.table.ICountry;
import com.itacademy.jd2.vn.sst.dao.api.entity.table.IRegion;
import com.itacademy.jd2.vn.sst.web.dto.CountryDTO;

@Component
public class CountryToDTOConverter implements Function<ICountry, CountryDTO> {

	@Override
	public CountryDTO apply(final ICountry entity) {

		final CountryDTO countryDto = new CountryDTO();
		countryDto.setId(entity.getId());
		countryDto.setName(entity.getName());
		countryDto.setCreated(entity.getCreated());
		countryDto.setUpdated(entity.getUpdated());

		final IRegion region = entity.getRegion();
		if (region != null) {
			countryDto.setRegionId(region.getId());
			countryDto.setRegionName(region.getName());
		}
		return countryDto;
	}
}
