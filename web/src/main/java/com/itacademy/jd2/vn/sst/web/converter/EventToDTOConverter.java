package com.itacademy.jd2.vn.sst.web.converter;

import java.util.function.Function;

import org.springframework.stereotype.Component;

import com.itacademy.jd2.vn.sst.dao.api.entity.table.IEvent;
import com.itacademy.jd2.vn.sst.dao.api.entity.table.IStadium;
import com.itacademy.jd2.vn.sst.web.dto.EventDTO;

@Component
public class EventToDTOConverter implements Function<IEvent, EventDTO> {

	@Override
	public EventDTO apply(final IEvent entity) {
		final EventDTO eventDTO = new EventDTO();
		eventDTO.setId(entity.getId());
		eventDTO.setName(entity.getName());
		eventDTO.setDate(entity.getDate());
		eventDTO.setTime(entity.getDate());
		
		final IStadium event = entity.getStadium();
		if (event != null) {
			eventDTO.setStadiumId(event.getId());
			eventDTO.setStadiumName(event.getName());

		}
		eventDTO.setCreated(entity.getCreated());
		eventDTO.setUpdated(entity.getUpdated());
		return eventDTO;
	}
}