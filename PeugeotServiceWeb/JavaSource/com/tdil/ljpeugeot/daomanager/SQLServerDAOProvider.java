package com.tdil.ljpeugeot.daomanager;

import java.sql.SQLException;

import com.tdil.ibatis.IBatisManager;
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
import com.tdil.ljpeugeot.dao.impl.sqlserver.AlarmConfDAOImpl;
import com.tdil.ljpeugeot.dao.impl.sqlserver.AsyncJobDAOImpl;
import com.tdil.ljpeugeot.dao.impl.sqlserver.BlobDataDAOImpl;
import com.tdil.ljpeugeot.dao.impl.sqlserver.CacheRegionDAOImpl;
import com.tdil.ljpeugeot.dao.impl.sqlserver.CameraConfDAOImpl;
import com.tdil.ljpeugeot.dao.impl.sqlserver.LightConfDAOImpl;
import com.tdil.ljpeugeot.dao.impl.sqlserver.PointOfInterestDAOImpl;
import com.tdil.ljpeugeot.dao.impl.sqlserver.SystemPropertyDAOImpl;
import com.tdil.ljpeugeot.dao.impl.sqlserver.SystemUserDAOImpl;
import com.tdil.ljpeugeot.dao.impl.sqlserver.VLUDataDAOImpl;
import com.tdil.ljpeugeot.dao.impl.sqlserver.VLUImportDAOImpl;
import com.tdil.ljpeugeot.dao.impl.sqlserver.VLUImportErrorDAOImpl;
import com.tdil.ljpeugeot.dao.impl.sqlserver.WebsiteUserDAOImpl;

public class SQLServerDAOProvider implements DAOProvider {

	public SystemPropertyDAO getSystemPropertyDAO() throws SQLException {
		return new SystemPropertyDAOImpl(IBatisManager.getClient());
	}

	public WebsiteUserDAO getWebsiteUserDAO() throws SQLException {
		return new WebsiteUserDAOImpl(IBatisManager.getClient());
	}

	public AlarmConfDAO getAlarmConfDAO() throws SQLException {
		return new AlarmConfDAOImpl(IBatisManager.getClient());
	}

	public LightConfDAO getLightConfDAO() throws SQLException {
		return new LightConfDAOImpl(IBatisManager.getClient());
	}

	public AsyncJobDAO getAsyncJobDAO() throws SQLException {
		return new AsyncJobDAOImpl(IBatisManager.getClient());
	}

	public BlobDataDAO getBlobDataDAO() throws SQLException {
		return new BlobDataDAOImpl(IBatisManager.getClient());
	}

	public CacheRegionDAO getCacheRegionDAO() throws SQLException {
		return new CacheRegionDAOImpl(IBatisManager.getClient());
	}

	public PointOfInterestDAO getPointOfInterestDAO() throws SQLException {
		return new PointOfInterestDAOImpl(IBatisManager.getClient());
	}
	
	@Override
	public SystemUserDAO getSystemUserDAO() throws SQLException {
		return new SystemUserDAOImpl(IBatisManager.getClient());
	}
	@Override
	public CameraConfDAO getCameraConfDAO() throws SQLException {
		return new CameraConfDAOImpl(IBatisManager.getClient());
	}
	
	@Override
	public VLUDataDAO getVLUDataDAO() throws SQLException {
		return new VLUDataDAOImpl(IBatisManager.getClient());
	}
	@Override
	public VLUImportDAO getVLUImportDAO() throws SQLException {
		return new VLUImportDAOImpl(IBatisManager.getClient());
	}
	@Override
	public VLUImportErrorDAO getVLUImportErrorDAO() throws SQLException {
		return new VLUImportErrorDAOImpl(IBatisManager.getClient());
	}
	@Override
	public String getVLU_DATATableName() {
		return "dbo.VLU_DATA";
	}
	@Override
	public String getVLU_IMPORTTableName() {
		return "dbo.VLU_IMPORT";
	}
}
