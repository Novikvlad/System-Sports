package com.itacademy.jd2.vn.sst.dao.api.filter;

import com.itacademy.jd2.vn.sst.dao.api.entity.enums.PresenceType;

public class TicketFilter extends AbstractFilter {

	private boolean fetchEvent;
	 private PresenceType presence;
	public final boolean getFetchEvent() {
		return fetchEvent;
	}

	public final void setFetchEvent(boolean fetchEvent) {
		this.fetchEvent = fetchEvent;
	}

	public final PresenceType getPresence() {
		return presence;
	}

	public final void setPresence(PresenceType presence) {
		this.presence = presence;
	}

}
