package com.tdil.lojack.struts.action;

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
import com.tdil.struts.actions.AbstractAction;

public class LoginActionMobile extends AbstractAction {

	@Override
	protected ActionForward basicExecute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		try {
			LoginForm login = (LoginForm) form;
			WebsiteUser user = (WebsiteUser) login.executeLogin();
			HttpSession session = request.getSession();
			session.setAttribute("user", user); // TODO hacer esto en login por fb y twitter
			return mapping.findForward("continue");
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
