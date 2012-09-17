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
import com.tdil.struts.ValidationException;
import com.tdil.struts.forms.SearchForm;

public class SearchAction extends AbstractAction  {

	@Override
	protected ActionForward basicExecute(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		TransactionProvider.executeInTransaction(new SearchTransactionalAction((SearchForm)form));
		return mapping.findForward("continue");
	}

	private static Logger getLog() {
		return LoggerProvider.getLogger(SearchAction.class);
	}
	
	class SearchTransactionalAction implements TransactionalAction {
		private SearchForm searchForm;
		
		public SearchTransactionalAction(SearchForm searchForm) {
			this.searchForm = searchForm;
		}
		
		@Override
		public void executeInTransaction() throws SQLException, ValidationException {
			this.searchForm.search();
		}
	}
	
}
