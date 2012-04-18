package com.tdil.validations;

import gnu.regexp.RE;
import gnu.regexp.UncheckedRE;

import java.io.IOException;
import java.io.InputStream;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.commons.io.IOUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.apache.struts.upload.FormFile;

import com.tdil.log4j.LoggerProvider;
import com.tdil.struts.ValidationError;
import com.tdil.struts.forms.UploadData;

public class FieldValidation {
	
	protected static final RE EMAIL_RE = new UncheckedRE("[a-zA-Z0-9\\.\\-_]+@[a-zA-Z0-9\\.\\-_]+\\.[\\.a-zA-Z0-9]{2,4}");

	private static Logger getLog() {
		return LoggerProvider.getLogger(FieldValidation.class);
	}
	
	public static String validateText(String text, String field, int length, ValidationError validation) {
		return validateText(text, field, length, true, validation);
	}
	
	public static String validateText(String text, String field, int length, boolean required, ValidationError validation) {
		String result = text;
		if (StringUtils.isEmpty(text) && required) {
			validation.setFieldError(field, ValidationErrors.CANNOT_BE_EMPTY);
		} else {
			result = text.trim();
			if (result.length() > length) {
				validation.setFieldError(field, ValidationErrors.TEXT_TOO_LONG);
			}
		}
		return result;
	}
	
	public static String validateNumber(String text, String field, int min, int max, ValidationError validation) {
		String result = text;
		if (StringUtils.isEmpty(text)) {
			validation.setFieldError(field, ValidationErrors.CANNOT_BE_EMPTY);
		} else {
			String idToValidate = text.trim();
			try {
				int resultTmp = Integer.parseInt(idToValidate);
				if (resultTmp < min) {
					validation.setFieldError(field, ValidationErrors.UNDER_MIN);
				}
				if (resultTmp > max) {
					validation.setFieldError(field, ValidationErrors.OVER_MAX);
				}
				return String.valueOf(resultTmp);
			} catch (NumberFormatException e) {
				validation.setFieldError(field, ValidationErrors.INVALID_NUMBER);
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
	
	public static UploadData validateFormFile(FormFile fileItem, String fieldName, boolean required, ValidationError validation) {
		boolean isEmpty = fileItem.getFileSize() == 0;
		if (isEmpty && required) {
			validation.setFieldError(fieldName, ValidationErrors.CANNOT_BE_EMPTY);
			return null;
		}
		if (isEmpty) {
			return null;
		}
		String fileName = fileItem.getFileName();
		InputStream io = null;
		try {
			io = fileItem.getInputStream();
			return new UploadData(fileName, IOUtils.toByteArray(io));
		} catch (IOException e) {
			getLog().error(e.getMessage(), e);
			validation.setGeneralError(e.getMessage());
		} finally {
			if (io != null) {
				try {
					io.close();
				} catch (IOException e) {
					getLog().error(e.getMessage(), e);
					validation.setGeneralError(e.getMessage());
				}
			}
		}
		return null;
	}
}
