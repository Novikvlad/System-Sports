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

import com.itacademy.jd2.vn.sst.dao.api.entity.table.ICountry;
import com.itacademy.jd2.vn.sst.dao.api.entity.table.IRegion;
import com.itacademy.jd2.vn.sst.dao.api.filter.CountryFilter;
import com.itacademy.jd2.vn.sst.service.ICountryService;
import com.itacademy.jd2.vn.sst.service.IRegionService;
import com.itacademy.jd2.vn.sst.web.converter.CountryFromDTOConverter;
import com.itacademy.jd2.vn.sst.web.converter.CountryToDTOConverter;
import com.itacademy.jd2.vn.sst.web.dto.CountryDTO;
import com.itacademy.jd2.vn.sst.web.dto.grid.GridStateDTO;

@Controller
@RequestMapping(value = "/country")
public class CountryController extends AbstractController {

	@Autowired
	private ICountryService countryService;
	@Autowired
	private IRegionService regionService;
	@Autowired
	private CountryFromDTOConverter fromDtoConverter;
	@Autowired
	private CountryToDTOConverter toDtoConverter;

	@RequestMapping(method = RequestMethod.GET)
	public ModelAndView index(final HttpServletRequest req,
			@RequestParam(name = "page", required = false) final Integer pageNumber,
			@RequestParam(name = "sort", required = false) final String sortColumn) {

		final GridStateDTO gridState = getListDTO(req);
		gridState.setPage(pageNumber);
		gridState.setSort(sortColumn, "id");

		final CountryFilter filter = new CountryFilter();
		prepareFilter(gridState, filter);
		gridState.setTotalCount(countryService.getCount(filter));

		filter.setFetchRegion(true);
		final List<ICountry> entities = countryService.find(filter);
		List<CountryDTO> dtos = entities.stream().map(toDtoConverter).collect(Collectors.toList());

		final Map<String, Object> countries = new HashMap<>();
		countries.put("gridItems", dtos);
		return new ModelAndView("country.list", countries);
	}

	@RequestMapping(value = "/add", method = RequestMethod.GET)
	public ModelAndView showForm() {
		final Map<String, Object> hashMap = new HashMap<>();
		hashMap.put("formModel", new CountryDTO());
		loadCommonFormModels(hashMap);
		return new ModelAndView("country.edit", hashMap);
	}

	@RequestMapping(method = RequestMethod.POST)
	public Object save(@Valid @ModelAttribute("formModel") final CountryDTO formCountry, final BindingResult result) {
		if (result.hasErrors()) {
			final Map<String, Object> hashMap = new HashMap<>();
			hashMap.put("formModel", formCountry);
			loadCommonFormModels(hashMap);
			return new ModelAndView("country.edit", hashMap);
		} else {
			final ICountry entity = fromDtoConverter.apply(formCountry);
			countryService.save(entity);
			return "redirect:/country";
		}
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public ModelAndView viewDetails(@PathVariable(name = "id", required = true) final Integer id) {
		final ICountry dbCountry = countryService.getFullInfo(id);
		final CountryDTO dto = toDtoConverter.apply(dbCountry);
		final Map<String, Object> hashMap = new HashMap<>();
		hashMap.put("formModel", dto);
		hashMap.put("readonly", true);
		loadCommonFormModels(hashMap);
		return new ModelAndView("country.edit", hashMap);
	}

	@RequestMapping(value = "/{id}/edit", method = RequestMethod.GET)
	public ModelAndView edit(@PathVariable(name = "id", required = true) final Integer id) {
		final CountryDTO dto = toDtoConverter.apply(countryService.getFullInfo(id));

		final Map<String, Object> hashMap = new HashMap<>();
		hashMap.put("formModel", dto);
		loadCommonFormModels(hashMap);
		return new ModelAndView("country.edit", hashMap);
	}

	@RequestMapping(value = "/{id}/delete", method = RequestMethod.GET)
	public String delete(@PathVariable(name = "id", required = true) final Integer id) {
		countryService.delete(id);
		return "redirect:/country";
	}

	private void loadCommonFormModels(final Map<String, Object> hashMap) {
		final List<IRegion> regions = regionService.getAll();

		/*
		 * final Map<Integer, String> brandsMap = new HashMap<>(); for (final IBrand
		 * iBrand : brands) { brandsMap.put(iBrand.getId(), iBrand.getName()); }
		 */

		final Map<Integer, String> regionsMap = regions.stream()
				.collect(Collectors.toMap(IRegion::getId, IRegion::getName));
		hashMap.put("regionsChoices", regionsMap);

	}
}
