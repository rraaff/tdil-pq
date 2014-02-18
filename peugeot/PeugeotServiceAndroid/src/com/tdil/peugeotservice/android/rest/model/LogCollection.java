package com.tdil.peugeotservice.android.rest.model;

import java.util.Collection;

public class LogCollection {
	
	private Collection<ChangeLog> logs;
	
	public Collection<ChangeLog> getLogs() {
		return logs;
	}

	public void setLogs(Collection<ChangeLog> logs) {
		this.logs = logs;
	}

	public LogCollection() {
	}
	
	public LogCollection(Collection<ChangeLog> logs) {
		super();
		this.logs = logs;
	}





}
