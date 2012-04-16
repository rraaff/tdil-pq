package com.tdil.struts;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.RequestProcessor;
import org.apache.struts.config.ForwardConfig;

import com.tdil.struts.actions.AbstractAction;
import com.tdil.users.Role;
import com.tdil.users.User;

public class AuthorizationRequestProcessor extends RequestProcessor {

	@Override
	protected boolean processRoles(HttpServletRequest request, HttpServletResponse response, ActionMapping mapping)
			throws IOException, ServletException {
		User user = AbstractAction.getLoggedUser(request);
		if (!Role.isValid(user, AbstractAction.getPermissions(mapping))) {
			if (user == null) {
				ForwardConfig expireForward = moduleConfig.findForwardConfig("notLogged"); 
				processForwardConfig(request,response,expireForward); 
			} else {
				ForwardConfig expireForward = moduleConfig.findForwardConfig("notAuthorized"); 
				processForwardConfig(request,response,expireForward); 
			}
			return false;
		}
		return true;
	}
	
}
