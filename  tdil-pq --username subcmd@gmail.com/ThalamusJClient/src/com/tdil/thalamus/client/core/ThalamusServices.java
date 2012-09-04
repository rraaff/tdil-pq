package com.tdil.thalamus.client.core;

public interface ThalamusServices {

	public String GET_COUNTRIES = "/thalamus/api/countries";

	public String LOGIN = "/thalamus/api/thalamus_security_check";
	public String LOGOUT = "/thalamus/api/j_spring_security_logout";
	
	public String REGISTER_PERSON = "/thalamus/api/people/register/profile";
}
