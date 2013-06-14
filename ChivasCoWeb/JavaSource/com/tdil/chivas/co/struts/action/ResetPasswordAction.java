package com.tdil.chivas.co.struts.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.tdil.chivas.co.struts.forms.ResetPasswordForm;
import com.tdil.chivas.co.utils.WebsiteUser;
import com.tdil.log4j.LoggerProvider;
import com.tdil.struts.ValidationError;
import com.tdil.struts.ValidationException;
import com.tdil.struts.actions.AbstractAction;
import com.tdil.validations.ValidationErrors;

public class ResetPasswordAction extends AbstractAction {

	@Override
	protected ActionForward basicExecute(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		ResetPasswordForm aForm = (ResetPasswordForm)form;
		ValidationError error = aForm.validate();
		if(error.hasError()) {
			return redirectToFailure(error, request, mapping);
		} else {
			try {
				WebsiteUser user = aForm.resetPassword();
				request.getSession().setAttribute("user", user);
				return mapping.findForward("continue");
			} catch (ValidationException ex) {
				return redirectToFailure(ex.getError(), request, mapping);
			} catch (Exception ex) {
				getLog().error(ex.getMessage(), ex);
				ValidationError exError = new ValidationError(ValidationErrors.GENERAL_ERROR_TRY_AGAIN);
				return redirectToFailure(exError, request, mapping);
			}
		}
	}

	private static Logger getLog() {
		return LoggerProvider.getLogger(ResetPasswordAction.class);
	}
}
