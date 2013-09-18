package com.tdil.lojack.rest;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import net.sf.json.JSONObject;

import com.tdil.log4j.LoggerProvider;
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
					return response(new JSONObject());
				} else {
					return response(result);
				}
			} else {
				return response(new JSONObject());
			}
		} catch (Exception e) {
			LOG.error(e.getMessage(), e);
			return response(new JSONObject());
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
					return response(new JSONObject());
				} else {
					return response(result);
				}
			} else {
				return response(new JSONObject());
			}
		} catch (Exception e) {
			LOG.error(e.getMessage(), e);
			return response(new JSONObject());
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
					return response(new JSONObject());
				} else {
					return response(result);
				}
			} else {
				return response(new JSONObject());
			}
		} catch (Exception e) {
			LOG.error(e.getMessage(), e);
			return response(new JSONObject());
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
