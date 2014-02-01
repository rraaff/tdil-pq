package com.tdil.ljpeugeot.feeds.actions;

import java.sql.SQLException;

import com.tdil.ljpeugeot.daomanager.DAOManager;
import com.tdil.ljpeugeot.model.DataImport;
import com.tdil.struts.TransactionalAction;

public class IncrementError implements TransactionalAction {
	private int importId;
	
	public IncrementError(int importId) {
		super();
		this.importId = importId;
	}

	public void executeInTransaction() throws SQLException {
		DataImport vluImport = DAOManager.getDataImportDAO().selectDataImportByPrimaryKey(this.importId);
		vluImport.setProcessed(vluImport.getProcessed() + 1);
		vluImport.setErrors(vluImport.getErrors() + 1);
		DAOManager.getDataImportDAO().updateDataImportByPrimaryKey(vluImport);
	}
}