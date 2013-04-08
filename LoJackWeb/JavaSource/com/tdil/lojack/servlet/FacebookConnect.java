package com.tdil.lojack.servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;

import com.tdil.log4j.LoggerProvider;
import com.tdil.lojack.utils.WebsiteUser;
import com.tdil.struts.actions.AbstractAction;
import com.tdil.thalamus.client.facade.ThalamusClientFacade;

public class FacebookConnect extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5611834065781809280L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		String code = req.getParameter("code");
        if (!StringUtils.isEmpty(code)) {
        	WebsiteUser user = (WebsiteUser)AbstractAction.getLoggedUser(req);
        	try {
				ThalamusClientFacade.addFacebook(user.getToken(), code);
        	} catch (Exception e) {
				getLog().error(e.getMessage(), e);
			}
        }
        RequestDispatcher dispatcher = req.getRequestDispatcher("/facebookconnect.do");
        dispatcher.forward(req, resp);
	}
	
	private static Logger getLog() {
		return LoggerProvider.getLogger(FacebookConnect.class);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		doGet(req, resp);
	}
}
