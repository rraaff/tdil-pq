package com.tdil.ljpeugeot.daomanager;

import java.sql.SQLException;

import com.tdil.ljpeugeot.dao.AlarmConfDAO;
import com.tdil.ljpeugeot.dao.AsyncJobDAO;
import com.tdil.ljpeugeot.dao.BlobDataDAO;
import com.tdil.ljpeugeot.dao.CacheRegionDAO;
import com.tdil.ljpeugeot.dao.CameraConfDAO;
import com.tdil.ljpeugeot.dao.LightConfDAO;
import com.tdil.ljpeugeot.dao.PointOfInterestDAO;
import com.tdil.ljpeugeot.dao.SystemPropertyDAO;
import com.tdil.ljpeugeot.dao.SystemUserDAO;
import com.tdil.ljpeugeot.dao.VLUDataDAO;
import com.tdil.ljpeugeot.dao.VLUImportDAO;
import com.tdil.ljpeugeot.dao.VLUImportErrorDAO;
import com.tdil.ljpeugeot.dao.WebsiteUserDAO;

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
	
	public abstract VLUDataDAO getVLUDataDAO() throws SQLException;
	public abstract VLUImportDAO getVLUImportDAO() throws SQLException;
	public abstract VLUImportErrorDAO getVLUImportErrorDAO() throws SQLException;

	public abstract String getVLU_DATATableName();

	public abstract String getVLU_IMPORTTableName();

}