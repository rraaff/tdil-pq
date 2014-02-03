package com.tdil.ljpeugeot.rest.model;

import javax.xml.bind.annotation.XmlRootElement;

import com.tdil.ljpeugeot.model.Dealer;

@XmlRootElement
public class DealerBean {

	private int id;
	private String name;
	private String address;
	private String email;
	private String phone;
	
	public DealerBean(Dealer dealer) {
		this.id = dealer.getId();
		this.name = dealer.getName();
		this.address = dealer.getAddress();
		this.email= dealer.getEmail();
		this.phone = dealer.getPhone();
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
}
