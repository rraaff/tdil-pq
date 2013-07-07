package com.tdil.lojack.gis.model;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class JobStatus {
	
	private int jobId = 0;
	private String jobStatus;
	
	public int getJobId() {
		return jobId;
	}
	public void setJobId(int jobId) {
		this.jobId = jobId;
	}
	public String getJobStatus() {
		return jobStatus;
	}
	public void setJobStatus(String jobStatus) {
		this.jobStatus = jobStatus;
	}
}
