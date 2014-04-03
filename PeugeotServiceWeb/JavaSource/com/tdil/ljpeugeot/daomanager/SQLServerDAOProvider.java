package com.tdil.ljpeugeot.daomanager;

import java.sql.SQLException;

import com.tdil.ibatis.IBatisManager;
import com.tdil.ljpeugeot.dao.AdviceDAO;
import com.tdil.ljpeugeot.dao.AlertDAO;
import com.tdil.ljpeugeot.dao.BlobDataDAO;
import com.tdil.ljpeugeot.dao.CacheRegionDAO;
import com.tdil.ljpeugeot.dao.CityDAO;
import com.tdil.ljpeugeot.dao.ContactDataDAO;
import com.tdil.ljpeugeot.dao.DataImportDAO;
import com.tdil.ljpeugeot.dao.DealerDAO;
import com.tdil.ljpeugeot.dao.KmDataDAO;
import com.tdil.ljpeugeot.dao.ModelDAO;
import com.tdil.ljpeugeot.dao.NativeAppDAO;
import com.tdil.ljpeugeot.dao.NotificationEmailDAO;
import com.tdil.ljpeugeot.dao.PointOfInterestDAO;
import com.tdil.ljpeugeot.dao.ServiceDAO;
import com.tdil.ljpeugeot.dao.StateDAO;
import com.tdil.ljpeugeot.dao.SystemPropertyDAO;
import com.tdil.ljpeugeot.dao.SystemUserDAO;
import com.tdil.ljpeugeot.dao.VehicleDAO;
import com.tdil.ljpeugeot.dao.WebsiteUserDAO;
import com.tdil.ljpeugeot.dao.impl.sqlserver.AlertDAOImpl;
import com.tdil.ljpeugeot.dao.impl.sqlserver.KmDataDAOImpl;
import com.tdil.ljpeugeot.dao.impl.sqlserver.AdviceDAOImpl;
import com.tdil.ljpeugeot.dao.impl.sqlserver.BlobDataDAOImpl;
import com.tdil.ljpeugeot.dao.impl.sqlserver.CacheRegionDAOImpl;
import com.tdil.ljpeugeot.dao.impl.sqlserver.CityDAOImpl;
import com.tdil.ljpeugeot.dao.impl.sqlserver.ContactDataDAOImpl;
import com.tdil.ljpeugeot.dao.impl.sqlserver.DataImportDAOImpl;
import com.tdil.ljpeugeot.dao.impl.sqlserver.DealerDAOImpl;
import com.tdil.ljpeugeot.dao.impl.sqlserver.ModelDAOImpl;
import com.tdil.ljpeugeot.dao.impl.sqlserver.NativeAppDAOImpl;
import com.tdil.ljpeugeot.dao.impl.sqlserver.NotificationEmailDAOImpl;
import com.tdil.ljpeugeot.dao.impl.sqlserver.PointOfInterestDAOImpl;
import com.tdil.ljpeugeot.dao.impl.sqlserver.ServiceDAOImpl;
import com.tdil.ljpeugeot.dao.impl.sqlserver.StateDAOImpl;
import com.tdil.ljpeugeot.dao.impl.sqlserver.SystemPropertyDAOImpl;
import com.tdil.ljpeugeot.dao.impl.sqlserver.SystemUserDAOImpl;
import com.tdil.ljpeugeot.dao.impl.sqlserver.VehicleDAOImpl;
import com.tdil.ljpeugeot.dao.impl.sqlserver.WebsiteUserDAOImpl;

public class SQLServerDAOProvider implements DAOProvider {

	public SystemPropertyDAO getSystemPropertyDAO() throws SQLException {
		return new SystemPropertyDAOImpl(IBatisManager.getClient());
	}

	public WebsiteUserDAO getWebsiteUserDAO() throws SQLException {
		return new WebsiteUserDAOImpl(IBatisManager.getClient());
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
	
	@Override
	public AdviceDAO getAdviceDAO() throws SQLException {
		return new AdviceDAOImpl(IBatisManager.getClient());
	}
	@Override
	public ServiceDAO getServiceDAO() throws SQLException {
		return new ServiceDAOImpl(IBatisManager.getClient());
	}
	
	@Override
	public ContactDataDAO getContactDataDAO() throws SQLException {
		return new ContactDataDAOImpl(IBatisManager.getClient());
	}
	
	@Override
	public DataImportDAO getDataImportDAO() throws SQLException {
		return new DataImportDAOImpl(IBatisManager.getClient());
	}
	
	@Override
	public NotificationEmailDAO getNotificationEmailDAO() throws SQLException {
		return new NotificationEmailDAOImpl(IBatisManager.getClient());
	}
	
	@Override
	public KmDataDAO getKmDataDAO() throws SQLException {
		return new KmDataDAOImpl(IBatisManager.getClient());
	}
	
	@Override
	public AlertDAO getAlertDAO() throws SQLException {
		return new AlertDAOImpl(IBatisManager.getClient());
	}
	
	@Override
	public NativeAppDAO getNativeAppDAO() throws SQLException {
		return new NativeAppDAOImpl(IBatisManager.getClient());
	}
}
