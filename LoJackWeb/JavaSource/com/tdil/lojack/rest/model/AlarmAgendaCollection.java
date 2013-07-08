package com.tdil.lojack.rest.model;

import java.util.Collection;

import javax.xml.bind.annotation.XmlRootElement;

import com.tdil.lojack.gis.model.AlarmAgenda;

@XmlRootElement
public class AlarmAgendaCollection {
	
	private Collection<AlarmAgenda> alarmAgendas;
	
	public AlarmAgendaCollection() {
	}
	
	public AlarmAgendaCollection(Collection<AlarmAgenda> alarmAgendas) {
		super();
		this.alarmAgendas = alarmAgendas;
	}

	public Collection<AlarmAgenda> getAlarmAgendas() {
		return alarmAgendas;
	}

	public void setAlarmAgendas(Collection<AlarmAgenda> alarmAgendas) {
		this.alarmAgendas = alarmAgendas;
	}




}
