package com.tdil.thalamus.client.test;

import net.sf.json.JSON;
import net.sf.json.JSONObject;

import com.tdil.thalamus.client.core.ThalamusClient;
import com.tdil.thalamus.client.facade.ThalamusClientFacade;
import com.tdil.thalamus.client.facade.json.beans.LoginResult;
import com.tdil.thalamus.client.facade.json.fields.LoginParameters;

public class TestSocialAdd extends ThalamusTestCase {

	public void testAddFacebok() throws Exception {
		
		ThalamusClient.setTHALAMUS_TOUCHPOINT_CODE("test");
		ThalamusClient.setTHALAMUS_TOUCHPOINT_TOKEN("testtesttesttesttesttesttesttesttesttesttesttesttesttesttesttest");
		
		JSONObject jsonObject = new JSONObject();
		jsonObject.put(LoginParameters.principal, "marcecioma1@hotmail.com");
		jsonObject.put(LoginParameters.password, "123456");
		
		LoginResult result = ThalamusClientFacade.login(jsonObject);
		
		JSON addfb = ThalamusClientFacade.addFacebook(result.getTokenHolder());
		System.out.println(addfb.toString(2));
	}
	
}
