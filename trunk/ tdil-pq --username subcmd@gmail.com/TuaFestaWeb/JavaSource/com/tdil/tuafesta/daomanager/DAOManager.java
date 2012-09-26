package com.tdil.tuafesta.daomanager;

import java.sql.SQLException;

import com.tdil.ibatis.IBatisManager;
import com.tdil.tuafesta.dao.BlobDataDAO;
import com.tdil.tuafesta.dao.CacheRegionDAO;
import com.tdil.tuafesta.dao.CategoryDAO;
import com.tdil.tuafesta.dao.ClientDAO;
import com.tdil.tuafesta.dao.Geo2DAO;
import com.tdil.tuafesta.dao.Geo3DAO;
import com.tdil.tuafesta.dao.Geo4DAO;
import com.tdil.tuafesta.dao.HighlightedCategoryDAO;
import com.tdil.tuafesta.dao.HighlightedProfesionalDAO;
import com.tdil.tuafesta.dao.NotificationEmailDAO;
import com.tdil.tuafesta.dao.ProfesionalChangeDAO;
import com.tdil.tuafesta.dao.ProfesionalDAO;
import com.tdil.tuafesta.dao.PromotionDAO;
import com.tdil.tuafesta.dao.PromotionPhotoDAO;
import com.tdil.tuafesta.dao.PromotionSellDAO;
import com.tdil.tuafesta.dao.RawInsertDAO;
import com.tdil.tuafesta.dao.SellDAO;
import com.tdil.tuafesta.dao.SellMediaDAO;
import com.tdil.tuafesta.dao.ServiceAreaDAO;
import com.tdil.tuafesta.dao.StatisticDAO;
import com.tdil.tuafesta.dao.SystemPropertyDAO;
import com.tdil.tuafesta.dao.SystemUserDAO;
import com.tdil.tuafesta.dao.WallDAO;
import com.tdil.tuafesta.dao.WallWrittingDAO;
import com.tdil.tuafesta.dao.impl.BlobDataDAOImpl;
import com.tdil.tuafesta.dao.impl.CacheRegionDAOImpl;
import com.tdil.tuafesta.dao.impl.CategoryDAOImpl;
import com.tdil.tuafesta.dao.impl.ClientDAOImpl;
import com.tdil.tuafesta.dao.impl.Geo2DAOImpl;
import com.tdil.tuafesta.dao.impl.Geo3DAOImpl;
import com.tdil.tuafesta.dao.impl.Geo4DAOImpl;
import com.tdil.tuafesta.dao.impl.HighlightedCategoryDAOImpl;
import com.tdil.tuafesta.dao.impl.HighlightedProfesionalDAOImpl;
import com.tdil.tuafesta.dao.impl.NotificationEmailDAOImpl;
import com.tdil.tuafesta.dao.impl.ProfesionalChangeDAOImpl;
import com.tdil.tuafesta.dao.impl.ProfesionalDAOImpl;
import com.tdil.tuafesta.dao.impl.PromotionDAOImpl;
import com.tdil.tuafesta.dao.impl.PromotionPhotoDAOImpl;
import com.tdil.tuafesta.dao.impl.PromotionSellDAOImpl;
import com.tdil.tuafesta.dao.impl.RawInsertDAOImpl;
import com.tdil.tuafesta.dao.impl.SellDAOImpl;
import com.tdil.tuafesta.dao.impl.SellMediaDAOImpl;
import com.tdil.tuafesta.dao.impl.ServiceAreaDAOImpl;
import com.tdil.tuafesta.dao.impl.StatisticDAOImpl;
import com.tdil.tuafesta.dao.impl.SystemPropertyDAOImpl;
import com.tdil.tuafesta.dao.impl.SystemUserDAOImpl;
import com.tdil.tuafesta.dao.impl.WallDAOImpl;
import com.tdil.tuafesta.dao.impl.WallWrittingDAOImpl;

public class DAOManager {


	public static SystemPropertyDAO getSystemPropertyDAO() throws SQLException {
		return new SystemPropertyDAOImpl(IBatisManager.getClient());
	}
	
	public static SystemUserDAO getSystemUserDAO() throws SQLException {
		return new SystemUserDAOImpl(IBatisManager.getClient());
	}
	
	public static BlobDataDAO getBlobDataDAO() throws SQLException {
		return new BlobDataDAOImpl(IBatisManager.getClient());
	}
	
	public static NotificationEmailDAO getNotificationEmailDAO() throws SQLException {
		return new NotificationEmailDAOImpl(IBatisManager.getClient());
	}
	
	public static RawInsertDAO getRawInsertDAO() throws SQLException {
		return new RawInsertDAOImpl(IBatisManager.getClient());
	}
	
	public static Geo2DAO getGeo2DAO() throws SQLException {
		return new Geo2DAOImpl(IBatisManager.getClient());
	}
	
	public static Geo3DAO getGeo3DAO() throws SQLException {
		return new Geo3DAOImpl(IBatisManager.getClient());
	}
	
	public static Geo4DAO getGeo4DAO() throws SQLException {
		return new Geo4DAOImpl(IBatisManager.getClient());
	}
	
	public static ProfesionalDAO getProfesionalDAO() throws SQLException {
		return new ProfesionalDAOImpl(IBatisManager.getClient());
	}
	
	public static WallDAO getWallDAO() throws SQLException {
		return new WallDAOImpl(IBatisManager.getClient());
	}
	
	public static WallWrittingDAO getWallWrittingDAO() throws SQLException {
		return new WallWrittingDAOImpl(IBatisManager.getClient());
	}
	
	public static ProfesionalChangeDAO getProfesionalChangeDAO() throws SQLException {
		return new ProfesionalChangeDAOImpl(IBatisManager.getClient());
	}
	public static SellDAO getSellDAO() throws SQLException {
		return new SellDAOImpl(IBatisManager.getClient());
	}
	public static ServiceAreaDAO getServiceAreaDAO() throws SQLException {
		return new ServiceAreaDAOImpl(IBatisManager.getClient());
	}
	public static PromotionDAO getPromotionDAO() throws SQLException {
		return new PromotionDAOImpl(IBatisManager.getClient());
	}
	public static PromotionSellDAO getPromotionSellDAO() throws SQLException {
		return new PromotionSellDAOImpl(IBatisManager.getClient());
	}
	public static PromotionPhotoDAO getPromotionPhotoDAO() throws SQLException {
		return new PromotionPhotoDAOImpl(IBatisManager.getClient());
	}
	public static StatisticDAO getStatisticDAO() throws SQLException {
		return new StatisticDAOImpl(IBatisManager.getClient());
	}
	public static ClientDAO getClientDAO() throws SQLException {
		return new ClientDAOImpl(IBatisManager.getClient());
	}
	public static CacheRegionDAO getCacheRegionDAO() throws SQLException {
		return new CacheRegionDAOImpl(IBatisManager.getClient());
	}
	
	public static HighlightedProfesionalDAO getHighlightedProfesionalDAO() throws SQLException {
		return new HighlightedProfesionalDAOImpl(IBatisManager.getClient());
	}
	
	public static HighlightedCategoryDAO getHighlightedCategoryDAO() throws SQLException {
		return new HighlightedCategoryDAOImpl(IBatisManager.getClient());
	}
	
	public static SellMediaDAO getSellMediaDAO() throws SQLException {
		return new SellMediaDAOImpl(IBatisManager.getClient());
	}

	public static CategoryDAO getCategoryDAO() throws SQLException {
		return new CategoryDAOImpl(IBatisManager.getClient());
	}
}
