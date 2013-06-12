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
		
		
		String login = "{ \"jSessionID\": \""+"temp3227"+"\", \"lojackToken\": \""+"a5b0981a0188bb9a5b7fe44b6c32d894"+
		"\", \"awselb\": \""+"59219FCD10EB10AEFCC1FD49BC63745625D275B0F0D224EB924EEDBE00AE31077EB5AB3FAEC3396F9349509FA92B474A4A0194AA8870FCD6B48062AE4C6CC3B135676ABA5AC5A8B96CDB7693C1036FA694254EFA57"+"\", \"timeZoneOffset\": -3 }";
		XMLResponse resp1 = PreventConnector.executePost("http://test.lojackgis.com.ar:8080/webgis/preventwcfServices/GISService.svc", "/Users/LoginPortal", login, null, "application/json");
		LoginResponse lr1 = (LoginResponse)resp1.getResult();
		System.out.println(lr1.getStatus());
		
		UserLogin userLogin = new UserLogin();
		userLogin.setUser("Prevent2");
		userLogin.setPassword("1234");
		XMLResponse resp = PreventConnector.login(userLogin);
		LoginResponse lr = (LoginResponse)resp.getResult();

		// listado de vehiculos
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

		// zonas seguras
		Vehicle vehicle = null;
		Vehicle vehicle1 = null;
		Vehicle vehicle2 = null;
		try {
			vehicle = vehicles.getVehiclesCollection().get(0);
			vehicle1 = vehicles.getVehiclesCollection().get(1);
			vehicle2 = vehicles.getVehiclesCollection().get(2);
			resp = PreventConnector.getVehicleSecureZones(lr, vehicle);
			SecureZones secureZones = (SecureZones)resp.getResult();
			System.out.println(secureZones);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}


		// cambio de zona
		// TODO


		// limite de velocidad
		SpeedLimits sp = null;
		try {
			resp = PreventConnector.getVehicleSpeedLimit(lr, vehicle);
			sp = (SpeedLimits)resp.getResult();
			System.out.println(sp);

			resp = PreventConnector.getVehicleSpeedLimit(lr, vehicle1);
			sp = (SpeedLimits)resp.getResult();
			System.out.println(sp);

			resp = PreventConnector.getVehicleSpeedLimit(lr, vehicle2);
			sp = (SpeedLimits)resp.getResult();
			System.out.println(sp);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		// cambio de limite de velocidad
		SpeedLimit selected = sp.getActiveSpeedLimit();
		System.out.println("Active is " + selected);
		SpeedLimit random = getRandomSpeedLimit(sp, selected);
		try {
			resp = PreventConnector.setVehicleSpeedLimit(lr, vehicle, random);
			SpeedLimitResponse slr = (SpeedLimitResponse)resp.getResult();
			System.out.println(slr);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		// telefonos
		try {
			resp = PreventConnector.getVehiclePhones(lr, vehicle);
			PhoneNumbers pn = (PhoneNumbers)resp.getResult();
			System.out.println(pn);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		// cambio de telefono
		UpdatePhoneNumbers phoneNumbers = new UpdatePhoneNumbers();
		phoneNumbers.setAlert(String.valueOf(System.currentTimeMillis()));
		phoneNumbers.setCrash(String.valueOf(System.currentTimeMillis()));
		phoneNumbers.setOther(String.valueOf(System.currentTimeMillis()));
		phoneNumbers.setUserToken(lr.getUserToken());
		phoneNumbers.setVehicleID(vehicle.getId());
		try {
			resp = PreventConnector.setVehiclePhones(lr, phoneNumbers);
			PhoneNumbersReponse pn = (PhoneNumbersReponse)resp.getResult();
			System.out.println(pn);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		// posicion satelital
		try {
			resp = PreventConnector.getVehicleSatPosition(lr, vehicle);
			SatellitePosition satpos = (SatellitePosition)resp.getResult();
			System.out.println(satpos);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		// posicion satelital de toda la flota
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

	private static SpeedLimit getRandomSpeedLimit(SpeedLimits sp,
			SpeedLimit selected) {
		for (SpeedLimit sl : sp.getLimits()) {
			if (!sl.getId().equals(selected.getId())) {
				return sl;
			}
		}
		return null;
	}

}