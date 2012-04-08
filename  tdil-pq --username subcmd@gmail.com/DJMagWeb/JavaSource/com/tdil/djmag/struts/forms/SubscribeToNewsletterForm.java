package com.tdil.djmag.struts.forms;

import java.sql.SQLException;

import com.tdil.simon.actions.validations.FieldValidation;
import com.tdil.struts.ValidationError;
import com.tdil.struts.ValidationException;
import com.tdil.struts.forms.TransactionalValidationForm;

public class SubscribeToNewsletterForm extends TransactionalValidationForm {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8206600031482405647L;

	private static String email_key = "SubscribeToNewsletterForm.email";
	private String email;

	public String getEmail() {
		return email;
	}


	public void setEmail(String email) {
		this.email = email;
	}

	@Override
	public void basicValidate(ValidationError validationError) {
		FieldValidation.validateEmail(this.getEmail(), email_key, validationError);
	}

	@Override
	public void validateInTransaction(ValidationError validationError) throws SQLException {
		// TODO Auto-generated method stub
	}

	@Override
	public void reset() throws SQLException {
		this.email = null;
	}

	@Override
	public void init() throws SQLException {
	}

	@Override
	public void save() throws SQLException, ValidationException {
		// TODO Auto-generated method stub
		
	}
	
	
}
