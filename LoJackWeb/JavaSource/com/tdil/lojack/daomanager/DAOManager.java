package com.tdil.lojack.daomanager;

import java.sql.SQLException;

import com.tdil.ibatis.IBatisManager;
import com.tdil.lojack.dao.BlobDataDAO;
import com.tdil.lojack.dao.CacheRegionDAO;
import com.tdil.lojack.dao.SystemPropertyDAO;
import com.tdil.lojack.dao.WebsiteUserDAO;
import com.tdil.lojack.dao.impl.BlobDataDAOImpl;
import com.tdil.lojack.dao.impl.CacheRegionDAOImpl;
import com.tdil.lojack.dao.impl.SystemPropertyDAOImpl;
import com.tdil.lojack.dao.impl.WebsiteUserDAOImpl;

public class DAOManager {


	public static SystemPropertyDAO getSystemPropertyDAO() throws SQLException {
		return new SystemPropertyDAOImpl(IBatisManager.getClient());
	}
	
	public static WebsiteUserDAO getWebsiteUserDAO() throws SQLException {
		return new WebsiteUserDAOImpl(IBatisManager.getClient());
	}
	
	public static BlobDataDAO getBlobDataDAO() throws SQLException {
		return new BlobDataDAOImpl(IBatisManager.getClient());
	}
	
	public static CacheRegionDAO getCacheRegionDAO() throws SQLException {
		return new CacheRegionDAOImpl(IBatisManager.getClient());
	}
	
}