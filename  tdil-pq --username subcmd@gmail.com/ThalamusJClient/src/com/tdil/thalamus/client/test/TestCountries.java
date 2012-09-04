package com.tdil.thalamus.client.test;

import junit.framework.TestCase;
import net.sf.json.JSON;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import com.tdil.thalamus.client.facade.ThalamusClientFacade;

public class TestCountries extends TestCase {

	public void testGetCountries() throws Exception {
		JSON result = ThalamusClientFacade.getCountries("user1@mail.com", "1234");
		// TODO assert sobre el result
		assertTrue(result instanceof JSONArray);
		JSONArray arr = (JSONArray)result;
		boolean found = false;
		for (Object o : arr) {
			JSONObject obj = (JSONObject)o;
			if (obj.get("name").equals("Argentina")) {
				found = true;
			}
		}
		assertTrue("Se deberia haber encontrado a Argentina", found);
	}
}
