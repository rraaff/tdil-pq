package com.tdil.thalamus.client.test;

import java.util.HashSet;
import java.util.Set;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import com.tdil.thalamus.client.facade.ThalamusClientFacade;
import com.tdil.thalamus.client.facade.json.beans.LoginResult;
import com.tdil.thalamus.client.facade.json.fields.LoginParameters;

public class TestActivities extends ThalamusTestCase {

	public void testUser1Activities() throws Exception {
		
		JSONObject jsonObject = new JSONObject();
		jsonObject.put(LoginParameters.principal, "user1@mail.com");
		jsonObject.put(LoginParameters.password, "1234");
		
		LoginResult result = ThalamusClientFacade.login(jsonObject);
		assertTrue(result.getResult() instanceof JSONObject);
		JSONObject json = (JSONObject)result.getResult();
		JSONArray links = json.getJSONObject("context").getJSONArray("links");
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
		
		Set<String> appliedActivities = new HashSet<String>();
		JSONArray activities= json.getJSONObject("context").getJSONArray("activities");
		for (int i = 0; i < activities.size(); i++) {
			JSONObject act = activities.getJSONObject(i);
			appliedActivities.add(act.getString("code"));
		}
		System.out.println("User 1 activities:" + appliedActivities);
	}
	
	public void testUser2Activities() throws Exception {
		
		JSONObject jsonObject = new JSONObject();
		jsonObject.put(LoginParameters.principal, "user2@mail.com");
		jsonObject.put(LoginParameters.password, "1234");
		
		LoginResult result = ThalamusClientFacade.login(jsonObject);
		assertTrue(result.getResult() instanceof JSONObject);
		JSONObject json = (JSONObject)result.getResult();
		JSONArray links = json.getJSONObject("context").getJSONArray("links");
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
		
		Set<String> appliedActivities = new HashSet<String>();
		JSONArray activities= json.getJSONObject("context").getJSONArray("activities");
		for (int i = 0; i < activities.size(); i++) {
			JSONObject act = activities.getJSONObject(i);
			appliedActivities.add(act.getString("code"));
		}
		System.out.println("User 2 activities:" + appliedActivities);
	}
	
	public void testUser3Activities() throws Exception {
		
		JSONObject jsonObject = new JSONObject();
		jsonObject.put(LoginParameters.principal, "user3@mail.com");
		jsonObject.put(LoginParameters.password, "1234");
		
		LoginResult result = ThalamusClientFacade.login(jsonObject);
		assertTrue(result.getResult() instanceof JSONObject);
		JSONObject json = (JSONObject)result.getResult();
		JSONArray links = json.getJSONObject("context").getJSONArray("links");
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
		
		Set<String> appliedActivities = new HashSet<String>();
		JSONArray activities= json.getJSONObject("context").getJSONArray("activities");
		for (int i = 0; i < activities.size(); i++) {
			JSONObject act = activities.getJSONObject(i);
			appliedActivities.add(act.getString("code"));
		}
		System.out.println("User 3 activities:" + appliedActivities);
	}
}
