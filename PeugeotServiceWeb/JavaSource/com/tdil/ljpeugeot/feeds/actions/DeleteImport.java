package com.tdil.ljpeugeot.feeds.actions;

import java.sql.SQLException;

import com.tdil.ljpeugeot.daomanager.DAOManager;
import com.tdil.struts.TransactionalAction;

public final class DeleteImport implements TransactionalAction {
	private String id;
	
	public DeleteImport(String id) {
		super();
		this.id = id;
	}
	public void executeInTransaction() throws SQLException {
		Integer id = Integer.parseInt(this.id);
		DAOManager.getDataImportDAO().deleteDataImportByPrimaryKey(id);
	}
}