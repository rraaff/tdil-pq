package com.tdil.lojack.struts.forms;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionMapping;

import com.tdil.utils.DateUtils;

public abstract class AgendaForm extends ThalamusForm {

	private static final long serialVersionUID = -5652817943012116296L;

	private String description;
	
	private boolean isMobile;
	private String from; // Fechas en formato YYYY-MM-DD
	private String to;
	private String type;
	
	private String fromday;
	private String frommonth;
	private String fromyear;
	private String today;
	private String tomonth;
	private String toyear;
	
	private List<String> years;
	
	private boolean monday;
	private boolean tuesday;
	private boolean wednesday;
	private boolean thursday;
	private boolean friday;
	private boolean saturday;
	private boolean sunday;
	
	// Hora en formato HH en 00-24:MM:SS, ejemplo 10:30:00 18:30:00
	
	private String activateTimeHour;
	private String activateTimeMinute;
	private String activateTimeSeconds;
	
	private String deactivateTimeHour;
	private String deactivateTimeMinute;
	private String deactivateTimeSeconds;
	
	@Override
	public void reset(ActionMapping mapping, HttpServletRequest request) {
		super.reset(mapping, request);
		this.monday = false;
		this.tuesday = false;
		this.wednesday = false;
		this.thursday = false;
		this.friday = false;
		this.saturday = false;
		this.sunday = false;
	}
	
	public void reset() {
		this.description= null;
		this.from= null; // Fechas en formato YYYY-MM-DD
		this.to= null;
		this.type= null;
		
		this.activateTimeHour= null;
		this.activateTimeMinute= null;
		this.activateTimeSeconds= null;
		
		this.deactivateTimeHour= null;
		this.deactivateTimeMinute= null;
		this.deactivateTimeSeconds= null;
		
	}
	
	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getFrom() {
		if (this.isMobile) {
			from = this.getFromyear() + "-" + this.getFrommonth() + "-" + this.getFromday();
		}
		return from;
	}

	public void setFrom(String from) {
		if (from != null) {
			Calendar cal = Calendar.getInstance();
			cal.setTime(DateUtils.parseDate(from));
			setFromday(String.valueOf(cal.get(Calendar.DATE)));
			if (this.getFromday().length() == 1) {
				this.setFromday("0" + this.getFromday());
			}
			setFrommonth(String.valueOf(cal.get(Calendar.MONTH) + 1));
			if (this.getFrommonth().length() == 1) {
				this.setFrommonth("0" + this.getFrommonth());
			}
			setFromyear(String.valueOf(cal.get(Calendar.YEAR)));
		}
		this.from = from;
	}

	public String getTo() {
		if (this.isMobile) {
			to = this.getToyear() + "-" + this.getTomonth() + "-" + this.getToday();
		}
		return to;
	}

	public void setTo(String to) {
		if (to != null) {
			Calendar cal = Calendar.getInstance();
			cal.setTime(DateUtils.parseDate(to));
			setToday(String.valueOf(cal.get(Calendar.DATE)));
			if (this.getToday().length() == 1) {
				this.setToday("0" + this.getToday());
			}
			setTomonth(String.valueOf(cal.get(Calendar.MONTH) + 1));
			if (this.getTomonth().length() == 1) {
				this.setTomonth("0" + this.getTomonth());
			}
			setToyear(String.valueOf(cal.get(Calendar.YEAR)));
		}
		this.to = to;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getCustomDays() {
		StringBuffer result = new StringBuffer();
		if (this.monday) {
			result.append("Lu,");
		}
		if (this.tuesday) {
			result.append("Ma,");
		}
		if (this.wednesday) {
			result.append("Mi,");
		}
		if (this.thursday) {
			result.append("Ju,");
		}
		if (this.friday) {
			result.append("Vi,");
		}
		if (this.saturday) {
			result.append("Sa,");
		}
		if (this.sunday) {
			result.append("Do,");
		}
		String days = result.toString();
		if (days.length() > 0) {
			return days.substring(0, days.length() - 1);
		} else {
			return "";
		}
	}

	public void setCustomDays(String customDays) {
		this.monday = customDays.indexOf("Lu") != -1;
		this.tuesday = customDays.indexOf("Ma") != -1;
		this.wednesday = customDays.indexOf("Mi") != -1;
		this.thursday = customDays.indexOf("Ju") != -1;
		this.friday = customDays.indexOf("Vi") != -1;
		this.saturday = customDays.indexOf("Sa") != -1;
		this.sunday = customDays.indexOf("Do") != -1;
	}

	public String getActivateTime() {
		return this.getActivateTimeHour() + ":" + this.getActivateTimeMinute() + ":" + this.getActivateTimeSeconds();
	}

	public void setActivateTime(String activateTime) {
		String arr[] = activateTime.split(":");
		this.setActivateTimeHour(arr[0]);
		this.setActivateTimeMinute(arr[1]);
		this.setActivateTimeSeconds(arr[2]);
	}

	public String getDeactivateTime() {
		return this.getDeactivateTimeHour() + ":" + this.getDeactivateTimeMinute() + ":" + this.getDeactivateTimeSeconds();
	}

	public void setDeactivateTime(String deactivateTime) {
		String arr[] = deactivateTime.split(":");
		this.setDeactivateTimeHour(arr[0]);
		this.setDeactivateTimeMinute(arr[1]);
		this.setDeactivateTimeSeconds(arr[2]);
	}
	
	public String getActivateTimeHour() {
		return activateTimeHour;
	}

	public void setActivateTimeHour(String activateTimeHour) {
		this.activateTimeHour = activateTimeHour;
	}

	public String getActivateTimeMinute() {
		return activateTimeMinute;
	}

	public void setActivateTimeMinute(String activateTimeMinute) {
		this.activateTimeMinute = activateTimeMinute;
	}

	public String getActivateTimeSeconds() {
		return activateTimeSeconds;
	}

	public void setActivateTimeSeconds(String activateTimeSeconds) {
		this.activateTimeSeconds = activateTimeSeconds;
	}

	public String getDeactivateTimeHour() {
		return deactivateTimeHour;
	}

	public void setDeactivateTimeHour(String deactivateTimeHour) {
		this.deactivateTimeHour = deactivateTimeHour;
	}

	public String getDeactivateTimeMinute() {
		return deactivateTimeMinute;
	}

	public void setDeactivateTimeMinute(String deactivateTimeMinute) {
		this.deactivateTimeMinute = deactivateTimeMinute;
	}

	public String getDeactivateTimeSeconds() {
		return deactivateTimeSeconds;
	}

	public void setDeactivateTimeSeconds(String deactivateTimeSeconds) {
		this.deactivateTimeSeconds = deactivateTimeSeconds;
	}

	public boolean isMonday() {
		return monday;
	}

	public void setMonday(boolean monday) {
		this.monday = monday;
	}

	public boolean isTuesday() {
		return tuesday;
	}

	public void setTuesday(boolean tuesday) {
		this.tuesday = tuesday;
	}

	public boolean isWednesday() {
		return wednesday;
	}

	public void setWednesday(boolean wednesday) {
		this.wednesday = wednesday;
	}

	public boolean isThursday() {
		return thursday;
	}

	public void setThursday(boolean thursday) {
		this.thursday = thursday;
	}

	public boolean isFriday() {
		return friday;
	}

	public void setFriday(boolean friday) {
		this.friday = friday;
	}

	public boolean isSaturday() {
		return saturday;
	}

	public void setSaturday(boolean saturday) {
		this.saturday = saturday;
	}

	public boolean isSunday() {
		return sunday;
	}

	public void setSunday(boolean sunday) {
		this.sunday = sunday;
	}

	public boolean isMobile() {
		return isMobile;
	}

	public void setMobile(boolean isMobile) {
		this.isMobile = isMobile;
	}

	public String getFromday() {
		return fromday;
	}

	public void setFromday(String fromday) {
		this.fromday = fromday;
	}

	public String getFrommonth() {
		return frommonth;
	}

	public void setFrommonth(String frommonth) {
		this.frommonth = frommonth;
	}

	public String getFromyear() {
		return fromyear;
	}

	public void setFromyear(String fromyear) {
		this.fromyear = fromyear;
	}

	public String getToday() {
		return today;
	}

	public void setToday(String today) {
		this.today = today;
	}

	public String getTomonth() {
		return tomonth;
	}

	public void setTomonth(String tomonth) {
		this.tomonth = tomonth;
	}

	public String getToyear() {
		return toyear;
	}

	public void setToyear(String toyear) {
		this.toyear = toyear;
	}

	public List<String> getYears() {
		if (years == null) {
			years = allYears();
		}
		return years;
	}

	public void setYears(List<String> years) {
		this.years = years;
	}
	
	private static List<String> allYears() {
		Calendar cal = Calendar.getInstance();
		int year = cal.get(Calendar.YEAR);
		int start = year - 5;
		List<String> result = new ArrayList<String>(10);
		for (int i = start; i <= year + 15; i++) {
			result.add(String.valueOf(i));
		}
		return result;
	}

}
