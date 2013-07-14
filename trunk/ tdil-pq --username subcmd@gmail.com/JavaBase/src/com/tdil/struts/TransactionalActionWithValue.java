package com.tdil.struts;

import java.sql.SQLException;

import org.apache.struts.action.ActionForm;

public interface TransactionalActionWithValue<T> {

	T executeInTransaction(ActionForm form) throws SQLException, ValidationException;
}
