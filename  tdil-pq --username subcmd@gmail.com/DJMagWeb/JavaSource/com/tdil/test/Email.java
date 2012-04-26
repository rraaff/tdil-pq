package com.tdil.test;

import javax.activation.MimetypesFileTypeMap;

public class Email {

	private String name;
	private String address;
	
	public Email(String name, String address) {
		super();
		this.name = name;
		this.address = address;
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
	
	public static void main(String[] args) {
		System.out.println(new MimetypesFileTypeMap().getContentType("asasa.jpg"));
	}
}
