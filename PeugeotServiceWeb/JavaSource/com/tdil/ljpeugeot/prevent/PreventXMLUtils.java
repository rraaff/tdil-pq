package com.tdil.ljpeugeot.prevent;

import java.io.ByteArrayOutputStream;

import com.tdil.ljpeugeot.prevent.model.LoginResponse;
import com.tdil.ljpeugeot.prevent.model.PhoneNumbers;
import com.tdil.ljpeugeot.prevent.model.PhoneNumbersReponse;
import com.tdil.ljpeugeot.prevent.model.SatellitePosition;
import com.tdil.ljpeugeot.prevent.model.SatellitePositionDesc;
import com.tdil.ljpeugeot.prevent.model.SatellitePositions;
import com.tdil.ljpeugeot.prevent.model.SecureZone;
import com.tdil.ljpeugeot.prevent.model.SecureZoneResponse;
import com.tdil.ljpeugeot.prevent.model.SecureZones;
import com.tdil.ljpeugeot.prevent.model.SpeedLimit;
import com.tdil.ljpeugeot.prevent.model.SpeedLimitResponse;
import com.tdil.ljpeugeot.prevent.model.SpeedLimits;
import com.tdil.ljpeugeot.prevent.model.UpdatePhoneNumbers;
import com.tdil.ljpeugeot.prevent.model.UserLogin;
import com.tdil.ljpeugeot.prevent.model.Vehicle;
import com.tdil.ljpeugeot.prevent.model.Vehicles;
import com.thoughtworks.xstream.XStream;

public class PreventXMLUtils {

	private static XStream xstream;

	private static XStream xstreamUpdatePhone;

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
		xstream.processAnnotations(SpeedLimitResponse.class);

		xstreamUpdatePhone = new XStream();
		xstreamUpdatePhone.processAnnotations(UpdatePhoneNumbers.class);
	}

	public static final String asXML(Object o) {
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		xstream.toXML(o, baos);
		byte[] result = baos.toByteArray();
		return new String(result);
	}

	public static final String asXMLUpdatePhone(Object o) {
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		xstreamUpdatePhone.toXML(o, baos);
		byte[] result = baos.toByteArray();
		return new String(result);
	}

	public static final Object fromXML(String xml) {
		return xstream.fromXML(xml);
	}

}
