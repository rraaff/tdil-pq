package com.tdil.lojack.struts.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessages;

import com.tdil.log4j.LoggerProvider;
import com.tdil.lojack.struts.forms.RequestResetPasswordForm;
import com.tdil.struts.ValidationError;
import com.tdil.struts.ValidationException;
import com.tdil.struts.actions.AbstractAction;

public class RequestResetPasswordActionMobile extends AbstractAction {

	@Override
	protected ActionForward basicExecute(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		RequestResetPasswordForm login = (RequestResetPasswordForm) form;
		try {
			int status = login.resetPassword();
			if (status == 201) {
				return mapping.findForward("continue");
			} else {
				return mapping.findForward("failure");
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
			return mapping.findForward("failure");
		}
	}

	private static Logger getLog() {
		return LoggerProvider.getLogger(RequestResetPasswordActionMobile.class);
	}
}