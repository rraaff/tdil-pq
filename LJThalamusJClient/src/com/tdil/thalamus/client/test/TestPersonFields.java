package com.tdil.thalamus.client.test;

import net.sf.json.JSON;

import com.tdil.thalamus.client.facade.ThalamusClientBeanFacade;
import com.tdil.thalamus.client.facade.ThalamusClientFacade;
import com.tdil.thalamus.client.facade.json.beans.PersonFields;

public class TestPersonFields extends ThalamusTestCase {

	public void testGet() throws Exception {
		JSON result = ThalamusClientFacade.getPersonFields();
		PersonFields personFields = new PersonFields(result);
		System.out.println(personFields);
	}
	
	public void testGetBeans() throws Exception {
		PersonFields personFields = ThalamusClientBeanFacade.getPersonFields();
		System.out.println(personFields);
	}
}
