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
import com.itacademy.jd2.vn.sst.dao.api.entity.table.IClub;
import com.itacademy.jd2.vn.sst.dao.api.entity.table.IFunOrganisation;
import com.itacademy.jd2.vn.sst.dao.api.filter.FunOrganisationFilter;
import com.itacademy.jd2.vn.sst.service.ICityService;
import com.itacademy.jd2.vn.sst.service.IClubService;
import com.itacademy.jd2.vn.sst.service.IFunOrganisationService;
import com.itacademy.jd2.vn.sst.web.converter.FunOrganisationFromDTOConverter;
import com.itacademy.jd2.vn.sst.web.converter.FunOrganisationToDTOConverter;
import com.itacademy.jd2.vn.sst.web.dto.FunOrganisationDTO;
import com.itacademy.jd2.vn.sst.web.dto.grid.GridStateDTO;

@Controller
@RequestMapping(value = "/funOrganisation")
public class FunOrganisationController extends AbstractController {

	@Autowired
	private IFunOrganisationService funOrganisationService;
	@Autowired
	private IClubService clubService;
	@Autowired
	private ICityService cityService;
	@Autowired
	private FunOrganisationFromDTOConverter fromDtoConverter;
	@Autowired
	private FunOrganisationToDTOConverter toDtoConverter;

	@RequestMapping(method = RequestMethod.GET)
	public ModelAndView index(final HttpServletRequest req,
			@RequestParam(name = "page", required = false) final Integer pageNumber,
			@RequestParam(name = "sort", required = false) final String sortColumn) {

		final GridStateDTO gridState = getListDTO(req);
		gridState.setPage(pageNumber);
		gridState.setSort(sortColumn, "id");

		final FunOrganisationFilter filter = new FunOrganisationFilter();
		prepareFilter(gridState, filter);
		gridState.setTotalCount(funOrganisationService.getCount(filter));
		
		filter.setFetchCity(true);
		filter.setFetchClub(true);
		
		final List<IFunOrganisation> entities = funOrganisationService.find(filter);
		List<FunOrganisationDTO> dtos = entities.stream().map(toDtoConverter).collect(Collectors.toList());

		

		final Map<String, Object> models = new HashMap<>();
		models.put("gridItems", dtos);
		return new ModelAndView("funOrganisation.list", models);
	}

	@RequestMapping(value = "/add", method = RequestMethod.GET)
	public ModelAndView showForm() {
		final Map<String, Object> hashMap = new HashMap<>();
		hashMap.put("formModel", new FunOrganisationDTO());
		loadCommonFormModels(hashMap);
		return new ModelAndView("funOrganisation.edit", hashMap);
	}

	@RequestMapping(method = RequestMethod.POST)
	public Object save(@Valid @ModelAttribute("formModel") final FunOrganisationDTO formModel,
			final BindingResult result) {
		if (result.hasErrors()) {
			final Map<String, Object> hashMap = new HashMap<>();
			hashMap.put("formModel", formModel);
			loadCommonFormModels(hashMap);
			return new ModelAndView("funOrganisation.edit", hashMap);
		} else {
			final IFunOrganisation entity = fromDtoConverter.apply(formModel);
			funOrganisationService.save(entity);
			return "redirect:/funOrganisation";
		}
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public ModelAndView viewDetails(@PathVariable(name = "id", required = true) final Integer id) {
		final IFunOrganisation dbModel = funOrganisationService.getFullInfo(id);
		final FunOrganisationDTO dto = toDtoConverter.apply(dbModel);
		final Map<String, Object> hashMap = new HashMap<>();
		hashMap.put("formModel", dto);
		hashMap.put("readonly", true);
		loadCommonFormModels(hashMap);
		return new ModelAndView("funOrganisation.edit", hashMap);
	}

	@RequestMapping(value = "/{id}/edit", method = RequestMethod.GET)
	public ModelAndView edit(@PathVariable(name = "id", required = true) final Integer id) {
		final FunOrganisationDTO dto = toDtoConverter.apply(funOrganisationService.getFullInfo(id));

		final Map<String, Object> hashMap = new HashMap<>();
		hashMap.put("formModel", dto);
		loadCommonFormModels(hashMap);
		return new ModelAndView("funOrganisation.edit", hashMap);
	}

	@RequestMapping(value = "/{id}/delete", method = RequestMethod.GET)
	public String delete(@PathVariable(name = "id", required = true) final Integer id) {
		funOrganisationService.delete(id);
		return "redirect:/funOrganisation";
	}

	private void loadCommonFormModels(Map<String, Object> hashMap) {
		final List<IClub> clubs = clubService.getAll();
		final Map<Integer, String> clubsMap = clubs.stream().collect(Collectors.toMap(IClub::getId, IClub::getName));
		hashMap.put("clubsChoices", clubsMap);

		final List<ICity> cities = cityService.getAll();
		final Map<Integer, String> citiesMap = cities.stream().collect(Collectors.toMap(ICity::getId, ICity::getName));
		hashMap.put("citiesChoices", citiesMap);

	}
}
