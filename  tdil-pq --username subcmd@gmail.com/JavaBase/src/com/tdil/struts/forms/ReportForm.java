package com.tdil.struts.forms;

import java.sql.SQLException;
import java.util.List;

import com.tdil.struts.ValidationException;

public abstract class ReportForm extends TransactionalValidationForm {

	/**
	 * 
	 */
	private static final long serialVersionUID = 7353205715985407902L;

	@Override
	public final void save() throws SQLException, ValidationException {
	}

	public abstract List<List<Object>> search() throws SQLException;
}
