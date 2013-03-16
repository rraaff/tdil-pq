package com.tdil.lojack.gis.test;

import org.apache.log4j.BasicConfigurator;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;

import com.tdil.lojack.gis.GISConnector;

public class Main {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		BasicConfigurator.configure();
		Logger rootLogger = Logger.getRootLogger();
		rootLogger.setLevel(Level.DEBUG);
		System.out.println(GISConnector.getAlarms("1"));
		
		System.out.println(GISConnector.sendPanicSignal("1"));
		
		System.out.println(GISConnector.activateAlarm("1", "s"));
		System.out.println(GISConnector.deactivateAlarm("1", "s"));
		
		System.out.println(GISConnector.getAlarmLog("1"));
		
		System.out.println(GISConnector.changeAlarmPassword("1", "newnew"));
	}

}
