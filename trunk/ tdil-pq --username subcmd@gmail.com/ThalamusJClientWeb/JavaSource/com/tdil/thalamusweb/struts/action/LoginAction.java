package com.tdil.thalamusweb.struts.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessages;

import com.tdil.struts.ValidationError;
import com.tdil.struts.ValidationException;
import com.tdil.thalamusweb.struts.forms.LoginForm;
import com.tdil.users.User;

public class LoginAction extends Action  {

	public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		LoginForm login = (LoginForm) form;
		try {
			User user = (User) login.executeLogin();
			request.getSession().setAttribute("user", user);
			return mapping.findForward("success");
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
			return mapping.findForward("failure");
		}
	}
	
}
