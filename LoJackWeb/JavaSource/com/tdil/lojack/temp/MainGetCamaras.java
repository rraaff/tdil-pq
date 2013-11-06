package com.tdil.lojack.temp;

import java.io.IOException;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpException;
import org.apache.commons.httpclient.HttpStatus;
import org.apache.commons.httpclient.methods.EntityEnclosingMethod;
import org.apache.commons.httpclient.methods.RequestEntity;
import org.apache.commons.httpclient.methods.StringRequestEntity;

import com.tdil.log4j.LoggerProvider;
import com.tdil.lojack.gis.CarpathiaTranslator;
import com.tdil.thalamus.client.core.CommunicationException;
import com.tdil.thalamus.client.core.HttpStatusException;
import com.tdil.thalamus.client.core.InvalidResponseException;
import com.tdil.thalamus.client.core.UnauthorizedException;
import com.tdil.thalamus.client.core.method.PostMethodCreator;

public class MainGetCamaras {
	
	private static final org.apache.log4j.Logger LOG = LoggerProvider.getLogger(MainGetCamaras.class);
	
	public static void main(String[] args) throws HttpStatusException, InvalidResponseException, CommunicationException, UnauthorizedException {
		String postString = CarpathiaTranslator.prepareRequest("getCamaras", "{\"guid\":\"2b64c399-69aa-4b8f-bd79-d5e8bf6075ee\",\"lojackUserId\":\"usuario\"}");
		String result = executePost("http://test.lojackgis.com.ar:8181/Carpathia.Middleware/Service1.svc/", "getCamaras", postString);
		System.out.println(CarpathiaTranslator.extractResponse("getCamaras", result));
		
		postString = CarpathiaTranslator.prepareRequest("getAlarms", "{\"guid\":\"2b64c399-69aa-4b8f-bd79-d5e8bf6075ee\",\"lojackUserId\":\"usuario\"}");
		result = executePost("http://test.lojackgis.com.ar:8181/Carpathia.Middleware/Service1.svc/", "getAlarms", postString);
		System.out.println(CarpathiaTranslator.extractResponse("getAlarms", result));
		
		postString = CarpathiaTranslator.prepareRequest("getLights", "{\"guid\":\"2b64c399-69aa-4b8f-bd79-d5e8bf6075ee\",\"lojackUserId\":\"usuario\"}");
		result = executePost("http://test.lojackgis.com.ar:8181/Carpathia.Middleware/Service1.svc/", "getLights", postString);
		System.out.println(CarpathiaTranslator.extractResponse("getLights", result));
		
		postString = CarpathiaTranslator.prepareRequest("getAlarmLog", "{\"guid\":\"2b64c399-69aa-4b8f-bd79-d5e8bf6075ee\",\"lojackUserId\":\"usuario\", \"idEntidad\": 70843, \"intPageSize\": 10}");
		result = executePost("http://test.lojackgis.com.ar:8181/Carpathia.Middleware/Service1.svc/", "getAlarmLog", postString);
		System.out.println(CarpathiaTranslator.extractResponse("getAlarmLog", result));
		
		postString = CarpathiaTranslator.prepareRequest("activateAlarm", "{\"guid\":\"2b64c399-69aa-4b8f-bd79-d5e8bf6075ee\",\"lojackUserId\":\"usuario\", \"idEntidad\": 70843, \"receiveNotification\": false}");
		result = executePost("http://test.lojackgis.com.ar:8181/Carpathia.Middleware/Service1.svc/", "activateAlarm", postString);
		System.out.println(CarpathiaTranslator.extractResponse("activateAlarm", result));
		
		postString = CarpathiaTranslator.prepareRequest("deactivateAlarm", "{\"guid\":\"2b64c399-69aa-4b8f-bd79-d5e8bf6075ee\",\"lojackUserId\":\"usuario\", \"idEntidad\": 70843, \"receiveNotification\": false}");
		result = executePost("http://test.lojackgis.com.ar:8181/Carpathia.Middleware/Service1.svc/", "deactivateAlarm", postString);
		System.out.println(CarpathiaTranslator.extractResponse("deactivateAlarm", result));
		
		postString = CarpathiaTranslator.prepareRequest("getHistoryJobStatus", "{\"guid\":\"2b64c399-69aa-4b8f-bd79-d5e8bf6075ee\",\"lojackUserId\":\"usuario\", \"jobId\": 232}");
		result = executePost("http://test.lojackgis.com.ar:8181/Carpathia.Middleware/Service1.svc/", "getHistoryJobStatus", postString);
		System.out.println(CarpathiaTranslator.extractResponse("getHistoryJobStatus", result));
		
		postString = CarpathiaTranslator.prepareRequest("getAlarmAgendas", "{\"guid\":\"2b64c399-69aa-4b8f-bd79-d5e8bf6075ee\",\"lojackUserId\":\"usuario\", \"idEntidad\": 70843}");
		result = executePost("http://test.lojackgis.com.ar:8181/Carpathia.Middleware/Service1.svc/", "getAlarmAgendas", postString);
		System.out.println(CarpathiaTranslator.extractResponse("getAlarmAgendas", result));
		
		
		/*String postString = CarpathiaTranslator.prepareRequest("getCamaras", "{\"guid\":\"2b64c399-69aa-4b8f-bd79-d5e8bf6075ee\",\"lojackUserId\":\"usuario\"}");
		String result = executePost("http://test.lojackgis.com.ar:8181/Carpathia.Middleware/Service1.svc/", "", postString);
		System.out.println(CarpathiaTranslator.extractResponse("getCamaras", result));*/
		
	}

	private static String executePost(String server, String service, String param) throws HttpStatusException, InvalidResponseException, CommunicationException, UnauthorizedException {
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
		EntityEnclosingMethod httpMethod = PostMethodCreator.INSTANCE.createMethod(server);
		try {
			httpMethod.setRequestHeader("Content-type", "text/xml");
			httpMethod.setRequestHeader("SOAPAction", "http://tempuri.org/IService1/" + service);
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
			//System.out.println(response);
			return response;
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
