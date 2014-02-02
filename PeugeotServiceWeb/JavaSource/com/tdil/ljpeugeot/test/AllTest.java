package com.tdil.ljpeugeot.test;

import java.util.Properties;

import junit.framework.Test;
import junit.framework.TestSuite;

import com.tdil.ibatis.IBatisManager;
import com.tdil.ljpeugeot.daomanager.DAOManager;
import com.tdil.ljpeugeot.daomanager.MySQLDAOProvider;

public class AllTest {
	
	public static Test suite() {
		
		DAOManager.setCurrentDao(new MySQLDAOProvider());
		Properties p = new Properties(); 
		p.setProperty("connectionURL", "jdbc:mysql://localhost:3306/PEUGEOTSVC");
		p.setProperty("username", "PEUGEOTSVC_USER");  
		p.setProperty("password", "PEUGEOTSVC_USER");
			  
		IBatisManager.init("SqlMapConfig-JDBC-MYSQL.xml", p);
		
		TestSuite suite = new TestSuite("Peugeot Test Suite");
		suite.addTestSuite(TestNotifications.class);
		suite.addTestSuite(GenerateData.class);
		return suite;
	}
	
}

