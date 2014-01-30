package com.tdil.ljpeugeot.struts.action;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.tdil.ljpeugeot.struts.forms.RegisterForm;
import com.tdil.ljpeugeot.utils.WebsiteUser;
import com.tdil.log4j.LoggerProvider;
import com.tdil.struts.ValidationError;
import com.tdil.struts.ValidationException;
import com.tdil.struts.actions.SaveAction;
import com.tdil.struts.forms.AbstractForm;
import com.tdil.validations.ValidationErrors;

public class RegisterActionMobile extends SaveAction {
	
	private static final org.apache.log4j.Logger LOG = LoggerProvider.getLogger(RegisterActionMobile.class);

	public ActionForward validateAndSave(final AbstractForm aform, HttpServletRequest request, ActionMapping mapping) {
		RegisterForm form = (RegisterForm)aform;
		ValidationError error = form.validate();
		if(error.hasError()) {
			return redirectToFailure(error, request, mapping);
		} else {
			try {
				WebsiteUser user = form.register();
				request.getSession().setAttribute("user", user);
				form.reset();
				form.init();
			} catch (ValidationException ex) {
				return redirectToFailure(ex.getError(), request, mapping);
			} catch (Exception ex) {
				LOG.error(ex.getMessage(), ex);
				ValidationError exError = new ValidationError(ValidationErrors.GENERAL_ERROR_TRY_AGAIN);
				return redirectToFailure(exError, request, mapping);
			}
		}
		return this.getSucessForward(form, mapping);
	}
}
