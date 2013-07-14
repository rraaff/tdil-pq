package com.tdil.ibatis;

import java.sql.SQLException;

import org.apache.log4j.Logger;
import org.apache.struts.action.ActionForm;

import com.tdil.log4j.LoggerProvider;
import com.tdil.struts.TransactionalAction;
import com.tdil.struts.TransactionalActionWithResult;
import com.tdil.struts.TransactionalActionWithValue;
import com.tdil.struts.ValidationError;
import com.tdil.struts.ValidationException;
import com.tdil.struts.forms.TransactionalValidationForm;

public class TransactionProvider {

	
	public static void executeInTransaction(TransactionalAction transactionalAction) throws SQLException, com.tdil.struts.ValidationException {
		try {
			IBatisManager.beginTransaction();
			transactionalAction.executeInTransaction();
            IBatisManager.commitTransaction();
		} finally {
			try {
				IBatisManager.endTransaction();
			} catch (SQLException e) {
				getLog().error(e.getMessage(), e);
			}
		}
	}
	
	public static <T> T executeInTransactionWithResult(TransactionalActionWithResult<T> transactionalAction) throws SQLException {
		T result = null;
		try {
			IBatisManager.beginTransaction();
			result = transactionalAction.executeInTransaction();
            IBatisManager.commitTransaction();
		} finally {
			try {
				IBatisManager.endTransaction();
			} catch (SQLException e) {
				getLog().error(e.getMessage(), e);
			}
		}
		return result;
	}
	
	private static Logger getLog() {
		return LoggerProvider.getLogger(TransactionProvider.class);
	}
	
	public static <T> T executeInTransaction(TransactionalActionWithValue<T> transactionalAction, ActionForm form) throws SQLException, ValidationException {
		T result = null;
		try {
			IBatisManager.beginTransaction();
			result = transactionalAction.executeInTransaction(form);
            IBatisManager.commitTransaction();
		} finally {
			try {
				IBatisManager.endTransaction();
			} catch (SQLException e) {
				getLog().error(e.getMessage(), e);
			}
		}
		return result;
	}
	
	public static void validateInTransaction(TransactionalValidationForm form, ValidationError validationError) throws SQLException {
		try {
			IBatisManager.beginTransaction();
			form.validateInTransaction(validationError);
            IBatisManager.commitTransaction();
		} finally {
			try {
				IBatisManager.endTransaction();
			} catch (SQLException e) {
				getLog().error(e.getMessage(), e);
			}
		}
	}
}
