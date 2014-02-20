package com.tdil.ljpeugeot.feeds;

import java.sql.SQLException;
import java.util.Calendar;
import java.util.Date;

import org.apache.log4j.Logger;

import com.tdil.ljpeugeot.daomanager.DAOManager;
import com.tdil.ljpeugeot.feeds.km.KMImportSpec;
import com.tdil.ljpeugeot.feeds.km.KmImportRunnable;
import com.tdil.ljpeugeot.model.DataImport;
import com.tdil.ljpeugeot.model.DataImportExample;
import com.tdil.log4j.LoggerProvider;
import com.tdil.struts.TransactionalActionWithResult;
import com.tdil.struts.ValidationException;
import com.tdil.subsystem.generic.GenericTransactionExecutionService;
import com.tdil.utils.DateUtils;

public class KMImportThread extends Thread {
	
	public static final String TYPE = "KM";
	
	private static int startHour = 2;
	private static int startMinutes = 0;
	
	private static int endHour = 6;
	private static int endMinutes  = 0;

	private static final class CreateDataImport implements TransactionalActionWithResult<DataImport> {
		public CreateDataImport() {
			super();
		}
		public DataImport executeInTransaction() throws SQLException {
			DataImport vluImport = new DataImport();
			vluImport.setFilename(DateUtils.formatDateSp(new Date()));
			vluImport.setType(TYPE);
			vluImport.setStatus("PENDING");
			vluImport.setProcessed(0);
			vluImport.setErrors(0);
			int result = DAOManager.getDataImportDAO().insertDataImport(vluImport);
			vluImport.setId(result);
			return vluImport;
		}
	}
	
	private static final class AreKMImportFinishedToday implements TransactionalActionWithResult<Boolean> {
		public AreKMImportFinishedToday() {
			super();
		}
		public Boolean executeInTransaction() throws SQLException {
			DataImportExample example = new DataImportExample();
			example.createCriteria().andStatusEqualTo(ImportRunnable.FINISHED).andTypeEqualTo(TYPE).andStarttimeGreaterThanOrEqualTo(DateUtils.date2FirstMomentOfDate(new Date()));
			return DAOManager.getDataImportDAO().countDataImportByExample(example) > 0;
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
			if (inInHourRange()) {
				if (!thereAreImportsToday()) {
					DataImport todayImport = generateDataImport();
					if (todayImport != null) {
						new Thread(new KmImportRunnable(todayImport, new KMImportSpec())).start();
					}
				}
			}
		}
	}
	
	private boolean thereAreImportsToday() {
		try {
			return GenericTransactionExecutionService.getInstance().execute(new AreKMImportFinishedToday());
		} catch (SQLException e) {
			getLog().error(e.getMessage(), e);
			return false;
		} catch (ValidationException e) {
			getLog().error(e.getMessage(), e);
			return false;
		} 
	}
	
	private DataImport generateDataImport() {
		try {
			return GenericTransactionExecutionService.getInstance().execute(new CreateDataImport());
		} catch (SQLException e) {
			getLog().error(e.getMessage(), e);
			return null;
		} catch (ValidationException e) {
			getLog().error(e.getMessage(), e);
			return null;
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

	private static Logger getLog() {
		return LoggerProvider.getLogger(KMImportThread.class);
	}

	public static int getStartHour() {
		return startHour;
	}

	public static void setStartHour(int startHour) {
		KMImportThread.startHour = startHour;
	}

	public static int getStartMinutes() {
		return startMinutes;
	}

	public static void setStartMinutes(int startMinutes) {
		KMImportThread.startMinutes = startMinutes;
	}

	public static int getEndHour() {
		return endHour;
	}

	public static void setEndHour(int endHour) {
		KMImportThread.endHour = endHour;
	}

	public static int getEndMinutes() {
		return endMinutes;
	}

	public static void setEndMinutes(int endMinutes) {
		KMImportThread.endMinutes = endMinutes;
	}
}
