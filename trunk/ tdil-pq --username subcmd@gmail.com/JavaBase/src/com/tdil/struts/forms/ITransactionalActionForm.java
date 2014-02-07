package com.tdil.struts.forms;

import java.sql.SQLException;

import com.tdil.struts.ValidationError;
import com.tdil.struts.ValidationException;

public interface ITransactionalActionForm {

	public String getOperation();
	
	public abstract void reset() throws SQLException;

	public abstract void init() throws SQLException;
	
	public abstract void initWith(int id) throws SQLException;

	public abstract ValidationError validate();
	
	public abstract void save() throws SQLException, ValidationException;
}
