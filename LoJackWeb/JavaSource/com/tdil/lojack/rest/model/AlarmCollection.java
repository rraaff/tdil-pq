package com.tdil.lojack.rest.model;

import java.util.Collection;

import javax.xml.bind.annotation.XmlRootElement;

import com.tdil.lojack.gis.model.Alarm;

@XmlRootElement
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
