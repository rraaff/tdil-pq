package com.tdil.thalamus.android.rest.model;

import java.util.Collection;

public class LightJobStatusCollection {
	
	private Collection<LightJobStatus> status;
	
	public LightJobStatusCollection() {
	}
	
	public LightJobStatusCollection(Collection<LightJobStatus> alarms) {
		super();
		this.status = alarms;
	}

	public Collection<LightJobStatus> getStatus() {
		return status;
	}

	public void setStatus(Collection<LightJobStatus> alarms) {
		this.status = alarms;
	}

}
