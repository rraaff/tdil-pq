package com.tdil.tuafesta.daomanager;

import java.sql.SQLException;

import com.tdil.ibatis.IBatisManager;
import com.tdil.tuafesta.dao.BlobDataDAO;
import com.tdil.tuafesta.dao.NotificationEmailDAO;
import com.tdil.tuafesta.dao.RawInsertDAO;
import com.tdil.tuafesta.dao.SystemPropertyDAO;
import com.tdil.tuafesta.dao.SystemUserDAO;
import com.tdil.tuafesta.dao.impl.BlobDataDAOImpl;
import com.tdil.tuafesta.dao.impl.NotificationEmailDAOImpl;
import com.tdil.tuafesta.dao.impl.RawInsertDAOImpl;
import com.tdil.tuafesta.dao.impl.SystemPropertyDAOImpl;
import com.tdil.tuafesta.dao.impl.SystemUserDAOImpl;

public class DAOManager {


	public static SystemPropertyDAO getSystemPropertyDAO() throws SQLException {
		return new SystemPropertyDAOImpl(IBatisManager.getClient());
	}
	
	public static SystemUserDAO getSystemUserDAO() throws SQLException {
		return new SystemUserDAOImpl(IBatisManager.getClient());
	}
	
	public static BlobDataDAO getBlobDataDAO() throws SQLException {
		return new BlobDataDAOImpl(IBatisManager.getClient());
	}
	
	public static NotificationEmailDAO getNotificationEmailDAO() throws SQLException {
		return new NotificationEmailDAOImpl(IBatisManager.getClient());
	}
	
	public static RawInsertDAO getRawInsertDAO() throws SQLException {
		return new RawInsertDAOImpl(IBatisManager.getClient());
	}
	
}
