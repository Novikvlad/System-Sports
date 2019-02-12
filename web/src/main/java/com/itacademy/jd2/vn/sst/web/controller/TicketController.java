package com.itacademy.jd2.vn.sst.web.controller;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.itacademy.jd2.vn.sst.dao.api.entity.enums.PresenceType;
import com.itacademy.jd2.vn.sst.dao.api.entity.table.IEvent;
import com.itacademy.jd2.vn.sst.dao.api.entity.table.ITicket;
import com.itacademy.jd2.vn.sst.dao.api.filter.TicketFilter;
import com.itacademy.jd2.vn.sst.service.IEventServise;
import com.itacademy.jd2.vn.sst.service.ITicketService;
import com.itacademy.jd2.vn.sst.web.converter.TicketFromDTOConverter;
import com.itacademy.jd2.vn.sst.web.converter.TicketToDTOConverter;
import com.itacademy.jd2.vn.sst.web.dto.TicketDTO;
import com.itacademy.jd2.vn.sst.web.dto.grid.GridStateDTO;

@Controller
@RequestMapping(value = "/ticket")
public class TicketController extends AbstractController {

	@Autowired
	private ITicketService ticketService;
	@Autowired
	private IEventServise eventService;
	@Autowired
	private TicketFromDTOConverter fromDtoConverter;
	@Autowired
	private TicketToDTOConverter toDtoConverter;

	@RequestMapping(method = RequestMethod.GET)
	public ModelAndView index(final HttpServletRequest req,
			@RequestParam(name = "page", required = false) final Integer pageNumber,
			@RequestParam(name = "sort", required = false) final String sortColumn) {

		final GridStateDTO gridState = getListDTO(req);
		gridState.setPage(pageNumber);
		gridState.setSort(sortColumn, "id");

		final TicketFilter filter = new TicketFilter();
		prepareFilter(gridState, filter);
		gridState.setTotalCount(ticketService.getCount(filter));

		filter.setFetchEvent(true);
		;
		final List<ITicket> entities = ticketService.find(filter);
		List<TicketDTO> dtos = entities.stream().map(toDtoConverter).collect(Collectors.toList());

		final Map<String, Object> models = new HashMap<>();
		models.put("gridItems", dtos);
		return new ModelAndView("ticket.list", models);
	}

	@RequestMapping(value = "/add", method = RequestMethod.GET)
	public ModelAndView showForm() {
		final Map<String, Object> hashMap = new HashMap<>();
		hashMap.put("formModel", new TicketDTO());
		loadCommonFormModels(hashMap);
		return new ModelAndView("ticket.edit", hashMap);
	}

	@RequestMapping(method = RequestMethod.POST)
	public Object save(@Valid @ModelAttribute("formModel") final TicketDTO formModel, final BindingResult result) {
		if (result.hasErrors()) {
			final Map<String, Object> hashMap = new HashMap<>();
			hashMap.put("formModel", formModel);
			loadCommonFormModels(hashMap);
			return new ModelAndView("ticket.edit", hashMap);
		} else {
			final ITicket entity = fromDtoConverter.apply(formModel);
			ticketService.save(entity);
			return "redirect:/ticket";
		}
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public ModelAndView viewDetails(@PathVariable(name = "id", required = true) final Integer id) {
		final ITicket dbModel = ticketService.getFullInfo(id);
		final TicketDTO dto = toDtoConverter.apply(dbModel);
		final Map<String, Object> hashMap = new HashMap<>();
		hashMap.put("formModel", dto);
		hashMap.put("readonly", true);
		loadCommonFormModels(hashMap);
		return new ModelAndView("ticket.edit", hashMap);
	}

	@RequestMapping(value = "/{id}/edit", method = RequestMethod.GET)
	public ModelAndView edit(@PathVariable(name = "id", required = true) final Integer id) {
		final TicketDTO dto = toDtoConverter.apply(ticketService.getFullInfo(id));

		final Map<String, Object> hashMap = new HashMap<>();
		hashMap.put("formModel", dto);
		loadCommonFormModels(hashMap);
		return new ModelAndView("ticket.edit", hashMap);
	}

	@RequestMapping(value = "/{id}/delete", method = RequestMethod.GET)
	public String delete(@PathVariable(name = "id", required = true) final Integer id) {
		ticketService.delete(id);
		return "redirect:/ticket";
	}

	private void loadCommonFormModels(Map<String, Object> hashMap) {
		final List<IEvent> tickets = eventService.getAll();
		final Map<Integer, String> eventsMap = tickets.stream()
				.collect(Collectors.toMap(IEvent::getId, IEvent::getName));
		hashMap.put("eventsChoices", eventsMap);

		final List<PresenceType> presenceList = Arrays.asList(PresenceType.values());
		final Map<String, String> typesMap = presenceList.stream()
				.collect(Collectors.toMap(PresenceType::name, PresenceType::name));
		hashMap.put("presenceChoices", typesMap);
	}

}
