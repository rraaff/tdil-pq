package com.tdil.struts.actions;

import java.io.IOException;
import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import net.sf.json.JSONObject;

import org.apache.log4j.Logger;
import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.tdil.log4j.LoggerProvider;
import com.tdil.struts.ValidationError;
import com.tdil.users.Role;
import com.tdil.users.SystemUser;

public abstract class AjaxAction extends Action {

	public SystemUser getLoggedUser(HttpServletRequest request) {
		HttpSession session = request.getSession(false);
		if (session == null) {
			return null;
		}
		SystemUser user = (SystemUser) session.getAttribute("user");
		if (user == null) {
			return null;
		}
		return user;
	}

	public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		if (userMustBeLogged()) {
			SystemUser user = getLoggedUser(request);
			if (user == null) {
				redirectToLogin(mapping, form, request, response);
				return null;
			}
			if (!Role.isValid(user, this.getAllowedRoles())) {
				getLog().fatal("Invalid action for " + this.getClass().getName() + " user " + user.toString());
				redirectToLogin(mapping, form, request, response);
				return null;
			}
		}
		return this.basicExecute(mapping, form, request, response);
	}
	
	public abstract boolean userMustBeLogged();
	
	protected final void writeJsonResponse(HashMap result, HttpServletResponse response) throws IOException {
		JSONObject json = JSONObject.fromObject(result);
		response.getOutputStream().write(json.toString().getBytes());
	}
	
	protected abstract Role[] getAllowedRoles();
	
	protected abstract ActionForward basicExecute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception;

	private void redirectToLogin(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		HashMap<String, String> result = new HashMap<String, String>();
		result.put("error", "notLogged");
		writeJsonResponse(result, response);
	}
	
	private static Logger getLog() {
		return LoggerProvider.getLogger(AjaxAction.class);
	}
}
