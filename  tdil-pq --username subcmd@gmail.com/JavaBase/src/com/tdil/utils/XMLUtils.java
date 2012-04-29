package com.tdil.utils;

import java.io.ByteArrayOutputStream;
import java.util.ArrayList;
import java.util.List;

import com.thoughtworks.xstream.XStream;

public class XMLUtils {

	private static List<XMLAlias> alias = new ArrayList<XMLAlias>();
	
	public static final String asXML(Object o) {
		return asXML(o, alias);
	}
	
	public static final String asXML(Object o, List<XMLAlias> alias) {
		XStream xstream = new XStream();
		addAliasForAllClasses(xstream, alias);
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		xstream.toXML(o, baos);
		byte[] result = baos.toByteArray();
		return new String(result);
	}
	
	public static final Object fromXML(String xml) {
		return fromXML(xml, alias);
	}
	
	public static final Object fromXML(String xml, List<XMLAlias> alias) {
		XStream xstream = new XStream();
		addAliasForAllClasses(xstream, alias);
		return xstream.fromXML(xml);
	}

	private static void addAliasForAllClasses(XStream xstream, List<XMLAlias> alias) {
		for (XMLAlias xmlAlias : alias) {
			xstream.alias(xmlAlias.getAlias(), xmlAlias.getaClass());
		}
	}
	
	public static synchronized void addAlias(String alias, Class aClass) {
		XMLUtils.alias.add(new XMLAlias(alias, aClass));
	}
}
