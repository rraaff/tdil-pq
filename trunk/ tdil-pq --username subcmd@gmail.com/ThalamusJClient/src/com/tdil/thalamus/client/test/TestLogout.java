package com.tdil.thalamus.client.test;

import junit.framework.TestCase;
import net.sf.json.JSON;
import net.sf.json.JSONObject;

import com.tdil.thalamus.client.facade.ThalamusClientFacade;

public class TestLogout extends TestCase {

	public void testLogoutSuccess() throws Exception {
		JSON result = ThalamusClientFacade.logout("user1@mail.com", "1234");
		System.out.println(result);
		assertTrue(result instanceof JSONObject);
		JSONObject json = (JSONObject)result;
		JSONObject errors = (JSONObject)json.get("errors");
		assertNotNull(errors.getString("unathorized"));
	}
}
