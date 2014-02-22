package com.tdil.ljpeugeot.feeds;

import java.sql.SQLException;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;

import com.tdil.ljpeugeot.daomanager.DAOManager;
import com.tdil.ljpeugeot.model.Dealer;
import com.tdil.ljpeugeot.model.Vehicle;
import com.tdil.ljpeugeot.model.WebsiteUser;
import com.tdil.ljpeugeot.services.EmailService;
import com.tdil.log4j.LoggerProvider;
import com.tdil.struts.TransactionalAction;
import com.tdil.struts.TransactionalActionWithResult;
import com.tdil.struts.ValidationException;
import com.tdil.subsystem.generic.GenericTransactionExecutionService;

public class SendEmailsAdviceThread extends Thread {
	
	public static final String TYPE = "SEND_EMAIL";
	
	private static int startHour = 2;
	private static int startMinutes = 0;
	
	private static int endHour = 6;
	private static int endMinutes  = 0;

	private static final class GetVehiclesForAdvise implements TransactionalActionWithResult<List<Vehicle>> {
		public GetVehiclesForAdvise() {
			super();
		}
		public List<Vehicle> executeInTransaction() throws SQLException {
			return DAOManager.getVehicleDAO().selectVehicleForAdvise(); // user TOP
		}
	}
	
	private static final class SendAdvise implements TransactionalAction {
		private Vehicle vehicle;
		
		public SendAdvise(Vehicle vehicle) {
			super();
			this.vehicle = vehicle;
		}
		public void executeInTransaction() throws SQLException {
			if (vehicle.getNeedsadvice3() == 1) {
				if (getLog().isInfoEnabled()) {
					getLog().info("enviando tercer aviso a " + vehicle.getDomain());
				}
				sendEmail(vehicle, EmailService.THIRD_ADVICE);
				vehicle.setAdvice3sent(1);
			} else {
				if (vehicle.getNeedsadvice2() == 1) {
					if (getLog().isInfoEnabled()) {
						getLog().info("enviando segundo aviso a " + vehicle.getDomain());
					}
					// mando email
					sendEmail(vehicle, EmailService.SECOND_ADVICE);
					vehicle.setAdvice2sent(1);
				} else {
					if (getLog().isInfoEnabled()) {
						getLog().info("enviando primer aviso a " + vehicle.getDomain());
					}
					// mando email
					sendEmail(vehicle, EmailService.FIRST_ADVICE);
					vehicle.setAdvice1sent(1);
				}
			}
			vehicle.setNeedsadvice(0);
			DAOManager.getVehicleDAO().updateVehicleByPrimaryKey(vehicle);
		}
	}

	
	public static void sendEmail(Vehicle vehicle, String advice) throws SQLException {
		WebsiteUser wu = DAOManager.getWebsiteUserDAO().selectWebsiteUserByPrimaryKey(vehicle.getIdWebsiteuser());
		Dealer dealer = DAOManager.getDealerDAO().selectDealerByPrimaryKey(vehicle.getIdDealer());
		Map<String, String> replacements = new HashMap<String, String>();
		if (dealer != null) {
			replacements.put(EmailService.DEALER_KEY, dealer.getName());
		}
		replacements.put(EmailService.DOMAIN_KEY, vehicle.getDomain());
		replacements.put(EmailService.FIRST_NAME_KEY, wu.getFirstname());
		replacements.put(EmailService.LAST_NAME_KEY, wu.getLastname());
		if (!StringUtils.isEmpty(wu.getEmail())) {
			EmailService.sendEmail(wu.getEmail(), replacements, advice);
		}
		// TODO enviar el email a la concesionaria
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
					List<Vehicle> imports =  GenericTransactionExecutionService.getInstance().execute(new GetVehiclesForAdvise());
					for(Vehicle imp : imports) {
						GenericTransactionExecutionService.getInstance().execute(new SendAdvise(imp));
					}
				}
			} catch (SQLException e) {
				getLog().error(e.getMessage(), e);
			} catch (ValidationException e) {
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

	private static Logger getLog() {
		return LoggerProvider.getLogger(SendEmailsAdviceThread.class);
	}

	public static int getStartHour() {
		return startHour;
	}

	public static void setStartHour(int startHour) {
		SendEmailsAdviceThread.startHour = startHour;
	}

	public static int getStartMinutes() {
		return startMinutes;
	}

	public static void setStartMinutes(int startMinutes) {
		SendEmailsAdviceThread.startMinutes = startMinutes;
	}

	public static int getEndHour() {
		return endHour;
	}

	public static void setEndHour(int endHour) {
		SendEmailsAdviceThread.endHour = endHour;
	}

	public static int getEndMinutes() {
		return endMinutes;
	}

	public static void setEndMinutes(int endMinutes) {
		SendEmailsAdviceThread.endMinutes = endMinutes;
	}
}
