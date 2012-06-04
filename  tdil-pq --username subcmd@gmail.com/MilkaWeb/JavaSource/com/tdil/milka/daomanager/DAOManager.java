package com.tdil.milka.daomanager;

import java.sql.SQLException;

import com.tdil.ibatis.IBatisManager;
import com.tdil.milka.dao.AuthorDAO;
import com.tdil.milka.dao.BlobDataDAO;
import com.tdil.milka.dao.ClickCounterDAO;
import com.tdil.milka.dao.EmailEndingsDAO;
import com.tdil.milka.dao.FilteredWordDAO;
import com.tdil.milka.dao.GoodMorningDAO;
import com.tdil.milka.dao.LinksDAO;
import com.tdil.milka.dao.LoveNicknamesDAO;
import com.tdil.milka.dao.MailToParentDAO;
import com.tdil.milka.dao.MilkaPhotoDAO;
import com.tdil.milka.dao.MilkaPhotoTagDAO;
import com.tdil.milka.dao.NotificationEmailDAO;
import com.tdil.milka.dao.PostItDAO;
import com.tdil.milka.dao.RawInsertDAO;
import com.tdil.milka.dao.SystemPropertyDAO;
import com.tdil.milka.dao.SystemUserDAO;
import com.tdil.milka.dao.TagDAO;
import com.tdil.milka.dao.VideoDAO;
import com.tdil.milka.dao.WallDAO;
import com.tdil.milka.dao.WallWrittingDAO;
import com.tdil.milka.dao.impl.AuthorDAOImpl;
import com.tdil.milka.dao.impl.BlobDataDAOImpl;
import com.tdil.milka.dao.impl.ClickCounterDAOImpl;
import com.tdil.milka.dao.impl.EmailEndingsDAOImpl;
import com.tdil.milka.dao.impl.FilteredWordDAOImpl;
import com.tdil.milka.dao.impl.GoodMorningDAOImpl;
import com.tdil.milka.dao.impl.LinksDAOImpl;
import com.tdil.milka.dao.impl.LoveNicknamesDAOImpl;
import com.tdil.milka.dao.impl.MailToParentDAOImpl;
import com.tdil.milka.dao.impl.MilkaPhotoDAOImpl;
import com.tdil.milka.dao.impl.MilkaPhotoTagDAOImpl;
import com.tdil.milka.dao.impl.NotificationEmailDAOImpl;
import com.tdil.milka.dao.impl.PostItDAOImpl;
import com.tdil.milka.dao.impl.RawInsertDAOImpl;
import com.tdil.milka.dao.impl.SystemPropertyDAOImpl;
import com.tdil.milka.dao.impl.SystemUserDAOImpl;
import com.tdil.milka.dao.impl.TagDAOImpl;
import com.tdil.milka.dao.impl.VideoDAOImpl;
import com.tdil.milka.dao.impl.WallDAOImpl;
import com.tdil.milka.dao.impl.WallWrittingDAOImpl;

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
	
	public static ClickCounterDAO getClickCounterDAO() throws SQLException {
		return new ClickCounterDAOImpl(IBatisManager.getClient());
	}
	
	public static NotificationEmailDAO getNotificationEmailDAO() throws SQLException {
		return new NotificationEmailDAOImpl(IBatisManager.getClient());
	}
	
	public static RawInsertDAO getRawInsertDAO() throws SQLException {
		return new RawInsertDAOImpl(IBatisManager.getClient());
	}
	
	public static TagDAO getTagDAO() throws SQLException {
		return new TagDAOImpl(IBatisManager.getClient());
	}
	
	public static AuthorDAO getAuthorDAO() throws SQLException {
		return new AuthorDAOImpl(IBatisManager.getClient());
	}
	
	public static MilkaPhotoDAO getMilkaPhotoDAO() throws SQLException {
		return new MilkaPhotoDAOImpl(IBatisManager.getClient());
	}
	
	public static MilkaPhotoTagDAO getMilkaPhotoTagDAO() throws SQLException {
		return new MilkaPhotoTagDAOImpl(IBatisManager.getClient());
	}
	
	public static PostItDAO getPostItDAO() throws SQLException {
		return new PostItDAOImpl(IBatisManager.getClient());
	}
	
	public static WallDAO getWallDAO() throws SQLException {
		return new WallDAOImpl(IBatisManager.getClient());
	}
	
	public static WallWrittingDAO getWallWrittingDAO() throws SQLException {
		return new WallWrittingDAOImpl(IBatisManager.getClient());
	}

	public static FilteredWordDAO getFilteredWordDAO() throws SQLException {
		return new FilteredWordDAOImpl(IBatisManager.getClient());
	}
	
	public static EmailEndingsDAO getEmailEndingsDAO() throws SQLException {
		return new EmailEndingsDAOImpl(IBatisManager.getClient());
	}
	
	public static MailToParentDAO getMailToParentDAO() throws SQLException {
		return new MailToParentDAOImpl(IBatisManager.getClient());
	}
	
	public static VideoDAO getVideoDAO() throws SQLException {
		return new VideoDAOImpl(IBatisManager.getClient());
	}
	
	public static LoveNicknamesDAO getLoveNicknamesDAO() throws SQLException {
		return new LoveNicknamesDAOImpl(IBatisManager.getClient());
	}
	
	public static LinksDAO getLinksDAO() throws SQLException {
		return new LinksDAOImpl(IBatisManager.getClient());
	}
	
	public static GoodMorningDAO getGoodMorningDAO() throws SQLException {
		return new GoodMorningDAOImpl(IBatisManager.getClient());
	}
}
