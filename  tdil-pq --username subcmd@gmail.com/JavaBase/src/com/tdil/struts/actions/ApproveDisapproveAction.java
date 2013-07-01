package com.tdil.struts.actions;

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
import com.tdil.struts.forms.ApproveDisapproveForm;
import com.tdil.struts.resources.ApplicationResources;
import com.tdil.validations.ValidationErrors;

public class ApproveDisapproveAction extends AbstractAction {

	private static final class Dissaprove implements TransactionalAction {
		private final ApproveDisapproveForm approveDisapproveForm;

		private Dissaprove(ApproveDisapproveForm approveDisapproveForm) {
			this.approveDisapproveForm = approveDisapproveForm;
		}

		public void executeInTransaction() throws SQLException, ValidationException {
			approveDisapproveForm.disapprove();
		}
	}

	private static final class Approve implements TransactionalAction {
		private final ApproveDisapproveForm approveDisapproveForm;

		private Approve(ApproveDisapproveForm approveDisapproveForm) {
			this.approveDisapproveForm = approveDisapproveForm;
		}

		public void executeInTransaction() throws SQLException, ValidationException {
			approveDisapproveForm.approve();
		}
	}

	@Override
	protected ActionForward basicExecute(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		final ApproveDisapproveForm approveDisapproveForm = (ApproveDisapproveForm)form;
		if (approveDisapproveForm.getOperation().equals(ApplicationResources.getMessage("approve"))) {
			ValidationError error = approveDisapproveForm.validate();
			if(error.hasError()) {
				return redirectToFailure(error, request, mapping);
			} else {
				try {
					TransactionProvider.executeInTransaction(new Approve(approveDisapproveForm));
					approveDisapproveForm.postApprove();
				} catch (Exception ex) {
					getLog().error(ex.getMessage(), ex);
					ValidationError exError = new ValidationError(ValidationErrors.GENERAL_ERROR_TRY_AGAIN);
					return redirectToFailure(exError, request, mapping);
				}
			}
		} else {
			try {
				TransactionProvider.executeInTransaction(new Dissaprove(approveDisapproveForm));
				approveDisapproveForm.postDisapprove();
			} catch (Exception ex) {
				getLog().error(ex.getMessage(), ex);
				ValidationError exError = new ValidationError(ValidationErrors.GENERAL_ERROR_TRY_AGAIN);
				return redirectToFailure(exError, request, mapping);
			}
		}
		return mapping.findForward("continue");
	}

	private static Logger getLog() {
		return LoggerProvider.getLogger(ApproveDisapproveAction.class);
	}
}
