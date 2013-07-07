package com.tdil.lojack.rest;

import javax.servlet.http.HttpSession;
import javax.ws.rs.WebApplicationException;

import com.tdil.lojack.utils.WebsiteUser;

public abstract class AbstractRESTService {

	public void validateLogged() {
		HttpSession session = getSession();
		if (session == null) {
			throw new WebApplicationException(401);
		}
		WebsiteUser user = (WebsiteUser)session.getAttribute("user");
		if (user == null) {
			throw new WebApplicationException(401);
		}
	}
	
	public WebsiteUser getUser() {
		HttpSession session = getSession();
		if (session == null) {
			throw new WebApplicationException(401);
		}
		return (WebsiteUser)session.getAttribute("user");
	}

	public abstract HttpSession getSession();
}
