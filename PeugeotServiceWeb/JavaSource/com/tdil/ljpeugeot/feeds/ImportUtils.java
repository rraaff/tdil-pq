package com.tdil.ljpeugeot.feeds;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import com.tdil.ljpeugeot.feeds.actions.DeleteImport;
import com.tdil.ljpeugeot.feeds.actions.GetDataImport;
import com.tdil.ljpeugeot.feeds.actions.InsertImport;
import com.tdil.ljpeugeot.model.DataImport;
import com.tdil.log4j.LoggerProvider;
import com.tdil.struts.ValidationException;
import com.tdil.subsystem.generic.GenericTransactionExecutionService;

public class ImportUtils {
	
	public static boolean registerImport(String fileName, ImportSpec importSpec) {
		try {
			DataImport result = GenericTransactionExecutionService.getInstance().execute(new InsertImport(fileName, importSpec.getType()));
			new Thread(new ImportRunnable(result, importSpec)).start();
			return true;
		} catch (SQLException e) {
			getLog().error(e.getMessage(), e);
			return false;
		} catch (ValidationException e) {
			getLog().error(e.getMessage(), e);
			return false;
		}
	}
	public static void deleteImport(String id) {
		try {
			GenericTransactionExecutionService.getInstance().execute(new DeleteImport(id));
		} catch (SQLException e) {
			getLog().error(e.getMessage(), e);
		} catch (ValidationException e) {
			getLog().error(e.getMessage(), e);
		}
	}

	public static List<DataImport> getImports(String type) {
		try {
			return GenericTransactionExecutionService.getInstance().execute(new GetDataImport(type));
		} catch (SQLException e) {
			getLog().error(e.getMessage(), e);
			return new ArrayList<DataImport>();
		} catch (ValidationException e) {
			getLog().error(e.getMessage(), e);
			return new ArrayList<DataImport>();
		}
	}
	
	private static Logger getLog() {
		return LoggerProvider.getLogger(ImportUtils.class);
	}
	
}
