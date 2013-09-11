package com.tdil.lojack.daomanager;

import java.sql.SQLException;

import com.tdil.ibatis.IBatisManager;
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
import com.tdil.lojack.dao.impl.sqlserver.SystemUserDAOImpl;
import com.tdil.lojack.dao.impl.sqlserver.AlarmConfDAOImpl;
import com.tdil.lojack.dao.impl.sqlserver.AsyncJobDAOImpl;
import com.tdil.lojack.dao.impl.sqlserver.BlobDataDAOImpl;
import com.tdil.lojack.dao.impl.sqlserver.CacheRegionDAOImpl;
import com.tdil.lojack.dao.impl.sqlserver.LightConfDAOImpl;
import com.tdil.lojack.dao.impl.sqlserver.PointOfInterestDAOImpl;
import com.tdil.lojack.dao.impl.sqlserver.SystemPropertyDAOImpl;
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
		// TODO Auto-generated method stub
		return null;
	}
}
