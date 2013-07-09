package com.tdil.lojack.rest;

import javax.servlet.http.HttpSession;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Response;

import net.sf.json.JSON;
import net.sf.json.util.JSONTokener;

import org.apache.commons.httpclient.HttpStatus;

import com.tdil.lojack.rest.model.AsyncJobResponse;
import com.tdil.lojack.rest.model.RESTResponse;
import com.tdil.lojack.utils.WebsiteUser;
import com.tdil.thalamus.client.core.InvalidResponseException;
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
		return Response.status(201).entity(RESTResponse.OK_RESPONSE).build();
	}

	public Response failResponse() {
		return Response.status(201).entity(RESTResponse.FAIL_RESPONSE).build();
	}

	public Response asyncFailResponse() {
		return Response.status(201).entity(new AsyncJobResponse(false)).build();
	}

	public Response asyncOkResponse() {
		return Response.status(201).entity(new AsyncJobResponse(true)).build();
	}

	public Response asResponse(boolean addAlarmAgenda) {
		if(addAlarmAgenda) {
			return okResponse();
		} else {
			return failResponse();
		}
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
