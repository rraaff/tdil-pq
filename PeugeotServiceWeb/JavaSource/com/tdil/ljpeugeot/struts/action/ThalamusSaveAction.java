package com.tdil.ljpeugeot.struts.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessages;

import com.tdil.log4j.LoggerProvider;
import com.tdil.struts.ValidationError;
import com.tdil.struts.ValidationException;
import com.tdil.struts.actions.AbstractAction;
import com.tdil.struts.forms.AbstractForm;
import com.tdil.struts.resources.ApplicationResources;
import com.tdil.validations.ValidationErrors;

public class ThalamusSaveAction extends AbstractAction {

	@Override
	protected ActionForward basicExecute(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		AbstractForm aForm = (AbstractForm)form;
		if (com.tdil.utils.StringUtils.equalsUnescaped(aForm.getOperation(), ApplicationResources.getMessage("reset"))) {
			aForm.reset();
			return mapping.findForward("continue");	
		} else {
			return this.validateAndSave(aForm, request, mapping);
		}
	}

	public ActionForward validateAndSave(final AbstractForm form, HttpServletRequest request, ActionMapping mapping) {
		ValidationError error = form.validate();
		if(error.hasError()) {
			return redirectToFailure(error, request, mapping);
		} else {
			try {
				form.save();
				form.reset();
				form.init();
			} catch (ValidationException e) {
				return redirectToFailure(e, request, mapping);
			} catch (Exception ex) {
				getLog().error(ex.getMessage(), ex);
				ValidationError exError = new ValidationError(ValidationErrors.GENERAL_ERROR_TRY_AGAIN);
				return redirectToFailure(exError, request, mapping);
			}
		}
		return mapping.findForward("continue");
	}
	
	protected ActionForward redirectToFailure(ValidationException ex, HttpServletRequest request, ActionMapping mapping) {
/*		ValidationError error1 = ex.getError();
		HashMap<String, Object> result = new HashMap<String, Object>();
		result.put("result", "ERR");
		if (!StringUtils.isEmpty(error1.getGeneralError())) {
			result.put("general", error1.getGeneralError());
		}
		for (Map.Entry<String, String> fieldErr : error1.getFieldErrors().entrySet()) {
			String keyToStore = fieldErr.getKey();
			String err = ApplicationResources.getMessage(fieldErr.getValue());
			if (StringUtils.isEmpty(err)) {
				result.put(keyToStore, fieldErr.getValue());
			} else {
				result.put(keyToStore, err);
			}
		}*/
		
		ActionMessages msg = ex.asMessages();
		if (msg != null) {
			request.setAttribute("hasError", "true");
			addErrors(request, msg);
		}
		ActionMessages errors = ex.getError().asActionsErrors();
		if (errors != null) {
			addErrors(request, errors);	
		}
		return mapping.findForward("failure");
	}
	
	private static Logger getLog() {
		return LoggerProvider.getLogger(ThalamusSaveAction.class);
	}
}
