package com.tdil.lojack.daomanager;

import java.sql.SQLException;

import com.tdil.ibatis.IBatisManager;
import com.tdil.lojack.dao.AlarmConfDAO;
import com.tdil.lojack.dao.AsyncJobDAO;
import com.tdil.lojack.dao.BlobDataDAO;
import com.tdil.lojack.dao.CacheRegionDAO;
import com.tdil.lojack.dao.CameraConfDAO;
import com.tdil.lojack.dao.LightConfDAO;
import com.tdil.lojack.dao.NativeAppDAO;
import com.tdil.lojack.dao.PointOfInterestDAO;
import com.tdil.lojack.dao.SystemPropertyDAO;
import com.tdil.lojack.dao.SystemUserDAO;
import com.tdil.lojack.dao.VLUDataDAO;
import com.tdil.lojack.dao.VLUImportDAO;
import com.tdil.lojack.dao.VLUImportErrorDAO;
import com.tdil.lojack.dao.WebsiteUserDAO;
import com.tdil.lojack.dao.impl.sqlserver.AlarmConfDAOImpl;
import com.tdil.lojack.dao.impl.sqlserver.AsyncJobDAOImpl;
import com.tdil.lojack.dao.impl.sqlserver.BlobDataDAOImpl;
import com.tdil.lojack.dao.impl.sqlserver.CacheRegionDAOImpl;
import com.tdil.lojack.dao.impl.sqlserver.CameraConfDAOImpl;
import com.tdil.lojack.dao.impl.sqlserver.LightConfDAOImpl;
import com.tdil.lojack.dao.impl.sqlserver.NativeAppDAOImpl;
import com.tdil.lojack.dao.impl.sqlserver.PointOfInterestDAOImpl;
import com.tdil.lojack.dao.impl.sqlserver.SystemPropertyDAOImpl;
import com.tdil.lojack.dao.impl.sqlserver.SystemUserDAOImpl;
import com.tdil.lojack.dao.impl.sqlserver.VLUDataDAOImpl;
import com.tdil.lojack.dao.impl.sqlserver.VLUImportDAOImpl;
import com.tdil.lojack.dao.impl.sqlserver.VLUImportErrorDAOImpl;
import com.tdil.lojack.dao.impl.sqlserver.WebsiteUserDAOImpl;

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
	public NativeAppDAO getNativeAppDAO() throws SQLException {
		return new NativeAppDAOImpl(IBatisManager.getClient());
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
