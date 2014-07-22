package com.tdil.peugeotservice.android.rest.model;

public class VehiclePhoneBean {
	
	private String id;
	private String domain;
	private String phone;
	
	public VehiclePhoneBean() {
		// TODO Auto-generated constructor stub
	}
	
	public VehiclePhoneBean(String id, String domain, String phone) {
		super();
		this.id = id;
		this.domain = domain;
		this.phone = phone;
	}
	
	public String getDomain() {
		return domain;
	}
	public void setDomain(String domain) {
		this.domain = domain;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

}
