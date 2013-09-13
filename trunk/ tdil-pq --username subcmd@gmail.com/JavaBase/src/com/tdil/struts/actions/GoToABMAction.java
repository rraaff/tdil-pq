package com.tdil.struts.actions;

import java.sql.SQLException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.tdil.ibatis.TransactionProvider;
import com.tdil.struts.TransactionalAction;
import com.tdil.struts.ValidationException;
import com.tdil.struts.forms.AbstractForm;
import com.tdil.subsystem.generic.GenericTransactionExecutionService;

public class GoToABMAction extends AbstractAction {

	private static final class ResetAndInit implements TransactionalAction {
		private final AbstractForm abstractForm;

		private ResetAndInit(AbstractForm abstractForm) {
			this.abstractForm = abstractForm;
		}

		public void executeInTransaction() throws SQLException, ValidationException {
			abstractForm.reset();
			abstractForm.init();
		}
	}

	@Override
	protected ActionForward basicExecute(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		final AbstractForm abstractForm = (AbstractForm) form;
		GenericTransactionExecutionService.getInstance().execute(new ResetAndInit(abstractForm));
		return mapping.findForward("continue");
	}

}
