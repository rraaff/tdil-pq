package com.tdil.ljpeugeot.feeds;

import java.sql.SQLException;
import java.util.ArrayList;
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
import com.tdil.utils.DateUtils;

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
				sendEmail(vehicle, EmailService.THIRD_ADVICE + (vehicle.getNeedsadvice3date() == null ? ".km" : ".date"));
				vehicle.setAdvice3sent(1);
			} else {
				if (vehicle.getNeedsadvice2() == 1) {
					if (getLog().isInfoEnabled()) {
						getLog().info("enviando segundo aviso a " + vehicle.getDomain());
					}
					// mando email
					sendEmail(vehicle, EmailService.SECOND_ADVICE + (vehicle.getNeedsadvice2date() == null ? ".km" : ".date"));
					vehicle.setAdvice2sent(1);
				} else {
					if (getLog().isInfoEnabled()) {
						getLog().info("enviando primer aviso a " + vehicle.getDomain());
					}
					// mando email
					sendEmail(vehicle, EmailService.FIRST_ADVICE + (vehicle.getNeedsadvice1date() == null ? ".km" : ".date"));
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
		List<String> sectionsToRemove = new ArrayList<String>();
		if (dealer != null) {
			replacements.put(EmailService.DEALER_NAME_KEY, dealer.getName());
			replacements.put(EmailService.DEALER_ADDRESS_KEY, com.tdil.utils.StringUtils.nvl(dealer.getAddress(), "-"));
			replacements.put(EmailService.DEALER_PHONE_KEY, com.tdil.utils.StringUtils.nvl(dealer.getPhone(), "-"));
			replacements.put(EmailService.DEALER_EMAIL_KEY, com.tdil.utils.StringUtils.nvl(dealer.getName(), "-"));
		} else {
			sectionsToRemove.add(EmailService.DEALER_SECTION_KEY);
		}
		replacements.put(EmailService.DOMAIN_KEY, vehicle.getDomain());
		replacements.put(EmailService.FIRST_NAME_KEY, wu.getFirstname());
		replacements.put(EmailService.LAST_NAME_KEY, wu.getLastname());
		replacements.put(EmailService.ACTUAL_KM_KEY, String.valueOf(vehicle.getKm()));
		replacements.put(EmailService.LAST_SERVICE_KM_KEY, String.valueOf(vehicle.getLastservicekm()));
		replacements.put(EmailService.NEXT_SERVICE_KM_KEY, String.valueOf(vehicle.getLastservicekm() + 12000));
		
		Calendar cal = Calendar.getInstance();
		cal.setTime(vehicle.getLastservicedate());
		cal.add(Calendar.MONTH, 12);
		replacements.put(EmailService.NEXT_SERVICE_DATE_KEY, DateUtils.formatDateSp(cal.getTime()));
		
		if (!StringUtils.isEmpty(wu.getEmail())) {
			EmailService.sendEmail(wu.getEmail(), replacements, sectionsToRemove, advice);
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
