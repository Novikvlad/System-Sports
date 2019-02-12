package com.itacademy.jd2.vn.sst.web.converter;

import java.util.function.Function;

import org.springframework.stereotype.Component;

import com.itacademy.jd2.vn.sst.dao.api.entity.table.IPartner;
import com.itacademy.jd2.vn.sst.web.dto.PartnerDTO;

@Component
public class PartnerToDTOConverter implements Function<IPartner, PartnerDTO> {

	@Override
	public PartnerDTO apply(IPartner entity) {
		final PartnerDTO dto = new PartnerDTO();
		dto.setId(entity.getId());
		dto.setName(entity.getName());
		dto.setCreated(entity.getCreated());
		dto.setUpdated(entity.getUpdated());
		return dto;
	}

}
