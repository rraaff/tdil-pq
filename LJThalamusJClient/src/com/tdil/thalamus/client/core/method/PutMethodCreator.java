package com.tdil.thalamus.client.core.method;

import org.apache.commons.httpclient.methods.EntityEnclosingMethod;
import org.apache.commons.httpclient.methods.PutMethod;

public class PutMethodCreator extends ThalamusMethodCreator {

	public static final PutMethodCreator INSTANCE = new PutMethodCreator();
	
	private PutMethodCreator() {
	}
	
	@Override
	public EntityEnclosingMethod createMethod(String url) {
		return new PutMethod(url);
	}
	
	@Override
	public String getDescription() {
		return "PUT";
	}
}
