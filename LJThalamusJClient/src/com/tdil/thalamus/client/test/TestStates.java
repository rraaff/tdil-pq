package com.tdil.thalamus.client.test;

import java.util.Collection;

import net.sf.json.JSON;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import com.tdil.thalamus.client.facade.ThalamusClientBeanFacade;
import com.tdil.thalamus.client.facade.ThalamusClientFacade;
import com.tdil.thalamus.client.facade.json.beans.StateBean;

public class TestStates extends ThalamusTestCase {

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
			if (obj.get("name").equals("Buenos Aires")) {
				foundCaba = true;
			}
		}
		assertTrue("Se deberia haber encontrado a Buenos Aires", foundCaba);
		
		Collection<StateBean> list = ThalamusClientBeanFacade.getStates(id);
		System.out.println(list);
		boolean foundCabaBean = false;
		for (StateBean o : list) {
			if (o.getName().equals("Buenos Aires")) {
				foundCabaBean = true;
			}
		}
		assertTrue("Se deberia haber encontrado a Buenos Aires", foundCabaBean);
	}
}
