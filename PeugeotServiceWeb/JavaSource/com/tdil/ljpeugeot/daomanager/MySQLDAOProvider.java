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
import com.tdil.ljpeugeot.dao.impl.AlarmConfDAOImpl;
import com.tdil.ljpeugeot.dao.impl.AsyncJobDAOImpl;
import com.tdil.ljpeugeot.dao.impl.BlobDataDAOImpl;
import com.tdil.ljpeugeot.dao.impl.CacheRegionDAOImpl;
import com.tdil.ljpeugeot.dao.impl.CameraConfDAOImpl;
import com.tdil.ljpeugeot.dao.impl.LightConfDAOImpl;
import com.tdil.ljpeugeot.dao.impl.PointOfInterestDAOImpl;
import com.tdil.ljpeugeot.dao.impl.SystemPropertyDAOImpl;
import com.tdil.ljpeugeot.dao.impl.SystemUserDAOImpl;
import com.tdil.ljpeugeot.dao.impl.VLUDataDAOImpl;
import com.tdil.ljpeugeot.dao.impl.VLUImportDAOImpl;
import com.tdil.ljpeugeot.dao.impl.VLUImportErrorDAOImpl;
import com.tdil.ljpeugeot.dao.impl.WebsiteUserDAOImpl;

public class MySQLDAOProvider implements DAOProvider {

	
	/* (non-Javadoc)
	 * @see com.tdil.ljpeugeot.daomanager.DAOProvider#getSystemPropertyDAO()
	 */
	@Override
	public SystemPropertyDAO getSystemPropertyDAO() throws SQLException {
		return new SystemPropertyDAOImpl(IBatisManager.getClient());
	}

	/* (non-Javadoc)
	 * @see com.tdil.ljpeugeot.daomanager.DAOProvider#getWebsiteUserDAO()
	 */
	@Override
	public WebsiteUserDAO getWebsiteUserDAO() throws SQLException {
		return new WebsiteUserDAOImpl(IBatisManager.getClient());
	}

	/* (non-Javadoc)
	 * @see com.tdil.ljpeugeot.daomanager.DAOProvider#getAlarmConfDAO()
	 */
	@Override
	public AlarmConfDAO getAlarmConfDAO() throws SQLException {
		return new AlarmConfDAOImpl(IBatisManager.getClient());
	}

	/* (non-Javadoc)
	 * @see com.tdil.ljpeugeot.daomanager.DAOProvider#getLightConfDAO()
	 */
	@Override
	public LightConfDAO getLightConfDAO() throws SQLException {
		return new LightConfDAOImpl(IBatisManager.getClient());
	}

	/* (non-Javadoc)
	 * @see com.tdil.ljpeugeot.daomanager.DAOProvider#getAsyncJobDAO()
	 */
	@Override
	public AsyncJobDAO getAsyncJobDAO() throws SQLException {
		return new AsyncJobDAOImpl(IBatisManager.getClient());
	}

	/* (non-Javadoc)
	 * @see com.tdil.ljpeugeot.daomanager.DAOProvider#getBlobDataDAO()
	 */
	@Override
	public BlobDataDAO getBlobDataDAO() throws SQLException {
		return new BlobDataDAOImpl(IBatisManager.getClient());
	}

	/* (non-Javadoc)
	 * @see com.tdil.ljpeugeot.daomanager.DAOProvider#getCacheRegionDAO()
	 */
	@Override
	public CacheRegionDAO getCacheRegionDAO() throws SQLException {
		return new CacheRegionDAOImpl(IBatisManager.getClient());
	}

	/* (non-Javadoc)
	 * @see com.tdil.ljpeugeot.daomanager.DAOProvider#getPointOfInterestDAO()
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
