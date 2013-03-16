package com.tdil.lojack.gis.test;

import com.tdil.lojack.gis.GISConnector;

public class Main {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		System.out.println(GISConnector.getAlarms("1"));
		
		System.out.println(GISConnector.sendPanicSignal("1"));
	}

}
