package com.tdil.tuafesta.web;

import java.sql.SQLException;
import java.util.List;

import org.apache.log4j.Logger;

import com.tdil.ibatis.TransactionProvider;
import com.tdil.log4j.LoggerProvider;
import com.tdil.struts.TransactionalActionWithResult;
import com.tdil.tuafesta.daomanager.DAOManager;
import com.tdil.tuafesta.model.SystemProperty;
import com.tdil.tuafesta.model.SystemPropertyExample;

public class SystemPropertyUtils {

	private static Logger getLog() {
		return LoggerProvider.getLogger(SystemPropertyUtils.class);
	}
	
	private static final class GetSystemProperty implements TransactionalActionWithResult {
		
		private String key;
		
		public GetSystemProperty(String key) {
			super();
			this.key = key;
		}

		public Object executeInTransaction() throws SQLException {
			SystemPropertyExample systemPropertyExample = new SystemPropertyExample();
			systemPropertyExample.createCriteria().andPropkeyEqualTo(key);
			return DAOManager.getSystemPropertyDAO().selectSystemPropertyByExample(systemPropertyExample);
		}
	}

	@SuppressWarnings("unchecked")
	public static String getSystemPropertValue(String key) {
		try {
			List<SystemProperty> result = (List<SystemProperty>)TransactionProvider.executeInTransactionWithResult(new GetSystemProperty(key));
			if (result.size() > 0) {
				return result.get(0).getPropvalue();
			} else {
				return "";
			}
		} catch (SQLException e) {
			getLog().error(e.getMessage(), e);
			return "";
		}
	}
	
	@SuppressWarnings("unchecked")
	public static String getSystemPropertValueInSameTransaction(String key) {
		try {
			List<SystemProperty> result = (List<SystemProperty>)new GetSystemProperty(key).executeInTransaction();
			if (result.size() > 0) {
				return result.get(0).getPropvalue();
			} else {
				return "";
			}
		} catch (SQLException e) {
			getLog().error(e.getMessage(), e);
			return "";
		}
	}
}
