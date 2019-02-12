package com.itacademy.jd2.vn.sst.web.converter;

import java.util.function.Function;

import org.springframework.stereotype.Component;

import com.itacademy.jd2.vn.sst.dao.api.entity.table.IRegion;
import com.itacademy.jd2.vn.sst.web.dto.RegionDTO;

@Component
public class RegionToDTOConverter implements Function<IRegion, RegionDTO> {

	@Override
	public RegionDTO apply(final IRegion entity) {
		final RegionDTO dto = new RegionDTO();
		dto.setId(entity.getId());
		dto.setName(entity.getName());
		dto.setCreated(entity.getCreated());
		dto.setUpdated(entity.getUpdated());
		return dto;
	}

}
