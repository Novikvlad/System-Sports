package com.itacademy.jd2.vn.sst.web.converter;

import java.util.function.Function;

import org.springframework.stereotype.Component;

import com.itacademy.jd2.vn.sst.dao.api.entity.table.IClub;
import com.itacademy.jd2.vn.sst.dao.api.entity.table.IPartner;
import com.itacademy.jd2.vn.sst.dao.api.entity.table.IPartnerContract;
import com.itacademy.jd2.vn.sst.web.dto.PartnerContractDTO;

@Component
public class PartnerContractToDTOConverter implements Function<IPartnerContract, PartnerContractDTO> {

	@Override
	public PartnerContractDTO apply(IPartnerContract entity) {

		final PartnerContractDTO partnerContractDTO = new PartnerContractDTO();
		partnerContractDTO.setId(entity.getId());
		partnerContractDTO.setDateSigning(entity.getDateSigning());
		partnerContractDTO.setContractTerm(entity.getContractTerm());
		partnerContractDTO.setContractValue(entity.getContractValue());
		partnerContractDTO.setCreated(entity.getCreated());
		partnerContractDTO.setUpdated(entity.getUpdated());
		final IClub club = entity.getClub();
		if (club != null) {
			partnerContractDTO.setClubId(club.getId());
			partnerContractDTO.setClubName(club.getName());
		}
		final IPartner partner = entity.getPartner();
		if (partner != null) {
			partnerContractDTO.setPartnerId(partner.getId());
			partnerContractDTO.setPartnerName(partner.getName());
		}
		return partnerContractDTO;
	}

}
