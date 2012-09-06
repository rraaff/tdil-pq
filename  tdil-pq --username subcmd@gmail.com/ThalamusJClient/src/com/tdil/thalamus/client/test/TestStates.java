package com.tdil.thalamus.client.test;

import junit.framework.TestCase;
import net.sf.json.JSON;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import com.tdil.thalamus.client.facade.ThalamusClientFacade;

public class TestStates extends TestCase {

	public void testGetStates() throws Exception {
		JSON result = ThalamusClientFacade.getCountries();
		// TODO assert sobre el result
		assertTrue(result instanceof JSONArray);
		JSONArray arr = (JSONArray)result;
		int id = 0;
		boolean found = false;
		for (Object o : arr) {
			JSONObject obj = (JSONObject)o;
			if (obj.get("name").equals("Argentina")) {
				id = obj.getInt("id");
				found = true;
			}
		}
		assertTrue("Se deberia haber encontrado a Argentina", found);
		
		JSON resultStates = ThalamusClientFacade.getStates(id);
		assertTrue(resultStates instanceof JSONArray);
		JSONArray states = (JSONArray)resultStates;
		
		boolean foundCaba = false;
		for (Object o : states) {
			JSONObject obj = (JSONObject)o;
			if (obj.get("code").equals("CABA")) {
				foundCaba = true;
			}
		}
		assertTrue("Se deberia haber encontrado a CABA", foundCaba);
	}
}
