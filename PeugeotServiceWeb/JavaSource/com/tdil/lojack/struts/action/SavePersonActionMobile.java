package com.tdil.lojack.struts.action;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.tdil.log4j.LoggerProvider;
import com.tdil.lojack.struts.forms.RegisterForm;
import com.tdil.lojack.utils.WebsiteUser;
import com.tdil.struts.ValidationError;
import com.tdil.struts.actions.SaveAction;
import com.tdil.struts.forms.AbstractForm;
import com.tdil.validations.ValidationErrors;

public class SavePersonActionMobile extends SaveAction {
	
	private static final org.apache.log4j.Logger LOG = LoggerProvider.getLogger(SavePersonActionMobile.class);

	public ActionForward validateAndSave(final AbstractForm form, HttpServletRequest request, ActionMapping mapping) {
		ValidationError error = form.validate();
		if(error.hasError()) {
			return redirectToFailure(error, request, mapping);
		} else {
			try {
				RegisterForm aForm = (RegisterForm)form;
				WebsiteUser user = aForm.update();
				WebsiteUser sessionUser = (WebsiteUser)request.getSession().getAttribute("user");
				sessionUser.setName(user.getName());
				aForm.reset();
				aForm.init();
			} catch (Exception ex) {
				LOG.error(ex.getMessage(), ex);
				ValidationError exError = new ValidationError(ValidationErrors.GENERAL_ERROR_TRY_AGAIN);
				return redirectToFailure(exError, request, mapping);
			}
		}
		return this.getSucessForward(form, mapping);
	}
}
