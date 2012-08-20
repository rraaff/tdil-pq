package com.tdil.struts.forms;

import java.sql.SQLException;

import org.apache.log4j.Logger;

import com.tdil.ibatis.TransactionProvider;
import com.tdil.log4j.LoggerProvider;
import com.tdil.struts.ValidationError;

public abstract class TransactionalValidationForm extends AbstractForm {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6486587349684446007L;

	public final ValidationError validate() {
		ValidationError validationError = new ValidationError();
		this.basicValidate(validationError);
		if (!validationError.hasError()) {
			try {
				this.executeValidationInTransaction(validationError);
			} catch (SQLException e) {
				getLog().error(e.getMessage(), e);
			}
		}
		return validationError;
	}

	private static Logger getLog() {
		return LoggerProvider.getLogger(TransactionalValidationForm.class);
	}

	public void executeValidationInTransaction(ValidationError validationError) throws SQLException {
		TransactionProvider.validateInTransaction(this, validationError);
	}

	public abstract void basicValidate(ValidationError validationError);

	public abstract void validateInTransaction(ValidationError validationError) throws SQLException;
}
