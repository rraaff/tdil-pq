package com.tdil.ljpeugeot.feeds.actions;

import java.sql.SQLException;

import com.tdil.ljpeugeot.daomanager.DAOManager;
import com.tdil.ljpeugeot.model.DataImport;
import com.tdil.struts.TransactionalActionWithResult;

public final class InsertImport implements TransactionalActionWithResult<DataImport> {
	private String filename;
	private String type;
	
	public InsertImport(String filename,String type) {
		super();
		this.filename = filename;
		this.type = type;
	}
	public DataImport executeInTransaction() throws SQLException {
		DataImport vluImport = new DataImport();
		vluImport.setFilename(this.filename);
		vluImport.setType(type);
		vluImport.setStatus("PENDING");
		vluImport.setProcessed(0);
		vluImport.setErrors(0);
		int result = DAOManager.getDataImportDAO().insertDataImport(vluImport);
		vluImport.setId(result);
		return vluImport;
	}
}