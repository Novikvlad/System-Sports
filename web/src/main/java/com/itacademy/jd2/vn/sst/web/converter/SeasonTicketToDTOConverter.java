package com.itacademy.jd2.vn.sst.web.converter;

import java.util.function.Function;

import org.springframework.stereotype.Component;

import com.itacademy.jd2.vn.sst.dao.api.entity.table.ISeasonTicket;
import com.itacademy.jd2.vn.sst.web.dto.SeasonTicketDTO;

@Component
public class SeasonTicketToDTOConverter implements Function<ISeasonTicket, SeasonTicketDTO> {


	@Override
	public SeasonTicketDTO apply(final ISeasonTicket entity) {
		final SeasonTicketDTO dto = new SeasonTicketDTO();
		dto.setId(entity.getId());
		dto.setName(entity.getName());
		dto.setSector(entity.getSector());
		dto.setRow(entity.getRow());
		dto.setSeat(entity.getSeat());
		dto.setPrice(entity.getPrice());
		dto.setDate(entity.getDate());
		dto.setPresence(entity.getPresence());
		dto.setCreated(entity.getCreated());
		dto.setUpdated(entity.getUpdated());
		return dto;
	}

}
