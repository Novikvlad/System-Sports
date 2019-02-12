package com.itacademy.jd2.vn.sst.dao.api.filter;

public class StadiumFilter extends AbstractFilter {
	private boolean fetchCity;
	private boolean fetchTicket;

	public boolean getFetchCity() {
		return fetchCity;
	}

	public void setFetchCity(final boolean fetchCity) {
		this.fetchCity = fetchCity;
	}

	public final boolean isFetchTicket() {
		return fetchTicket;
	}

	public final void setFetchTicket(boolean fetchTicket) {
		this.fetchTicket = fetchTicket;
	}

}
