package com.tdil.thalamus.client.test;

import junit.framework.TestCase;
import net.sf.json.JSON;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import com.tdil.thalamus.client.facade.RegistrationParameters;
import com.tdil.thalamus.client.facade.ThalamusClientFacade;

public class TestRegister extends TestCase {

	public void testRegisterSuccess() throws Exception {
		String email = "Marcos" + System.currentTimeMillis() + "@gmail.com";
		JSONObject person = new JSONObject();
		person.put(RegistrationParameters.firstName, "Marcos");
		person.put(RegistrationParameters.lastName, "Marcos");
		person.put(RegistrationParameters.email, email);
		person.put(RegistrationParameters.birthDate, 714070884661l);
		person.put(RegistrationParameters.street, "Marcos");
		person.put(RegistrationParameters.city, "Marcos");
		person.put(RegistrationParameters.stateId, 1);
		person.put(RegistrationParameters.countryId, 8);
		person.put(RegistrationParameters.postalCode, "1900");
		person.put(RegistrationParameters.addressType, "home");
		person.put(RegistrationParameters.phoneNumber, "+54 0221 4513521");
		person.put(RegistrationParameters.phoneNumberType, "cellphone");
		person.put(RegistrationParameters.password, "1234");
		JSON result1 = ThalamusClientFacade.register(person);
		
		JSON result = ThalamusClientFacade.login(email, "1234");
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
	}
}
