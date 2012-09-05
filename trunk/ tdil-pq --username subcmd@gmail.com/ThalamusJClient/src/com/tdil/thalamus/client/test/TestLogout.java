package com.tdil.thalamus.client.test;

import junit.framework.TestCase;
import net.sf.json.JSONObject;

import com.tdil.thalamus.client.core.UnauthorizedException;
import com.tdil.thalamus.client.facade.ThalamusClientFacade;

public class TestLogout extends TestCase {

	public void testLogoutSuccess() throws Exception {
		try {
			ThalamusClientFacade.logout("user1@mail.com", "1234");
			fail("Deberia haber dado error de autorizacion");
		} catch (UnauthorizedException ex) {
			System.out.println(ex.getResult());
			assertTrue(ex.getResult() instanceof JSONObject);
			JSONObject json = (JSONObject)ex.getResult();
			JSONObject errors = (JSONObject)json.get("errors");
			assertNotNull(errors.getString("unathorized"));
		}
	}
}
