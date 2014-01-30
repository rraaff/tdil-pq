package com.tdil.ljpeugeot.struts.action;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.tdil.ljpeugeot.utils.WebsiteUser;
import com.tdil.log4j.LoggerProvider;
import com.tdil.struts.ValidationError;
import com.tdil.struts.actions.SaveAction;
import com.tdil.struts.forms.AbstractForm;
import com.tdil.validations.ValidationErrors;

public class ContactActionMobile extends SaveAction {
	
	private static final org.apache.log4j.Logger LOG = LoggerProvider.getLogger(ContactActionMobile.class);

	public ActionForward validateAndSave(final AbstractForm form, HttpServletRequest request, ActionMapping mapping) {
		ValidationError error = form.validate();
		if(error.hasError()) {
			return redirectToFailure(error, request, mapping);
		} else {
			try {
				form.save();
			} catch (Exception ex) {
				LOG.error(ex.getMessage(), ex);
				ValidationError exError = new ValidationError(ValidationErrors.GENERAL_ERROR_TRY_AGAIN);
				return redirectToFailure(exError, request, mapping);
			}
		}
		WebsiteUser sessionUser = (WebsiteUser)request.getSession().getAttribute("user");
		if (sessionUser == null || !sessionUser.isLogged()) {
			return mapping.findForward("index");
		} else {
			return this.getSucessForward(form, mapping);
		}
	}
}
