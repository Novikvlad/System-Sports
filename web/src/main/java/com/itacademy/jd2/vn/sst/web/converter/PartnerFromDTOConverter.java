package com.itacademy.jd2.vn.sst.web.converter;

import java.util.function.Function;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.itacademy.jd2.vn.sst.dao.api.entity.table.IPartner;
import com.itacademy.jd2.vn.sst.service.IPartnerService;
import com.itacademy.jd2.vn.sst.web.dto.PartnerDTO;

@Component
public class PartnerFromDTOConverter implements Function<PartnerDTO, IPartner> {
	
	@Autowired
	private IPartnerService partnerService;

	@Override
	public IPartner apply(PartnerDTO dto) {
		final IPartner entity = partnerService.createEntity();
		entity.setId(dto.getId());
		entity.setName(dto.getName());
		return entity;
	}
}
