package com.tdil.lojack.utils;

import java.sql.SQLException;
import java.util.List;

import org.apache.log4j.Logger;

import com.tdil.ibatis.TransactionProvider;
import com.tdil.log4j.LoggerProvider;
import com.tdil.lojack.daomanager.DAOManager;
import com.tdil.lojack.model.WebsiteUserExample;
import com.tdil.struts.TransactionalAction;
import com.tdil.struts.TransactionalActionWithResult;
import com.tdil.struts.ValidationException;

public class WebsiteUserUtils {
	
	private static final class InitWebSiteUser implements TransactionalAction {
		private String lojackUserId;
		public InitWebSiteUser(String lojackUserId) {
			super();
			this.lojackUserId = lojackUserId;
		}
		public void executeInTransaction() throws SQLException {
			WebsiteUserExample example = new WebsiteUserExample();
			example.createCriteria().andLojackuseridEqualTo(lojackUserId);
			List<com.tdil.lojack.model.WebsiteUser> users = DAOManager.getWebsiteUserDAO().selectWebsiteUserByExample(example);
			if (users.isEmpty()) {
				com.tdil.lojack.model.WebsiteUser user = new com.tdil.lojack.model.WebsiteUser();
				user.setLojackuserid(this.lojackUserId);
				user.setDeleted(0);
				DAOManager.getWebsiteUserDAO().insertWebsiteUser(user);
			}
		}
	}

	private static final class GetWebSiteUser implements TransactionalActionWithResult {
		private String lojackUserId;
		public GetWebSiteUser(String lojackUserId) {
			super();
			this.lojackUserId = lojackUserId;
		}
		public Object executeInTransaction() throws SQLException {
			WebsiteUserExample example = new WebsiteUserExample();
			example.createCriteria().andLojackuseridEqualTo(lojackUserId);
			return DAOManager.getWebsiteUserDAO().selectWebsiteUserByExample(example);
		}
	}
	
	@SuppressWarnings("unchecked")
	public static com.tdil.lojack.model.WebsiteUser getWebSiteUser(String lojackUserId) {
		try {
			TransactionProvider.executeInTransaction(new InitWebSiteUser(lojackUserId));
			List<com.tdil.lojack.model.WebsiteUser> result = (List<com.tdil.lojack.model.WebsiteUser>)TransactionProvider.executeInTransactionWithResult(new GetWebSiteUser(lojackUserId));
			if (result.size() > 0) {
				return result.get(0);
			} else {
				return null;
			}
		} catch (SQLException e) {
			getLog().error(e.getMessage(), e);
			return null;
		} catch (ValidationException e) {
			getLog().error(e.getMessage(), e);
			return null;
		}
	}
	
	public static boolean hasAvatar(com.tdil.lojack.model.WebsiteUser usr) {
		if (usr == null) {
			return false;
		}
		return usr.getIdAvatar() != null && usr.getIdAvatar() != 0;
	}
	
	private static Logger getLog() {
		return LoggerProvider.getLogger(WebsiteUserUtils.class);
	}
	
}
