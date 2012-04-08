package com.tdil.struts.forms;

import java.sql.SQLException;

import org.apache.struts.action.ActionForm;

import com.tdil.struts.ValidationError;
import com.tdil.struts.ValidationException;

public abstract class AbstractForm extends ActionForm {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3795941759332948905L;
	private String operation;

	public String getOperation() {
		return operation;
	}

	public void setOperation(String operation) {
		this.operation = operation;
	}
	
	public abstract void reset() throws SQLException;

	public abstract void init() throws SQLException;

	public abstract ValidationError validate();
	
	public abstract void save() throws SQLException, ValidationException;
	
}
