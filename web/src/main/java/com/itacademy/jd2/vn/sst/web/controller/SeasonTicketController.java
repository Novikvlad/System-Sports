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

import com.itacademy.jd2.vn.sst.dao.api.entity.enums.PresenceType;
import com.itacademy.jd2.vn.sst.dao.api.entity.table.ISeasonTicket;
import com.itacademy.jd2.vn.sst.dao.api.filter.SeasonTicketFilter;
import com.itacademy.jd2.vn.sst.service.ISeasonTicketService;
import com.itacademy.jd2.vn.sst.web.converter.SeasonTicketFromDTOConverter;
import com.itacademy.jd2.vn.sst.web.converter.SeasonTicketToDTOConverter;
import com.itacademy.jd2.vn.sst.web.dto.SeasonTicketDTO;
import com.itacademy.jd2.vn.sst.web.dto.grid.GridStateDTO;


@Controller
@RequestMapping(value = "/seasonTicket")
public class SeasonTicketController extends AbstractController{
	 @Autowired
	    private ISeasonTicketService seasonTicketService;
	    @Autowired
	    private SeasonTicketFromDTOConverter fromDtoConverter;

	    @Autowired
	    private SeasonTicketToDTOConverter toDtoConverter;

	    @RequestMapping(method = RequestMethod.GET)
	    public ModelAndView index(final HttpServletRequest req,
	            @RequestParam(name = "page", required = false) final Integer pageNumber,
	            @RequestParam(name = "sort", required = false) final String sortColumn) {

	        final GridStateDTO gridState = getListDTO(req);
	        gridState.setPage(pageNumber);
	        gridState.setSort(sortColumn, "id");

	        SeasonTicketFilter filter = new SeasonTicketFilter();
	        prepareFilter(gridState, filter);

	        final List<ISeasonTicket> entities = seasonTicketService.find(filter);
	        List<SeasonTicketDTO> dtos = entities.stream().map(toDtoConverter).collect(Collectors.toList());
	        gridState.setTotalCount(seasonTicketService.getCount(filter));

	        final HashMap<String, Object> models = new HashMap<>();
	        models.put("gridItems", dtos);
	        return new ModelAndView("seasonTicket.list", models);
	    }

	    @RequestMapping(value = "/add", method = RequestMethod.GET)
	    public ModelAndView showForm() {
	        final Map<String, Object> hashMap = new HashMap<>();
	        final SeasonTicketDTO dto = new SeasonTicketDTO();
	        hashMap.put("formModel", dto);
	        return new ModelAndView("seasonTicket.edit", hashMap);
	    }

	    @RequestMapping(method = RequestMethod.POST)
	    public Object save(@Valid @ModelAttribute("formModel") final SeasonTicketDTO formModel, final BindingResult result) {
	        if (result.hasErrors()) {
	            Map<String, Object> hashMap = new HashMap<>();
	            return new ModelAndView("seasonTicket.edit", hashMap) ;
	        } else {
	            final ISeasonTicket entity = fromDtoConverter.apply(formModel);
	            seasonTicketService.save(entity);
	            return "redirect:/seasonTicket";
	        }
	    }

	    @RequestMapping(value = "/{id}/delete", method = RequestMethod.GET)
	    public String delete(@PathVariable(name = "id", required = true) final Integer id) {
	    	seasonTicketService.delete(id);
	        return "redirect:/seasonTicket";
	    }

	    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
	    public ModelAndView viewDetails(@PathVariable(name = "id", required = true) final Integer id) {
	        final ISeasonTicket dbModel = seasonTicketService.get(id);
	        final SeasonTicketDTO dto = toDtoConverter.apply(dbModel);
	        final Map<String, Object> hashMap = new HashMap<>();
	        hashMap.put("formModel", dto);
	        hashMap.put("readonly", true);
	        return new ModelAndView("seasonTicket.edit", hashMap);
	    }

	    @RequestMapping(value = "/{id}/edit", method = RequestMethod.GET)
	    public ModelAndView edit(@PathVariable(name = "id", required = true) final Integer id) {
	        final SeasonTicketDTO dto = toDtoConverter.apply(seasonTicketService.get(id));
	        final Map<String, Object> hashMap = new HashMap<>();
	        hashMap.put("formModel", dto);
	    
	        final List<PresenceType> presenceList = Arrays.asList(PresenceType.values());
			final Map<String, String> typesMap = presenceList.stream()
					.collect(Collectors.toMap(PresenceType::name, PresenceType::name));
			hashMap.put("seasonChoices", typesMap);
	        return new ModelAndView("seasonTicket.edit", hashMap);
	    }

}
