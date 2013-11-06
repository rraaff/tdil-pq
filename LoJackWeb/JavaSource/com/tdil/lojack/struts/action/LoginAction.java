package com.tdil.lojack.struts.action;

import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessages;

import com.tdil.lojack.struts.forms.LoginForm;
import com.tdil.lojack.utils.WebsiteUser;
import com.tdil.struts.ValidationError;
import com.tdil.struts.ValidationException;
import com.tdil.struts.actions.AjaxAction;

public class LoginAction extends AjaxAction  {

	@Override
	protected ActionForward basicExecute(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
			LoginForm login = (LoginForm) form;
		try {
			WebsiteUser user = (WebsiteUser) login.executeLogin();
			HttpSession session = request.getSession();
			user.createUserJobCollection(session);
			session.setAttribute("user", user); // TODO hacer esto en login por fb y twitter
			HashMap<String, Object> result = new HashMap<String, Object>();
			result.put("result", "OK");
			writeJsonResponse(result, response);
			return null;
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
