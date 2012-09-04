package com.tdil.thalamus.client.test;

import junit.framework.Test;
import junit.framework.TestSuite;


public class AllTest {
	
	public static Test suite() {
		
		TestSuite suite = new TestSuite("Thalamus test suite");
		suite.addTestSuite(TestLogin.class);
		suite.addTestSuite(TestLogout.class);
		suite.addTestSuite(TestCountries.class);
		suite.addTestSuite(TestRegister.class);
		return suite;
	}
	
}

