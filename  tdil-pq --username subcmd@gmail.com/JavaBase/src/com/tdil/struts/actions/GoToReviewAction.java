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
import com.tdil.struts.forms.ApproveDisapproveForm;

public class GoToReviewAction extends AbstractAction {

	private static final class InitForReview implements TransactionalAction {
		private final AbstractForm abstractForm;

		private InitForReview(AbstractForm abstractForm) {
			this.abstractForm = abstractForm;
		}

		public void executeInTransaction() throws SQLException, ValidationException {
			abstractForm.reset();
			((ApproveDisapproveForm)abstractForm).initForReview();
		}
	}

	@Override
	protected ActionForward basicExecute(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		final AbstractForm abstractForm = (AbstractForm) form;
		TransactionProvider.executeInTransaction(new InitForReview(abstractForm));

		return mapping.findForward("continue");
	}

}
