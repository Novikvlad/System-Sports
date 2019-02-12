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
import com.itacademy.jd2.vn.sst.dao.api.entity.table.IPartner;
import com.itacademy.jd2.vn.sst.dao.api.filter.PartnerFilter;
import com.itacademy.jd2.vn.sst.service.IPartnerService;
import com.itacademy.jd2.vn.sst.web.converter.PartnerFromDTOConverter;
import com.itacademy.jd2.vn.sst.web.converter.PartnerToDTOConverter;
import com.itacademy.jd2.vn.sst.web.dto.PartnerDTO;

@Controller
@RequestMapping(value = "/partner")
public class PartnerController extends AbstractController {
	private IPartnerService partnerService;
	private PartnerToDTOConverter toDtoConverter;
	private PartnerFromDTOConverter fromDtoConverter;

	@Autowired
	private PartnerController(IPartnerService partnerService, PartnerToDTOConverter toDtoConverter,
			PartnerFromDTOConverter fromDtoConverter) {
		super();
		this.partnerService = partnerService;
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

		final PartnerFilter filter = new PartnerFilter();
		prepareFilter(gridState, filter);
		gridState.setTotalCount(partnerService.getCount(filter));

		final List<IPartner> entities = partnerService.find(filter);
		List<PartnerDTO> dtos = entities.stream().map(toDtoConverter).collect(Collectors.toList());

		final Map<String, Object> models = new HashMap<>();
		models.put("gridItems", dtos);
		return new ModelAndView("partner.list", models);
	}

	@RequestMapping(value = "/add", method = RequestMethod.GET)
	public ModelAndView showForm() {
		final Map<String, Object> hashMap = new HashMap<>();
		final IPartner newEntity = partnerService.createEntity();
		hashMap.put("formModel", toDtoConverter.apply(newEntity));

		return new ModelAndView("partner.edit", hashMap);
	}

	@RequestMapping(method = RequestMethod.POST)
	public String save(@Valid @ModelAttribute("formModel") final PartnerDTO formModel, final BindingResult result) {
		if (result.hasErrors()) {
			return "partner.edit";
		} else {
			final IPartner entity = fromDtoConverter.apply(formModel);
			partnerService.save(entity);
			return "redirect:/partner"; 
			}
	}

	@RequestMapping(value = "/{id}/delete", method = RequestMethod.GET)
	public String delete(@PathVariable(name = "id", required = true) final Integer id) {
		partnerService.delete(id);
		return "redirect:/partner";
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public ModelAndView viewDetails(@PathVariable(name = "id", required = true) final Integer id) {
		final IPartner dbModel = partnerService.get(id);
		final PartnerDTO dto = toDtoConverter.apply(dbModel);
		final Map<String, Object> hashMap = new HashMap<>();
		hashMap.put("formModel", dto);
		hashMap.put("readonly", true);

		return new ModelAndView("partner.edit", hashMap);
	}

	@RequestMapping(value = "/{id}/edit", method = RequestMethod.GET)
	public ModelAndView edit(@PathVariable(name = "id", required = true) final Integer id) {
		final PartnerDTO dto = toDtoConverter.apply(partnerService.get(id));

		final Map<String, Object> hashMap = new HashMap<>();
		hashMap.put("formModel", dto);

		return new ModelAndView("partner.edit", hashMap);
	}
}
