package com.tdil.lojack.utils;

import java.io.ByteArrayOutputStream;

import com.tdil.lojack.prevent.model.LoginResponse;
import com.tdil.lojack.prevent.model.PhoneNumbers;
import com.tdil.lojack.prevent.model.PhoneNumbersReponse;
import com.tdil.lojack.prevent.model.SatellitePosition;
import com.tdil.lojack.prevent.model.SatellitePositionDesc;
import com.tdil.lojack.prevent.model.SatellitePositions;
import com.tdil.lojack.prevent.model.SecureZone;
import com.tdil.lojack.prevent.model.SecureZoneResponse;
import com.tdil.lojack.prevent.model.SecureZones;
import com.tdil.lojack.prevent.model.SpeedLimit;
import com.tdil.lojack.prevent.model.SpeedLimits;
import com.tdil.lojack.prevent.model.UserLogin;
import com.tdil.lojack.prevent.model.Vehicle;
import com.tdil.lojack.prevent.model.Vehicles;
import com.thoughtworks.xstream.XStream;

public class XMLUtils {
	
	private static XStream xstream;

	static {
		xstream = new XStream();
		xstream.processAnnotations(UserLogin.class);
		xstream.processAnnotations(LoginResponse.class);
		xstream.processAnnotations(Vehicle.class);
		xstream.processAnnotations(Vehicles.class);
		xstream.processAnnotations(SecureZone.class);
		xstream.processAnnotations(SecureZones.class);
		xstream.processAnnotations(SecureZoneResponse.class);
		xstream.processAnnotations(SpeedLimit.class);
		xstream.processAnnotations(SpeedLimits.class);
		xstream.processAnnotations(PhoneNumbers.class);
		xstream.processAnnotations(PhoneNumbersReponse.class);
		xstream.processAnnotations(SatellitePosition.class);
		xstream.processAnnotations(SatellitePositionDesc.class);
		xstream.processAnnotations(SatellitePositions.class);
	}
	
	public static final String asXML(Object o) {
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		xstream.toXML(o, baos);
		byte[] result = baos.toByteArray();
		return new String(result);
	}
	
	public static final Object fromXML(String xml) {
		return xstream.fromXML(xml);
	}
	
}
