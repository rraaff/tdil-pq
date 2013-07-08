package com.tdil.lojack.rest.model;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class AsyncJobResponse {

	private boolean accepted;
	
	public static final AsyncJobResponse OK_RESPONSE = new AsyncJobResponse(true);
	public static final AsyncJobResponse FAIL_RESPONSE = new AsyncJobResponse(false);
	
	public AsyncJobResponse() {
	}

	public AsyncJobResponse(boolean accepted) {
		super();
		this.accepted = accepted;
	}

	public boolean isAccepted() {
		return accepted;
	}

	public void setAccepted(boolean accepted) {
		this.accepted = accepted;
	}
}
