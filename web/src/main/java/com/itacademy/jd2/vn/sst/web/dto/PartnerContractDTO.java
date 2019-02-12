package com.itacademy.jd2.vn.sst.web.dto;

import java.math.BigDecimal;
import java.util.Date;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.springframework.format.annotation.DateTimeFormat;

public class PartnerContractDTO {

	private Integer id;
	@Size(min = 1, max = 50)
	private String name;
	@NotNull
	private Integer clubId;
	private String clubName;
	@NotNull
	private Integer partnerId;
	private String partnerName;
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date dateSigning;
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date contractTerm;
	private BigDecimal contractValue;
	private Date created;
	private Date updated;
	public final Integer getId() {
		return id;
	}
	public final void setId(Integer id) {
		this.id = id;
	}
	public final String getName() {
		return name;
	}
	public final void setName(String name) {
		this.name = name;
	}
	public final Integer getClubId() {
		return clubId;
	}
	public final void setClubId(Integer clubId) {
		this.clubId = clubId;
	}
	public final String getClubName() {
		return clubName;
	}
	public final void setClubName(String clubName) {
		this.clubName = clubName;
	}
	public final Integer getPartnerId() {
		return partnerId;
	}
	public final void setPartnerId(Integer partnerId) {
		this.partnerId = partnerId;
	}
	public final String getPartnerName() {
		return partnerName;
	}
	public final void setPartnerName(String partnerName) {
		this.partnerName = partnerName;
	}
	public final Date getDateSigning() {
		return dateSigning;
	}
	public final void setDateSigning(Date dateSigning) {
		this.dateSigning = dateSigning;
	}
	
	public final Date getContractTerm() {
		return contractTerm;
	}
	public final void setContractTerm(Date date) {
		this.contractTerm = date;
	}
	public final BigDecimal getContractValue() {
		return contractValue;
	}
	public final void setContractValue(BigDecimal contractValue) {
		this.contractValue = contractValue;
	}
	public final Date getCreated() {
		return created;
	}
	public final void setCreated(Date created) {
		this.created = created;
	}
	public final Date getUpdated() {
		return updated;
	}
	public final void setUpdated(Date updated) {
		this.updated = updated;
	}

}
