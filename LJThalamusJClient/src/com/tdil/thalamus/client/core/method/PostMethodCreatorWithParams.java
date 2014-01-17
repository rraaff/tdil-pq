package com.tdil.thalamus.client.core.method;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.httpclient.NameValuePair;
import org.apache.commons.httpclient.methods.EntityEnclosingMethod;
import org.apache.commons.httpclient.methods.PostMethod;

public class PostMethodCreatorWithParams extends ThalamusMethodCreator {

	private List<NameValuePair> params = new ArrayList<NameValuePair>();
	
	public PostMethodCreatorWithParams() {
	}
	
	public PostMethodCreatorWithParams(NameValuePair nvp) {
		addParam(nvp);
	}
	
	public void addParam(NameValuePair nvp) {
		params.add(nvp);
	}
	
	@Override
	public EntityEnclosingMethod createMethod(String url) {
		PostMethod pm = new PostMethod(url);
		for (NameValuePair nvp : params) {
			pm.addParameter(nvp);
		}
		return pm;
	}
	
	@Override
	public String getDescription() {
		return "POST";
	}

}
