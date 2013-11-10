package com.tdil.struts.actions;

import java.sql.SQLException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.tdil.struts.TransactionalAction;
import com.tdil.struts.ValidationError;
import com.tdil.struts.ValidationException;
import com.tdil.struts.forms.PhysicalDeleteForm;
import com.tdil.subsystem.generic.GenericTransactionExecutionService;

public class PhysicalDeleteAction extends AbstractAction {

	private static final class ResetAfterToggle implements TransactionalAction {
		private final PhysicalDeleteForm abstractForm;

		private ResetAfterToggle(PhysicalDeleteForm abstractForm) {
			this.abstractForm = abstractForm;
		}

		public void executeInTransaction() throws SQLException, ValidationException {
			abstractForm.resetAfterDelete();
		}
	}

	private static final class ToggleDeletedFlag implements TransactionalAction {
		private final PhysicalDeleteForm abstractForm;

		private ToggleDeletedFlag(PhysicalDeleteForm abstractForm) {
			this.abstractForm = abstractForm;
		}

		public void executeInTransaction() throws SQLException, ValidationException {
			abstractForm.physicalDelete();
		}
	}

	@Override
	protected ActionForward basicExecute(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		
		final PhysicalDeleteForm abstractForm = (PhysicalDeleteForm) form;
		final int userId = Integer.parseInt(request.getParameter("id"));
		abstractForm.initForDeleteWith(userId);
		ValidationError validationError = new ValidationError();
		abstractForm.validateForToggleDeletedFlag(validationError);
		if(!validationError.hasError()) {
			GenericTransactionExecutionService.getInstance().execute(new ToggleDeletedFlag(abstractForm));
			GenericTransactionExecutionService.getInstance().execute(new ResetAfterToggle(abstractForm));
			return mapping.findForward("continue");
		}
		return redirectToFailure(validationError, request, mapping);
	}

}
