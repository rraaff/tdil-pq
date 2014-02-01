package com.tdil.ljpeugeot.feeds;

import java.io.FileReader;
import java.sql.SQLException;

import org.apache.log4j.Logger;
import org.supercsv.cellprocessor.ift.CellProcessor;
import org.supercsv.io.CsvBeanReader;
import org.supercsv.prefs.CsvPreference;

import com.tdil.ljpeugeot.feeds.actions.ChangeStatus;
import com.tdil.ljpeugeot.feeds.actions.IncrementError;
import com.tdil.ljpeugeot.feeds.actions.IncrementProcessed;
import com.tdil.ljpeugeot.model.DataImport;
import com.tdil.log4j.LoggerProvider;
import com.tdil.struts.ValidationException;
import com.tdil.subsystem.generic.GenericTransactionExecutionService;
import com.tdil.utils.SystemPropertyCache;

public class ImportRunnable implements Runnable {

	private DataImport dataImport;
	private ImportSpec importSpec;
	
	public ImportRunnable(DataImport dataImport, ImportSpec importSpec) {
		super();
		this.dataImport = dataImport;
		this.importSpec = importSpec;
	}

	@Override
	public void run() {
		boolean error = false;
		try {
			changeStatus(dataImport.getId(), "PROCESSING");
			CsvBeanReader beanReader = null;
			try {
				beanReader = new CsvBeanReader(new FileReader(SystemPropertyCache.getTempPath() + "/" + dataImport.getFilename()),CsvPreference.STANDARD_PREFERENCE);

				// the header elements are used to map the values to the bean (names
				// must match)
				final String[] header = beanReader.getHeader(true);
				final CellProcessor[] processors = importSpec.getCellProcessor();

				Object importRecord;
				while ((importRecord = beanReader.read(importSpec.getRecordClass(), header, processors)) != null) {
					try {
						importSpec.processRow(importRecord, dataImport);
						incrementProcess(dataImport);
					} catch (Exception e) {
						getLog().error(e.getMessage(), e);
						incrementError(dataImport);
					}
				}
			} finally {
				if (beanReader != null) {
					beanReader.close();
				}
			}
		} catch (Exception e) {
			getLog().error(e.getMessage(), e);
			changeStatus(dataImport.getId(), "ERROR");
			error = true;
		} finally {
			if (!error) {
				try {
					importSpec.importFinished(dataImport);
				} catch (Exception e) {
					getLog().error(e.getMessage(), e);
				}
				changeStatus(dataImport.getId(), "FINISHED");
			}
		}

	}
	
	private void incrementProcess(DataImport dataImport2) {
		try {
			GenericTransactionExecutionService.getInstance().execute(new IncrementProcessed(dataImport2.getId()));
		} catch (SQLException e) {
			getLog().error(e.getMessage(), e);
		} catch (ValidationException e) {
			getLog().error(e.getMessage(), e);
		}
	}
	
	private void incrementError(DataImport dataImport2) {
		try {
			GenericTransactionExecutionService.getInstance().execute(new IncrementError(dataImport2.getId()));
		} catch (SQLException e) {
			getLog().error(e.getMessage(), e);
		} catch (ValidationException e) {
			getLog().error(e.getMessage(), e);
		}
	}

	public static void changeStatus(int id, String newStatus) {
		try {
			GenericTransactionExecutionService.getInstance().execute(new ChangeStatus(id, newStatus));
		} catch (SQLException e) {
			getLog().error(e.getMessage(), e);
		} catch (ValidationException e) {
			getLog().error(e.getMessage(), e);
		}
	}
	
	private static Logger getLog() {
		return LoggerProvider.getLogger(ImportRunnable.class);
	}

	public DataImport getDataImport() {
		return dataImport;
	}

	public void setDataImport(DataImport dataImport) {
		this.dataImport = dataImport;
	}

	public ImportSpec getImportSpec() {
		return importSpec;
	}

	public void setImportSpec(ImportSpec importSpec) {
		this.importSpec = importSpec;
	}

}
