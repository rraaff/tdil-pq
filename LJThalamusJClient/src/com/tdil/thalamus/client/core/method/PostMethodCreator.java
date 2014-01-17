package com.tdil.thalamus.client.core.method;

import org.apache.commons.httpclient.methods.EntityEnclosingMethod;
import org.apache.commons.httpclient.methods.PostMethod;

public class PostMethodCreator extends ThalamusMethodCreator {

	public static final PostMethodCreator INSTANCE = new PostMethodCreator();
	
	private PostMethodCreator() {
	}
	
	@Override
	public EntityEnclosingMethod createMethod(String url) {
		return new PostMethod(url);
	}
	
	@Override
	public String getDescription() {
		return "POST";
	}
}
