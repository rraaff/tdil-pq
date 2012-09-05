package com.tdil.thalamus.client.core;

public interface ThalamusServices {

	public String GET_COUNTRIES = "/thalamus/api/referencedata/countries";
	public String GET_BRANDS = "/thalamus/api/referencedata/brands";

	public String LOGIN = "/thalamus/api/thalamus_security_check";
	public String LOGOUT = "/thalamus/api/thalamus_security_logout";
	
	public String REGISTER_PERSON = "/thalamus/api/people/register/profile";
	
	public String GET_PERSON_PROFILE = "/thalamus/api/people/profile";

	// TODO
	public String GET_PERSON_CONSUMER = "/thalamus/api/people/consumer";
	
	public String REGISTER_PERSON_CONSUMER = "/thalamus/api/people/register/profileconsumer";
	public String UPDATE_PROFILE = "/thalamus/api/people/register/profile";
	
	public String LOGIN_ACTIVITY_START = "/thalamus/api/activities/";
	public String LOGIN_ACTIVITY_END = "/login";
}
