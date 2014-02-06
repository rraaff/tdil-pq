package com.tdil.ljpeugeot.services;

import java.sql.SQLException;
import java.util.List;

import org.apache.log4j.Logger;

import com.tdil.ljpeugeot.daomanager.DAOManager;
import com.tdil.ljpeugeot.model.SystemUser;
import com.tdil.ljpeugeot.model.SystemUserExample;
import com.tdil.log4j.LoggerProvider;
import com.tdil.struts.TransactionalActionWithResult;
import com.tdil.struts.ValidationException;
import com.tdil.subsystem.generic.GenericTransactionExecutionService;

public class CCServices {

	private static final class GetCCUser implements TransactionalActionWithResult<SystemUser> {
		
		private String username;
		
		public GetCCUser(String username) {
			super();
			this.username = username;
		}
		public SystemUser executeInTransaction() throws SQLException {
			SystemUserExample stateExample = new SystemUserExample();
			stateExample.createCriteria().andUsernameEqualTo(this.username).andDeletedEqualTo(0).andTypeEqualTo(1);
			List<SystemUser> result = DAOManager.getSystemUserDAO().selectSystemUserByExample(stateExample);
			if (result.size() > 0) {
				return result.get(0);
			} else {
				return null;
			}
		}
	}
	
	public static SystemUser getSystemUser(String username) {
		try {
			return GenericTransactionExecutionService.getInstance().execute(new GetCCUser(username));
		} catch (SQLException e) {
			getLog().error(e.getMessage(), e);
			return null;
		} catch (ValidationException e) {
			getLog().error(e.getMessage(), e);
			return null;
		} 
	}
	
	private static Logger getLog() {
		return LoggerProvider.getLogger(CCServices.class);
	}
	
}
