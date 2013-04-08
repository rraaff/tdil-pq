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

public class TwitterConnect extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5611834065781809280L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		String code = req.getParameter("oauth_token");
		String oauth_verifier = req.getParameter("oauth_verifier");
        if (!StringUtils.isEmpty(code) && !StringUtils.isEmpty(oauth_verifier)) {
        	WebsiteUser user = (WebsiteUser)AbstractAction.getLoggedUser(req);
        	try {
				ThalamusClientFacade.addTwitter(user.getToken(), code, oauth_verifier);
			} catch (Exception e) {
				getLog().error(e.getMessage(), e);
			}
        }
        RequestDispatcher dispatcher = req.getRequestDispatcher("/twitterconnect.do");
		dispatcher.forward(req, resp);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		doGet(req, resp);
	}
	
	private static Logger getLog() {
		return LoggerProvider.getLogger(TwitterConnect.class);
	}
}
