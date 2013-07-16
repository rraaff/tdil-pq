package com.tdil.lojack.gis.model;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class JobStatus {
	
	private int jobId = 0;
	private int jobStatus;
	
	public int getJobId() {
		return jobId;
	}
	public void setJobId(int jobId) {
		this.jobId = jobId;
	}
	public int getJobStatus() {
		return jobStatus;
	}
	public void setJobStatus(int jobStatus) {
		this.jobStatus = jobStatus;
	}
}
