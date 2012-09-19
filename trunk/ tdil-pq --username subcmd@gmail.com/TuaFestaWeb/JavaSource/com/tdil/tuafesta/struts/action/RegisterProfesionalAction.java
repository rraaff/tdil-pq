package com.tdil.tuafesta.struts.action;

import java.sql.SQLException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.tdil.ibatis.TransactionProvider;
import com.tdil.log4j.LoggerProvider;
import com.tdil.struts.TransactionalAction;
import com.tdil.struts.ValidationError;
import com.tdil.struts.ValidationException;
import com.tdil.struts.actions.AbstractAction;
import com.tdil.struts.forms.AbstractForm;
import com.tdil.struts.resources.ApplicationResources;
import com.tdil.tuafesta.struts.forms.ProfesionalForm;
import com.tdil.tuafesta.struts.forms.WebsiteLoginForm;
import com.tdil.validations.ValidationErrors;

public class RegisterProfesionalAction extends AbstractAction {

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
				TransactionProvider.executeInTransaction(new TransactionalAction() {
					public void executeInTransaction() throws SQLException, ValidationException {
						form.save();
					}
				});
			} catch (Exception ex) {
				getLog().error(ex.getMessage(), ex);
				ValidationError exError = new ValidationError(ValidationErrors.GENERAL_ERROR_TRY_AGAIN);
				return redirectToFailure(exError, request, mapping);
			}
		}
		ProfesionalForm clientForm = (ProfesionalForm)form;
		if (clientForm.isFacebookRegister()) {
			request.getSession().setAttribute("user", WebsiteLoginForm.getWebsiteUserFor(clientForm.getLogged()));
			return mapping.findForward("logged");
		} else {
			return this.getSucessForward(form, mapping);
		}
	}
	
	protected ActionForward getSucessForward(AbstractForm form, ActionMapping mapping) {
		return mapping.findForward("continue");
	}

	private static Logger getLog() {
		return LoggerProvider.getLogger(RegisterProfesionalAction.class);
	}
}
