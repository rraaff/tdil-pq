package com.tdil.lojack.rest;

import javax.servlet.http.HttpSession;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Response;

import net.sf.json.JSON;
import net.sf.json.JSONObject;
import net.sf.json.util.JSONTokener;

import org.apache.commons.httpclient.HttpStatus;

import com.tdil.lojack.rest.model.AsyncJobResponse;
import com.tdil.lojack.rest.model.RESTResponse;
import com.tdil.lojack.utils.WebsiteUser;
import com.tdil.thalamus.client.core.InvalidResponseException;
import com.tdil.thalamus.client.core.ThalamusResponse;
import com.tdil.thalamus.client.facade.json.beans.TokenHolder;

public abstract class AbstractRESTService {

	public void validateLogged() {
		HttpSession session = getSession();
		if (session == null) {
			throw new WebApplicationException(401);
		}
		WebsiteUser user = (WebsiteUser) session.getAttribute("user");
		if (user == null) {
			throw new WebApplicationException(401);
		}
	}

	public WebsiteUser getUser() {
		HttpSession session = getSession();
		if (session == null) {
			throw new WebApplicationException(401);
		}
		return (WebsiteUser) session.getAttribute("user");
	}
	
	public TokenHolder getTokenHolder() {
		return this.getUser().getToken();
	}

	public abstract HttpSession getSession();

	public Response okResponse() {
		return Response.status(201).entity(asJSON(RESTResponse.OK_RESPONSE)).build();
	}

	public Response failResponse() {
		return Response.status(201).entity(asJSON(RESTResponse.FAIL_RESPONSE)).build();
	}

	public Response asyncFailResponse() {
		return Response.status(201).entity(asJSON(new AsyncJobResponse(false))).build();
	}

	public Response asyncOkResponse() {
		return Response.status(201).entity(asJSON(new AsyncJobResponse(true))).build();
	}
	
	public static String asJSON(Object object) {
		return JSONObject.fromObject(object).toString();
	}

	public Response asResponse(boolean addAlarmAgenda) {
		if(addAlarmAgenda) {
			return okResponse();
		} else {
			return failResponse();
		}
	}

	protected Response failResponse(ThalamusResponse response) {
		RESTResponse restResponse = new RESTResponse(false);
		JSONObject jsonObject = (JSONObject)response.getResult();
		JSONObject errorsJson = jsonObject.getJSONObject("errors");
		for (Object o : errorsJson.keySet()) {
			restResponse.getErrors().put(o.toString(), errorsJson.getString(o.toString()));
		}
		return Response.status(401).entity(restResponse).build();
	}

	public Response createResponse(int status, Object intermediate) {
		return Response.status(status).entity(asJSON(intermediate)).build();
	}

	public static JSON extractJSONObjectResponse(String response) {
		try {
			JSONTokener tokener = new JSONTokener(response);
			Object rawResponseMessage = tokener.nextValue();
			if (rawResponseMessage == null) {
				throw new InvalidResponseException();
			}
			return (JSON) rawResponseMessage;
		} catch (InvalidResponseException e) {
			throw new WebApplicationException(HttpStatus.SC_BAD_REQUEST);
		}
	}
}
