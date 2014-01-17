package com.tdil.thalamus.client.test;

import java.util.HashSet;
import java.util.Set;

import net.sf.json.JSON;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import com.tdil.thalamus.client.facade.ThalamusClientFacade;
import com.tdil.thalamus.client.facade.json.beans.LoginResult;
import com.tdil.thalamus.client.facade.json.fields.LoginParameters;

public class TestLogin extends ThalamusTestCase {

	public void testLoginSuccess() throws Exception {
		
//		JSON person = ThalamusClientFacade.getPerson("user1@mail.com", "4321");
//		System.out.println(person.toString(2));
		
		JSONObject jsonObject = new JSONObject();
		jsonObject.put(LoginParameters.principal, "subcmd@gmail.com");
		jsonObject.put(LoginParameters.password, "123456");
		
		LoginResult result = ThalamusClientFacade.login(jsonObject);
		// TODO client.getState().getCookies()
		
		assertTrue(result.getResult() instanceof JSONObject);
		JSONObject json = (JSONObject)result.getResult();
		System.out.println(json.toString(2));
		
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
		System.out.println(appliedActivities);
		
		JSON person = ThalamusClientFacade.getPerson(result.getTokenHolder());
		System.out.println(person.toString(2));
		
		JSON social = ThalamusClientFacade.getSocial(result.getTokenHolder());
		System.out.println(social.toString(2));
	}
}
