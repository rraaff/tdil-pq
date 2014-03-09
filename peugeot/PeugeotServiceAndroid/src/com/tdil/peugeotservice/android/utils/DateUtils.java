package com.tdil.peugeotservice.android.utils;

import java.text.SimpleDateFormat;
import java.util.Date;

public class DateUtils {

	public static String formatDateSp(Date fromDate2) {
		if (fromDate2 == null) {
			return "";
		}
		SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
		return dateFormat.format(fromDate2);
	}
}
