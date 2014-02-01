package com.tdil.ljpeugeot.feeds.actions;

import java.sql.SQLException;
import java.util.List;

import com.tdil.ljpeugeot.daomanager.DAOManager;
import com.tdil.ljpeugeot.model.DataImport;
import com.tdil.ljpeugeot.model.DataImportExample;
import com.tdil.struts.TransactionalActionWithResult;

public final class GetDataImport implements TransactionalActionWithResult<List<DataImport>> {
	
	private String type;
	
	public GetDataImport(String type) {
		super();
		this.type = type;
	}
	public List<DataImport> executeInTransaction() throws SQLException {
		DataImportExample importExample = new DataImportExample();
		importExample.createCriteria().andTypeEqualTo(this.type);
		importExample.setOrderByClause("id");
		return DAOManager.getDataImportDAO().selectDataImportByExample(importExample);
	}
}