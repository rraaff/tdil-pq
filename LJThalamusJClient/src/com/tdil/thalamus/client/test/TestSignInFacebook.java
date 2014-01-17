package com.tdil.thalamus.client.test;

import java.net.URLDecoder;

import net.sf.json.JSONObject;

import com.tdil.thalamus.client.core.ThalamusClient;
import com.tdil.thalamus.client.facade.ThalamusClientFacade;

public class TestSignInFacebook extends ThalamusTestCase {

	public void testSignInFacebook() throws Exception {
		
//		JSON person = ThalamusClientFacade.getPerson("user1@mail.com", "4321");
//		System.out.println(person.toString(2));
		ThalamusClient.setTHALAMUS_TOUCHPOINT_CODE("test");
		ThalamusClient.setTHALAMUS_TOUCHPOINT_TOKEN("testtesttesttesttesttesttesttesttesttesttesttesttesttesttesttest");
		JSONObject result = (JSONObject)ThalamusClientFacade.signInFacebook();
		System.out.println(result.toString(2));
		JSONObject link = result.getJSONObject("link");
		String href = link.getString("href");
		// TODO client.getState().getCookies()
		System.out.println(href);
		System.out.println(URLDecoder.decode(href, "ISO-8859-1"));
		
	}
}
