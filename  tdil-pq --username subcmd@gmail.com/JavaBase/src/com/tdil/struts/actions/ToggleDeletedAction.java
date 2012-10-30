package com.tdil.struts.actions;

import java.sql.SQLException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.tdil.ibatis.TransactionProvider;
import com.tdil.struts.TransactionalAction;
import com.tdil.struts.ValidationError;
import com.tdil.struts.ValidationException;
import com.tdil.struts.forms.ToggleDeletedFlagForm;

public class ToggleDeletedAction extends AbstractAction {

	@Override
	protected ActionForward basicExecute(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		
		final ToggleDeletedFlagForm abstractForm = (ToggleDeletedFlagForm) form;
		final int userId = Integer.parseInt(request.getParameter("id"));
		abstractForm.initForDeleteWith(userId);
		ValidationError validationError = new ValidationError();
		abstractForm.validateForToggleDeletedFlag(validationError);
		if(!validationError.hasError()) {
			TransactionProvider.executeInTransaction(new TransactionalAction() {
				public void executeInTransaction() throws SQLException, ValidationException {
					abstractForm.toggleDeletedFlag();
				}
			});
			TransactionProvider.executeInTransaction(new TransactionalAction() {
				public void executeInTransaction() throws SQLException, ValidationException {
					abstractForm.resetAfterDelete();
				}
			});
			return mapping.findForward("continue");
		}
		return mapping.findForward("failure");
	}

}
