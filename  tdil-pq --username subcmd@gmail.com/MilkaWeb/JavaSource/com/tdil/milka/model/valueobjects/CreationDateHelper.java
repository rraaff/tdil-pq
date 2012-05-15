package com.tdil.milka.model.valueobjects;

import java.text.SimpleDateFormat;
import java.util.Date;

public class CreationDateHelper {

	public static String getCreationDateAsString(Date creationDate) {
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		return dateFormat.format(creationDate);
	}
}
