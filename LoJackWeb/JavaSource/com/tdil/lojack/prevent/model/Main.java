package com.tdil.lojack.prevent.model;

import java.io.IOException;

import org.apache.commons.io.IOUtils;

import com.tdil.lojack.prevent.PreventConnector;
import com.tdil.lojack.utils.XMLUtils;
import com.tdil.thalamus.client.core.CommunicationException;
import com.tdil.thalamus.client.core.HttpStatusException;
import com.tdil.thalamus.client.core.InvalidResponseException;
import com.tdil.thalamus.client.core.UnauthorizedException;

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
		/*UserLogin userLogin = new UserLogin();
		userLogin.setUser("Prevent2");
		userLogin.setPassword("1234");
		PreventConnector.login(userLogin);*/
		String vehicles = IOUtils.toString(Main.class.getResourceAsStream("Vehicles.xml"));
		System.out.println(vehicles);	
		Object o = XMLUtils.fromXML(vehicles);
		System.out.println(o);
		
		
		String zones = IOUtils.toString(Main.class.getResourceAsStream("SatellitePositions.xml"));
		System.out.println(zones);	
		o = XMLUtils.fromXML(zones);
		System.out.println(o);
	}

}
