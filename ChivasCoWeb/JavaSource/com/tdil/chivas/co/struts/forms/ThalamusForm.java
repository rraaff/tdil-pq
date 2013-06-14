package com.tdil.chivas.co.struts.forms;

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
}
