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
import com.tdil.thalamus.client.facade.json.beans.DocumentTypeBean;

public class TestDocumentTypes extends ThalamusTestCase {

	public void testGet() throws Exception {
		JSON result = ThalamusClientFacade.getDocumentTypes();
		// TODO assert sobre el result
		assertTrue(result instanceof JSONArray);
		JSONArray arr = (JSONArray)result;
		boolean found = false;
		for (Object o : arr) {
			JSONObject obj = (JSONObject)o;
			if (obj.get("name").equals("DNI")) {
				found = true;
			}
		}
		assertTrue("Se deberia haber encontrado a DNI", found);
	}
	
	public void testGetAsBeans() throws HttpStatusException, InvalidResponseException, CommunicationException, UnauthorizedException {
		Collection<DocumentTypeBean> result = ThalamusClientBeanFacade.getDocumentTypes();
		System.out.println(result);
		boolean found = false;
		for (DocumentTypeBean o : result) {
			if (o.getName().equals("DNI")) {
				found = true;
			}
		}
		assertTrue("Se deberia haber encontrado a DNI", found);
	}
}
