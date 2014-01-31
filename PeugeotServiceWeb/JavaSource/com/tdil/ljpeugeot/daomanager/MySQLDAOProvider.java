package com.tdil.ljpeugeot.daomanager;

import java.sql.SQLException;

import com.tdil.ibatis.IBatisManager;
import com.tdil.ljpeugeot.dao.BlobDataDAO;
import com.tdil.ljpeugeot.dao.CacheRegionDAO;
import com.tdil.ljpeugeot.dao.CityDAO;
import com.tdil.ljpeugeot.dao.DealerDAO;
import com.tdil.ljpeugeot.dao.ModelDAO;
import com.tdil.ljpeugeot.dao.PointOfInterestDAO;
import com.tdil.ljpeugeot.dao.StateDAO;
import com.tdil.ljpeugeot.dao.SystemPropertyDAO;
import com.tdil.ljpeugeot.dao.SystemUserDAO;
import com.tdil.ljpeugeot.dao.VehicleDAO;
import com.tdil.ljpeugeot.dao.WebsiteUserDAO;
import com.tdil.ljpeugeot.dao.impl.BlobDataDAOImpl;
import com.tdil.ljpeugeot.dao.impl.CacheRegionDAOImpl;
import com.tdil.ljpeugeot.dao.impl.CityDAOImpl;
import com.tdil.ljpeugeot.dao.impl.DealerDAOImpl;
import com.tdil.ljpeugeot.dao.impl.ModelDAOImpl;
import com.tdil.ljpeugeot.dao.impl.PointOfInterestDAOImpl;
import com.tdil.ljpeugeot.dao.impl.StateDAOImpl;
import com.tdil.ljpeugeot.dao.impl.SystemPropertyDAOImpl;
import com.tdil.ljpeugeot.dao.impl.SystemUserDAOImpl;
import com.tdil.ljpeugeot.dao.impl.VehicleDAOImpl;
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
	public CityDAO getCityDAO() throws SQLException {
		return new CityDAOImpl(IBatisManager.getClient());
	}

	@Override
	public StateDAO getStateDAO() throws SQLException {
		return new StateDAOImpl(IBatisManager.getClient());
	}

	@Override
	public DealerDAO getDealerDAO() throws SQLException {
		return new DealerDAOImpl(IBatisManager.getClient());
	}
	
	@Override
	public ModelDAO getModelDAO() throws SQLException {
		return new ModelDAOImpl(IBatisManager.getClient());
	}
	
	@Override
	public VehicleDAO getVehicleDAO() throws SQLException {
		return new VehicleDAOImpl(IBatisManager.getClient());
	}
	
}
