package com.tdil.thalamus.android.rest.model;

import java.io.Serializable;
import java.util.Collection;

public class AlarmAgendaCollection implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -7104312829558442550L;
	private Collection<AlarmAgenda> list;
	
	public AlarmAgendaCollection() {
	}
	
	public AlarmAgendaCollection(Collection<AlarmAgenda> alarms) {
		super();
		this.list = alarms;
	}

	public Collection<AlarmAgenda> getList() {
		return list;
	}

	public void setList(Collection<AlarmAgenda> alarms) {
		this.list = alarms;
	}

}
