package com.tdil.thalamus.client.core;

public interface ThalamusServices {

	public String GET_IS_ALIVE = "/api/v1/isAlive";
	
	public String GET_COUNTRIES = "/api/v1/referencedata/countries";
	public String GET_BRANDS = "/api/v1/referencedata/brands";
	public String GET_CHANNELS = "/api/v1/referencedata/channels";
	public String GET_BRANDSFAMILIES = "/api/v1/referencedata/brandsfamilies";
	public String GET_DOCUMENTTYPES = "/api/v1/referencedata/documenttypes";
	
	public String GET_STATES_START = "/api/v1/referencedata/countries/";
	public String GET_STATES_END = "/states";
	

	public String LOGIN = "/api/v1/thalamus_security_check";
	public String LOGIN_V2 = "/api/v1/signin";
	public String LOGOUT = "/api/v1/logout";
	public String REQUEST_RESET_PASSWORD = "/api/v1/person/password/requestreset";
	public String RESET_PASSWORD = "/api/v1/person/password";
	public String CHANGE_PASSWORD = "/api/v1/person/passwordchange";
	
	public String VALIDATE_PASSWORD = "/api/v1/person/passwordvalid";
	
	// POST
	public String SIGN_IN_FACEBOOK = "/api/v1/signin/facebook";
	public String SIGN_IN_TWITTER = "/api/v1/signin/twitter";
	
	// POST
	public String CONNECT_FACEBOOK = "/api/v1/connect/facebook";
	public String CONNECT_TWITTER = "/api/v1/connect/twitter";
	
	// POST
	public String GET_SOCIAL_CONNECT = "/api/v1/connect";
	public String GET_SOCIAL_CONNECT_FACEBOOK = "/api/v1/connect/facebook";
	public String GET_SOCIAL_CONNECT_TWITTER = "/api/v1/connect/twitter";
	
	
	public String GET_PERSON_FIELDS = "/api/v1/person/fields";
	public String GET_PERSON = "/api/v1/person";
	public String REGISTER_PERSON = GET_PERSON;
	public String UPDATE_PERSON = GET_PERSON;

	public String REGISTER_PERSON_CONSUMER = "/api/v1/person";
	public String UPDATE_PROFILE = "/api/v1/people/register/profile";
	
	public String LOGIN_ACTIVITY_START = "/api/v1/activities/";
	public String LOGIN_ACTIVITY_END = "/login";

}
