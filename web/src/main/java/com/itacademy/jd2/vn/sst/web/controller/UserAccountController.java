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

import com.itacademy.jd2.vn.sst.dao.api.entity.enums.UserType;
import com.itacademy.jd2.vn.sst.dao.api.entity.table.IClub;
import com.itacademy.jd2.vn.sst.dao.api.entity.table.ICountry;
import com.itacademy.jd2.vn.sst.dao.api.entity.table.IFunOrganisation;
import com.itacademy.jd2.vn.sst.dao.api.entity.table.IUserAccount;
import com.itacademy.jd2.vn.sst.dao.api.filter.UserAccountFilter;
import com.itacademy.jd2.vn.sst.service.IClubService;
import com.itacademy.jd2.vn.sst.service.ICountryService;
import com.itacademy.jd2.vn.sst.service.IFunOrganisationService;
import com.itacademy.jd2.vn.sst.service.IUserAccountService;
import com.itacademy.jd2.vn.sst.web.converter.UserAccountFromDTOConverter;
import com.itacademy.jd2.vn.sst.web.converter.UserAccountToDTOConverter;
import com.itacademy.jd2.vn.sst.web.dto.UserAccountDTO;
import com.itacademy.jd2.vn.sst.web.dto.grid.GridStateDTO;

@Controller
@RequestMapping(value = "/userAccount")
public class UserAccountController extends AbstractController {
	@Autowired
	private IUserAccountService userAccountService;
	@Autowired
	private ICountryService countryService;
	@Autowired
	private IFunOrganisationService funOrganisationService;
	@Autowired
	private IClubService clubService;
	@Autowired
	private UserAccountFromDTOConverter fromDtoConverter;
	@Autowired
	private UserAccountToDTOConverter toDtoConverter;

	@RequestMapping(method = RequestMethod.GET)
	public ModelAndView index(final HttpServletRequest req,
			@RequestParam(name = "page", required = false) final Integer pageNumber,
			@RequestParam(name = "sort", required = false) final String sortColumn) {
		final GridStateDTO gridState = getListDTO(req);
		gridState.setPage(pageNumber);
		gridState.setSort(sortColumn, "id");

		final UserAccountFilter filter = new UserAccountFilter();
		prepareFilter(gridState, filter);
		gridState.setTotalCount(userAccountService.getCount(filter));

		filter.setFetchCountry(true);
		filter.setFetchFunOrganisation(true);
		
		final List<IUserAccount> entities = userAccountService.find(filter);
		List<UserAccountDTO> dtos = entities.stream().map(toDtoConverter).collect(Collectors.toList());

		final Map<String, Object> models = new HashMap<>();
		models.put("gridItems", dtos);
		return new ModelAndView("userAccount.list", models);
	}

	@RequestMapping(value = "/add", method = RequestMethod.GET)
	public ModelAndView showForm() {
		final Map<String, Object> hashMap = new HashMap<>();
		hashMap.put("formModel", new UserAccountDTO());
		loadCommonFormModels(hashMap);
		return new ModelAndView("userAccount.edit", hashMap);
	}

	@RequestMapping(method = RequestMethod.POST)
	public Object save(@Valid @ModelAttribute("formModel") final UserAccountDTO formModel, final BindingResult result) {
		if (result.hasErrors()) {
			final Map<String, Object> hashMap = new HashMap<>();
			hashMap.put("formModel", formModel);
			loadCommonFormModels(hashMap);
			return new ModelAndView("userAccount.edit", hashMap);
		} else {
			final IUserAccount entity = fromDtoConverter.apply(formModel);
			userAccountService.save(entity);
			return "redirect:/userAccount";
		}
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public ModelAndView viewDetails(@PathVariable(name = "id", required = true) final Integer id) {
		final IUserAccount dbModel = userAccountService.getFullInfo(id);
		final UserAccountDTO dto = toDtoConverter.apply(dbModel);
		final Map<String, Object> hashMap = new HashMap<>();
		hashMap.put("formModel", dto);
		hashMap.put("readonly", true);
		loadCommonFormModels(hashMap);
		return new ModelAndView("userAccount.edit", hashMap);
	}

	@RequestMapping(value = "/{id}/edit", method = RequestMethod.GET)
	public ModelAndView edit(@PathVariable(name = "id", required = true) final Integer id) {
		final UserAccountDTO dto = toDtoConverter.apply(userAccountService.getFullInfo(id));

		final Map<String, Object> hashMap = new HashMap<>();
		hashMap.put("formModel", dto);
		loadCommonFormModels(hashMap);
		return new ModelAndView("userAccount.edit", hashMap);
	}

	@RequestMapping(value = "/{id}/delete", method = RequestMethod.GET)
	public String delete(@PathVariable(name = "id", required = true) final Integer id) {
		userAccountService.delete(id);
		return "redirect:/userAccount";
	}

	private void loadCommonFormModels(Map<String, Object> hashMap) {
		final List<ICountry> countries = countryService.getAll();
		final Map<Integer, String> countriesMap = countries.stream()
				.collect(Collectors.toMap(ICountry::getId, ICountry::getName));
		hashMap.put("countriesChoices", countriesMap);

		final List<IFunOrganisation> funOrganisations = funOrganisationService.getAll();
		final Map<Integer, String> funOrganisationsMap = funOrganisations.stream()
				.collect(Collectors.toMap(IFunOrganisation::getId, IFunOrganisation::getName));
		hashMap.put("funOrganisationsChoices", funOrganisationsMap);

		final Map<Integer, String> clubsMap = clubService.getAll().stream()
				.collect(Collectors.toMap(IClub::getId, IClub::getName));
		hashMap.put("clubsChoices", clubsMap);

		final List<UserType> typeList = Arrays.asList(UserType.values());
		final Map<String, String> typesMap = typeList.stream()
				.collect(Collectors.toMap(UserType::name, UserType::name));
		hashMap.put("typeChoices", typesMap);
	}
}
