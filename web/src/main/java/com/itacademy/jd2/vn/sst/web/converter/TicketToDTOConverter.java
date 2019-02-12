package com.itacademy.jd2.vn.sst.web.converter;

import java.util.function.Function;

import org.springframework.stereotype.Component;

import com.itacademy.jd2.vn.sst.dao.api.entity.table.IEvent;
import com.itacademy.jd2.vn.sst.dao.api.entity.table.ITicket;
import com.itacademy.jd2.vn.sst.web.dto.TicketDTO;

@Component
public class TicketToDTOConverter implements Function<ITicket, TicketDTO> {

	@Override
	public TicketDTO apply(final ITicket entity) {
		final TicketDTO ticketDTO = new TicketDTO();
		ticketDTO.setId(entity.getId());
		ticketDTO.setName(entity.getName());
		ticketDTO.setSector(entity.getSector());
		ticketDTO.setRow(entity.getRow());
		ticketDTO.setSeat(entity.getSeat());
		ticketDTO.setPrice(entity.getPrice());
		ticketDTO.setPresence(entity.getPresence());
		ticketDTO.setCreated(entity.getCreated());
		ticketDTO.setUpdated(entity.getUpdated());

		final IEvent event = entity.getEvent();
		if (event != null) {
			ticketDTO.setEventId(event.getId());
			ticketDTO.setEventName(event.getName());
		}

		return ticketDTO;

	}

}
