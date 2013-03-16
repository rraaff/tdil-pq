package com.tdil.lojack.gis;

import java.util.List;

import com.tdil.lojack.gis.model.Alarm;
import com.tdil.lojack.gis.model.ChangeLog;
import com.tdil.lojack.gis.model.Light;

// TODO ver que datos de autheticacion hay que proveer
public class GISConnector {
	
	private static String gisServer;

	public static List<Alarm> getAlarms(String userId) {
		return null;
	}
	public static boolean sendPanicSignal(Alarm alarm) {
		return false;
	}
	public static boolean activateAlarm(Alarm alarm, String password) {
		return false;
		
	}
	public static boolean deactivateAlarm(Alarm alarm, String password) {
		return false;
		
	}
	public static List<ChangeLog> getAlarmLog(Alarm alarm) {
		return null;
	}
	public static boolean changeAlarmPassword(Alarm alarm, String newPassword) {
		return false;
	}

	public static List<Light> getLights(String userId) {
		return null;
	}
	public static boolean activateLight(Light light, String password) {
		return false;
	}
	public static boolean deactivateLight(Light light, String password) {
		return false;
	}
	public static List<ChangeLog> getLightLog(Light light) {
		return null;
	}
	public static boolean changeLightPassword(Light light, String newPassword) {
		return false;
	}
	public static String getGisServer() {
		return gisServer;
	}
	public static void setGisServer(String gisServer) {
		GISConnector.gisServer = gisServer;
	}
	
}
