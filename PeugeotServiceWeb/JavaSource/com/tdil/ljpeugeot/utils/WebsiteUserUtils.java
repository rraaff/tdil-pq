package com.tdil.ljpeugeot.utils;

import java.sql.SQLException;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;

import com.tdil.ibatis.TransactionProvider;
import com.tdil.ljpeugeot.daomanager.DAOManager;
import com.tdil.ljpeugeot.model.WebsiteUser;
import com.tdil.ljpeugeot.model.WebsiteUserExample;
import com.tdil.ljpeugeot.model.WebsiteUserExample.Criteria;
import com.tdil.log4j.LoggerProvider;
import com.tdil.struts.TransactionalAction;
import com.tdil.struts.TransactionalActionWithResult;
import com.tdil.struts.ValidationException;
import com.tdil.subsystem.generic.GenericTransactionExecutionService;

public class WebsiteUserUtils {
	
	private static final class InitWebSiteUser implements TransactionalAction {
		private String lojackUserId;
		private String homeUserId;
		private String preventUserId;
		private String petUserId;
		private String dni;
		
		public InitWebSiteUser(String dni, String lojackUserId, String homeUserId, String preventUserId, String petUserId) {
			super();
			this.lojackUserId = lojackUserId;
			this.homeUserId = homeUserId;
			this.preventUserId = preventUserId;
			this.petUserId = petUserId;
			this.dni = dni;
		}
		public void executeInTransaction() throws SQLException {
			WebsiteUserExample example = new WebsiteUserExample();
			example.createCriteria().andLojackuseridEqualTo(lojackUserId);
			List<com.tdil.ljpeugeot.model.WebsiteUser> users = DAOManager.getWebsiteUserDAO().selectWebsiteUserByExample(example);
			if (users.isEmpty()) {
				com.tdil.ljpeugeot.model.WebsiteUser user = new com.tdil.ljpeugeot.model.WebsiteUser();
				user.setDni(this.dni);
				user.setLojackuserid(this.lojackUserId);
				user.setHomeuserid(this.homeUserId);
				user.setPreventuserid(this.preventUserId);
				user.setPetuserid(this.petUserId);
				user.setDeleted(0);
				DAOManager.getWebsiteUserDAO().insertWebsiteUser(user);
			} else {
				com.tdil.ljpeugeot.model.WebsiteUser user = users.get(0);
				user.setDni(this.dni);
				user.setHomeuserid(this.homeUserId);
				user.setPreventuserid(this.preventUserId);
				user.setPetuserid(this.petUserId);
				user.setDeleted(0);
				DAOManager.getWebsiteUserDAO().updateWebsiteUserByPrimaryKey(user);
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
			return DAOManager.getWebsiteUserDAO().selectWebsiteUserByExample(example).get(0);
		}
	}
	
	private static final class GetWebSiteUserByid implements TransactionalActionWithResult {
		private int id;
		public GetWebSiteUserByid(int lojackUserId) {
			super();
			this.id = lojackUserId;
		}
		public WebsiteUser executeInTransaction() throws SQLException {
			return DAOManager.getWebsiteUserDAO().selectWebsiteUserByPrimaryKey(this.id);
		}
	}
	
	private static final class GetWebSiteUserByDniAndEmail implements TransactionalActionWithResult<WebsiteUser> {
		private String dni;
		private String email;
		
		public GetWebSiteUserByDniAndEmail(String dni, String email) {
			super();
			this.dni = dni;
			this.email = email;
		}
		public WebsiteUser executeInTransaction() throws SQLException {
			WebsiteUserExample example = new WebsiteUserExample();
			Criteria criteria = example.createCriteria();
			if (!StringUtils.isEmpty(this.dni) || !StringUtils.isEmpty(this.email)) {
				if (!StringUtils.isEmpty(this.dni)) {
					criteria.andDniEqualTo(this.dni);
				}
				if (!StringUtils.isEmpty(this.email)) {
					criteria.andEmailEqualTo(this.email);
				}
				List<WebsiteUser> users = DAOManager.getWebsiteUserDAO().selectWebsiteUserByExample(example);
				if (users.size() > 0) {
					return users.get(0);
				} else {
					return null;
				}
			} else {
				return null;
			}
		}
	}
	
	private static final class GetWebSiteUserByHomeUserId implements TransactionalActionWithResult {
		private String lojackUserId;
		public GetWebSiteUserByHomeUserId(String lojackUserId) {
			super();
			this.lojackUserId = lojackUserId;
		}
		public Object executeInTransaction() throws SQLException {
			WebsiteUserExample example = new WebsiteUserExample();
			example.createCriteria().andHomeuseridEqualTo(lojackUserId);
			List<WebsiteUser> result = DAOManager.getWebsiteUserDAO().selectWebsiteUserByExample(example);
			if (result.isEmpty()) {
				return null;
			} else {
				return result.get(0);
			}
		}
	}
	
	public static com.tdil.ljpeugeot.model.WebsiteUser getWebSiteUserByDniAndEmail(String dni, String email) {
		try {
			return GenericTransactionExecutionService.getInstance().execute(new GetWebSiteUserByDniAndEmail(dni, email));
		} catch (SQLException e) {
			getLog().error(e.getMessage(), e);
			return null;
		} catch (ValidationException e) {
			getLog().error(e.getMessage(), e);
			return null;
		} 
	}
	
	public static com.tdil.ljpeugeot.model.WebsiteUser getWebSiteUserByHomeUserId(String lojackUserId) {
		if (StringUtils.isEmpty(lojackUserId)) {
			return null;
		}
		try {
			return (com.tdil.ljpeugeot.model.WebsiteUser)GenericTransactionExecutionService.getInstance().execute(new GetWebSiteUserByHomeUserId(lojackUserId));
		} catch (SQLException e) {
			getLog().error(e.getMessage(), e);
			return null;
		} catch (ValidationException e) {
			getLog().error(e.getMessage(), e);
			return null;
		} 
	}
	
	public static com.tdil.ljpeugeot.model.WebsiteUser getWebSiteUser(String lojackUserId) {
		try {
			return (com.tdil.ljpeugeot.model.WebsiteUser)TransactionProvider.executeInTransactionWithResult(new GetWebSiteUser(lojackUserId));
		} catch (SQLException e) {
			getLog().error(e.getMessage(), e);
			return null;
		} 
	}
	
	public static com.tdil.ljpeugeot.model.WebsiteUser getWebSiteUserById(int id) {
		try {
			return (com.tdil.ljpeugeot.model.WebsiteUser)TransactionProvider.executeInTransactionWithResult(new GetWebSiteUserByid(id));
		} catch (SQLException e) {
			getLog().error(e.getMessage(), e);
			return null;
		} 
	}
	
	public static com.tdil.ljpeugeot.model.WebsiteUser getWebSiteUserUpdatingData(String dni, String lojackUserId, String homeUserId, String preventUserId, String petUserId) {
		try {
			TransactionProvider.executeInTransaction(new InitWebSiteUser(dni, lojackUserId, homeUserId, preventUserId, petUserId));
			return (com.tdil.ljpeugeot.model.WebsiteUser)TransactionProvider.executeInTransactionWithResult(new GetWebSiteUser(lojackUserId));
		} catch (SQLException e) {
			getLog().error(e.getMessage(), e);
			return null;
		} catch (ValidationException e) {
			getLog().error(e.getMessage(), e);
			return null;
		}
	}
	
	public static boolean hasAvatar(com.tdil.ljpeugeot.model.WebsiteUser usr) {
		if (usr == null) {
			return false;
		}
		return usr.getIdAvatar() != null && usr.getIdAvatar() != 0;
	}
	
	private static Logger getLog() {
		return LoggerProvider.getLogger(WebsiteUserUtils.class);
	}
	
}
