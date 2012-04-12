package com.tdil.struts.resources;

import org.apache.struts.util.MessageResources;

public class ApplicationResources {

	private static MessageResources mResources = null;

	public static void init(String resources) {
		mResources = MessageResources.getMessageResources(resources);
	}

	public static String getMessage(String key) {
		return mResources.getMessage(key);
	}
}
