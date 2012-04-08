package com.tdil.struts;

import java.sql.SQLException;

public interface TransactionalAction {

	public void executeInTransaction() throws SQLException, ValidationException;
}
