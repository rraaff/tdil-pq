package com.tdil.struts;

import java.sql.SQLException;

public interface TransactionalActionWithResult<T> {

	T executeInTransaction() throws SQLException;
}
