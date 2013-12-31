package com.tdil.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang.StringUtils;

public class DateUtils {

	public static List<String> ALL_DAYS;
	public static List<String> ALL_MONTHS;
	
	public static List<String> ALL_HOURS;
	public static List<String> ALL_MINUTES;
	public static List<String> ALL_SECONDS;

	static {
		List<String> days = new ArrayList<String>(31);
		for (int i = 1; i < 10; i++) {
			days.add("0" + i);
		}
		for (int i = 10; i <= 31; i++) {
			days.add(String.valueOf(i));
		}
		ALL_DAYS = Collections.unmodifiableList(days);
		
		List<String> months = new ArrayList<String>(31);
		for (int i = 1; i < 10; i++) {
			months.add("0" + i);
		}
		for (int i = 10; i <= 12; i++) {
			months.add(String.valueOf(i));
		}
		ALL_MONTHS = Collections.unmodifiableList(months);
		
		List<String> result = new ArrayList<String>(24);
		for (int i = 0; i < 10; i++) {
			result.add("0" + i);
		}
		for (int i = 10; i < 24; i++) {
			result.add(String.valueOf(i));
		}
		ALL_HOURS = Collections.unmodifiableList(result);

		result = new ArrayList<String>(60);
		for (int i = 0; i < 10; i++) {
			result.add("0" + i);
		}
		for (int i = 10; i < 60; i++) {
			result.add(String.valueOf(i));
		}
		ALL_MINUTES = Collections.unmodifiableList(result);
		ALL_SECONDS = ALL_MINUTES;
	}

	public static Date date2LastMomentOfDate(Date date) {
		if (date == null) {
			return null;
		}
		try {
			Calendar calendar = getCalendarWith(date,
				23,59,59,999);
			return calendar.getTime();
		} catch (Exception e) {
			return new Date();
		}
	}

	public static Date date2FirstMomentOfDate(Date date) {
		if (date == null) {
			return null;
		}
		try {
			Calendar calendar = getCalendarWith(date,
				0,0,0,0);
			return calendar.getTime();
		} catch (Exception e) {
			return new Date();
		}
	}

	public static Date date2FirstMomentOfMonth(Date date) {
		if (date == null) {
			return null;
		}
		try {
			Calendar calendar = getCalendarWith(date,
				0,0,0,0);
			calendar.set(Calendar.DAY_OF_MONTH, 1);
			return calendar.getTime();
		} catch (Exception e) {
			return new Date();
		}
	}

	protected static Calendar getCalendarWith(Date date, int hour, int minutes, int seconds, int milliseconds) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.set(Calendar.HOUR_OF_DAY, hour);
		calendar.set(Calendar.MINUTE, minutes);
		calendar.set(Calendar.SECOND, seconds);
		calendar.set(Calendar.MILLISECOND, milliseconds);
		return calendar;
	}

	public static String formatDate(Date fromDate2) {
		if (fromDate2 == null) {
			return "";
		}
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		return dateFormat.format(fromDate2);
	}

	public static Date parseDate(String fromDate2) {
		try {
			if (StringUtils.isEmpty(fromDate2)) {
				return null;
			}
			SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
			return dateFormat.parse(fromDate2);
		} catch (ParseException e) {
//			HighlightedCategoryForm.getLog().error(e.getMessage(), e);
			//throw new RuntimeException(e);
			return null;
		}
	}

	public static String formatDateSp(Date fromDate2) {
		if (fromDate2 == null) {
			return "";
		}
		SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
		return dateFormat.format(fromDate2);
	}
	
	public static List<String> allYears() {
		Calendar cal = Calendar.getInstance();
		int year = cal.get(Calendar.YEAR);
		int start = year - 100;
		List<String> result = new ArrayList<String>(100);
		for (int i = start; i <= year; i++) {
			result.add(String.valueOf(i));
		}
		return result;
	}

	public static Date parseDateSp(String fromDate2) {
		try {
			if (StringUtils.isEmpty(fromDate2)) {
				return null;
			}
			SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
			return dateFormat.parse(fromDate2);
		} catch (ParseException e) {
//			HighlightedCategoryForm.getLog().error(e.getMessage(), e);
			//throw new RuntimeException(e);
			return null;
		}
	}

	public static Date parseDateSp(String fromDate2, String format) {
		try {
			if (StringUtils.isEmpty(fromDate2)) {
				return null;
			}
			SimpleDateFormat dateFormat = new SimpleDateFormat(format);
			return dateFormat.parse(fromDate2);
		} catch (ParseException e) {
//			HighlightedCategoryForm.getLog().error(e.getMessage(), e);
			//throw new RuntimeException(e);
			return null;
		}
	}
	
	public static String getTimeTo(Date date) {
		Calendar now = Calendar.getInstance();
		long diff = date2LastMomentOfDate(date).getTime() - now.getTime().getTime();

		long diffSeconds = diff / 1000 % 60;
		long diffMinutes = diff / (60 * 1000) % 60;
		long diffHours = diff / (60 * 60 * 1000) % 24;
		long diffDays = diff / (24 * 60 * 60 * 1000);

		StringBuilder result = new StringBuilder();
		result.append(diffDays + " dias ");
		result.append(diffHours + " horas ");
		result.append(diffMinutes + " minutos ");
		result.append(diffSeconds + " segundos");
		return result.toString();
	}
}
