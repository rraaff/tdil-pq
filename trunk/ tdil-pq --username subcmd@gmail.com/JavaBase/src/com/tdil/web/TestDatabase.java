package com.tdil.web;

import java.sql.SQLException;

import com.tdil.ibatis.IBatisManager;
import com.tdil.struts.TransactionalActionWithResult;

final class TestDatabase implements TransactionalActionWithResult {
	@Override
	public Object executeInTransaction() throws SQLException {
		return (Integer)IBatisManager.getClient().queryForObject("VERSION.selectVersion");
	}
}