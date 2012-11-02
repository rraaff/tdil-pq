package com.tdil.struts.actions;

import java.sql.SQLException;

import com.tdil.struts.TransactionalAction;
import com.tdil.struts.ValidationException;
import com.tdil.struts.forms.SearchForm;

public class SearchTransactionalAction implements TransactionalAction {
	private SearchForm searchForm;
	
	public SearchTransactionalAction(SearchForm searchForm) {
		this.searchForm = searchForm;
	}
	
	@Override
	public void executeInTransaction() throws SQLException, ValidationException {
		this.searchForm.search();
	}
}