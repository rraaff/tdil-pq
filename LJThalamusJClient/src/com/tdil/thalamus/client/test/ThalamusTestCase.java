package com.tdil.thalamus.client.test;

import junit.framework.TestCase;

import org.apache.log4j.BasicConfigurator;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;

import com.tdil.thalamus.client.core.ThalamusClient;

public class ThalamusTestCase extends TestCase {

	private static boolean loggerInitialized = false;
	
	@Override
	protected void setUp() throws Exception {
		// TODO Auto-generated method stub
		super.setUp();
		if (!loggerInitialized) {
			BasicConfigurator.configure();
			Logger rootLogger = Logger.getRootLogger();
			rootLogger.setLevel(Level.ERROR);
			Logger.getLogger(ThalamusClient.class).setLevel(Level.DEBUG);
			loggerInitialized = true;
		}
	}
}
