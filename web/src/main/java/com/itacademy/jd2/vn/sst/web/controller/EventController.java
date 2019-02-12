package com.itacademy.jd2.vn.sst.web.controller;

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

import com.itacademy.jd2.vn.sst.dao.api.entity.table.IEvent;
import com.itacademy.jd2.vn.sst.dao.api.entity.table.IStadium;
import com.itacademy.jd2.vn.sst.dao.api.entity.table.ISeasonTicket;
import com.itacademy.jd2.vn.sst.dao.api.filter.EventFilter;
import com.itacademy.jd2.vn.sst.service.IEventServise;
import com.itacademy.jd2.vn.sst.service.IStadiumService;
import com.itacademy.jd2.vn.sst.service.ISeasonTicketService;
import com.itacademy.jd2.vn.sst.web.converter.EventFromDTOConverter;
import com.itacademy.jd2.vn.sst.web.converter.EventToDTOConverter;
import com.itacademy.jd2.vn.sst.web.dto.EventDTO;
import com.itacademy.jd2.vn.sst.web.dto.grid.GridStateDTO;
import com.itacademy.jd2.vn.sst.web.dto.search.EventSearchDTO;

@Controller
@RequestMapping(value = "/event")
public class EventController extends AbstractController {

	private static final String SEARCH_FORM_MODEL = "searchFormModel";
	private static final String SEARCH_DTO = EventController.class.getSimpleName() + "_SEACH_DTO";

	@Autowired
	private IEventServise eventService;
	@Autowired
	private ISeasonTicketService seasonTicketService;
	@Autowired
	private IStadiumService stadiumService;
	@Autowired
	private EventFromDTOConverter fromDtoConverter;
	@Autowired
	private EventToDTOConverter toDtoConverter;

	@RequestMapping(method = { RequestMethod.POST, RequestMethod.GET })
	public ModelAndView index(final HttpServletRequest req, 
			@ModelAttribute(SEARCH_FORM_MODEL) EventSearchDTO searchDto,
			@RequestParam(name = "page", required = false) final Integer pageNumber,
			@RequestParam(name = "sort", required = false) final String sortColumn) {

		final GridStateDTO gridState = getListDTO(req);
		gridState.setPage(pageNumber);
		gridState.setSort(sortColumn, "id");

		if (req.getMethod().equalsIgnoreCase("get")) {
			searchDto = getSearchDTO(req);
		} else {
			req.getSession().setAttribute(SEARCH_DTO, searchDto);
		}

		final EventFilter filter = new EventFilter();
		if (searchDto.getName() != null) {
			filter.setName(searchDto.getName());
		}

		prepareFilter(gridState, filter);
		gridState.setTotalCount(eventService.getCount(filter));

		filter.setFetchStadium(true);
		final List<IEvent> entities = eventService.find(filter);
		List<EventDTO> dtos = entities.stream().map(toDtoConverter).collect(Collectors.toList());

		final Map<String, Object> models = new HashMap<>();
		models.put("gridItems", dtos);
		models.put(SEARCH_FORM_MODEL, searchDto);
		return new ModelAndView("event.list", models);
	}

	@RequestMapping(value = "/add", method = RequestMethod.GET)
	public ModelAndView showForm() {
		final Map<String, Object> hashMap = new HashMap<>();
		hashMap.put("formModel", new EventDTO());
		loadCommonFormModels(hashMap);
		return new ModelAndView("event.edit", hashMap);
	}

	@RequestMapping(value="/save", method = RequestMethod.POST)
	public Object save(@Valid @ModelAttribute("formModel") final EventDTO formModel, final BindingResult result) {
		if (result.hasErrors()) {
			final Map<String, Object> hashMap = new HashMap<>();
			hashMap.put("formModel", formModel);
			loadCommonFormModels(hashMap);
			return new ModelAndView("event.edit", hashMap);
		} else {
			final IEvent entity = fromDtoConverter.apply(formModel);
			eventService.save(entity);
			return "redirect:/event";
		}
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public ModelAndView viewDetails(@PathVariable(name = "id", required = true) final Integer id) {
		final IEvent dbModel = eventService.getFullInfo(id);
		final EventDTO dto = toDtoConverter.apply(dbModel);
		final Map<String, Object> hashMap = new HashMap<>();
		hashMap.put("formModel", dto);
		hashMap.put("readonly", true);
		loadCommonFormModels(hashMap);
		return new ModelAndView("event.edit", hashMap);
	}

	@RequestMapping(value = "/{id}/edit", method = RequestMethod.GET)
	public ModelAndView edit(@PathVariable(name = "id", required = true) final Integer id) {
		final EventDTO dto = toDtoConverter.apply(eventService.getFullInfo(id));

		final Map<String, Object> hashMap = new HashMap<>();
		hashMap.put("formModel", dto);
		loadCommonFormModels(hashMap);
		return new ModelAndView("event.edit", hashMap);
	}

	@RequestMapping(value = "/{id}/delete", method = RequestMethod.GET)
	public String delete(@PathVariable(name = "id", required = true) final Integer id) {
		eventService.delete(id);
		return "redirect:/event";
	}

	private void loadCommonFormModels(final Map<String, Object> hashMap) {
		final List<IStadium> events = stadiumService.getAll();

		final Map<Integer, String> stadiumsMap = events.stream()
				.collect(Collectors.toMap(IStadium::getId, IStadium::getName));
		hashMap.put("stadiumsChoices", stadiumsMap);

		final Map<Integer, String> seasonTicketsMap = seasonTicketService.getAll().stream()
				.collect(Collectors.toMap(ISeasonTicket::getId, ISeasonTicket::getName));
		hashMap.put("seasonTicketsChoices", seasonTicketsMap);
	}

	private EventSearchDTO getSearchDTO(final HttpServletRequest req) {
		EventSearchDTO searchDTO = (EventSearchDTO) req.getSession().getAttribute(SEARCH_DTO);
		if (searchDTO == null) {
			searchDTO = new EventSearchDTO();
			req.getSession().setAttribute(SEARCH_DTO, searchDTO);
		}
		return searchDTO;
	}
}
