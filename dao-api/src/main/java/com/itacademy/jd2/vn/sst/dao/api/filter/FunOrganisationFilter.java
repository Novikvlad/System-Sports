package com.itacademy.jd2.vn.sst.dao.api.filter;


public class FunOrganisationFilter extends AbstractFilter {
	private boolean fetchClub ;
	private boolean fetchCity;

	public boolean getFetchClub() {
		return fetchClub;
	}

	public void setFetchClub(final boolean fetchClub) {
		this.fetchClub = fetchClub;
	}

	public boolean getFetchCity() {
		return fetchCity;
	}

	public void setFetchCity(final boolean fetchCity) {
		this.fetchCity = fetchCity;
	}
	
}
