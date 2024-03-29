package com.tdil.thalamus.client.test;

import java.util.HashSet;
import java.util.Set;

import junit.framework.TestCase;
import net.sf.json.JSON;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import com.tdil.thalamus.client.facade.ThalamusClientFacade;

public class TestLogin extends TestCase {

	public void testLoginSuccess() throws Exception {
		JSON result = ThalamusClientFacade.login("user1@mail.com", "1234");
		assertTrue(result instanceof JSONObject);
		JSONObject json = (JSONObject)result;
		JSONArray links = json.getJSONArray("links");
		boolean foundhome = false;
		for (Object obj : links) {
			JSONObject jsonObj = (JSONObject)obj;
			if (jsonObj.getString("ref").equals("home")) {
				foundhome = true;
			}
		}
		assertTrue(foundhome);
		JSONObject errors = json.getJSONObject("errors");
		assertTrue(errors.isEmpty());
		System.out.println(json);
		
		Set<Integer> appliedActivities = new HashSet<Integer>();
		JSONObject data = json.getJSONObject("data");
		JSONArray activities= data.getJSONArray("activities");
		for (int i = 0; i < activities.size(); i++) {
			JSONObject act = activities.getJSONObject(i);
			appliedActivities.add(act.getInt("id"));
		}
		System.out.println(appliedActivities);
	}
}
