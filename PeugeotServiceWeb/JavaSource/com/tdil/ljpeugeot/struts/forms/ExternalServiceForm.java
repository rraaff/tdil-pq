package com.tdil.ljpeugeot.struts.forms;

import java.sql.SQLException;

import org.apache.struts.action.ActionForm;

import com.tdil.struts.ValidationException;

public abstract class ExternalServiceForm extends ActionForm {

	private static final long serialVersionUID = -1708098060709999963L;

	public abstract boolean save() throws SQLException, ValidationException;
}
