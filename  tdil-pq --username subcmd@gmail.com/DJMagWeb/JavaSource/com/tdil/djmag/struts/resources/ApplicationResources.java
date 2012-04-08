package com.tdil.djmag.struts.resources;

import org.apache.struts.util.MessageResources;

public class ApplicationResources {
	
	private static MessageResources mResources = MessageResources.getMessageResources( "com.tdil.djmag.struts.ApplicationResources" );
	
	public static String getMessage(String key) {
		return mResources.getMessage(key);
	}
}
