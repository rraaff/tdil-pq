package com.tdil.lojack.rest.model;

import java.util.Collection;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
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
