package com.tdil.lojack.vlu;

import java.io.FileReader;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.omg.CORBA.StringHolder;
import org.supercsv.cellprocessor.ift.CellProcessor;
import org.supercsv.io.CsvBeanReader;
import org.supercsv.prefs.CsvPreference;

import com.tdil.ibatis.IBatisManager;
import com.tdil.ibatis.TransactionProvider;
import com.tdil.log4j.LoggerProvider;
import com.tdil.lojack.daomanager.DAOManager;
import com.tdil.lojack.model.ImportType;
import com.tdil.lojack.model.VLUImport;
import com.tdil.lojack.model.VLUImportExample;
import com.tdil.pool.DatasourceManager;
import com.tdil.struts.TransactionalAction;
import com.tdil.struts.TransactionalActionWithResult;
import com.tdil.struts.ValidationException;
import com.tdil.thalamus.client.core.ProxyConfiguration;
import com.tdil.utils.DateUtils;
import com.tdil.utils.SystemPropertyCache;

public class VLUImportThread extends Thread {
	
	private static final String FINISHED = "FINISHED";
	private static int startHour = 2;
	private static int startMinutes = 0;
	
	private static int endHour = 6;
	private static int endMinutes  = 0;
	
	/* Variables para importacion de dominios reparados */
	private static int startRepairedHour = 0;
	private static int startRepairedMinutes = 0;
	private static int endRepairedHour = 23;
	private static int endRepairedMinutes  = 59;
	private static String repairedDomainURL = "";
	private static ProxyConfiguration PROXY = null;

	private static final class GetVLUImportPending implements TransactionalActionWithResult<List<VLUImport>> {
		public GetVLUImportPending() {
			super();
		}
		public List<VLUImport> executeInTransaction() throws SQLException {
			VLUImportExample example = new VLUImportExample();
			example.createCriteria().andStatusEqualTo("PENDING").andImporttypeEqualTo(ImportType.VLU_COMPLETE.getType());
			example.setOrderByClause("id");
			return DAOManager.getVLUImportDAO().selectVLUImportByExample(example);
		}
	}
	
	private static final class GetVLURepairedPending implements TransactionalActionWithResult<List<VLUImport>> {
		public GetVLURepairedPending() {
			super();
		}
		public List<VLUImport> executeInTransaction() throws SQLException {
			VLUImportExample example = new VLUImportExample();
			example.createCriteria().andStatusEqualTo("PENDING").andImporttypeEqualTo(ImportType.VLU_DELETE_REPAIRED.getType());
			example.setOrderByClause("id");
			return DAOManager.getVLUImportDAO().selectVLUImportByExample(example);
		}
	}
	private static final class AreRepairedImportToday implements TransactionalActionWithResult<Boolean> {
		public AreRepairedImportToday() {
			super();
		}
		public Boolean executeInTransaction() throws SQLException {
			VLUImportExample example = new VLUImportExample();
			example.createCriteria().andImporttypeEqualTo(ImportType.VLU_DELETE_REPAIRED.getType()).andStarttimeGreaterThanOrEqualTo(DateUtils.date2FirstMomentOfDate(new Date()));
			return DAOManager.getVLUImportDAO().countVLUImportByExample(example) > 0;
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
	
	@Override
	public void run() {
		boolean stopped = false;
		while (true) {
			try {
				Thread.sleep(1000 /** 60*/); // sleep de un minuto
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
				if (inInHourRangeForRepairedDomains()) {
					if (!StringUtils.isEmpty(getRepairedDomainURL())) {
						if (!TransactionProvider.executeInTransactionWithResult(new AreRepairedImportToday())) {
							downloadCSVAndImport();
							List<VLUImport> imports =  TransactionProvider.executeInTransactionWithResult(new GetVLURepairedPending());
							for(VLUImport imp : imports) {
								DeleteRepairedDomainsThread.processImport(imp);
							}
						}
					}
				}
			} catch (SQLException e) {
				getLog().error(e.getMessage(), e);
			} catch (Exception e) {
				getLog().error(e.getMessage(), e);
			}
		}
	}
	
	private void downloadCSVAndImport() {
		String filePath = com.tdil.utils.SystemPropertyCache.getTempPath() + "/";
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd-HH-mm");
		String fileName = "importRepaired_" + dateFormat.format(new Date()) + ".csv";
		filePath = filePath + fileName;
		if(getLog().isInfoEnabled()) {
			getLog().info("Start to download " + getRepairedDomainURL() + " as " + filePath);
		}
		try {
			VLUUtils.download(getRepairedDomainURL(), filePath);
			if(getLog().isInfoEnabled()) {
				getLog().info("End download of " + getRepairedDomainURL());
			}
			VLUUtils.registerNewImportRepairedDomains(fileName);
			
		} catch (Exception e) {
			getLog().error(e.getMessage(), e);
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
	
	private boolean inInHourRangeForRepairedDomains() {
		Calendar cal = Calendar.getInstance();
		int hour = cal.get(Calendar.HOUR_OF_DAY);
		int minutes = cal.get(Calendar.MINUTE);
		if (hour < getStartRepairedHour()) {
			return false;
		}
		if (hour == getStartRepairedHour() && minutes < getStartRepairedMinutes()) {
			return false;
		}
		if (hour > getEndRepairedHour()) {
			return false;
		}
		if (hour == getEndRepairedHour() && minutes > getEndRepairedMinutes()) {
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

			PreparedStatement deleteALLOldVlu = conn.prepareStatement("delete from " + DAOManager.getVLU_DATATableName()+ " where idvluimport != ?");
			deleteALLOldVlu.setInt(1, imp.getId());
			
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
			deleteALLOldVlu.execute();
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
				changeStatus(imp.getId(), FINISHED);
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
	
	/***************************** NO USADOS *****************************/
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

	public static int getStartRepairedHour() {
		return startRepairedHour;
	}

	public static void setStartRepairedHour(int startRepairedHour) {
		VLUImportThread.startRepairedHour = startRepairedHour;
	}

	public static int getStartRepairedMinutes() {
		return startRepairedMinutes;
	}

	public static void setStartRepairedMinutes(int startRepairedMinutes) {
		VLUImportThread.startRepairedMinutes = startRepairedMinutes;
	}

	public static int getEndRepairedHour() {
		return endRepairedHour;
	}

	public static void setEndRepairedHour(int endRepairedHour) {
		VLUImportThread.endRepairedHour = endRepairedHour;
	}

	public static int getEndRepairedMinutes() {
		return endRepairedMinutes;
	}

	public static void setEndRepairedMinutes(int endRepairedMinutes) {
		VLUImportThread.endRepairedMinutes = endRepairedMinutes;
	}

	public static String getRepairedDomainURL() {
		return repairedDomainURL;
	}

	public static void setRepairedDomainURL(String repairedDomainURL) {
		VLUImportThread.repairedDomainURL = repairedDomainURL;
	}

	public static ProxyConfiguration getPROXY() {
		return PROXY;
	}

	public static void setPROXY(ProxyConfiguration pROXY) {
		PROXY = pROXY;
	}
}
