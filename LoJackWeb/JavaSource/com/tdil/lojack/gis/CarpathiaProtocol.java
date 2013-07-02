package com.tdil.lojack.gis;

import java.io.IOException;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpException;
import org.apache.commons.httpclient.HttpStatus;
import org.apache.commons.httpclient.methods.EntityEnclosingMethod;
import org.apache.commons.httpclient.methods.RequestEntity;
import org.apache.commons.httpclient.methods.StringRequestEntity;

import net.sf.json.JSON;

import com.tdil.thalamus.client.core.CommunicationException;
import com.tdil.thalamus.client.core.HttpStatusException;
import com.tdil.thalamus.client.core.InvalidResponseException;
import com.tdil.thalamus.client.core.UnauthorizedException;
import com.tdil.thalamus.client.core.method.PostMethodCreator;
import static com.tdil.lojack.gis.LoJackServicesConnector.LOG;
import static com.tdil.lojack.gis.LoJackServicesConnector.configureTimeout;
import static com.tdil.lojack.gis.LoJackServicesConnector.extractJSONObjectResponse;
import static com.tdil.thalamus.client.core.ThalamusClient.getResponseString;

public class CarpathiaProtocol extends MiddlewareProtocol {
	
	public static final String PROTOCOL = "soap-json";

	@Override
	public JSONResponse execute(String server, JSON json, String serviceParam) throws HttpStatusException, InvalidResponseException,
			CommunicationException, UnauthorizedException {
		String service = serviceParam.replace(".json", "");
		long start = System.currentTimeMillis();
		if (LOG.isDebugEnabled()) {
			LOG.debug("Execute: " + service + " json: " + (json == null ? "null" : json));
		}
		HttpClient client = new HttpClient();
		configureTimeout(client);
		EntityEnclosingMethod httpMethod = PostMethodCreator.INSTANCE.createMethod(server);
		try {
			httpMethod.setRequestHeader("Content-type", "text/xml");
			httpMethod.setRequestHeader("SOAPAction", "http://tempuri.org/IService1/" + service);
			if (json != null) {
				String toPost = CarpathiaTranslator.prepareRequest(service, json.toString());
				RequestEntity requestEntity = new StringRequestEntity(toPost);
				httpMethod.setRequestEntity(requestEntity);
			}
			client.executeMethod(httpMethod);
			int statusCode = httpMethod.getStatusCode();
			String response = getResponseString(httpMethod);
			if (LOG.isDebugEnabled()) {
				LOG.debug("Remote result is: " + response + " with status: " + statusCode);
			}
			if (statusCode != HttpStatus.SC_OK) {
				LOG.error("Remote result for " + (server + service) + " status: " + statusCode + " and body: " + (response == null ? "null" : response));
				throw new HttpStatusException(statusCode, HttpStatus.getStatusText(statusCode));
			}
			String jsonResponseString = CarpathiaTranslator.extractResponse(service, response);
			JSON result = extractJSONObjectResponse(jsonResponseString);
			return new JSONResponse(result, statusCode);
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
