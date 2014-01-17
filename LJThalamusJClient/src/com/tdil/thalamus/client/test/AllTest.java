package com.tdil.thalamus.client.test;

import junit.framework.Test;
import junit.framework.TestSuite;


public class AllTest {
	
	public static Test suite() {
		
		TestSuite suite = new TestSuite("Thalamus test suite");
		suite.addTestSuite(TestIsAlive.class);
		suite.addTestSuite(TestLogin.class);
		suite.addTestSuite(TestLogout.class);
		suite.addTestSuite(TestCountries.class);
		suite.addTestSuite(TestStates.class);
		suite.addTestSuite(TestBrands.class);
		suite.addTestSuite(TestBrandsFamilies.class);
		suite.addTestSuite(TestDocumentTypes.class);
		suite.addTestSuite(TestChannels.class);
		suite.addTestSuite(TestPersonFields.class);
		suite.addTestSuite(TestRegister.class);
		suite.addTestSuite(TestResetPassword.class);
		suite.addTestSuite(TestParsePersonFields.class);
		suite.addTestSuite(TestActivities.class);
		return suite;
	}
	
}

