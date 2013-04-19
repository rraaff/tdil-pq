package com.tdil.lojack.utils;

import java.sql.SQLException;
import java.util.List;

import org.apache.log4j.Logger;

import com.tdil.ibatis.TransactionProvider;
import com.tdil.log4j.LoggerProvider;
import com.tdil.lojack.daomanager.DAOManager;
import com.tdil.lojack.model.WebsiteUserExample;
import com.tdil.struts.TransactionalActionWithResult;

public class WebsiteUserUtils {

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
			List<com.tdil.lojack.model.WebsiteUser> result = (List<com.tdil.lojack.model.WebsiteUser>)TransactionProvider.executeInTransactionWithResult(new GetWebSiteUser(lojackUserId));
			if (result.size() > 0) {
				return result.get(0);
			} else {
				return null;
			}
		} catch (SQLException e) {
			getLog().error(e.getMessage(), e);
			return null;
		}
	}
	
	private static Logger getLog() {
		return LoggerProvider.getLogger(WebsiteUserUtils.class);
	}
	
}
