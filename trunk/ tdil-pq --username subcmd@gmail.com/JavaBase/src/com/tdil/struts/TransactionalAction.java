package com.tdil.struts;

import java.sql.SQLException;

public interface TransactionalAction {

	void executeInTransaction() throws SQLException, ValidationException;
}
