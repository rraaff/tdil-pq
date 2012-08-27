package com.tdil.tuafesta.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.commons.lang.StringUtils;

import com.tdil.tuafesta.struts.forms.HighlightedCategoryForm;

public class DateUtils {

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
			HighlightedCategoryForm.getLog().error(e.getMessage(), e);
			//throw new RuntimeException(e);
			return null;
		}
	}

}
