package com.itacademy.jd2.vn.sst.dao.api.filter;

public class EventFilter extends AbstractFilter {
	private String name;
	private boolean fetchStadium;

	public final String getName() {
		return name;
	}

	public final void setName(String name) {
		this.name = name;
	}

	public boolean getFetchStadium() {
		return fetchStadium;
	}

	public void setFetchStadium(final boolean fetchStadium) {
		this.fetchStadium = fetchStadium;
	}

}
