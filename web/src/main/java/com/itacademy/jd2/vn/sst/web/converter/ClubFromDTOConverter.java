package com.itacademy.jd2.vn.sst.web.converter;

import java.util.function.Function;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.itacademy.jd2.vn.sst.dao.api.entity.table.IClub;
import com.itacademy.jd2.vn.sst.dao.api.entity.table.IStadium;
import com.itacademy.jd2.vn.sst.service.IClubService;
import com.itacademy.jd2.vn.sst.service.IStadiumService;
import com.itacademy.jd2.vn.sst.web.dto.ClubDTO;

@Component
public class ClubFromDTOConverter implements Function<ClubDTO, IClub> {

	@Autowired
	private IClubService clubServise;
	@Autowired
	private IStadiumService stadiumService;

	@Override
	public IClub apply(final ClubDTO dto) {

		final IClub entity = clubServise.createEntity();
		entity.setId(dto.getId());
		entity.setName(dto.getName());
		entity.setBased(dto.getBased());

		final IStadium stadium = stadiumService.createEntity();
		stadium.setId(dto.getStadiumId());
		entity.setStadium(stadium);
		return entity;
	}

}
