package com.itacademy.jd2.vn.sst.dao.api.filter;

public class CityFilter extends AbstractFilter {
	private boolean fetchCountry;

	public boolean getFetchCountry() {
		return fetchCountry;
	}

	public void setFetchCountry(final boolean fetchCountry) {
		this.fetchCountry = fetchCountry;
	}
}
