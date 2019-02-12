package com.itacademy.jd2.vn.sst.web.converter;

import java.util.function.Function;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.itacademy.jd2.vn.sst.dao.api.entity.table.IRegion;
import com.itacademy.jd2.vn.sst.service.IRegionService;
import com.itacademy.jd2.vn.sst.web.dto.RegionDTO;

@Component
public class RegionFromDTOConverter implements Function<RegionDTO, IRegion> {

	@Autowired
	private IRegionService regionService;

	@Override
	public IRegion apply(final RegionDTO dto) {
		final IRegion entity = regionService.createEntity();
		entity.setId(dto.getId());
		entity.setName(dto.getName());
		return entity;
	}
}
