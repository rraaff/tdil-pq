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
import com.tdil.tuafesta.struts.forms.VerifyProfesionalForm;
import com.tdil.validations.ValidationErrors;

public class DisapproveProfesionalAction extends AbstractAction {

	private static final Logger LOG = LoggerProvider.getLogger(DisapproveProfesionalAction.class);
	
	@Override
	protected ActionForward basicExecute(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		final VerifyProfesionalForm abstractForm = (VerifyProfesionalForm) form;
		ValidationError error = abstractForm.validateForDisapprove();
		if(error.hasError()) {
			return redirectToFailure(error, request, mapping);
		} else {
			try {
				TransactionProvider.executeInTransaction(new TransactionalAction() {
					public void executeInTransaction() throws SQLException, ValidationException {
						abstractForm.disapprove();
					}
				});
			} catch (Exception ex) {
				LOG.error(ex.getMessage(), ex);
				ValidationError exError = new ValidationError(ValidationErrors.GENERAL_ERROR_TRY_AGAIN);
				return redirectToFailure(exError, request, mapping);
			}
		}
		return mapping.findForward("continue");
	}

}
