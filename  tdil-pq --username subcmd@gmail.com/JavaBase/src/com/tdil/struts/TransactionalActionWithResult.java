package com.tdil.struts;

import java.sql.SQLException;

public interface TransactionalActionWithResult {

	Object executeInTransaction() throws SQLException;
}
