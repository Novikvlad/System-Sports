package com.itacademy.jd2.vn.sst.web.converter;

import java.util.function.Function;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.itacademy.jd2.vn.sst.dao.api.entity.table.IClub;
import com.itacademy.jd2.vn.sst.dao.api.entity.table.IPartner;
import com.itacademy.jd2.vn.sst.dao.api.entity.table.IPartnerContract;
import com.itacademy.jd2.vn.sst.service.IClubService;
import com.itacademy.jd2.vn.sst.service.IPartnerContractService;
import com.itacademy.jd2.vn.sst.service.IPartnerService;
import com.itacademy.jd2.vn.sst.web.dto.PartnerContractDTO;

@Component
public class PartnerContractFromDTOConverter implements Function<PartnerContractDTO, IPartnerContract> {

	@Autowired
	private IPartnerContractService partnerContractService;
	@Autowired
	private IClubService clubService;
	@Autowired
	private IPartnerService partnerService;

	@Override
	public IPartnerContract apply(PartnerContractDTO dto) {
		
		final IPartnerContract entity = partnerContractService.createEntity();
		entity.setId(dto.getId());
		entity.setDateSigning(dto.getDateSigning());
		entity.setContractTerm(dto.getContractTerm());
		entity.setContractValue(dto.getContractValue());

		final IClub club = clubService.createEntity();
		club.setId(dto.getClubId());
		entity.setClub(club);

		final IPartner partner = partnerService.createEntity();
		partner.setId(dto.getPartnerId());
		entity.setPartner(partner);
		return entity;
	}

}
