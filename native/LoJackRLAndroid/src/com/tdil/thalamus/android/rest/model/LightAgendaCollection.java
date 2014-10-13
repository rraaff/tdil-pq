package com.tdil.thalamus.android.rest.model;

import java.util.Collection;

public class LightAgendaCollection {
	
	private Collection<LightAgenda> list;
	
	public LightAgendaCollection() {
	}
	
	public LightAgendaCollection(Collection<LightAgenda> alarms) {
		super();
		this.list = alarms;
	}

	public Collection<LightAgenda> getList() {
		return list;
	}

	public void setList(Collection<LightAgenda> alarms) {
		this.list = alarms;
	}

}
