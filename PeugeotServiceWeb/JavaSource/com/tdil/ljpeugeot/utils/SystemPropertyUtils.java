package com.tdil.ljpeugeot.utils;

import java.net.MalformedURLException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import com.tdil.ibatis.TransactionProvider;
import com.tdil.ljpeugeot.daomanager.DAOManager;
import com.tdil.ljpeugeot.model.SystemProperty;
import com.tdil.ljpeugeot.model.SystemPropertyExample;
import com.tdil.log4j.LoggerProvider;
import com.tdil.struts.TransactionalAction;
import com.tdil.struts.TransactionalActionWithResult;
import com.tdil.struts.ValidationException;

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
			systemPropertyExample.createCriteria().andPropkeyEqualTo(key).andDeletedEqualTo(0);
			return DAOManager.getSystemPropertyDAO().selectSystemPropertyByExample(systemPropertyExample);
		}
	}
	
	private static final class ChangeSystemProperty implements TransactionalAction {
		
		private String key;
		private String value;
		
		public ChangeSystemProperty(String key, String value) {
			super();
			this.key = key;
			this.value = value;
		}

		public void executeInTransaction() throws SQLException {
			SystemPropertyExample systemPropertyExample = new SystemPropertyExample();
			systemPropertyExample.createCriteria().andPropkeyEqualTo(key);
			List<SystemProperty> list = DAOManager.getSystemPropertyDAO().selectSystemPropertyByExample(systemPropertyExample);
			if (!list.isEmpty()) {
				SystemProperty sp = list.get(0);
				sp.setPropvalue(this.value);
				DAOManager.getSystemPropertyDAO().updateSystemPropertyByPrimaryKey(sp);
			}
		}
	}
	
	private static final class ToggleDeleteSystemProperty implements TransactionalAction {
		
		private int id;
		
		public ToggleDeleteSystemProperty(int id) {
			super();
			this.id = id;
		}

		public void executeInTransaction() throws SQLException {
			SystemProperty sp = DAOManager.getSystemPropertyDAO().selectSystemPropertyByPrimaryKey(this.id);
			if (sp.getDeleted().equals(1)) {
				sp.setDeleted(0);
			} else {
				sp.setDeleted(1);
			}
			DAOManager.getSystemPropertyDAO().updateSystemPropertyByPrimaryKey(sp);
		}
	}
	
	private static final class GetSystemProperties implements TransactionalActionWithResult {
		
		public GetSystemProperties() {
			super();
		}

		public Object executeInTransaction() throws SQLException {
			SystemPropertyExample systemPropertyExample = new SystemPropertyExample();
			systemPropertyExample.setOrderByClause("propKey");
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
	
	public static List<SystemProperty> getSystemProperties() {
		try {
			List<SystemProperty> result = (List<SystemProperty>)TransactionProvider.executeInTransactionWithResult(new GetSystemProperties());
			return result;
		} catch (SQLException e) {
			getLog().error(e.getMessage(), e);
			return new ArrayList<SystemProperty>();
		}
	}
	
	public static void updateSystemProperty(String key, String value) {
		try {
			TransactionProvider.executeInTransaction(new ChangeSystemProperty(key, value));
		} catch (SQLException e) {
		} catch (ValidationException e) {
		}
	}
	
	public static void toggleDelete(int id) {
		try {
			TransactionProvider.executeInTransaction(new ToggleDeleteSystemProperty(id));
		} catch (SQLException e) {
		} catch (ValidationException e) {
		}
	}
	
	public static void reloadSysProperties() {
		try {
			TransactionProvider.executeInTransaction(new TransactionalAction() {
				
				@Override
				public void executeInTransaction() throws SQLException, ValidationException {
					try {
						LJPeugeotConfig.basicInitSystem();
					} catch (MalformedURLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			});
		} catch (SQLException e) {
		} catch (ValidationException e) {
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
