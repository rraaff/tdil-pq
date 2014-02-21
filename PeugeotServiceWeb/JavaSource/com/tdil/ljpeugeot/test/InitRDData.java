package com.tdil.ljpeugeot.test;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.SQLException;

import junit.framework.TestCase;

import com.tdil.ljpeugeot.daomanager.DAOManager;
import com.tdil.ljpeugeot.feeds.ImportRunnable;
import com.tdil.ljpeugeot.feeds.dealer.DealerImportSpec;
import com.tdil.ljpeugeot.feeds.model.ModelImportSpec;
import com.tdil.ljpeugeot.model.DataImport;
import com.tdil.struts.TransactionalAction;
import com.tdil.struts.ValidationException;
import com.tdil.subsystem.generic.GenericTransactionExecutionService;
import com.tdil.utils.SystemPropertyCache;

public class InitRDData extends TestCase {
	
	private static String OS = System.getProperty("os.name").toLowerCase();
	
	public void testGenerate() throws SQLException, ValidationException {
		GenericTransactionExecutionService.getInstance().execute(new TransactionalAction() {
			@Override
			public void executeInTransaction() throws SQLException, ValidationException {
				
					String oldTemp = SystemPropertyCache.getTempPath();
					if(OS.indexOf("win") >= 0) {
						SystemPropertyCache.put(com.tdil.utils.SystemPropertyCache.TEMP_PATH , "C:/Thalamus/Workspace/PeugeotServiceWeb/JavaSource/com/tdil/ljpeugeot/feeds/model");
					} else {
					SystemPropertyCache.put(com.tdil.utils.SystemPropertyCache.TEMP_PATH , "/home/mgodoy/icarus/workspace/thalamus/PeugeotServiceWeb/JavaSource/com/tdil/ljpeugeot/feeds/model");
					}
					DataImport dataImport = new DataImport();
					dataImport.setProcessed(0);
					dataImport.setErrors(0);
					dataImport.setStatus("PENDING");
					dataImport.setType(ModelImportSpec.TYPE);
					dataImport.setFilename("model.csv");
					int id = DAOManager.getDataImportDAO().insertDataImport(dataImport);
					dataImport.setId(id);
					try {
						new ImportRunnable(dataImport, new ModelImportSpec()).processImport();
					} catch (FileNotFoundException e) {
						new RuntimeException(e);
					} catch (IOException e) {
						new RuntimeException(e);
					}
					dataImport.setProcessed(0);
					dataImport.setErrors(0);
					dataImport.setStatus("PENDING");
					dataImport.setType(DealerImportSpec.TYPE);
					dataImport.setFilename("dealer.csv");
					id = DAOManager.getDataImportDAO().insertDataImport(dataImport);
					dataImport.setId(id);
					if(OS.indexOf("win") >= 0) {
						SystemPropertyCache.put(com.tdil.utils.SystemPropertyCache.TEMP_PATH , "C:/Thalamus/Workspace/PeugeotServiceWeb/JavaSource/com/tdil/ljpeugeot/feeds/dealer");
					} else {
						SystemPropertyCache.put(com.tdil.utils.SystemPropertyCache.TEMP_PATH , "/home/mgodoy/icarus/workspace/thalamus/PeugeotServiceWeb/JavaSource/com/tdil/ljpeugeot/feeds/dealer");
					}
					try {
						new ImportRunnable(dataImport, new DealerImportSpec()).processImport();
					} catch (FileNotFoundException e) {
						new RuntimeException(e);
					} catch (IOException e) {
						new RuntimeException(e);
					}
				}
		});
	}
	
}
