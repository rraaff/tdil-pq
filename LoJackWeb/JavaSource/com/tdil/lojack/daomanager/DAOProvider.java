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
import com.tdil.lojack.dao.WebsiteUserDAO;

public interface DAOProvider {

	public abstract SystemPropertyDAO getSystemPropertyDAO() throws SQLException;

	public abstract WebsiteUserDAO getWebsiteUserDAO() throws SQLException;

	public abstract AlarmConfDAO getAlarmConfDAO() throws SQLException;

	public abstract LightConfDAO getLightConfDAO() throws SQLException;

	public abstract AsyncJobDAO getAsyncJobDAO() throws SQLException;

	public abstract BlobDataDAO getBlobDataDAO() throws SQLException;

	public abstract CacheRegionDAO getCacheRegionDAO() throws SQLException;

	public abstract PointOfInterestDAO getPointOfInterestDAO() throws SQLException;

	public abstract SystemUserDAO getSystemUserDAO() throws SQLException;
	
	public abstract CameraConfDAO getCameraConfDAO() throws SQLException;

}