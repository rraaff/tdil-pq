package com.tdil.lojack.daomanager;

import java.sql.SQLException;

import com.tdil.lojack.dao.AlarmConfDAO;
import com.tdil.lojack.dao.AsyncJobDAO;
import com.tdil.lojack.dao.BlobDataDAO;
import com.tdil.lojack.dao.CacheRegionDAO;
import com.tdil.lojack.dao.CameraConfDAO;
import com.tdil.lojack.dao.LightConfDAO;
import com.tdil.lojack.dao.PointOfInterestDAO;
import com.tdil.lojack.dao.SystemPropertyDAO;
import com.tdil.lojack.dao.SystemUserDAO;
import com.tdil.lojack.dao.VLUDataDAO;
import com.tdil.lojack.dao.VLUImportDAO;
import com.tdil.lojack.dao.VLUImportErrorDAO;
import com.tdil.lojack.dao.WebsiteUserDAO;

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
	
	public static SystemUserDAO getSystemUserDAO() throws SQLException {
		return currentDao.getSystemUserDAO();
	}
	
	public static CameraConfDAO getCameraConfDAO() throws SQLException {
		return currentDao.getCameraConfDAO();
	}
	
	public static VLUDataDAO getVLUDataDAO() throws SQLException {
		return currentDao.getVLUDataDAO();
	}
	public static VLUImportDAO getVLUImportDAO() throws SQLException {
		return currentDao.getVLUImportDAO();
	}
	public static VLUImportErrorDAO getVLUImportErrorDAO() throws SQLException {
		return currentDao.getVLUImportErrorDAO();
	}
	
	public static String getVLU_DATATableName() throws SQLException {
		return currentDao.getVLU_DATATableName();
	}
	public static String getVLU_IMPORTTableName() throws SQLException {
		return currentDao.getVLU_IMPORTTableName();
	}
	

	public static DAOProvider getCurrentDao() {
		return currentDao;
	}

	public static void setCurrentDao(DAOProvider currentDao) {
		DAOManager.currentDao = currentDao;
	}
	
}