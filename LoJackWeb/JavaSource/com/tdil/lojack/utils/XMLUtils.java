package com.tdil.lojack.utils;

import java.io.ByteArrayOutputStream;

import com.tdil.lojack.prevent.model.LoginResponse;
import com.tdil.lojack.prevent.model.UserLogin;
import com.thoughtworks.xstream.XStream;

public class XMLUtils {
	
	private static XStream xstream;

	static {
		xstream = new XStream();
		xstream.processAnnotations(UserLogin.class);
		xstream.processAnnotations(LoginResponse.class);
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
