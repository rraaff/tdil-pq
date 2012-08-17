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
import com.tdil.struts.ValidationException;
import com.tdil.struts.actions.AbstractAction;
import com.tdil.tuafesta.struts.forms.ProfesionalSearchResultForm;

public class SearchProfesionalByGeoLevel4 extends AbstractAction {
	
	private static final Logger LOG = LoggerProvider.getLogger(SearchProfesionalByGeoLevel4.class);

	@Override
	protected ActionForward basicExecute(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		
		final ProfesionalSearchResultForm abstractForm = (ProfesionalSearchResultForm) form;
		final int userId = Integer.parseInt(request.getParameter("id"));
		TransactionProvider.executeInTransaction(new TransactionalAction() {
			public void executeInTransaction() throws SQLException, ValidationException {
				abstractForm.searchByGeoLevel4(userId);
			}
		});
		return mapping.findForward("continue");
	}

}
