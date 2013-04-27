package com.tdil.lojack.prevent;

import java.io.IOException;

import net.sf.json.JSON;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpException;
import org.apache.commons.httpclient.HttpStatus;
import org.apache.commons.httpclient.methods.EntityEnclosingMethod;
import org.apache.commons.httpclient.methods.RequestEntity;
import org.apache.commons.httpclient.methods.StringRequestEntity;
import org.apache.log4j.Logger;

import com.tdil.log4j.LoggerProvider;
import com.tdil.lojack.gis.JSONResponse;
import com.tdil.lojack.prevent.model.UserLogin;
import com.tdil.lojack.utils.XMLUtils;
import com.tdil.thalamus.client.core.CommunicationException;
import com.tdil.thalamus.client.core.HttpStatusException;
import com.tdil.thalamus.client.core.InvalidResponseException;
import com.tdil.thalamus.client.core.UnauthorizedException;
import com.tdil.thalamus.client.core.method.PostMethodCreator;

public class PreventConnector {

	private static final Logger LOG = LoggerProvider.getLogger(PreventConnector.class);
	
	public static XMLResponse login(UserLogin userLogin) throws HttpStatusException, InvalidResponseException, CommunicationException, UnauthorizedException {
		XMLResponse result = executePost("http://www.lojackgis.com.ar/PreventWCFServices/GISService.svc", userLogin, "/Users/Login");
		System.out.println(result);
		return result;
	}
	
	private static XMLResponse executePost(String server, Object param, String service) throws HttpStatusException, InvalidResponseException, CommunicationException, UnauthorizedException {
		String xml = null;
		if (param != null) {
			xml = XMLUtils.asXML(param);
		}
		if (LOG.isDebugEnabled()) {
			LOG.debug("Execute: " + service + " xml: " + (xml == null ? "null" : xml));
		}
		HttpClient client = new HttpClient();
		EntityEnclosingMethod httpMethod = PostMethodCreator.INSTANCE.createMethod(server + service);
		try {
			httpMethod.setRequestHeader("Content-type", "text/xml");
			if (xml != null) {
				RequestEntity requestEntity = new StringRequestEntity(xml);
				httpMethod.setRequestEntity(requestEntity);
			}
			client.executeMethod(httpMethod);
			int statusCode = httpMethod.getStatusCode();
			String response = httpMethod.getResponseBodyAsString();
			if (LOG.isDebugEnabled()) {
				LOG.debug("Remote result is: " + response + " with status: " + statusCode);
			}
			if (statusCode != HttpStatus.SC_OK) {
				throw new HttpStatusException(statusCode, HttpStatus.getStatusText(statusCode));
			}
			Object result = XMLUtils.fromXML(response);
			return new XMLResponse(result, statusCode);
		} catch (HttpException e) {
			throw new CommunicationException(e);
		} catch (IOException e) {
			throw new CommunicationException(e);
		}
	}
}
