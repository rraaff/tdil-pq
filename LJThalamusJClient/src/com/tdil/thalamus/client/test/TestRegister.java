package com.tdil.thalamus.client.test;

import net.sf.json.JSON;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import com.tdil.thalamus.client.facade.ThalamusClientFacade;
import com.tdil.thalamus.client.facade.json.beans.LoginResult;
import com.tdil.thalamus.client.facade.json.fields.ConsumerParameters;
import com.tdil.thalamus.client.facade.json.fields.CredentialParameters;
import com.tdil.thalamus.client.facade.json.fields.LoginParameters;
import com.tdil.thalamus.client.facade.json.fields.OptInsParameters;
import com.tdil.thalamus.client.facade.json.fields.PersonFieldNames;

public class TestRegister extends ThalamusTestCase {

	public void t$estRegisterSuccess() throws Exception {
		String email = "Marcos" + System.nanoTime() + "@gmail.com";
		JSONObject registration = new JSONObject();
		
		JSONObject consumer = new JSONObject();
		consumer.put(ConsumerParameters.activeConsumer, true);
		consumer.put(ConsumerParameters.preferedBrandId, 1);
		consumer.put(ConsumerParameters.alternativeBrandId, 2);
		consumer.put(ConsumerParameters.consumptionFrequency, 2);
		registration.put("consumer", consumer);
		
		// TODO que es esto preferedProductId y alternativeProductdId
		JSONObject person = new JSONObject();
		person.put(PersonFieldNames.firstName, "Marcos");
		person.put(PersonFieldNames.lastName, "Godoy");
		person.put(PersonFieldNames.email, email);
		person.put(PersonFieldNames.birthDate, 714070884661L);
		person.put(PersonFieldNames.street1, "Robles");
		person.put(PersonFieldNames.city, "Dolores");
		person.put(PersonFieldNames.stateId, 1);
		person.put(PersonFieldNames.countryId, 8);
		person.put(PersonFieldNames.postalCode, "7100");
		person.put(PersonFieldNames.addressType, "home");
		person.put(PersonFieldNames.phoneNumber, "+54 0221 4513521");
		person.put(PersonFieldNames.phoneType, "cellphone");
		registration.put("profile", person);
		
		JSONArray optInsArray = new JSONArray();
		JSONObject optIns = new JSONObject();
		optIns.put(OptInsParameters.brandFamilyId, 4);
		optIns.put(OptInsParameters.channel, 6001);
		optIns.put(OptInsParameters.accepted, true);
		optInsArray.add(optIns);
		registration.put("optIns", optInsArray);
		
		JSONObject credentials = new JSONObject();
		credentials.put(CredentialParameters.principal, email);
		credentials.put(CredentialParameters.password, "1234");
		registration.put("credential", credentials);
		
		System.out.println(registration.toString(2));
		JSON result1 = ThalamusClientFacade.registerPerson(registration);
		System.out.println(result1.toString(2));
	}
	
	public void testRegisterConsumerSuccessAndUpdate() throws Exception {
		String email = "Marcos" + System.nanoTime() + "@gmail.com";
		JSONObject registration = new JSONObject();
		
		JSONObject consumer = new JSONObject();
		consumer.put(ConsumerParameters.activeConsumer, true);
		consumer.put(ConsumerParameters.preferedBrandId, 1);
		consumer.put(ConsumerParameters.alternativeBrandId, 2);
		consumer.put(ConsumerParameters.consumptionFrequency, 2);
		registration.put("consumer", consumer);
		
		// TODO que es esto preferedProductId y alternativeProductdId
		JSONObject person = new JSONObject();
		person.put(PersonFieldNames.firstName, "Marcos");
		person.put(PersonFieldNames.lastName, "Godoy");
		person.put(PersonFieldNames.email, email);
		person.put(PersonFieldNames.birthDate, 714070884661L);
		person.put(PersonFieldNames.street1, "Robles");
		person.put(PersonFieldNames.city, "Dolores");
		person.put(PersonFieldNames.stateId, 1);
		person.put(PersonFieldNames.countryId, 8);
		person.put(PersonFieldNames.postalCode, "7100");
		person.put(PersonFieldNames.addressType, "home");
		person.put(PersonFieldNames.phoneNumber, "+54 0221 4513521");
		person.put(PersonFieldNames.phoneType, "cellphone");
		registration.put("profile", person);
		
		JSONArray optInsArray = new JSONArray();
		JSONObject optIns = new JSONObject();
		optIns.put(OptInsParameters.brandFamilyId, 4);
		optIns.put(OptInsParameters.channel, 6001);
		optIns.put(OptInsParameters.accepted, true);
		optInsArray.add(optIns);
		registration.put("optIns", optInsArray);
		
		JSONObject credentials = new JSONObject();
		credentials.put(CredentialParameters.principal, email);
		credentials.put(CredentialParameters.password, "1234");
		registration.put("credential", credentials);
		
		System.out.println(registration.toString(2));
		JSON result1 = ThalamusClientFacade.registerPerson(registration);
		System.out.println(result1.toString(2));
		
		//JSON result = ThalamusClientFacade.login(email, "1234");
		
		JSONObject jsonObject = new JSONObject();
		jsonObject.put(LoginParameters.principal, email);
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
		
		/**/
		JSON getProfile = ThalamusClientFacade.getPerson(result.getTokenHolder());
		assertTrue(getProfile instanceof JSONObject);
		JSONObject getProf = (JSONObject)getProfile;
		System.out.println(getProf.toString(2));
		JSONObject data = getProf.getJSONObject("person");
		String firstName = (data).getJSONObject("profile").getString("firstname");
		String lastName = (data).getJSONObject("profile").getString("lastname");
		assertEquals("Marcos", firstName);
		assertEquals("Godoy", lastName);
		
		// Update
		JSONObject update = new JSONObject();
		update.put("consumer", consumer);
		person.put(PersonFieldNames.firstName, "Marcos Rafael");
		person.put(PersonFieldNames.lastName, "GodoyX");
		update.put("profile", person);
		update.put("optIns", optInsArray);
		JSON updateResult = ThalamusClientFacade.updatePerson(result.getTokenHolder(), update).getResult();
		System.out.println(updateResult.toString(2));
		getProfile = ThalamusClientFacade.getPerson(result.getTokenHolder());
		assertTrue(getProfile instanceof JSONObject);
		getProf = (JSONObject)getProfile;
		System.out.println(getProf.toString(2));
		data = getProf.getJSONObject("person");
		firstName = (data).getJSONObject("profile").getString("firstname");
		lastName = (data).getJSONObject("profile").getString("lastname");
		assertEquals("Marcos Rafael", firstName);
		assertEquals("GodoyX", lastName);
		
		//ThalamusClientFacade.logout();
	}
	
	public void t$estRegisterErrors() throws Exception {
//		String email = "Marcos" + System.nanoTime() + "@gmail.com";
//		JSONObject person = new JSONObject();
//		person.put(ProfileParameters.firstName, "Marcos");
//		person.put(ProfileParameters.lastName, "sss");
//		person.put(ProfileParameters.email, email);
//		person.put(ProfileParameters.birthDate, 714070884661l);
//		person.put(ProfileParameters.street, "Robles");
//		person.put(ProfileParameters.city, "Dolores");
//		person.put(ProfileParameters.stateId, 1);
//		person.put(ProfileParameters.countryId, 8);
//		person.put(ProfileParameters.postalCode, "7100");
//		person.put(ProfileParameters.addressType, "home");
//		person.put(ProfileParameters.phoneNumber, "+54 0221 4513521");
//		person.put(ProfileParameters.phoneNumberType, "cellphone");
//		person.put(ProfileParameters.password, "1234");
//		
//		person.put(ProfileParameters.activeConsumer, true);
//		person.put(ProfileParameters.preferedBrandId, 1);
//		person.put(ProfileParameters.alternativeBrandId, 2);
//		person.put(ProfileParameters.consumptionFrequency, 2);
//		JSON result1 = ThalamusClientFacade.register(person);
//		JSONObject errors = ((JSONObject)result1).getJSONObject("errors");
//		assertFalse(errors.isEmpty());
	}
}
