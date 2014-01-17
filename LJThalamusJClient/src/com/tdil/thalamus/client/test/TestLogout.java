package com.tdil.thalamus.client.test;

import net.sf.json.JSON;
import net.sf.json.JSONObject;

import com.tdil.thalamus.client.core.HttpStatusException;
import com.tdil.thalamus.client.core.UnauthorizedException;
import com.tdil.thalamus.client.facade.ThalamusClientFacade;

public class TestLogout extends ThalamusTestCase {

	public void testLogoutSuccess() throws Exception {
		try {
			JSON logoutresp = ThalamusClientFacade.logout();
			System.out.println(logoutresp.toString(2));
			//fail("Deberia haber dado error de autorizacion");
		} catch (HttpStatusException e) {
			assertEquals(401, e.getStatus());
		} catch (UnauthorizedException ex) {
			System.out.println(ex.getResult());
			assertTrue(ex.getResult() instanceof JSONObject);
			JSONObject json = (JSONObject)ex.getResult();
			JSONObject errors = (JSONObject)json.get("errors");
			assertNotNull(errors.getString("unathorized"));
		}
	}
}
