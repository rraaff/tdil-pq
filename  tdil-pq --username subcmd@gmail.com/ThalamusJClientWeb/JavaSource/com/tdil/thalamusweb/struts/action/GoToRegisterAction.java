package com.tdil.thalamusweb.struts.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.tdil.log4j.LoggerProvider;
import com.tdil.struts.ValidationError;
import com.tdil.struts.actions.AbstractAction;
import com.tdil.thalamusweb.struts.forms.RegisterForm;
import com.tdil.validations.ValidationErrors;

public class GoToRegisterAction extends AbstractAction {
	
	@Override
	protected ActionForward basicExecute(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		RegisterForm registerForm = (RegisterForm)form;
		try {
			registerForm.reset();
			registerForm.searchReferenceData();
			return mapping.findForward("continue");
		} catch (Exception ex) {
			getLog().error(ex.getMessage(), ex);
			ValidationError exError = new ValidationError(ValidationErrors.GENERAL_ERROR_TRY_AGAIN);
			return redirectToFailure(exError, request, mapping);
		}
	}
	
	private static Logger getLog() {
		return LoggerProvider.getLogger(GoToRegisterAction.class);
	}
	
}
