package com.tdil.lojack.vlu;

import java.io.FileReader;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import org.apache.log4j.Logger;
import org.supercsv.cellprocessor.ift.CellProcessor;
import org.supercsv.io.CsvBeanReader;
import org.supercsv.prefs.CsvPreference;

import com.tdil.log4j.LoggerProvider;
import com.tdil.lojack.daomanager.DAOManager;
import com.tdil.lojack.model.VLUImport;
import com.tdil.pool.DatasourceManager;
import com.tdil.utils.SystemPropertyCache;

public class DeleteRepairedDomainsThread extends Thread {
	
	private VLUImport imp;

	public DeleteRepairedDomainsThread(VLUImport importVlu) {
		super();
		this.imp = importVlu;
	}
	
	@Override
	public void run() {
		processImport(this.imp);
		
	}

	public static void processImport(VLUImport imp) {
		Connection conn = null;
		boolean error = false;
		try {
			VLUImportThread.changeStatus(imp.getId(), "PROCESSING");
			conn = DatasourceManager.getConnection();
			conn.setAutoCommit(false);
			PreparedStatement deleteOldVlu = conn.prepareStatement("delete from " + DAOManager.getVLU_DATATableName()+ " where domain = ?");
			PreparedStatement incrementImport = conn.prepareStatement("update " + DAOManager.getVLU_IMPORTTableName()+ " set processed = processed + 1 where id = ?");

			incrementImport.setInt(1, imp.getId());
			CsvBeanReader beanReader = null;
			try {
				beanReader = new CsvBeanReader(new FileReader(SystemPropertyCache.getTempPath() + "/" + imp.getFilename()),CsvPreference.STANDARD_PREFERENCE);

				// the header elements are used to map the values to the bean (names
				// must match)
				final String[] header = beanReader.getHeader(true);
				final CellProcessor[] processors = getProcessors();

				DeleteRepairedDomainRecord importRecord;
				int commitCount = 1;
				while ((importRecord = beanReader.read(DeleteRepairedDomainRecord.class, header, processors)) != null) {
					deleteOldVlu.clearParameters();
					deleteOldVlu.setString(1, importRecord.getDominio());
					deleteOldVlu.addBatch();
					incrementImport.executeUpdate();
					if (commitCount >= 100) {
						deleteOldVlu.executeBatch();
						conn.commit();
						commitCount = 0;
					}
					commitCount++;
				}
				//changeStatus(imp.getId(), "FINISHED");
			} finally {
				if (beanReader != null) {
					beanReader.close();
				}
			}
			deleteOldVlu.executeBatch();
			conn.commit();
		} catch (Exception e) {
			try {
				conn.commit();
			} catch (SQLException e1) {
				getLog().error(e1.getMessage(), e1);
			}
			getLog().error(e.getMessage(), e);
			VLUImportThread.changeStatus(imp.getId(), "ERROR");
			error = true;
		} finally {
			if (conn != null) {
				try {
					conn.close();
				} catch (SQLException e) {
					getLog().error(e.getMessage(), e);
				}
			}
			if (!error) {
				VLUImportThread.changeStatus(imp.getId(), "FINISHED");
			}
		}
	}
	
	private static CellProcessor[] getProcessors() {

		final CellProcessor[] processors = new CellProcessor[] { null // domain
		};

		return processors;
	}
	
	private static Logger getLog() {
		return LoggerProvider.getLogger(DeleteRepairedDomainsThread.class);
	}

	public VLUImport getImp() {
		return imp;
	}

	public void setImp(VLUImport importVlu) {
		this.imp = importVlu;
	}
	
}
