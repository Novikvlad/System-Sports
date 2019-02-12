package com.itacademy.jd2.vn.sst.dao.api.filter;

public class UserAccountFilter extends AbstractFilter {

	private boolean fetchCountry;
	private boolean fetchFunOrganisation;

	public final boolean getFetchCountry() {
		return fetchCountry;
	}

	public final void setFetchCountry(boolean fetchCountry) {
		this.fetchCountry = fetchCountry;
	}

	public final boolean getFetchFunOrganisation() {
		return fetchFunOrganisation;
	}

	public final void setFetchFunOrganisation(boolean fetchFunOrganisation) {
		this.fetchFunOrganisation = fetchFunOrganisation;
	}

}
