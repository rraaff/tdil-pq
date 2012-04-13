package com.tdil.simon.actions.validations;

import gnu.regexp.RE;
import gnu.regexp.UncheckedRE;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.commons.lang.StringUtils;

import com.tdil.struts.ValidationError;

public class FieldValidation {
	
	protected static final RE EMAIL_RE = new UncheckedRE("[a-zA-Z0-9\\.\\-_]+@[a-zA-Z0-9\\.\\-_]+\\.[\\.a-zA-Z0-9]{2,4}");

	public static String validateText(String text, String field, int length, ValidationError validation) {
		String result = text;
		if (StringUtils.isEmpty(text)) {
			validation.setFieldError(field, ValidationErrors.CANNOT_BE_EMPTY);
		} else {
			result = text.trim();
			if (result.length() > length) {
				validation.setFieldError(field, ValidationErrors.TEXT_TOO_LONG);
			}
		}
		return result;
	}
	
	public static String validateTextForLength(String text, String field, int length, ValidationError validation) {
		String result = text;
		if (StringUtils.isEmpty(text)) {
			return "";
		} else {
			result = text.trim();
			if (result.length() > length) {
				validation.setFieldError(field, ValidationErrors.TEXT_TOO_LONG);
			}
		}
		return result;
	}
	
	public static String validateEmail(String email, String field, ValidationError validation) {
		String result = email;
		if (StringUtils.isEmpty(email)) {
			validation.setFieldError(field, ValidationErrors.CANNOT_BE_EMPTY);
		} else {
			result = email.trim();
			if (result.length() > 100) {
				validation.setFieldError(field, ValidationErrors.TEXT_TOO_LONG);
			}
			if (!EMAIL_RE.isMatch(result)) {
				validation.setFieldError(field, ValidationErrors.INVALID_EMAIL);
			}
		}
		return result;
	}

	public static boolean validateBoolean(String st, ValidationError validation) {
		if (StringUtils.isEmpty(st)) {
			return false;
		} else {
			return Boolean.valueOf(st);
		}
	}
	
	public static Date validateDate(String date, String field, boolean requiered, ValidationError validation) {
		String result = date;
		if (StringUtils.isEmpty(result)) {
			if (requiered) {
				validation.setFieldError(field, ValidationErrors.CANNOT_BE_EMPTY);
			} 
			return null;
		} else {
			result = result.trim();
			DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
			try {
				return dateFormat.parse(result);
			} catch (ParseException e) {
				validation.setFieldError(field, ValidationErrors.INVALID_DATE);
				return null;
			}
			
		}
	}
}
