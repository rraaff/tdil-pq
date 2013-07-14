package com.tdil.subsystem.generic;

import java.sql.SQLException;

import org.apache.struts.action.ActionForm;

import com.tdil.struts.TransactionalAction;
import com.tdil.struts.TransactionalActionWithResult;
import com.tdil.struts.TransactionalActionWithValue;
import com.tdil.struts.ValidationException;
import com.tdil.transaction.Transactional;

public interface IGenericTransactionExecutionService {

	@Transactional
	public abstract void execute(TransactionalAction action)
			throws SQLException, ValidationException;

	@Transactional
	public abstract <T> T execute(TransactionalActionWithResult<T> action)
			throws SQLException, ValidationException;

	@Transactional
	public abstract <T> T executeInTransaction(
			TransactionalActionWithValue<T> transactionalAction, ActionForm form)
			throws SQLException, ValidationException;

}