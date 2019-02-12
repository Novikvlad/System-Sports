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

import com.itacademy.jd2.vn.sst.dao.api.entity.table.IClub;
import com.itacademy.jd2.vn.sst.dao.api.entity.table.IPartner;
import com.itacademy.jd2.vn.sst.dao.api.entity.table.IPartnerContract;
import com.itacademy.jd2.vn.sst.dao.api.filter.PartnerContractFilter;
import com.itacademy.jd2.vn.sst.service.IClubService;
import com.itacademy.jd2.vn.sst.service.IPartnerContractService;
import com.itacademy.jd2.vn.sst.service.IPartnerService;
import com.itacademy.jd2.vn.sst.web.converter.PartnerContractFromDTOConverter;
import com.itacademy.jd2.vn.sst.web.converter.PartnerContractToDTOConverter;
import com.itacademy.jd2.vn.sst.web.dto.PartnerContractDTO;
import com.itacademy.jd2.vn.sst.web.dto.grid.GridStateDTO;

@Controller
@RequestMapping(value = "/partnerContract")
public class PartnerContractController extends AbstractController {

	@Autowired
	private IPartnerContractService partnerContractService;
	@Autowired
	private IClubService clubService;
	@Autowired
	private IPartnerService partnerService;
	@Autowired
	private PartnerContractFromDTOConverter fromDTOConverter;
	@Autowired
	private PartnerContractToDTOConverter toDTOConverter;

	@RequestMapping(method = RequestMethod.GET)
	public ModelAndView index(final HttpServletRequest req,
			@RequestParam(name = "page", required = false) final Integer pageNumber,
			@RequestParam(name = "sort", required = false) final String sortColumn) {

		final GridStateDTO gridState = getListDTO(req);
		gridState.setPage(pageNumber);
		gridState.setSort(sortColumn, "id");

		final PartnerContractFilter filter = new PartnerContractFilter();
		prepareFilter(gridState, filter);
		gridState.setTotalCount(partnerContractService.getCount(filter));

		filter.setFetchClub(true);
		final List<IPartnerContract> entities = partnerContractService.find(filter);
		List<PartnerContractDTO> dtos = entities.stream().map(toDTOConverter).collect(Collectors.toList());

		filter.setFetchPartner(true);

		final Map<String, Object> models = new HashMap<>();
		models.put("gridItems", dtos);
		return new ModelAndView("partnerContract.list", models);
	}

	@RequestMapping(value = "/add", method = RequestMethod.GET)
	public ModelAndView showForm() {
		final Map<String, Object> hashMap = new HashMap<>();
		hashMap.put("formModel", new PartnerContractDTO());
		loadCommonFormModels(hashMap);
		return new ModelAndView("partnerContract.edit", hashMap);
	}

	@RequestMapping(method = RequestMethod.POST)
	public Object save(@Valid @ModelAttribute("formModel") final PartnerContractDTO formModel,
			final BindingResult result) {
		if (result.hasErrors()) {
			final Map<String, Object> hashMap = new HashMap<>();
			hashMap.put("formModel", formModel);
			loadCommonFormModels(hashMap);
			return new ModelAndView("partnerContract.edit", hashMap);
		} else {
			final IPartnerContract entity = fromDTOConverter.apply(formModel);
			partnerContractService.save(entity);
			return "redirect:/partnerContract";
		}
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public ModelAndView viewDetails(@PathVariable(name = "id", required = true) final Integer id) {
		final IPartnerContract dbModel = partnerContractService.getFullInfo(id);
		final PartnerContractDTO dto = toDTOConverter.apply(dbModel);
		final Map<String, Object> hashMap = new HashMap<>();
		hashMap.put("formModel", dto);
		hashMap.put("readonly", true);
		loadCommonFormModels(hashMap);
		return new ModelAndView("partnerContract.edit", hashMap);
	}

	@RequestMapping(value = "/{id}/edit", method = RequestMethod.GET)
	public ModelAndView edit(@PathVariable(name = "id", required = true) final Integer id) {
		final PartnerContractDTO dto = toDTOConverter.apply(partnerContractService.getFullInfo(id));

		final Map<String, Object> hashMap = new HashMap<>();
		hashMap.put("formModel", dto);
		loadCommonFormModels(hashMap);
		return new ModelAndView("partnerContract.edit", hashMap);
	}

	@RequestMapping(value = "/{id}/delete", method = RequestMethod.GET)
	public String delete(@PathVariable(name = "id", required = true) final Integer id) {
		partnerContractService.delete(id);
		return "redirect:/partnerContract";
	}

	private void loadCommonFormModels(Map<String, Object> hashMap) {
		final List<IClub> clubs = clubService.getAll();
		final Map<Integer, String> clubsMap = clubs.stream().collect(Collectors.toMap(IClub::getId, IClub::getName));
		hashMap.put("clubsChoices", clubsMap);

		final List<IPartner> cities = partnerService.getAll();
		final Map<Integer, String> partnersMap = cities.stream()
				.collect(Collectors.toMap(IPartner::getId, IPartner::getName));
		hashMap.put("partnersChoices", partnersMap);

	}
}
