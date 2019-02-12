package com.itacademy.jd2.vn.sst.dao.api.filter;

public class PartnerContractFilter extends AbstractFilter{
	
	private Integer clubId;
	private boolean fetchClub ;
	private boolean fetchPartner;
	
	public final Integer getClubId() {
		return clubId;
	}
	public final void setClubId(Integer clubId) {
		this.clubId = clubId;
	}
	public final boolean getFetchClub() {
		return fetchClub;
	}
	public final void setFetchClub(boolean fetchClub) {
		this.fetchClub = fetchClub;
	}
	public final boolean getFetchPartner() {
		return fetchPartner;
	}
	public final void setFetchPartner(boolean fetchPartner) {
		this.fetchPartner = fetchPartner;
	}

}
