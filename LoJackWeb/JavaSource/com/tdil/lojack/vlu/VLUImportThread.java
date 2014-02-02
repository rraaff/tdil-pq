package com.tdil.lojack.vlu;

import java.io.FileReader;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.supercsv.cellprocessor.ift.CellProcessor;
import org.supercsv.io.CsvBeanReader;
import org.supercsv.prefs.CsvPreference;

import com.tdil.ibatis.IBatisManager;
import com.tdil.ibatis.TransactionProvider;
import com.tdil.log4j.LoggerProvider;
import com.tdil.lojack.daomanager.DAOManager;
import com.tdil.lojack.model.VLUData;
import com.tdil.lojack.model.VLUDataExample;
import com.tdil.lojack.model.VLUImport;
import com.tdil.lojack.model.VLUImportExample;
import com.tdil.pool.DatasourceManager;
import com.tdil.struts.TransactionalAction;
import com.tdil.struts.TransactionalActionWithResult;
import com.tdil.struts.ValidationException;
import com.tdil.utils.SystemPropertyCache;

public class VLUImportThread extends Thread {
	
	private static int startHour = 2;
	private static int startMinutes = 0;
	
	private static int endHour = 6;
	private static int endMinutes  = 0;

	private static final class GetVLUImportPending implements TransactionalActionWithResult<List<VLUImport>> {
		public GetVLUImportPending() {
			super();
		}
		public List<VLUImport> executeInTransaction() throws SQLException {
			VLUImportExample example = new VLUImportExample();
			example.createCriteria().andStatusEqualTo("PENDING");
			example.setOrderByClause("id");
			return DAOManager.getVLUImportDAO().selectVLUImportByExample(example);
		}
	}
	
	private static final class ChangeStatus implements TransactionalAction {
		private int id;
		private String status;
		private Date end;
		
		public ChangeStatus(int id, String status) {
			super();
			this.id = id;
			this.status = status;
		}

		public void executeInTransaction() throws SQLException {
			VLUImport vluImport = DAOManager.getVLUImportDAO().selectVLUImportByPrimaryKey(this.id);
			vluImport.setStatus(this.status);
			if (vluImport.getStarttime() == null) {
				vluImport.setStarttime(new Date());
			}
			vluImport.setEndtime(new Date());
			DAOManager.getVLUImportDAO().updateVLUImportByPrimaryKey(vluImport);
		}
	}
	
	private static final class InsertVLUData implements TransactionalAction {
		private int importId;
		private VLUImportRecord importRecord;
		
		public InsertVLUData(int importId, VLUImportRecord importRecord) {
			super();
			this.importId = importId;
			this.importRecord = importRecord;
		}

		public void executeInTransaction() throws SQLException {
			Connection conn = IBatisManager.getClient().getCurrentConnection();
			PreparedStatement preparedStatement = conn.prepareStatement("insert into VLU_DATA(dni, domain, message, idvluimport) values(?,?,?,?)");
			preparedStatement.setString(1, this.importRecord.getDni());
			preparedStatement.setString(2, this.importRecord.getDomain());
			if (StringUtils.isEmpty(this.importRecord.getMessage())) {
				preparedStatement.setString(3, null);
			} else {
				preparedStatement.setString(3, this.importRecord.getMessage());
			}
			preparedStatement.setInt(4, this.importId);
			preparedStatement.executeUpdate();
			
			preparedStatement = conn.prepareStatement("delete from VLU_DATA where dni = ? and domain = ? and idvluimport != ?");
			preparedStatement.setString(1, this.importRecord.getDni());
			preparedStatement.setString(2, this.importRecord.getDomain());
			preparedStatement.setInt(3, this.importId);
			preparedStatement.executeUpdate();
			
			preparedStatement = conn.prepareStatement("update VLU_IMPORT set processed = processed + 1 where id = ?");
			preparedStatement.setInt(1, this.importId);
			preparedStatement.executeUpdate();
			conn.commit();
			
			//DAOManager.getVLUDataDAO().deleteOLDVLUData(this.importRecord.getDni(), this.importRecord.getDomain(), this.importId);
			/*VLUData vluData = new VLUData();
			vluData.setIdvluimport(this.importId);
			vluData.setDni(this.importRecord.getDni());
			vluData.setDomain(this.importRecord.getDomain());
			vluData.setMessage(this.importRecord.getMessage());
			vluData.setDeleted(0);
			DAOManager.getVLUDataDAO().insertVLUData(vluData);*/
//			DAOManager.getVLUImportDAO().incrementProcessed(this.importId);
		}
	}
	
	private static final class IncrementError implements TransactionalAction {
		private int importId;
		
		public IncrementError(int importId) {
			super();
			this.importId = importId;
		}

		public void executeInTransaction() throws SQLException {
			VLUImport vluImport = DAOManager.getVLUImportDAO().selectVLUImportByPrimaryKey(this.importId);
			vluImport.setProcessed(vluImport.getProcessed() + 1);
			vluImport.setErrors(vluImport.getErrors() + 1);
			DAOManager.getVLUImportDAO().updateVLUImportByPrimaryKey(vluImport);
		}
	}
	
	@Override
	public void run() {
		boolean stopped = false;
		while (true) {
			try {
				Thread.sleep(1000 * 60); // sleep de un minuto
			} catch (InterruptedException e1) {
				stopped = true;
			}
			try {
				if (inInHourRange()) {
					List<VLUImport> imports =  TransactionProvider.executeInTransactionWithResult(new GetVLUImportPending());
					for(VLUImport imp : imports) {
						processImport(imp);
					}
				}
			} catch (SQLException e) {
				getLog().error(e.getMessage(), e);
			} catch (Exception e) {
				getLog().error(e.getMessage(), e);
			}
		}
	}
	
	private boolean inInHourRange() {
		Calendar cal = Calendar.getInstance();
		int hour = cal.get(Calendar.HOUR_OF_DAY);
		int minutes = cal.get(Calendar.MINUTE);
		if (hour < getStartHour()) {
			return false;
		}
		if (hour == getStartHour() && minutes < getStartMinutes()) {
			return false;
		}
		if (hour > getEndHour()) {
			return false;
		}
		if (hour == getEndHour() && minutes > getEndMinutes()) {
			return false;
		}
		return true;
	}

	private static CellProcessor[] getProcessors() {

		final CellProcessor[] processors = new CellProcessor[] { null, // dni
				null, // dominio
				null // estado
		};

		return processors;
	}
	
	private void processImport(VLUImport imp) {
		Connection conn = null;
		boolean error = false;
		try {
			changeStatus(imp.getId(), "PROCESSING");
			conn = DatasourceManager.getConnection();
			conn.setAutoCommit(false);
			PreparedStatement insertVlu = conn.prepareStatement("insert into " + DAOManager.getVLU_DATATableName()+ "(dni, domain, message, idvluimport) values(?,?,?,?)");
			PreparedStatement deleteOldVlu = conn.prepareStatement("delete from " + DAOManager.getVLU_DATATableName()+ " where dni = ? and domain = ? and idvluimport != ?");
			PreparedStatement incrementImport = conn.prepareStatement("update " + DAOManager.getVLU_IMPORTTableName()+ " set processed = processed + 1 where id = ?");
			incrementImport.setInt(1, imp.getId());
			CsvBeanReader beanReader = null;
			try {
				beanReader = new CsvBeanReader(new FileReader(SystemPropertyCache.getTempPath() + "/" + imp.getFilename()),CsvPreference.STANDARD_PREFERENCE);

				// the header elements are used to map the values to the bean (names
				// must match)
				final String[] header = beanReader.getHeader(true);
				final CellProcessor[] processors = getProcessors();

				VLUImportRecord importRecord;
				int commitCount = 1;
				while ((importRecord = beanReader.read(VLUImportRecord.class, header, processors)) != null) {
					processImport(beanReader, imp, importRecord, insertVlu, deleteOldVlu, incrementImport);
					if (commitCount >= 1000) {
						insertVlu.executeBatch();
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
			insertVlu.executeBatch();
			deleteOldVlu.executeBatch();
			conn.commit();
		} catch (Exception e) {
			try {
				conn.commit();
			} catch (SQLException e1) {
				getLog().error(e1.getMessage(), e1);
			}
			getLog().error(e.getMessage(), e);
			changeStatus(imp.getId(), "ERROR");
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
				changeStatus(imp.getId(), "FINISHED");
			}
		}
		
	}
	
	private void processImport(CsvBeanReader beanReader, VLUImport vluImport, VLUImportRecord importRecord, 
			PreparedStatement insertVlu, PreparedStatement deleteOldVlu, PreparedStatement incrementImport) throws SQLException {
		insertVlu.clearParameters();
		insertVlu.setString(1, importRecord.getDni());
		insertVlu.setString(2, importRecord.getDomain());
		insertVlu.setString(3, importRecord.getMessage());
		insertVlu.setInt(4, vluImport.getId());
		insertVlu.addBatch();
		deleteOldVlu.clearParameters();
		deleteOldVlu.setString(1, importRecord.getDni());
		deleteOldVlu.setString(2, importRecord.getDomain());
		deleteOldVlu.setInt(3, vluImport.getId());
		deleteOldVlu.addBatch();
		incrementImport.executeUpdate();
	}

	public static void changeStatus(int id, String newStatus) {
		try {
			TransactionProvider.executeInTransaction(new ChangeStatus(id, newStatus));
		} catch (SQLException e) {
			getLog().error(e.getMessage(), e);
		} catch (ValidationException e) {
			getLog().error(e.getMessage(), e);
		}
	}

	private static Logger getLog() {
		return LoggerProvider.getLogger(VLUImportThread.class);
	}

	public static int getStartHour() {
		return startHour;
	}

	public static void setStartHour(int startHour) {
		VLUImportThread.startHour = startHour;
	}

	public static int getStartMinutes() {
		return startMinutes;
	}

	public static void setStartMinutes(int startMinutes) {
		VLUImportThread.startMinutes = startMinutes;
	}

	public static int getEndHour() {
		return endHour;
	}

	public static void setEndHour(int endHour) {
		VLUImportThread.endHour = endHour;
	}

	public static int getEndMinutes() {
		return endMinutes;
	}

	public static void setEndMinutes(int endMinutes) {
		VLUImportThread.endMinutes = endMinutes;
	}
}
