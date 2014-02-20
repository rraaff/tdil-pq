package com.tdil.ljpeugeot.daomanager;

import java.sql.SQLException;

import com.tdil.ljpeugeot.dao.AdviceDAO;
import com.tdil.ljpeugeot.dao.BlobDataDAO;
import com.tdil.ljpeugeot.dao.CacheRegionDAO;
import com.tdil.ljpeugeot.dao.CityDAO;
import com.tdil.ljpeugeot.dao.ContactDataDAO;
import com.tdil.ljpeugeot.dao.DataImportDAO;
import com.tdil.ljpeugeot.dao.DealerDAO;
import com.tdil.ljpeugeot.dao.KmDataDAO;
import com.tdil.ljpeugeot.dao.ModelDAO;
import com.tdil.ljpeugeot.dao.NotificationEmailDAO;
import com.tdil.ljpeugeot.dao.PointOfInterestDAO;
import com.tdil.ljpeugeot.dao.ServiceDAO;
import com.tdil.ljpeugeot.dao.StateDAO;
import com.tdil.ljpeugeot.dao.SystemPropertyDAO;
import com.tdil.ljpeugeot.dao.SystemUserDAO;
import com.tdil.ljpeugeot.dao.VehicleDAO;
import com.tdil.ljpeugeot.dao.WebsiteUserDAO;

public interface DAOProvider {

	public abstract SystemPropertyDAO getSystemPropertyDAO() throws SQLException;

	public abstract WebsiteUserDAO getWebsiteUserDAO() throws SQLException;

	public abstract CacheRegionDAO getCacheRegionDAO() throws SQLException;
	
	public abstract BlobDataDAO getBlobDataDAO() throws SQLException;

	public abstract PointOfInterestDAO getPointOfInterestDAO() throws SQLException;

	public abstract SystemUserDAO getSystemUserDAO() throws SQLException;
	
	public abstract CityDAO getCityDAO() throws SQLException;
	
	public abstract StateDAO getStateDAO() throws SQLException;
	
	public abstract DealerDAO getDealerDAO() throws SQLException;
	
	public abstract ModelDAO getModelDAO() throws SQLException;
	public abstract VehicleDAO getVehicleDAO() throws SQLException;
	public abstract AdviceDAO getAdviceDAO() throws SQLException;
	public abstract ServiceDAO getServiceDAO() throws SQLException;
	
	public abstract ContactDataDAO getContactDataDAO() throws SQLException;
	
	public abstract DataImportDAO getDataImportDAO() throws SQLException;

	public abstract NotificationEmailDAO getNotificationEmailDAO() throws SQLException;

	public abstract KmDataDAO getKmDataDAO() throws SQLException;
}