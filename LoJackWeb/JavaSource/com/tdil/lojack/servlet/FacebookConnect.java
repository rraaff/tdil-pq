package com.tdil.lojack.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;

import org.apache.commons.lang.StringUtils;

import com.tdil.lojack.utils.WebsiteUser;
import com.tdil.struts.actions.AbstractAction;
import com.tdil.thalamus.client.core.CommunicationException;
import com.tdil.thalamus.client.core.HttpStatusException;
import com.tdil.thalamus.client.core.InvalidResponseException;
import com.tdil.thalamus.client.core.UnauthorizedException;
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
				JSONObject json = (JSONObject)ThalamusClientFacade.addFacebook(user.getToken(), code);
				System.out.println(json.toString(2));
			} catch (HttpStatusException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (InvalidResponseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (CommunicationException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (UnauthorizedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
        	//return mapping.findForward("register");
        } else { // si cancelo
        	//return mapping.findForward("cancel");
        }
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		doGet(req, resp);
	}
}
