package com.tdil.peugeotservice.android.rest.model;

import java.util.ArrayList;
import java.util.List;

public class ContactDataV1Bean {

	private String contact1name;
	private String contact1relation;
	private String contact1phone;
	private List<VehiclePhoneBean> vehiclesPhones = new ArrayList<VehiclePhoneBean>();
	private String contact1secword;
	private String contact1healthi;
	private String contact2name;
	private String contact2relation;
	private String contact2phone;
	private String contact3name;
	private String contact3relation;
	private String contact3phone;
	
	public ContactDataV1Bean() {
	}
	
	public String getContact1name() {
		return contact1name;
	}
	public void setContact1name(String contact1name) {
		this.contact1name = contact1name;
	}
	public String getContact1relation() {
		return contact1relation;
	}
	public void setContact1relation(String contact1relation) {
		this.contact1relation = contact1relation;
	}
	public String getContact1phone() {
		return contact1phone;
	}
	public void setContact1phone(String contact1phone) {
		this.contact1phone = contact1phone;
	}
	public String getContact1secword() {
		return contact1secword;
	}
	public void setContact1secword(String contact1secword) {
		this.contact1secword = contact1secword;
	}
	public String getContact1healthi() {
		return contact1healthi;
	}
	public void setContact1healthi(String contact1healthi) {
		this.contact1healthi = contact1healthi;
	}
	public String getContact2name() {
		return contact2name;
	}
	public void setContact2name(String contact2name) {
		this.contact2name = contact2name;
	}
	public String getContact2relation() {
		return contact2relation;
	}
	public void setContact2relation(String contact2relation) {
		this.contact2relation = contact2relation;
	}
	public String getContact2phone() {
		return contact2phone;
	}
	public void setContact2phone(String contact2phone) {
		this.contact2phone = contact2phone;
	}
	public String getContact3name() {
		return contact3name;
	}
	public void setContact3name(String contact3name) {
		this.contact3name = contact3name;
	}
	public String getContact3relation() {
		return contact3relation;
	}
	public void setContact3relation(String contact3relation) {
		this.contact3relation = contact3relation;
	}
	public String getContact3phone() {
		return contact3phone;
	}
	public void setContact3phone(String contact3phone) {
		this.contact3phone = contact3phone;
	}

	public List<VehiclePhoneBean> getVehiclesPhones() {
		return vehiclesPhones;
	}
	public void setVehiclesPhones(List<VehiclePhoneBean> vehiclesPhones) {
		this.vehiclesPhones = vehiclesPhones;
	}
	
}