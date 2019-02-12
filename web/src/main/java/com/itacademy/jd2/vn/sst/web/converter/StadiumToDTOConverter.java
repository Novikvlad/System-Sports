package com.itacademy.jd2.vn.sst.web.converter;

import java.util.function.Function;

import org.springframework.stereotype.Component;

import com.itacademy.jd2.vn.sst.dao.api.entity.table.ICity;
import com.itacademy.jd2.vn.sst.dao.api.entity.table.IStadium;
import com.itacademy.jd2.vn.sst.web.dto.StadiumDTO;

@Component
public class StadiumToDTOConverter implements Function<IStadium, StadiumDTO> {

	@Override
	public StadiumDTO apply(final IStadium entity) {
		final StadiumDTO stadiumDTO = new StadiumDTO();
		stadiumDTO.setId(entity.getId());
		stadiumDTO.setName(entity.getName());
		stadiumDTO.setCapacity(entity.getCapacity());
		stadiumDTO.setAddress(entity.getAddress());
		stadiumDTO.setBased(entity.getBased());
		stadiumDTO.setCreated(entity.getCreated());
		stadiumDTO.setUpdated(entity.getUpdated());

		final ICity city = entity.getCity();
		if (city != null) {
			stadiumDTO.setCityId(city.getId());
			stadiumDTO.setCityName(city.getName());
		}
		return stadiumDTO;
	}

}
