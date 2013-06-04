package com.tdil.lojack.gis;

import net.sf.json.JSON;

import com.tdil.thalamus.client.core.CommunicationException;
import com.tdil.thalamus.client.core.HttpStatusException;
import com.tdil.thalamus.client.core.InvalidResponseException;
import com.tdil.thalamus.client.core.UnauthorizedException;

public abstract class MiddlewareProtocol {

	public abstract JSONResponse execute(String server, JSON json, String service) throws HttpStatusException, InvalidResponseException,
			CommunicationException, UnauthorizedException;
}
