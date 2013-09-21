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

public class AuthorizationRequestProcessor extends RequestProcessor {

	@Override
	protected void processLocale(HttpServletRequest request,
			HttpServletResponse response) {
		//super.processLocale(request, response);
	}
	
	@Override
	protected boolean processRoles(HttpServletRequest request, HttpServletResponse response, ActionMapping mapping)
			throws IOException, ServletException {
		
		Role roles[] = AbstractAction.getPermissions(mapping);
		if (!Role.isValid(request, roles)) {
			if (Role.getUsers(request).isEmpty()) {
				String notLogged = getNotLoged(roles);
				ForwardConfig expireForward = moduleConfig.findForwardConfig(notLogged); 
				processForwardConfig(request,response,expireForward); 
			} else {
				String notAuthorized = getNotAuthorized(roles);
				ForwardConfig expireForward = moduleConfig.findForwardConfig(notAuthorized); 
				processForwardConfig(request,response,expireForward); 
			}
			return false;
		}
		return true;
	}

	private String getNotLoged(Role[] roles) {
		if (roles == null || roles.length == 0) {
			return "notLogged";
		}
		for (Role role : roles) {
			return role.getNotLogged();
		}
		return "notLogged";
	}
	
	private String getNotAuthorized(Role[] roles) {
		if (roles == null || roles.length == 0) {
			return "notAuthorized";
		}
		for (Role role : roles) {
			return role.getNotAuthorized();
		}
		return "notAuthorized";
	}
	
}
