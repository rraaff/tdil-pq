package com.tdil.lojack.utils;

import java.sql.SQLException;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;

import com.tdil.ibatis.TransactionProvider;
import com.tdil.log4j.LoggerProvider;
import com.tdil.lojack.daomanager.DAOManager;
import com.tdil.lojack.model.SystemUser;
import com.tdil.lojack.model.SystemUserExample;
import com.tdil.struts.TransactionalActionWithResult;
import com.tdil.utils.CryptoUtils;

public class SystemUserUtils {

	private static final class GetSystemUser implements TransactionalActionWithResult {
		private String username;
		private String password;
		
		public GetSystemUser(String username, String password) {
			super();
			this.username = username;
			this.password = password;
		}
		public Object executeInTransaction() throws SQLException {
			SystemUserExample example = new SystemUserExample();
			example.createCriteria().andUsernameEqualTo(this.username);
			List<SystemUser> list = DAOManager.getSystemUserDAO().selectSystemUserByExample(example);
			if (list.isEmpty()) {
				return null;
			} else {
				SystemUser su = list.get(0);
				if (su.getPassword().equals(CryptoUtils.getHashedValue(password))) {
					return su;
				} else {
					return null;
				}
			}
		}
	}
	
	public static SystemUser getSystemUser(String username, String password) {
		if (StringUtils.isEmpty(username)) {
			return null;
		}
		if (StringUtils.isEmpty(password)) {
			return null;
		}
		try {
			return (SystemUser)TransactionProvider.executeInTransactionWithResult(new GetSystemUser(username, password));
		} catch (SQLException e) {
			getLog().error(e.getMessage(), e);
			return null;
		} 
	}
	
	private static Logger getLog() {
		return LoggerProvider.getLogger(SystemUserUtils.class);
	}
	
}
