package com.itacademy.jd2.vn.sst.dao.api.filter;

public class ClubFilter extends AbstractFilter{
    private boolean fetchStadium;
    private boolean fetchPartnerContract;
    public boolean getFetchStadium() {
        return fetchStadium;
    }

    public void setFetchStadium(final boolean fetchStadium) {
        this.fetchStadium = fetchStadium;
    }

	public final boolean isFetchPartnerContract() {
		return fetchPartnerContract;
	}

	public final void setFetchPartnerContract(boolean fetchPartnerContract) {
		this.fetchPartnerContract = fetchPartnerContract;
	}

}
