package com.itacademy.jd2.vn.sst.web.converter;

import java.util.function.Function;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.itacademy.jd2.vn.sst.dao.api.entity.table.ISeasonTicket;
import com.itacademy.jd2.vn.sst.service.ISeasonTicketService;
import com.itacademy.jd2.vn.sst.web.dto.SeasonTicketDTO;

@Component
public class SeasonTicketFromDTOConverter implements Function<SeasonTicketDTO, ISeasonTicket> {

	@Autowired
	private ISeasonTicketService seasonTicketService;

	@Override
	public ISeasonTicket apply(SeasonTicketDTO dto) {
		final ISeasonTicket entity = seasonTicketService.createEntity();
		entity.setId(dto.getId());
		entity.setName(dto.getName());
		entity.setSector(dto.getSector());
		entity.setRow(dto.getRow());
		entity.setSeat(dto.getSeat());
		entity.setPrice(dto.getPrice());
		entity.setDate(dto.getDate());
		entity.setPresence(dto.getPresence());;

		return entity;
	}

}
