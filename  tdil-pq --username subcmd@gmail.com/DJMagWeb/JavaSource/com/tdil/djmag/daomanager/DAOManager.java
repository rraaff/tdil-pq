package com.tdil.djmag.daomanager;

import java.sql.SQLException;

import com.tdil.djmag.dao.BannerDAO;
import com.tdil.djmag.dao.BannerPositionDAO;
import com.tdil.djmag.dao.CountryDAO;
import com.tdil.djmag.dao.FacebookFeedDAO;
import com.tdil.djmag.dao.MagazineDAO;
import com.tdil.djmag.dao.MenuItemDAO;
import com.tdil.djmag.dao.NewsletterDAO;
import com.tdil.djmag.dao.NoteCountryDAO;
import com.tdil.djmag.dao.NoteDAO;
import com.tdil.djmag.dao.NoteImageDAO;
import com.tdil.djmag.dao.RankingNoteCountryDAO;
import com.tdil.djmag.dao.RankingNoteDAO;
import com.tdil.djmag.dao.SectionDAO;
import com.tdil.djmag.dao.SystemPropertyDAO;
import com.tdil.djmag.dao.SystemUserDAO;
import com.tdil.djmag.dao.TwitterFeedDAO;
import com.tdil.djmag.dao.VideoDAO;
import com.tdil.djmag.dao.impl.BannerDAOImpl;
import com.tdil.djmag.dao.impl.BannerPositionDAOImpl;
import com.tdil.djmag.dao.impl.CountryDAOImpl;
import com.tdil.djmag.dao.impl.FacebookFeedDAOImpl;
import com.tdil.djmag.dao.impl.MagazineDAOImpl;
import com.tdil.djmag.dao.impl.MenuItemDAOImpl;
import com.tdil.djmag.dao.impl.NewsletterDAOImpl;
import com.tdil.djmag.dao.impl.NoteCountryDAOImpl;
import com.tdil.djmag.dao.impl.NoteDAOImpl;
import com.tdil.djmag.dao.impl.NoteImageDAOImpl;
import com.tdil.djmag.dao.impl.RankingNoteCountryDAOImpl;
import com.tdil.djmag.dao.impl.RankingNoteDAOImpl;
import com.tdil.djmag.dao.impl.SectionDAOImpl;
import com.tdil.djmag.dao.impl.SystemPropertyDAOImpl;
import com.tdil.djmag.dao.impl.SystemUserDAOImpl;
import com.tdil.djmag.dao.impl.TwitterFeedDAOImpl;
import com.tdil.djmag.dao.impl.VideoDAOImpl;
import com.tdil.ibatis.IBatisManager;

public class DAOManager {

	public static CountryDAO getCountryDAO() throws SQLException {
		return new CountryDAOImpl(IBatisManager.getClient());
	}
	
	public static MenuItemDAO getMenuItemDAO() throws SQLException {
		return new MenuItemDAOImpl(IBatisManager.getClient());
	}
	
	public static NewsletterDAO getNewsletterDAO() throws SQLException {
		return new NewsletterDAOImpl(IBatisManager.getClient());
	}
	
	public static SectionDAO getSectionDAO() throws SQLException {
		return new SectionDAOImpl(IBatisManager.getClient());
	}
	
	public static SystemPropertyDAO getSystemPropertyDAO() throws SQLException {
		return new SystemPropertyDAOImpl(IBatisManager.getClient());
	}
	
	public static SystemUserDAO getSystemUserDAO() throws SQLException {
		return new SystemUserDAOImpl(IBatisManager.getClient());
	}
	
	public static RankingNoteDAO getRankingNoteDAO() throws SQLException {
		return new RankingNoteDAOImpl(IBatisManager.getClient());
	}
	
	public static RankingNoteCountryDAO getRankingNoteCountryDAO() throws SQLException {
		return new RankingNoteCountryDAOImpl(IBatisManager.getClient());
	}
	
	public static NoteDAO getNoteDAO() throws SQLException {
		return new NoteDAOImpl(IBatisManager.getClient());
	}
	
	public static NoteCountryDAO getNoteCountryDAO() throws SQLException {
		return new NoteCountryDAOImpl(IBatisManager.getClient());
	}
	
	public static NoteImageDAO getNoteImageDAO() throws SQLException {
		return new NoteImageDAOImpl(IBatisManager.getClient());
	}
	
	public static BannerDAO getBannerDAO() throws SQLException {
		return new BannerDAOImpl(IBatisManager.getClient());
	}
	
	public static TwitterFeedDAO getTwitterFeedDAO() throws SQLException {
		return new TwitterFeedDAOImpl(IBatisManager.getClient());
	}
	
	public static FacebookFeedDAO getFacebookFeedDAO() throws SQLException {
		return new FacebookFeedDAOImpl(IBatisManager.getClient());
	}
	
	public static MagazineDAO getMagazineDAO() throws SQLException {
		return new MagazineDAOImpl(IBatisManager.getClient());
	}
	
	public static VideoDAO getVideoDAO() throws SQLException {
		return new VideoDAOImpl(IBatisManager.getClient());
	}
	
	public static BannerPositionDAO getBannerPositionDAO() throws SQLException {
		return new BannerPositionDAOImpl(IBatisManager.getClient());
	}
}
