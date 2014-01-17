package com.tdil.thalamus.client.test;

import com.tdil.thalamus.client.facade.ThalamusClientFacade;

public class TestIsAlive extends ThalamusTestCase {

	public void testLoginSuccess() throws Exception {
		assertTrue("Thalamus should be alive", ThalamusClientFacade.isAlive());
	}
}
