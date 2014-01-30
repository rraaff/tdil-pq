package com.tdil.ljpeugeot.servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;

import com.tdil.ljpeugeot.utils.WebsiteUser;
import com.tdil.log4j.LoggerProvider;
import com.tdil.struts.actions.AbstractAction;
import com.tdil.thalamus.client.core.ThalamusResponse;
import com.tdil.thalamus.client.facade.ThalamusClientFacade;

public class FacebookConnect extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5611834065781809280L;
	
	public static final String FACEBOOK_CONN_ERR = "facebook_conn_err";

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		String code = req.getParameter("code");
        if (!StringUtils.isEmpty(code)) {
        	WebsiteUser user = (WebsiteUser)AbstractAction.getLoggedUser(req);
        	try {
        		ThalamusResponse response = ThalamusClientFacade.addFacebook(user.getToken(), code);
        		if (response.isBadRequest()) {
        			req.getSession().setAttribute(FACEBOOK_CONN_ERR, "1");
        		} 
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
