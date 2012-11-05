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
import com.tdil.struts.resources.ApplicationResources;
import com.tdil.tuafesta.struts.forms.ReviewProfesionalForm;
import com.tdil.validations.ValidationErrors;

public class ApproveProfesionalBusinessDataChangeAction extends AbstractAction {
	
	private static final Logger LOG = LoggerProvider.getLogger(ApproveProfesionalBusinessDataChangeAction.class);

	@Override
	protected ActionForward basicExecute(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		final ReviewProfesionalForm approveDisapproveForm = (ReviewProfesionalForm) form;
		if (approveDisapproveForm.getOperation().equals(ApplicationResources.getMessage("Approve"))) {
			try {
				TransactionProvider.executeInTransaction(new TransactionalAction() {
					public void executeInTransaction() throws SQLException, ValidationException {
						approveDisapproveForm.approveBusinessDataChange();
					}
				});
				TransactionProvider.executeInTransaction(new TransactionalAction() {
					public void executeInTransaction() throws SQLException, ValidationException {
						approveDisapproveForm.initWith(approveDisapproveForm.getProfesional().getId());
					}
				});
			} catch (Exception ex) {
				LOG.error(ex.getMessage(), ex);
				ValidationError exError = new ValidationError(ValidationErrors.GENERAL_ERROR_TRY_AGAIN);
				return redirectToFailure(exError, request, mapping);
			}
		} else {
			if (approveDisapproveForm.getOperation().equals(ApplicationResources.getMessage("Block"))) {
				try {
					TransactionProvider.executeInTransaction(new TransactionalAction() {
						public void executeInTransaction() throws SQLException, ValidationException {
							approveDisapproveForm.blockProfesional();
						}
					});
				} catch (Exception ex) {
					LOG.error(ex.getMessage(), ex);
					ValidationError exError = new ValidationError(ValidationErrors.GENERAL_ERROR_TRY_AGAIN);
					return redirectToFailure(exError, request, mapping);
				}
			}
		}
		return mapping.findForward("continue");
	}
	

}
