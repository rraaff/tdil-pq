package com.tdil.lojack.prevent.model;

import java.io.IOException;

import com.tdil.lojack.prevent.PreventConnector;
import com.tdil.lojack.prevent.URLParams;
import com.tdil.lojack.prevent.XMLResponse;
import com.tdil.thalamus.client.core.CommunicationException;
import com.tdil.thalamus.client.core.HttpStatusException;
import com.tdil.thalamus.client.core.InvalidResponseException;
import com.tdil.thalamus.client.core.UnauthorizedException;

@Deprecated
public class Main {

	/**
	 * @param args
	 * @throws UnauthorizedException 
	 * @throws CommunicationException 
	 * @throws InvalidResponseException 
	 * @throws HttpStatusException 
	 * @throws IOException 
	 */
	public static void main(String[] args) throws HttpStatusException, InvalidResponseException, CommunicationException, UnauthorizedException, IOException {
		UserLogin userLogin = new UserLogin();
		userLogin.setUser("grecovery");
		userLogin.setPassword("7744");
		XMLResponse resp = PreventConnector.login(userLogin);
		LoginResponse lr = (LoginResponse)resp.getResult();
		
		
		Vehicles vehicles = null;
		try {
			URLParams getVehicles = new URLParams(lr).index("0");
			resp = PreventConnector.getVehicles(getVehicles);
			vehicles = (Vehicles)resp.getResult();
			System.out.println(vehicles);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		Vehicle vehicle = null;
		try {
			vehicle = vehicles.getVehiclesCollection().get(0);
			resp = PreventConnector.getVehicleSecureZones(lr, vehicle);
			SecureZones secureZones = (SecureZones)resp.getResult();
			System.out.println(secureZones);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		try {
			resp = PreventConnector.getVehicleSpeedLimit(lr, vehicle);
			SpeedLimits sp = (SpeedLimits)resp.getResult();
			System.out.println(sp);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		try {
			resp = PreventConnector.getVehiclePhones(lr, vehicle);
			PhoneNumbers pn = (PhoneNumbers)resp.getResult();
			System.out.println(pn);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		try {
			resp = PreventConnector.getVehicleSatPosition(lr, vehicle);
			SatellitePosition satpos = (SatellitePosition)resp.getResult();
			System.out.println(satpos);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		try {
			resp = PreventConnector.getFlotSatPosition(lr);
			SatellitePositions satposs = (SatellitePositions)resp.getResult();
			System.out.println(satposs);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		/*		
		String vehicles = IOUtils.toString(Main.class.getResourceAsStream("Vehicles.xml"));
		System.out.println(vehicles);	
		Object o = XMLUtils.fromXML(vehicles);
		System.out.println(o);
		
		
		String zones = IOUtils.toString(Main.class.getResourceAsStream("SatellitePositions.xml"));
		System.out.println(zones);	
		o = XMLUtils.fromXML(zones);
		System.out.println(o);*/
	}

}
