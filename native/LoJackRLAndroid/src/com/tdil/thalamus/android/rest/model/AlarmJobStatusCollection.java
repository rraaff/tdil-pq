package com.tdil.thalamus.android.rest.model;

import java.util.Collection;

public class AlarmJobStatusCollection {
	
	private Collection<AlarmJobStatus> status;
	
	public AlarmJobStatusCollection() {
	}
	
	public AlarmJobStatusCollection(Collection<AlarmJobStatus> alarms) {
		super();
		this.status = alarms;
	}

	public Collection<AlarmJobStatus> getStatus() {
		return status;
	}

	public void setStatus(Collection<AlarmJobStatus> alarms) {
		this.status = alarms;
	}

}
