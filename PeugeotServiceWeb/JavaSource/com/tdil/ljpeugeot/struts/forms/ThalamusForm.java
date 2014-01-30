package com.tdil.ljpeugeot.struts.forms;

import java.sql.SQLException;

import org.apache.struts.action.ActionForm;

import com.tdil.struts.ValidationError;

public abstract class ThalamusForm extends ActionForm {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2247229478183276994L;

	public final ValidationError validate() {
		ValidationError validationError = new ValidationError();
		this.basicValidate(validationError);
		return validationError;
	}
	
	public abstract void basicValidate(ValidationError validationError);
	
	public abstract void reset() throws SQLException;
}
