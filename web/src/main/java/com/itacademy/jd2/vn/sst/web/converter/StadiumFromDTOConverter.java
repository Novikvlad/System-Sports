package com.itacademy.jd2.vn.sst.web.converter;

import java.util.function.Function;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.itacademy.jd2.vn.sst.dao.api.entity.table.ICity;
import com.itacademy.jd2.vn.sst.dao.api.entity.table.IStadium;
import com.itacademy.jd2.vn.sst.service.ICityService;
import com.itacademy.jd2.vn.sst.service.IStadiumService;
import com.itacademy.jd2.vn.sst.web.dto.StadiumDTO;

@Component
public class StadiumFromDTOConverter implements Function<StadiumDTO, IStadium> {

	@Autowired
	private IStadiumService stadiumService;
	@Autowired
	private ICityService cityService;

	@Override
	public IStadium apply(final StadiumDTO dto) {

		final IStadium entity = stadiumService.createEntity();
		entity.setId(dto.getId());
		entity.setName(dto.getName());
		entity.setCapacity(dto.getCapacity());
		entity.setAddress(dto.getAddress());
		entity.setBased(dto.getBased());

		final ICity city = cityService.createEntity();
		city.setId(dto.getCityId());
		entity.setCity(city);
		return entity;
	}

}
