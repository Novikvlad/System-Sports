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
import com.itacademy.jd2.vn.sst.dao.api.entity.table.ICountry;
import com.itacademy.jd2.vn.sst.dao.api.filter.CityFilter;
import com.itacademy.jd2.vn.sst.service.ICityService;
import com.itacademy.jd2.vn.sst.service.ICountryService;
import com.itacademy.jd2.vn.sst.web.converter.CityFromDTOConverter;
import com.itacademy.jd2.vn.sst.web.converter.CityToDTOConverter;
import com.itacademy.jd2.vn.sst.web.dto.CityDTO;
import com.itacademy.jd2.vn.sst.web.dto.grid.GridStateDTO;

@Controller
@RequestMapping(value = "/city")
public class CityController extends AbstractController {

	@Autowired
	private ICityService cityService;
	@Autowired
	private ICountryService countryService;
	@Autowired
	private CityFromDTOConverter fromDtoConverter;
	@Autowired
	private CityToDTOConverter toDtoConverter;

	@RequestMapping(method = RequestMethod.GET)
	public ModelAndView index(final HttpServletRequest req,
			@RequestParam(name = "page", required = false) final Integer pageNumber,
			@RequestParam(name = "sort", required = false) final String sortColumn) {

		final GridStateDTO gridState = getListDTO(req);
		gridState.setPage(pageNumber);
		gridState.setSort(sortColumn, "id");

		final CityFilter filter = new CityFilter();
		prepareFilter(gridState, filter);
		gridState.setTotalCount(cityService.getCount(filter));

		filter.setFetchCountry(true);
		final List<ICity> entities = cityService.find(filter);
		List<CityDTO> dtos = entities.stream().map(toDtoConverter).collect(Collectors.toList());

		final Map<String, Object> cities = new HashMap<>();
		cities.put("gridItems", dtos);
		return new ModelAndView("city.list", cities);
	}

	@RequestMapping(value = "/add", method = RequestMethod.GET)
	public ModelAndView showForm() {
		final Map<String, Object> hashMap = new HashMap<>();
		hashMap.put("formModel", new CityDTO());
		loadCommonFormModels(hashMap);
		return new ModelAndView("city.edit", hashMap);
	}

	@RequestMapping(method = RequestMethod.POST)
	public Object save(@Valid @ModelAttribute("formModel") final CityDTO formModel, final BindingResult result) {
		if (result.hasErrors()) {
			final Map<String, Object> hashMap = new HashMap<>();
			hashMap.put("formModel", formModel);
			loadCommonFormModels(hashMap);
			return new ModelAndView("city.edit", hashMap);
		} else {
			final ICity entity = fromDtoConverter.apply(formModel);
			cityService.save(entity);
			return "redirect:/city";
		}
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public ModelAndView viewDetails(@PathVariable(name = "id", required = true) final Integer id) {
		final ICity dbCity = cityService.getFullInfo(id);
		final CityDTO dto = toDtoConverter.apply(dbCity);
		final Map<String, Object> hashMap = new HashMap<>();
		hashMap.put("formModel", dto);
		hashMap.put("readonly", true);
		loadCommonFormModels(hashMap);
		return new ModelAndView("city.edit", hashMap);
	}

	@RequestMapping(value = "/{id}/edit", method = RequestMethod.GET)
	public ModelAndView edit(@PathVariable(name = "id", required = true) final Integer id) {
		final ICity dbCity = cityService.getFullInfo(id);
		final CityDTO dto = toDtoConverter.apply(dbCity);
		final Map<String, Object> hashMap = new HashMap<>();
		hashMap.put("formModel", dto);
		loadCommonFormModels(hashMap);
		return new ModelAndView("city.edit", hashMap);
	}

	@RequestMapping(value = "/{id}/delete", method = RequestMethod.GET)
	public String delete(@PathVariable(name = "id", required = true) final Integer id) {
		cityService.delete(id);
		return "redirect:/city";
	}

	private void loadCommonFormModels(final Map<String, Object> hashMap) {
		final List<ICountry> countries = countryService.getAll();
		final Map<Integer, String> countriesMap = countries.stream()
				.collect(Collectors.toMap(ICountry::getId, ICountry::getName));
		hashMap.put("countriesChoices", countriesMap);

	}

}
