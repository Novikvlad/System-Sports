package com.itacademy.jd2.vn.sst.web.converter;

import java.util.function.Function;

import org.springframework.stereotype.Component;

import com.itacademy.jd2.vn.sst.dao.api.entity.table.IClub;
import com.itacademy.jd2.vn.sst.dao.api.entity.table.IStadium;
import com.itacademy.jd2.vn.sst.web.dto.ClubDTO;

@Component
public class ClubToDTOConverter implements Function<IClub, ClubDTO> {

	@Override
	public ClubDTO apply(final IClub entity) {
		final ClubDTO clubDto = new ClubDTO();
		clubDto.setId(entity.getId());
		clubDto.setName(entity.getName());
		clubDto.setBased(entity.getBased());
		clubDto.setCreated(entity.getCreated());
		clubDto.setUpdated(entity.getUpdated());
		
		final IStadium stadium = entity.getStadium();
		if (stadium != null) {
			clubDto.setStadiumId(stadium.getId());
			clubDto.setStadiumName(stadium.getName());
		}
		return clubDto;
	}
}
