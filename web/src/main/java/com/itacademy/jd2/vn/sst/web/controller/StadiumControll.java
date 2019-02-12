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

import com.itacademy.jd2.vn.sst.dao.api.entity.table.ICity;
import com.itacademy.jd2.vn.sst.dao.api.entity.table.IStadium;
import com.itacademy.jd2.vn.sst.dao.api.filter.StadiumFilter;
import com.itacademy.jd2.vn.sst.service.ICityService;
import com.itacademy.jd2.vn.sst.service.IStadiumService;
import com.itacademy.jd2.vn.sst.web.converter.StadiumFromDTOConverter;
import com.itacademy.jd2.vn.sst.web.converter.StadiumToDTOConverter;
import com.itacademy.jd2.vn.sst.web.dto.StadiumDTO;
import com.itacademy.jd2.vn.sst.web.dto.grid.GridStateDTO;

@Controller
@RequestMapping(value = "/stadium")
public class StadiumControll extends AbstractController {

	@Autowired
	private IStadiumService stadiumService;
	@Autowired
	private ICityService cityService;
	@Autowired
	private StadiumFromDTOConverter fromDtoConverter;
	@Autowired
	private StadiumToDTOConverter toDtoConverter;

	@RequestMapping(method = RequestMethod.GET)
	public ModelAndView index(final HttpServletRequest req,
			@RequestParam(name = "page", required = false) final Integer pageNumber,
			@RequestParam(name = "sort", required = false) final String sortColumn) {

		final GridStateDTO gridState = getListDTO(req);
		gridState.setPage(pageNumber);
		gridState.setSort(sortColumn, "id");

		final StadiumFilter filter = new StadiumFilter();
		prepareFilter(gridState, filter);
		gridState.setTotalCount(stadiumService.getCount(filter));

		filter.setFetchCity(true);
		filter.setFetchTicket(true);
		final List<IStadium> entities = stadiumService.find(filter);
		List<StadiumDTO> dtos = entities.stream().map(toDtoConverter).collect(Collectors.toList());

		final Map<String, Object> models = new HashMap<>();
		models.put("gridItems", dtos);
		return new ModelAndView("stadium.list", models);
	}

	@RequestMapping(value = "/add", method = RequestMethod.GET)
	public ModelAndView showForm() {
		final Map<String, Object> hashMap = new HashMap<>();
		hashMap.put("formModel", new StadiumDTO());
		loadCommonFormModels(hashMap);
		return new ModelAndView("stadium.edit", hashMap);
	}

	@RequestMapping(method = RequestMethod.POST)
	public Object save(@Valid @ModelAttribute("formModel") final StadiumDTO formStadium, final BindingResult result) {
		if (result.hasErrors()) {
			final Map<String, Object> hashMap = new HashMap<>();
			hashMap.put("formModel", formStadium);
			loadCommonFormModels(hashMap);
			return new ModelAndView("stadium.edit", hashMap);
		} else {
			final IStadium entity = fromDtoConverter.apply(formStadium);
			stadiumService.save(entity);
			return "redirect:/stadium";
		}
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public ModelAndView viewDetails(@PathVariable(name = "id", required = true) final Integer id) {
		final IStadium dbStadium = stadiumService.getFullInfo(id);
		final StadiumDTO dto = toDtoConverter.apply(dbStadium);
		final Map<String, Object> hashMap = new HashMap<>();
		hashMap.put("formModel", dto);
		hashMap.put("readonly", true);
		loadCommonFormModels(hashMap);
		return new ModelAndView("stadium.edit", hashMap);
	}

	@RequestMapping(value = "/{id}/edit", method = RequestMethod.GET)
	public ModelAndView edit(@PathVariable(name = "id", required = true) final Integer id) {
		final StadiumDTO dto = toDtoConverter.apply(stadiumService.getFullInfo(id));
		final Map<String, Object> hashMap = new HashMap<>();
		hashMap.put("formModel", dto);
		loadCommonFormModels(hashMap);
		return new ModelAndView("stadium.edit", hashMap);
	}

	@RequestMapping(value = "/{id}/delete", method = RequestMethod.GET)
	public String delete(@PathVariable(name = "id", required = true) final Integer id) {
		stadiumService.delete(id);
		return "redirect:/stadium";
	}

	private void loadCommonFormModels(final Map<String, Object> hashMap) {
		final List<ICity> cities = cityService.getAll();
		final Map<Integer, String> citiesMap = cities.stream().collect(Collectors.toMap(ICity::getId, ICity::getName));
		hashMap.put("citiesChoices", citiesMap);

	}
	
}
