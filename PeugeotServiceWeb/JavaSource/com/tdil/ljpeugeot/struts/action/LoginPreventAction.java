package com.tdil.ljpeugeot.struts.action;

import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessages;

import com.tdil.ljpeugeot.struts.forms.PreventLoginForm;
import com.tdil.ljpeugeot.utils.WebsiteUser;
import com.tdil.log4j.LoggerProvider;
import com.tdil.struts.ValidationError;
import com.tdil.struts.ValidationException;
import com.tdil.struts.actions.AjaxAction;

public class LoginPreventAction extends AjaxAction {

	private static final org.apache.log4j.Logger LOG = LoggerProvider.getLogger(LoginPreventAction.class);
	
	@Override
	protected ActionForward basicExecute(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		PreventLoginForm login = (PreventLoginForm) form;
		try {
			final WebsiteUser sessionUser = (WebsiteUser)request.getSession().getAttribute("user");
			login.setUsername(sessionUser.getPreventUserId());
			boolean logged = login.executeLogin();
			HashMap<String, Object> result = new HashMap<String, Object>();
			if (logged) {
				sessionUser.setPreventPassword(login.getPassword());
				sessionUser.setPreventAccessToken(login.getPreventAccessToken());
				sessionUser.setPreventLoginResponse(login.getPreventLoginResponse());
				sessionUser.setPreventLogged(true);
				request.getSession().setAttribute("user", sessionUser);
				result.put("result", "OK");
				writeJsonResponse(result, response);
				return null;
			} else {
				result.put("result", "ERR");
				writeJsonResponse(result, response);
				return null;
			}
		} catch (ValidationException e) {
			ValidationError error = e.getError();
			ActionMessages msg = error.asMessages();
			if (msg != null) {
				request.setAttribute("hasError", "true");
				addMessages(request, msg);
			}
			ActionMessages errors = error.asActionsErrors();
			if (errors != null) {
				addErrors(request, errors);
			}
			HashMap<String, Object> result = new HashMap<String, Object>();
			result.put("result", "ERR");
			writeJsonResponse(result, response);
			return null;
		}
	}

}
