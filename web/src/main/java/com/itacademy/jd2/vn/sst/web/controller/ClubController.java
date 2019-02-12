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
import com.itacademy.jd2.vn.sst.dao.api.entity.table.IPartnerContract;
import com.itacademy.jd2.vn.sst.dao.api.entity.table.IStadium;
import com.itacademy.jd2.vn.sst.dao.api.filter.ClubFilter;
import com.itacademy.jd2.vn.sst.dao.api.filter.PartnerContractFilter;
import com.itacademy.jd2.vn.sst.service.IClubService;
import com.itacademy.jd2.vn.sst.service.IPartnerContractService;
import com.itacademy.jd2.vn.sst.service.IStadiumService;
import com.itacademy.jd2.vn.sst.web.converter.ClubFromDTOConverter;
import com.itacademy.jd2.vn.sst.web.converter.ClubToDTOConverter;
import com.itacademy.jd2.vn.sst.web.dto.ClubDTO;
import com.itacademy.jd2.vn.sst.web.dto.grid.GridStateDTO;

@Controller
@RequestMapping(value = "/club")
public class ClubController extends AbstractController {

	@Autowired
	private IClubService clubService;
	@Autowired
	private IStadiumService stadiumService;
	@Autowired
	private IPartnerContractService partnerContractService;
	@Autowired
	private ClubFromDTOConverter fromDtoConverter;
	@Autowired
	private ClubToDTOConverter toDtoConverter;

	@RequestMapping(method = RequestMethod.GET)
	public ModelAndView index(final HttpServletRequest req,
			@RequestParam(name = "page", required = false) final Integer pageNumber,
			@RequestParam(name = "sort", required = false) final String sortColumn) {

		final GridStateDTO gridState = getListDTO(req);
		gridState.setPage(pageNumber);
		gridState.setSort(sortColumn, "id");

		final ClubFilter filter = new ClubFilter();
		prepareFilter(gridState, filter);
		gridState.setTotalCount(clubService.getCount(filter));
		filter.setFetchPartnerContract(true);
		filter.setFetchStadium(true);
		final List<IClub> entities = clubService.find(filter);
		List<ClubDTO> dtos = entities.stream().map(toDtoConverter).collect(Collectors.toList());

		final Map<String, Object> clubs = new HashMap<>();
		clubs.put("gridItems", dtos);
		return new ModelAndView("club.list", clubs);
	}

	@RequestMapping(value = "/add", method = RequestMethod.GET)
	public ModelAndView showForm() {
		final Map<String, Object> hashMap = new HashMap<>();
		hashMap.put("formModel", new ClubDTO());
		loadCommonFormModels(hashMap);
		return new ModelAndView("club.edit", hashMap);
	}

	@RequestMapping(method = RequestMethod.POST)
	public Object save(@Valid @ModelAttribute("formModel") final ClubDTO formModel, final BindingResult result) {
		if (result.hasErrors()) {
			final Map<String, Object> hashMap = new HashMap<>();
			hashMap.put("formModel", formModel);
			loadCommonFormModels(hashMap);
			return new ModelAndView("club.edit", hashMap);
		} else {
			final IClub entity = fromDtoConverter.apply(formModel);
			clubService.save(entity);
			return "redirect:/club";
		}
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public ModelAndView viewDetails(@PathVariable(name = "id", required = true) final Integer id) {
		final IClub dbClub = clubService.getFullInfo(id);
		final ClubDTO dto = toDtoConverter.apply(dbClub);
		final Map<String, Object> hashMap = new HashMap<>();
		hashMap.put("formModel", dto);
		hashMap.put("readonly", true);
		loadCommonFormModels(hashMap);

		PartnerContractFilter filter = new PartnerContractFilter();
		filter.setClubId(id);
		final List<IPartnerContract> partnerContracts = partnerContractService.find(filter);
		StringBuilder sb = new StringBuilder("");
		sb.append("[");
		sb.append("['col1','col2'],");
		for (IPartnerContract contract : partnerContracts) {
			sb.append("[");
			sb.append("'" + contract.getPartner().getName() + "',");
			sb.append(contract.getContractValue());
			sb.append("],");
		}
		sb.append("]");

		hashMap.put("jsData", sb.toString());
		return new ModelAndView("club.edit", hashMap);
	}

	@RequestMapping(value = "/{id}/edit", method = RequestMethod.GET)
	public ModelAndView edit(@PathVariable(name = "id", required = true) final Integer id) {
	final IClub dbClub=clubService.getFullInfo(id);
		final ClubDTO dto = toDtoConverter.apply(dbClub);

		final Map<String, Object> hashMap = new HashMap<>();
		hashMap.put("formModel", dto);
		loadCommonFormModels(hashMap);
		return new ModelAndView("club.edit", hashMap);
	}

	@RequestMapping(value = "/{id}/delete", method = RequestMethod.GET)
	public String delete(@PathVariable(name = "id", required = true) final Integer id) {
		clubService.delete(id);
		return "redirect:/stadium";
	}

	private void loadCommonFormModels(Map<String, Object> hashMap) {
		final List<IStadium> stadiums = stadiumService.getAll();

		final Map<Integer, String> stadiumsMap = stadiums.stream()
				.collect(Collectors.toMap(IStadium::getId, IStadium::getName));
		hashMap.put("stadiumsChoices", stadiumsMap);

	}
}
