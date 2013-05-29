package com.tdil.lojack.temp;

import java.io.IOException;
import java.util.Map;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpException;
import org.apache.commons.httpclient.HttpStatus;
import org.apache.commons.httpclient.methods.EntityEnclosingMethod;
import org.apache.commons.httpclient.methods.RequestEntity;
import org.apache.commons.httpclient.methods.StringRequestEntity;

import com.tdil.log4j.LoggerProvider;
import com.tdil.lojack.prevent.PreventXMLUtils;
import com.tdil.lojack.prevent.URLParams;
import com.tdil.lojack.prevent.XMLResponse;
import com.tdil.thalamus.client.core.CommunicationException;
import com.tdil.thalamus.client.core.HttpStatusException;
import com.tdil.thalamus.client.core.InvalidResponseException;
import com.tdil.thalamus.client.core.UnauthorizedException;
import com.tdil.thalamus.client.core.method.PostMethodCreator;

public class MainGetCamaras {
	
	private static final org.apache.log4j.Logger LOG = LoggerProvider.getLogger(MainGetCamaras.class);
	
	public static void main(String[] args) throws HttpStatusException, InvalidResponseException, CommunicationException, UnauthorizedException {
		executePost("http://test.lojackgis.com.ar:8181/Carpathia.Middleware/Service1.svc/", "", "<s:Envelope xmlns:s=\"http://schemas.xmlsoap.org/soap/envelope/\"><s:Body><getCamaras xmlns=\"http://tempuri.org/\"><request>{\"guid\":\"2b64c399-69aa-4b8f-bd79-d5e8bf6075ee\",\"lojackUserId\":\"usuario\"}</request></getCamaras></s:Body></s:Envelope>");
	}

	private static XMLResponse executePost(String server, String service, String param) throws HttpStatusException, InvalidResponseException, CommunicationException, UnauthorizedException {
		long start = System.currentTimeMillis();
		String xml = param;
		String url = service;
		/*
		if (urlParams != null) {
			for (Map.Entry<String, String> entries : urlParams.getParams().entrySet()) {
				url = url.replace(entries.getKey(), entries.getValue());
			}
		}*/
		if (LOG.isDebugEnabled()) {
			LOG.debug("Execute: " + server + url + " xml: " + (xml == null ? "null" : xml));
		}
		HttpClient client = new HttpClient();
		EntityEnclosingMethod httpMethod = PostMethodCreator.INSTANCE.createMethod(server + url);
		try {
			httpMethod.setRequestHeader("Content-type", "text/xml");
			httpMethod.setRequestHeader("SOAPAction", "http://tempuri.org/IService1/getCamaras");
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
				LOG.error("Remote result for " + (service) + " status: " + statusCode + " and body: " + (response == null ? "null" : response));
				throw new HttpStatusException(statusCode, HttpStatus.getStatusText(statusCode));
			}
			System.out.println(response);
			return null;
		} catch (HttpException e) {
			throw new CommunicationException(e);
		} catch (IOException e) {
			throw new CommunicationException(e);
		} finally {
			if (LOG.isInfoEnabled()) {
				long end = System.currentTimeMillis();
				LOG.info("Execute: " + service + " took " + (end - start) + " millis");
			}
		}
	}
}
