package com.tdil.tuafesta.struts.action;

import java.sql.SQLException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.tdil.ibatis.TransactionProvider;
import com.tdil.struts.TransactionalAction;
import com.tdil.struts.ValidationException;
import com.tdil.struts.actions.AbstractAction;
import com.tdil.tuafesta.struts.forms.ReviewProfesionalForm;

public class ReviewSellAction extends AbstractAction {

	@Override
	protected ActionForward basicExecute(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		
		final ReviewProfesionalForm abstractForm = (ReviewProfesionalForm) form;
		final int type = Integer.parseInt(request.getParameter("type"));
		final int id = Integer.parseInt(request.getParameter("id"));
		TransactionProvider.executeInTransaction(new TransactionalAction() {
			public void executeInTransaction() throws SQLException, ValidationException {
				abstractForm.initWith(type, id);
			}
		});

		return mapping.findForward("continue");
	}

}
