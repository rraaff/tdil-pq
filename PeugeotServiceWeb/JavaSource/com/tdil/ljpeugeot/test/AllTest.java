package com.tdil.ljpeugeot.test;

import junit.framework.Test;
import junit.framework.TestSuite;

public class AllTest {
	
	public static Test suite() {
		
//		Properties p = new Properties(); 
//		p.setProperty("connectionURL", connectionURL);
//		p.setProperty("username", dbUser);  
//		p.setProperty("password", dbPassword);
//			  
//		IBatisManager.init("SqlMapConfig-JDBC-MYSQL.xml", p);
		
		TestSuite suite = new TestSuite("Peugeot Test Suite");
		suite.addTestSuite(TestNotifications.class);
		return suite;
	}
	
}

