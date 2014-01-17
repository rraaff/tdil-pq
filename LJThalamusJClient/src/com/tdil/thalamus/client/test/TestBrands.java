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
import com.tdil.thalamus.client.facade.json.beans.BrandBean;

public class TestBrands extends ThalamusTestCase {

	public void testGet() throws Exception {
		System.out.println(System.currentTimeMillis());
		JSON result = ThalamusClientFacade.getBrands();
		System.out.println(System.currentTimeMillis());
		System.out.println(result.toString(2));
		assertTrue(result instanceof JSONArray);
		JSONArray arr = (JSONArray)result;
		boolean found = false;
		for (Object o : arr) {
			JSONObject obj = (JSONObject)o;
			if (obj.get("name").equals("Red Bull")) {
				found = true;
			}
		}
		assertTrue("Se deberia haber encontrado a RedBull", found);
	}
	
	public void testGetAsBeans() throws HttpStatusException, InvalidResponseException, CommunicationException, UnauthorizedException {
		Collection<BrandBean> result = ThalamusClientBeanFacade.getBrands();
		System.out.println(result);
		boolean found = false;
		for (BrandBean o : result) {
			if (o.getName().equals("Red Bull")) {
				found = true;
			}
		}
		assertTrue("Se deberia haber encontrado a RedBull", found);
		// Test de cache
		Collection<BrandBean> result1 = ThalamusClientBeanFacade.getBrands();
	}
}
