package com.itacademy.jd2.vn.sst.web.converter;

import java.util.Calendar;
import java.util.Date;
import java.util.Set;
import java.util.function.Function;
import java.util.stream.Collectors;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.itacademy.jd2.vn.sst.dao.api.entity.table.IEvent;
import com.itacademy.jd2.vn.sst.dao.api.entity.table.IStadium;
import com.itacademy.jd2.vn.sst.dao.api.entity.table.ISeasonTicket;
import com.itacademy.jd2.vn.sst.service.IEventServise;
import com.itacademy.jd2.vn.sst.service.IStadiumService;
import com.itacademy.jd2.vn.sst.service.ISeasonTicketService;
import com.itacademy.jd2.vn.sst.web.dto.EventDTO;

@Component
public class EventFromDTOConverter implements Function<EventDTO, IEvent> {
	@Autowired
	private IEventServise eventService;
	@Autowired
	private IStadiumService stadiumService;
	@Autowired
	private ISeasonTicketService seasonTicketService;

	@Override
	public IEvent apply(final EventDTO dto) {
		final IEvent entity = eventService.createEntity();
		entity.setId(dto.getId());
		entity.setName(dto.getName());
		entity.setDate(dto.getDate());

		final Date date = dto.getDate();
        if (date != null) {
            final Calendar fullDateCalendar = Calendar.getInstance();
            fullDateCalendar.setTime(date);

            final Date time = dto.getTime();
            if (time != null) {
                final Calendar timeCalendar = Calendar.getInstance();
                timeCalendar.setTime(time);
                fullDateCalendar.set(Calendar.HOUR_OF_DAY,
                        timeCalendar.get(Calendar.HOUR_OF_DAY));
                fullDateCalendar.set(Calendar.MINUTE, timeCalendar.get(Calendar.MINUTE));
            }

            entity.setDate(fullDateCalendar.getTime());
        }
		
		final IStadium stadium = stadiumService.createEntity();
		stadium.setId(dto.getStadiumId());
		entity.setStadium(stadium);

		final Set<Integer> seasonTicketIds = dto.getSeasonTicketIds();
		if (CollectionUtils.isNotEmpty(seasonTicketIds)) {
			entity.setSeasonTicket(seasonTicketIds.stream().map((id) -> {
				final ISeasonTicket seasonTicket = seasonTicketService.createEntity();
				seasonTicket.setId(id);
				return seasonTicket;
			}).collect(Collectors.toSet()));
		}
		return entity;
	}
}
