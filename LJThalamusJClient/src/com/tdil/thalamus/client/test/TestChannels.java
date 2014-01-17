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
import com.tdil.thalamus.client.facade.json.beans.BrandFamilyBean;
import com.tdil.thalamus.client.facade.json.beans.ChannelBean;

public class TestChannels extends ThalamusTestCase {

	public void testGet() throws Exception {
		JSON result = ThalamusClientFacade.getChannels();
		assertTrue(result instanceof JSONArray);
		JSONArray arr = (JSONArray)result;
		boolean found = false;
		for (Object o : arr) {
			JSONObject obj = (JSONObject)o;
			if (obj.get("name").equals("email")) {
				found = true;
			}
		}
		assertTrue("Se deberia haber encontrado a EMAIL", found);
	}
	
	public void testGetAsBeans() throws HttpStatusException, InvalidResponseException, CommunicationException, UnauthorizedException {
		Collection<ChannelBean> result = ThalamusClientBeanFacade.getChannels();
		System.out.println(result);
		boolean found = false;
		for (ChannelBean o : result) {
			if (o.getName().equals("email")) {
				found = true;
			}
		}
		assertTrue("Se deberia haber encontrado a email", found);
	}
}
