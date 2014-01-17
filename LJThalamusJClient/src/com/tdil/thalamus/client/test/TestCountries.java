package com.tdil.thalamus.client.test;

import java.util.Collection;

import net.sf.json.JSON;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import com.tdil.thalamus.client.core.CommunicationException;
import com.tdil.thalamus.client.core.HttpStatusException;
import com.tdil.thalamus.client.core.InvalidResponseException;
import com.tdil.thalamus.client.core.UnauthorizedException;
import com.tdil.thalamus.client.facade.ThalamusClientBeanFacade;
import com.tdil.thalamus.client.facade.ThalamusClientFacade;
import com.tdil.thalamus.client.facade.json.beans.ChannelBean;
import com.tdil.thalamus.client.facade.json.beans.CountryBean;

public class TestCountries extends ThalamusTestCase {

	public void testGet() throws Exception {
		JSON result = ThalamusClientFacade.getCountries();
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
	
	public void testGetAsBeans() throws HttpStatusException, InvalidResponseException, CommunicationException, UnauthorizedException {
		Collection<CountryBean> result = ThalamusClientBeanFacade.getCountries();
		System.out.println(result);
		boolean found = false;
		for (CountryBean o : result) {
			if (o.getName().equals("Argentina")) {
				found = true;
			}
		}
		assertTrue("Se deberia haber encontrado a Argentina", found);
	}
}
