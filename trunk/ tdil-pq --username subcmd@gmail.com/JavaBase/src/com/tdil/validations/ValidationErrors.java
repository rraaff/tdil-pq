package com.tdil.validations;

public interface ValidationErrors {

	// General errors
	String GENERAL_ERROR_TRY_AGAIN = "GENERAL_ERROR_TRY_AGAIN";
	String CANNOT_BE_EMPTY = "CANNOT_BE_EMPTY";
	String INVALID_NUMBER = "INVALID_NUMBER";
	String INVALID_EMAIL = "INVALID_EMAIL";
	String INVALID_DATE = "INVALID_DATE";
	String TEXT_TOO_LONG = "TEXT_TOO_LONG";
	String TOO_BIG = "TOO_BIG";
	
	String DUPLICATED = "DUPLICATED";
	
	String UNDER_MIN = "UNDER_MIN";
	String OVER_MAX = "OVER_MAX";
	
	String PASSWORD_INCORRECT = "PASSWORD_INCORRECT"; // the password does not match
	String PASSWORD_EXPIRED = "PASSWORD_EXPIRED"; // the password is expired
	
	String EMAIL_FAILED = "EMAIL_FAILED";
	
	// New password and retype password are different
	String PASSWORD_NOT_EQUALS = "PASSWORD_NOT_EQUALS";
	
	String INVALID_IMAGE = "INVALID_IMAGE";
	
	String CONSTRAINT_VIOLATION = "CONSTRAINT_VIOLATION";
	
	int TEXT_LENGTH = 65000;
	int MEDIUMTEXT_LENGTH = 16000000;
	int BLOB_LENGTH = 65000;
	int MEDIUMBLOB_LENGTH = 16000000;
}
