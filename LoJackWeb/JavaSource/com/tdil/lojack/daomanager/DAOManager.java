package com.tdil.lojack.daomanager;

import java.sql.SQLException;

import com.tdil.ibatis.IBatisManager;
import com.tdil.lojack.dao.AlarmConfDAO;
import com.tdil.lojack.dao.AsyncJobDAO;
import com.tdil.lojack.dao.BlobDataDAO;
import com.tdil.lojack.dao.CacheRegionDAO;
import com.tdil.lojack.dao.LightConfDAO;
import com.tdil.lojack.dao.PointOfInterestDAO;
import com.tdil.lojack.dao.SystemPropertyDAO;
import com.tdil.lojack.dao.WebsiteUserDAO;
import com.tdil.lojack.dao.impl.AlarmConfDAOImpl;
import com.tdil.lojack.dao.impl.AsyncJobDAOImpl;
import com.tdil.lojack.dao.impl.BlobDataDAOImpl;
import com.tdil.lojack.dao.impl.CacheRegionDAOImpl;
import com.tdil.lojack.dao.impl.LightConfDAOImpl;
import com.tdil.lojack.dao.impl.PointOfInterestDAOImpl;
import com.tdil.lojack.dao.impl.SystemPropertyDAOImpl;
import com.tdil.lojack.dao.impl.WebsiteUserDAOImpl;

public abstract class DAOManager {

	private static DAOProvider currentDao;

	public static SystemPropertyDAO getSystemPropertyDAO() throws SQLException {
		return currentDao.getSystemPropertyDAO();
	}

	public static WebsiteUserDAO getWebsiteUserDAO() throws SQLException {
		return currentDao.getWebsiteUserDAO();
	}

	public static AlarmConfDAO getAlarmConfDAO() throws SQLException {
		return currentDao.getAlarmConfDAO();
	}

	public static LightConfDAO getLightConfDAO() throws SQLException {
		return currentDao.getLightConfDAO();
	}

	public static AsyncJobDAO getAsyncJobDAO() throws SQLException {
		return currentDao.getAsyncJobDAO();
	}

	public static BlobDataDAO getBlobDataDAO() throws SQLException {
		return currentDao.getBlobDataDAO();
	}

	public static CacheRegionDAO getCacheRegionDAO() throws SQLException {
		return currentDao.getCacheRegionDAO();
	}

	public static PointOfInterestDAO getPointOfInterestDAO() throws SQLException {
		return currentDao.getPointOfInterestDAO();
	}

	public static DAOProvider getCurrentDao() {
		return currentDao;
	}

	public static void setCurrentDao(DAOProvider currentDao) {
		DAOManager.currentDao = currentDao;
	}
	
}