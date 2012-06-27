package com.tdil.milka.model.valueobjects;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class CreationDateHelper {
	
	private static final String MONTHS[] = new String[] {"Ene","Feb","Mar","Abr","May","Jun","Jul","Ago","Sep","Oct","Nov","Dic"};

	public static String getCreationDateAsString(Date creationDate) {
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		return dateFormat.format(creationDate);
	}
	
	public static String getDateAsString(Date creationDate) {
		SimpleDateFormat dateFormat = new SimpleDateFormat("dd");
		return dateFormat.format(creationDate);
	}
	
	// Hacerlo por numero y con array de traducciones al espaniol
	public static String getMonthAsString(Date creationDate) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(creationDate);
		return MONTHS[cal.get(Calendar.MONTH)];
	}
}
