package com.tdil.milka.daomanager;

import java.sql.SQLException;

import com.tdil.ibatis.IBatisManager;
import com.tdil.milka.dao.BlobDataDAO;
import com.tdil.milka.dao.SystemPropertyDAO;
import com.tdil.milka.dao.SystemUserDAO;
import com.tdil.milka.dao.impl.BlobDataDAOImpl;
import com.tdil.milka.dao.impl.SystemPropertyDAOImpl;
import com.tdil.milka.dao.impl.SystemUserDAOImpl;

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
}
