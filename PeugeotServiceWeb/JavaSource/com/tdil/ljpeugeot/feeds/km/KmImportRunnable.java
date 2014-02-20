package com.tdil.ljpeugeot.feeds.km;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import com.tdil.ljpeugeot.daomanager.DAOManager;
import com.tdil.ljpeugeot.feeds.ImportRunnable;
import com.tdil.ljpeugeot.feeds.ImportSpec;
import com.tdil.ljpeugeot.model.DataImport;
import com.tdil.ljpeugeot.model.KmData;
import com.tdil.struts.TransactionalActionWithResult;
import com.tdil.struts.ValidationException;
import com.tdil.subsystem.generic.GenericTransactionExecutionService;

public class KmImportRunnable extends ImportRunnable {

	public KmImportRunnable(DataImport dataImport, ImportSpec importSpec) {
		super(dataImport, importSpec);
	}

	
	public void processImport() throws FileNotFoundException, IOException {
		changeStatus(getDataImport().getId(), PROCESSING);
		// Select ordenado por id, pidiendo id mayor a ultimo id, top x
		int startId = -1;
		List<KmData> importWindow = getKmDataToProcess(startId);
		while (!importWindow.isEmpty()) {
			for (KmData kmData : importWindow) {
				try {
					getImportSpec().processRow(kmData, getDataImport());
					incrementProcess(getDataImport());
				} catch (Exception e) {
					getLog().error(e.getMessage(), e);
					incrementError(getDataImport());
				}
			}
		}
	}


	private List<KmData> getKmDataToProcess(int startId) {
		try {
			return GenericTransactionExecutionService.getInstance().execute(new GetKmDataToProcess(startId));
		} catch (SQLException e) {
			getLog().error(e.getMessage(), e);
			return null;
		} catch (ValidationException e) {
			getLog().error(e.getMessage(), e);
			return null;
		} 
	}
	
	private static final class GetKmDataToProcess implements TransactionalActionWithResult<List<KmData>> {
		private int startId;
		public GetKmDataToProcess(int startId) {
			super();
			this.startId = startId;
		}
		public List<KmData> executeInTransaction() throws SQLException {
			return DAOManager.getKmDataDAO().selectKmDataToProcess(startId);
		}
	}
}
