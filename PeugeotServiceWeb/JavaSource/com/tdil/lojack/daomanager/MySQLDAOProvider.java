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
import com.tdil.lojack.dao.VLUDataDAO;
import com.tdil.lojack.dao.VLUImportDAO;
import com.tdil.lojack.dao.VLUImportErrorDAO;
import com.tdil.lojack.dao.WebsiteUserDAO;
import com.tdil.lojack.dao.impl.AlarmConfDAOImpl;
import com.tdil.lojack.dao.impl.AsyncJobDAOImpl;
import com.tdil.lojack.dao.impl.BlobDataDAOImpl;
import com.tdil.lojack.dao.impl.CacheRegionDAOImpl;
import com.tdil.lojack.dao.impl.CameraConfDAOImpl;
import com.tdil.lojack.dao.impl.LightConfDAOImpl;
import com.tdil.lojack.dao.impl.PointOfInterestDAOImpl;
import com.tdil.lojack.dao.impl.SystemPropertyDAOImpl;
import com.tdil.lojack.dao.impl.SystemUserDAOImpl;
import com.tdil.lojack.dao.impl.VLUDataDAOImpl;
import com.tdil.lojack.dao.impl.VLUImportDAOImpl;
import com.tdil.lojack.dao.impl.VLUImportErrorDAOImpl;
import com.tdil.lojack.dao.impl.WebsiteUserDAOImpl;

public class MySQLDAOProvider implements DAOProvider {

	
	/* (non-Javadoc)
	 * @see com.tdil.lojack.daomanager.DAOProvider#getSystemPropertyDAO()
	 */
	@Override
	public SystemPropertyDAO getSystemPropertyDAO() throws SQLException {
		return new SystemPropertyDAOImpl(IBatisManager.getClient());
	}

	/* (non-Javadoc)
	 * @see com.tdil.lojack.daomanager.DAOProvider#getWebsiteUserDAO()
	 */
	@Override
	public WebsiteUserDAO getWebsiteUserDAO() throws SQLException {
		return new WebsiteUserDAOImpl(IBatisManager.getClient());
	}

	/* (non-Javadoc)
	 * @see com.tdil.lojack.daomanager.DAOProvider#getAlarmConfDAO()
	 */
	@Override
	public AlarmConfDAO getAlarmConfDAO() throws SQLException {
		return new AlarmConfDAOImpl(IBatisManager.getClient());
	}

	/* (non-Javadoc)
	 * @see com.tdil.lojack.daomanager.DAOProvider#getLightConfDAO()
	 */
	@Override
	public LightConfDAO getLightConfDAO() throws SQLException {
		return new LightConfDAOImpl(IBatisManager.getClient());
	}

	/* (non-Javadoc)
	 * @see com.tdil.lojack.daomanager.DAOProvider#getAsyncJobDAO()
	 */
	@Override
	public AsyncJobDAO getAsyncJobDAO() throws SQLException {
		return new AsyncJobDAOImpl(IBatisManager.getClient());
	}

	/* (non-Javadoc)
	 * @see com.tdil.lojack.daomanager.DAOProvider#getBlobDataDAO()
	 */
	@Override
	public BlobDataDAO getBlobDataDAO() throws SQLException {
		return new BlobDataDAOImpl(IBatisManager.getClient());
	}

	/* (non-Javadoc)
	 * @see com.tdil.lojack.daomanager.DAOProvider#getCacheRegionDAO()
	 */
	@Override
	public CacheRegionDAO getCacheRegionDAO() throws SQLException {
		return new CacheRegionDAOImpl(IBatisManager.getClient());
	}

	/* (non-Javadoc)
	 * @see com.tdil.lojack.daomanager.DAOProvider#getPointOfInterestDAO()
	 */
	@Override
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
		return "VLU_DATA";
	}
	@Override
	public String getVLU_IMPORTTableName() {
		return "VLU_IMPORT";
	}
}
