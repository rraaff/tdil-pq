package com.tdil.lojack.rest.model;

import java.util.Collection;

import javax.xml.bind.annotation.XmlRootElement;

import com.tdil.lojack.gis.model.ChangeLog;

@XmlRootElement
public class LogCollection {
	
	private Collection<ChangeLog> logs;
	
	public LogCollection() {
	}
	
	public LogCollection(Collection<ChangeLog> logs) {
		super();
		this.logs = logs;
	}

	public Collection<ChangeLog> getLogs() {
		return logs;
	}

	public void setLogs(Collection<ChangeLog> logs) {
		this.logs = logs;
	}




}
