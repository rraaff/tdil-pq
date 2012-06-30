package com.tdil.milka.model.valueobjects;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class CreationDateHelper {
	
	private static final String MONTHS[] = new String[] {"Ene","Feb","Mar","Abr","May","Jun","Jul","Ago","Sep","Oct","Nov","Dic"};

	private static final String MONTHS_FULL[] = new String[] {"Enero","Febrero","Marzo","Abril","Mayo","Junio","Julio","Agosto","Septiembre","Octubre","Noviembre","Diciembre"};
	
	public static String getCreationDateAsString(Date creationDate) {
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		return dateFormat.format(creationDate);
	}
	
	public static String getDateAsString(Date creationDate) {
		SimpleDateFormat dateFormat = new SimpleDateFormat("dd");
		return dateFormat.format(creationDate);
	}
	
	public static String getMonthAsString(Date creationDate) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(creationDate);
		return MONTHS[cal.get(Calendar.MONTH)];
	}
	
	public static String getDateFull(Date creationDate) {
		StringBuffer result = new StringBuffer();
		Calendar cal = Calendar.getInstance();
		cal.setTime(creationDate);
		result.append(cal.get(Calendar.DATE));
		result.append(" de ").append(MONTHS_FULL[cal.get(Calendar.MONTH)]);
		result.append(" de ").append(cal.get(Calendar.YEAR));
		return result.toString();
	}
}
