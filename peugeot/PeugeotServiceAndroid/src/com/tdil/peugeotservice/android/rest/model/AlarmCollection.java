package com.tdil.peugeotservice.android.rest.model;

import java.util.Collection;

public class AlarmCollection {
	
	private Collection<Alarm> alarms;
	
	public AlarmCollection() {
	}
	
	public AlarmCollection(Collection<Alarm> alarms) {
		super();
		this.alarms = alarms;
	}



	public Collection<Alarm> getAlarms() {
		return alarms;
	}

	public void setAlarms(Collection<Alarm> alarms) {
		this.alarms = alarms;
	}

}
