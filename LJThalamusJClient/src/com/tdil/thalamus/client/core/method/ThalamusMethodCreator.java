package com.tdil.thalamus.client.core.method;

import org.apache.commons.httpclient.methods.EntityEnclosingMethod;

public abstract class ThalamusMethodCreator {

	public abstract EntityEnclosingMethod createMethod(String url);
	
	public abstract String getDescription();
}
