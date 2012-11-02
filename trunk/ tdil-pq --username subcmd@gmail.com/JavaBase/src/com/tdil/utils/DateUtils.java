package com.tdil.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import org.apache.commons.lang.StringUtils;

public class DateUtils {

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
}
