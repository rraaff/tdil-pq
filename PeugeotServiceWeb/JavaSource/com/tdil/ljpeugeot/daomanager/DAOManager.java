package com.tdil.ljpeugeot.daomanager;

import java.sql.SQLException;

import com.tdil.ljpeugeot.dao.BlobDataDAO;
import com.tdil.ljpeugeot.dao.CacheRegionDAO;
import com.tdil.ljpeugeot.dao.CityDAO;
import com.tdil.ljpeugeot.dao.DataImportDAO;
import com.tdil.ljpeugeot.dao.DealerDAO;
import com.tdil.ljpeugeot.dao.ModelDAO;
import com.tdil.ljpeugeot.dao.PointOfInterestDAO;
import com.tdil.ljpeugeot.dao.StateDAO;
import com.tdil.ljpeugeot.dao.SystemPropertyDAO;
import com.tdil.ljpeugeot.dao.SystemUserDAO;
import com.tdil.ljpeugeot.dao.WebsiteUserDAO;

public abstract class DAOManager {

	private static DAOProvider currentDao;

	public static SystemPropertyDAO getSystemPropertyDAO() throws SQLException {
		return currentDao.getSystemPropertyDAO();
	}

	public static WebsiteUserDAO getWebsiteUserDAO() throws SQLException {
		return currentDao.getWebsiteUserDAO();
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
	
	public static SystemUserDAO getSystemUserDAO() throws SQLException {
		return currentDao.getSystemUserDAO();
	}
	
	public static StateDAO getStateDAO() throws SQLException {
		return currentDao.getStateDAO();
	}
	
	public static CityDAO getCityDAO() throws SQLException {
		return currentDao.getCityDAO();
	}
	
	public static DealerDAO getDealerDAO() throws SQLException {
		return currentDao.getDealerDAO();
	}
	
	public static ModelDAO getModelDAO() throws SQLException {
		return currentDao.getModelDAO();
	}
	
	public static DataImportDAO getDataImportDAO() throws SQLException {
		return currentDao.getDataImportDAO();
	}
	
		public static DAOProvider getCurrentDao() {
		return currentDao;
	}

	public static void setCurrentDao(DAOProvider currentDao) {
		DAOManager.currentDao = currentDao;
	}
	
}