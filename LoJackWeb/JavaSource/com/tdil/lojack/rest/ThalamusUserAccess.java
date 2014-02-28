package com.tdil.lojack.rest;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import net.sf.json.JSONObject;

import com.tdil.log4j.LoggerProvider;
import com.tdil.lojack.gis.LoJackServicesConnector;
import com.tdil.lojack.thalamus.ThalamusLoginCache;

@Path("/lojack")
public class ThalamusUserAccess extends AbstractRESTService {

	private static final String THALAMUS_SESSION_COOKIE = "TJSESSIONID";
	private static final String THALAMUS_AWSELB_COOKIE = "AWSELB";
	
	private static final org.apache.log4j.Logger LOG = LoggerProvider.getLogger(ThalamusUserAccess.class);
	
	@Context
    HttpServletRequest request;
	
	public HttpSession getSession() {
		return request.getSession(false);
	}
	
	public HttpSession getSession(boolean create) {
		return request.getSession(create);
	}
	
	@GET
	@Path("/api/v1/person/profile/homeUser")
	@Produces(MediaType.APPLICATION_JSON)
	public Response homeUser() {
		try {
			String cacheKey = getCacheKey();
			if (cacheKey != null) {
				JSONObject result = ThalamusLoginCache.getHomeJSON(cacheKey);
				if (result == null) {
					LOG.warn("No homeUser for " + cacheKey);
					return response(200, new JSONObject());
				} else {
					if (LOG.isInfoEnabled()) {
						LOG.info("Home user found " + cacheKey + "=" + result.toString());
					}
					return response(200, result);
				}
			} else {
				if (LOG.isInfoEnabled()) {
					LOG.info("Cache key is null, user is not logged?");
				}
				return response(200, new JSONObject());
			}
		} catch (Exception e) {
			LOG.error(e.getMessage(), e);
			return response(200, new JSONObject());
		} 
	}
	
	@GET
	@Path("/api/v1/person/profile/petUser")
	@Produces(MediaType.APPLICATION_JSON)
	public Response petUser() {
		try {
			String cacheKey = getCacheKey();
			if (cacheKey != null) {
				JSONObject result = ThalamusLoginCache.getPetJSON(cacheKey);
				if (result == null) {
					LOG.warn("No petUser for " + cacheKey);
					return response(200, new JSONObject());
				} else {
					if (LOG.isInfoEnabled()) {
						LOG.info("Pet user found " + cacheKey + "=" + result.toString());
					}
					return response(200, result);
				}
			} else {
				if (LOG.isInfoEnabled()) {
					LOG.info("Cache key is null, user is not logged?");
				}
				return response(200, new JSONObject());
			}
		} catch (Exception e) {
			LOG.error(e.getMessage(), e);
			return response(200, new JSONObject());
		} 
	}
	
	@GET
	@Path("/api/v1/person/profile/preventUser")
	@Produces(MediaType.APPLICATION_JSON)
	public Response preventUser() {
		try {
			String cacheKey = getCacheKey();
			if (cacheKey != null) {
				JSONObject result = ThalamusLoginCache.getPreventJSON(cacheKey);
				if (result == null) {
					LOG.warn("No preventUser for " + cacheKey);
					return response(200, new JSONObject());
				} else {
					if (LOG.isInfoEnabled()) {
						LOG.info("Prevent user found for " + cacheKey + "=" + result.toString());
					}
					return response(200, result);
				}
			} else {
				if (LOG.isInfoEnabled()) {
					LOG.info("Cache key is null, user is not logged?");
				}
				return response(200, new JSONObject());
			}
		} catch (Exception e) {
			LOG.error(e.getMessage(), e);
			return response(200, new JSONObject());
		} 
	}
	
	@POST
	@Path("/peugeot/add")
	@Produces(MediaType.APPLICATION_JSON)
	public Response addPeugeot(String body) {
		try {
			JSONObject json = (JSONObject)LoJackServicesConnector.extractJSONObjectResponse(body);
			ThalamusLoginCache.updateCache(json, request.getHeader("signHeader"));
			return okResponse();
		} catch (Exception e) {
			LOG.error(e.getMessage(), e);
			throw new WebApplicationException(401);
		}
	}

	private String getCacheKey() {
		Cookie tSessionCookie = getCookie(THALAMUS_SESSION_COOKIE);
		if (tSessionCookie == null) {
			LOG.warn("No thalamus session cookie " + THALAMUS_SESSION_COOKIE);
			return null;
		}
		Cookie awsElbCookie = getCookie(THALAMUS_AWSELB_COOKIE);
		if (awsElbCookie == null) {
			LOG.warn("No aws elb session cookie " + THALAMUS_AWSELB_COOKIE);
			return null;
		}
		return tSessionCookie.getValue() + "-" + awsElbCookie.getValue();
	}

	private Cookie getCookie(String thalamusSessionCookie) {
		Cookie cookies[] = request.getCookies();
		if (cookies == null) {
			return null;
		}
		for (Cookie cookie : cookies) {
			if (cookie.getName().equalsIgnoreCase(thalamusSessionCookie)) {
				return cookie;
			}
		}
		return null;
	}
	
}
