package com.itacademy.jd2.vn.sst.web.converter;

import java.util.function.Function;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.itacademy.jd2.vn.sst.dao.api.entity.table.IEvent;
import com.itacademy.jd2.vn.sst.dao.api.entity.table.ITicket;
import com.itacademy.jd2.vn.sst.service.IEventServise;
import com.itacademy.jd2.vn.sst.service.ITicketService;
import com.itacademy.jd2.vn.sst.web.dto.TicketDTO;

@Component
public class TicketFromDTOConverter implements Function<TicketDTO, ITicket> {

	@Autowired
	private ITicketService ticketService;
	@Autowired
	private IEventServise eventServise;

	@Override
	public ITicket apply(TicketDTO dto) {
		final ITicket entity = ticketService.createEntity();
		entity.setId(dto.getId());
		entity.setName(dto.getName());
		entity.setSector(dto.getSector());
		entity.setRow(dto.getRow());
		entity.setSeat(dto.getSeat());
		entity.setPrice(dto.getPrice());
		entity.setPresence(dto.getPresence());

		final IEvent event = eventServise.createEntity();
		event.setId(dto.getEventId());
		entity.setEvent(event);

		return entity;
	}

}
