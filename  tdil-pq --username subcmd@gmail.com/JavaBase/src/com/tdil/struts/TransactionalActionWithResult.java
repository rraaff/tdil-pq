package com.tdil.struts;

import java.sql.SQLException;

public interface TransactionalActionWithResult {

	public Object executeInTransaction() throws SQLException;
}
