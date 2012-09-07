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
import com.tdil.struts.resources.ApplicationResources;
import com.tdil.thalamusweb.struts.forms.RegisterForm;
import com.tdil.thalamusweb.utils.WebsiteUser;
import com.tdil.validations.ValidationErrors;

public class ThalamusRegisterAction extends AbstractAction {

	@Override
	protected ActionForward basicExecute(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		RegisterForm aForm = (RegisterForm)form;
		if (com.tdil.utils.StringUtils.equalsUnescaped(aForm.getOperation(), ApplicationResources.getMessage("reset"))) {
			aForm.reset();
			return mapping.findForward("continue");	
		} else {
			return this.validateAndSave(aForm, request, mapping);
		}
	}

	public ActionForward validateAndSave(final RegisterForm form, HttpServletRequest request, ActionMapping mapping) {
		ValidationError error = form.validate();
		if(error.hasError()) {
			return redirectToFailure(error, request, mapping);
		} else {
			try {
				WebsiteUser user = form.register();
				request.getSession().setAttribute("user", user);
				form.reset();
				form.init();
			} catch (Exception ex) {
				getLog().error(ex.getMessage(), ex);
				ValidationError exError = new ValidationError(ValidationErrors.GENERAL_ERROR_TRY_AGAIN);
				return redirectToFailure(exError, request, mapping);
			}
		}
		return mapping.findForward("continue");
	}
	
	private static Logger getLog() {
		return LoggerProvider.getLogger(ThalamusRegisterAction.class);
	}
}
