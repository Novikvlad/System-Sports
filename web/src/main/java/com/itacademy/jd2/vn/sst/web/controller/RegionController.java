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

import com.itacademy.jd2.vn.sst.web.dto.grid.GridStateDTO;
import com.itacademy.jd2.vn.sst.dao.api.entity.table.IRegion;
import com.itacademy.jd2.vn.sst.dao.api.filter.RegionFilter;
import com.itacademy.jd2.vn.sst.service.IRegionService;
import com.itacademy.jd2.vn.sst.web.converter.RegionFromDTOConverter;
import com.itacademy.jd2.vn.sst.web.converter.RegionToDTOConverter;
import com.itacademy.jd2.vn.sst.web.dto.RegionDTO;

@Controller
@RequestMapping(value = "/region")
public class RegionController extends AbstractController {
	private IRegionService regionService;
	private RegionToDTOConverter toDtoConverter;
	private RegionFromDTOConverter fromDtoConverter;

	@Autowired
	private RegionController(IRegionService regionService, RegionToDTOConverter toDtoConverter,
			RegionFromDTOConverter fromDtoConverter) {
		super();
		this.regionService = regionService;
		this.toDtoConverter = toDtoConverter;
		this.fromDtoConverter = fromDtoConverter;
	}

	@RequestMapping(method = RequestMethod.GET)
	public ModelAndView index(final HttpServletRequest req,
			@RequestParam(name = "page", required = false) final Integer pageNumber,
			@RequestParam(name = "sort", required = false) final String sortColumn) {
		final GridStateDTO gridState = getListDTO(req);
		gridState.setPage(pageNumber);
		gridState.setSort(sortColumn, "id");

		final RegionFilter filter = new RegionFilter();
		prepareFilter(gridState, filter);
		gridState.setTotalCount(regionService.getCount(filter));

		final List<IRegion> entities = regionService.find(filter);
		List<RegionDTO> dtos = entities.stream().map(toDtoConverter).collect(Collectors.toList());

		final Map<String, Object> models = new HashMap<>();
		models.put("gridItems", dtos);
		return new ModelAndView("region.list", models);
	}

	@RequestMapping(value = "/add", method = RequestMethod.GET)
	public ModelAndView showForm() {
		final Map<String, Object> hashMap = new HashMap<>();
		final IRegion newEntity = regionService.createEntity();
		hashMap.put("formModel", toDtoConverter.apply(newEntity));

		return new ModelAndView("region.edit", hashMap);
	}

	@RequestMapping(method = RequestMethod.POST)
	public String save(@Valid @ModelAttribute("formModel") final RegionDTO formModel, final BindingResult result) {
		if (result.hasErrors()) {
			return "region.edit";
		} else {
			final IRegion entity = fromDtoConverter.apply(formModel);
			regionService.save(entity);
			return "redirect:/region";
		}
	}

	@RequestMapping(value = "/{id}/delete", method = RequestMethod.GET)
	public String delete(@PathVariable(name = "id", required = true) final Integer id) {
		regionService.delete(id);
		return "redirect:/region";
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public ModelAndView viewDetails(@PathVariable(name = "id", required = true) final Integer id) {
		final IRegion dbModel = regionService.get(id);
		final RegionDTO dto = toDtoConverter.apply(dbModel);
		final Map<String, Object> hashMap = new HashMap<>();
		hashMap.put("formModel", dto);
		hashMap.put("readonly", true);

		return new ModelAndView("region.edit", hashMap);
	}

	@RequestMapping(value = "/{id}/edit", method = RequestMethod.GET)
	public ModelAndView edit(@PathVariable(name = "id", required = true) final Integer id) {
		final RegionDTO dto = toDtoConverter.apply(regionService.get(id));

		final Map<String, Object> hashMap = new HashMap<>();
		hashMap.put("formModel", dto);

		return new ModelAndView("region.edit", hashMap);
	}
}
