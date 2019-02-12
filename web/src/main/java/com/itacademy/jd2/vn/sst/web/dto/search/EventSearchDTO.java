package com.itacademy.jd2.vn.sst.web.dto.search;

public class EventSearchDTO {

	private String name;
	private String stadium;
	private boolean freeOnly;

	public final String getName() {
		return name;
	}

	public final void setName(String name) {
		this.name = name;
	}

	public final String getStadium() {
		return stadium;
	}

	public final void setStadium(String stadium) {
		this.stadium = stadium;
	}

	public final boolean isFreeOnly() {
		return freeOnly;
	}

	public final void setFreeOnly(boolean freeOnly) {
		this.freeOnly = freeOnly;
	}

}
