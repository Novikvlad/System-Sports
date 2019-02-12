package com.itacademy.jd2.vn.sst.dao.api.filter;

public class CountryFilter extends AbstractFilter {
	private boolean fetchRegion;

	public final boolean isFetchRegion() {
		return fetchRegion;
	}

	public final void setFetchRegion(boolean fetchRegion) {
		this.fetchRegion = fetchRegion;
	}

}
