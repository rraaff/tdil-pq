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
import com.tdil.thalamus.client.facade.json.beans.BrandFamilyBean;

public class TestBrandsFamilies extends ThalamusTestCase {

	public void testGet() throws Exception {
		JSON result = ThalamusClientFacade.getBrandsFamilies();
		// TODO assert sobre el result
		assertTrue(result instanceof JSONArray);
		JSONArray arr = (JSONArray)result;
		boolean found = false;
		for (Object o : arr) {
			JSONObject obj = (JSONObject)o;
			if (obj.get("name").equals("E Family")) {
				found = true;
			}
		}
		assertTrue("Se deberia haber encontrado a E Family", found);
	}
	
	public void testGetAsBeans() throws HttpStatusException, InvalidResponseException, CommunicationException, UnauthorizedException {
		Collection<BrandFamilyBean> result = ThalamusClientBeanFacade.getBrandFamilies();
		System.out.println(result);
		boolean found = false;
		for (BrandFamilyBean o : result) {
			if (o.getName().equals("E Family")) {
				found = true;
			}
		}
		assertTrue("Se deberia haber encontrado a E Family", found);
	}
}
