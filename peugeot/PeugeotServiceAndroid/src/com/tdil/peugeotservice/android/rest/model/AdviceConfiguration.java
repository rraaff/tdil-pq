package com.tdil.peugeotservice.android.rest.model;

public class AdviceConfiguration {

	private String email;
	private boolean receiveEmail;
	private boolean dealerEmail;
	
	public AdviceConfiguration(String email) {
		super();
		this.email = email;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public boolean getReceiveEmail() {
		return receiveEmail;
	}

	public void setReceiveEmail(boolean receiveEmail) {
		this.receiveEmail = receiveEmail;
	}

	public boolean getDealerEmail() {
		return dealerEmail;
	}

	public void setDealerEmail(boolean dealerEmail) {
		this.dealerEmail = dealerEmail;
	}
}
