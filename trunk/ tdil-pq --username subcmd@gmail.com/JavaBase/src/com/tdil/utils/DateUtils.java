package com.tdil.utils;

import java.util.Calendar;
import java.util.Date;

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
}
