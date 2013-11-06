package com.tdil.lojack.gis.test;

import org.apache.log4j.BasicConfigurator;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;

public class Main {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		BasicConfigurator.configure();
		Logger rootLogger = Logger.getRootLogger();
		rootLogger.setLevel(Level.DEBUG);
		/*
		System.out.println(LoJackServicesConnector.getAlarms("1"));
		
		System.out.println(LoJackServicesConnector.sendPanicSignal("1", "1"));
		
		System.out.println(LoJackServicesConnector.activateAlarm("1", "1", "s"));
		System.out.println(LoJackServicesConnector.deactivateAlarm("1", "1", "s"));
		
		System.out.println(LoJackServicesConnector.getAlarmLog("1", "1"));
		
		System.out.println(LoJackServicesConnector.changeAlarmPassword("1", "1", "newnew"));*/
		
		
	}

}
