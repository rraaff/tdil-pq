package com.tdil.ljpeugeot.feeds.actions;

import java.sql.SQLException;
import java.util.Date;

import com.tdil.ljpeugeot.daomanager.DAOManager;
import com.tdil.ljpeugeot.model.DataImport;
import com.tdil.struts.TransactionalAction;

public final class ChangeStatus implements TransactionalAction {
	private int id;
	private String status;
	private Date end;
	
	public ChangeStatus(int id, String status) {
		super();
		this.id = id;
		this.status = status;
	}

	public void executeInTransaction() throws SQLException {
		DataImport vluImport = DAOManager.getDataImportDAO().selectDataImportByPrimaryKey(this.id);
		vluImport.setStatus(this.status);
		if (vluImport.getStarttime() == null) {
			vluImport.setStarttime(new Date());
		}
		vluImport.setEndtime(new Date());
		DAOManager.getDataImportDAO().updateDataImportByPrimaryKey(vluImport);
	}
}