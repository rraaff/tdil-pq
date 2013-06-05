package com.tdil.lojack.utils;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.List;
import java.util.Properties;

import javax.servlet.ServletContextEvent;

import org.apache.commons.io.FileUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;

import com.tdil.cache.blob.BlobLocalDiskCache;
import com.tdil.log4j.LoggerProvider;
import com.tdil.lojack.cache.blob.BlobDataType;
import com.tdil.lojack.cache.blob.PublicBlobResolver;
import com.tdil.lojack.daomanager.DAOManager;
import com.tdil.lojack.daomanager.MySQLDAOProvider;
import com.tdil.lojack.daomanager.SQLServerDAOProvider;
import com.tdil.lojack.gis.CarpathiaProtocol;
import com.tdil.lojack.gis.JSONProtocol;
import com.tdil.lojack.gis.LoJackServicesConnector;
import com.tdil.lojack.gis.UpdateMiddlewareJobsThread;
import com.tdil.lojack.model.SystemProperty;
import com.tdil.lojack.model.SystemPropertyExample;
import com.tdil.lojack.pets.PetsConnector;
import com.tdil.lojack.prevent.PreventConnector;
import com.tdil.lojack.roles.HomeUser;
import com.tdil.lojack.roles.PreventUser;
import com.tdil.lojack.roles.WebsiteUser;
import com.tdil.thalamus.client.cache.ThalamusCache;
import com.tdil.thalamus.client.core.ThalamusClient;
import com.tdil.thalamus.client.facade.ThalamusClientBeanFacade;
import com.tdil.users.None;
import com.tdil.users.Role;
import com.tdil.utils.SystemConfig;
import com.tdil.utils.SystemPropertyCache;

public class LoJackConfig extends SystemConfig {

	private static String FRONT_SERVER;

	private static String GUID;
	
	private static long FRONT_LOGIN_DELAY;

	private static Logger getLog() {
		return LoggerProvider.getLogger(LoJackConfig.class);
	}

	@Override
	public void init(ServletContextEvent sce) {
		try {
			String dAOProvider = sce.getServletContext().getInitParameter("DAOProvider");
			if (StringUtils.isEmpty(dAOProvider) || "MYSQL".equals(dAOProvider)) {
				DAOManager.setCurrentDao(new MySQLDAOProvider());
			} else {
				if ("SQLSERVER".equals(dAOProvider)) {
					DAOManager.setCurrentDao(new SQLServerDAOProvider());
				} 
			}
			super.init(sce);
			this.loadFilteredWords();
			String frontserver = SystemPropertyUtils.getSystemPropertValue("front.server");
			if (frontserver != null) {
				setFRONT_SERVER(frontserver);
			}
			getLog().fatal("Front server is " + frontserver);

			long delay = 0;
			try {
				delay = Long.parseLong(SystemPropertyUtils.getSystemPropertValue("front.login.deplay"));
			} catch (Exception e) {
			}
			setFRONT_LOGIN_DELAY(delay);
			getLog().fatal("Front login delay is " + delay);

			String thalamusserver = SystemPropertyUtils.getSystemPropertValue("thalamus.server");
			if (thalamusserver != null) {
				ThalamusClient.setTHALAMUS_SERVER(thalamusserver);
			}
			getLog().fatal("Thalamus server is " + ThalamusClient.getTHALAMUS_SERVER());

			URL thalamusUrl = new URL(thalamusserver);
			String thalamushost = SystemPropertyUtils.getSystemPropertValue("thalamus.host");
			if (!org.apache.commons.lang.StringUtils.isEmpty(thalamushost)) {
				ThalamusClient.setTHALAMUS_HOST(thalamushost);
			} else {
				ThalamusClient.setTHALAMUS_HOST(thalamusUrl.getHost());
			}
			getLog().fatal("Thalamus host is " + ThalamusClient.getTHALAMUS_HOST());
			
			String thalamustimeout = SystemPropertyUtils.getSystemPropertValue("thalamus.timeout");
			if (thalamustimeout != null) {
				ThalamusClient.setTIMEOUT(Integer.parseInt(thalamustimeout));
			}
			getLog().fatal("Thalamus timeout is " + ThalamusClient.getTIMEOUT());

			String thalamuscookiepath = SystemPropertyUtils.getSystemPropertValue("thalamus.cookiePath");
			if (!org.apache.commons.lang.StringUtils.isEmpty(thalamuscookiepath)) {
				ThalamusClient.setTHALAMUS_JSESSIONID_COOKIE_PATH(thalamuscookiepath);
			} else {
				ThalamusClient.setTHALAMUS_JSESSIONID_COOKIE_PATH(thalamusUrl.getPath());
			}
			getLog().fatal("Thalamus cookie path is " + ThalamusClient.getTHALAMUS_JSESSIONID_COOKIE_PATH());

			String thalamustouchpointCode = SystemPropertyUtils.getSystemPropertValue("thalamus.touchpoint.code");
			if (thalamustouchpointCode != null) {
				ThalamusClient.setTHALAMUS_TOUCHPOINT_CODE(thalamustouchpointCode);
			}
			getLog().fatal("Thalamus touchpoint is " + ThalamusClient.getTHALAMUS_TOUCHPOINT_CODE());

			String thalamustouchpointToken = SystemPropertyUtils.getSystemPropertValue("thalamus.touchpoint.token");
			if (thalamustouchpointToken != null) {
				ThalamusClient.setTHALAMUS_TOUCHPOINT_TOKEN(thalamustouchpointToken);
			}
			getLog().fatal("Thalamus touchpoint is " + ThalamusClient.getTHALAMUS_TOUCHPOINT_TOKEN());
			ThalamusCache.configureWith(new Properties());
			// Initializes the caches
			try {
				getLog().fatal("About to initialize cache");
				getLog().fatal("Initializing brand families cache");
				ThalamusClientBeanFacade.getBrandFamilies();
				getLog().fatal("Initializing brands cache");
				ThalamusClientBeanFacade.getBrands();
				getLog().fatal("Initializing channels cache");
				ThalamusClientBeanFacade.getChannels();
				getLog().fatal("Initializing countries cache");
				ThalamusClientBeanFacade.getCountries();
				getLog().fatal("Initializing document types cache");
				ThalamusClientBeanFacade.getDocumentTypes();
				getLog().fatal("Initializing person fields cache");
				ThalamusClientBeanFacade.getPersonFields();
				getLog().fatal("Initializing facebook login");
				ThalamusClientBeanFacade.getFacebookLogin();
				getLog().fatal("Initializing twitter login");
				ThalamusClientBeanFacade.getTwitterLogin();
				getLog().fatal("Cache initialized");
			} catch (Exception e) {
				getLog().error("Can not initialize caches", e);
			}
			Role.addRole(WebsiteUser.INSTANCE);
			
			String guid = SystemPropertyUtils.getSystemPropertValue("guid");
			if (guid != null) {
				setGUID(guid);
			}
			getLog().fatal("GUID is " + guid);
			
			String mwprotocol = SystemPropertyUtils.getSystemPropertValue("mw.protocol");
			if (StringUtils.isEmpty(mwprotocol)) {
				mwprotocol = JSONProtocol.PROTOCOL;
			} 
			LoJackServicesConnector.setProtocol(JSONProtocol.PROTOCOL.equalsIgnoreCase(mwprotocol) ? new JSONProtocol() : new CarpathiaProtocol());
			getLog().fatal("MW protocol is " + mwprotocol);

			String gisserver = SystemPropertyUtils.getSystemPropertValue("gis.server");
			if (gisserver != null) {
				LoJackServicesConnector.setGisServer(gisserver);
			}
			getLog().fatal("GIS server is " + (gisserver == null ? "null" : gisserver));

			String servicesserver = SystemPropertyUtils.getSystemPropertValue("services.server");
			if (servicesserver != null) {
				LoJackServicesConnector.setServicesServer(servicesserver);
			}
			getLog().fatal("Services server is " + (servicesserver == null ? "null" : servicesserver));
			
			String servicestimeout = SystemPropertyUtils.getSystemPropertValue("services.timeout");
			if (servicestimeout != null) {
				LoJackServicesConnector.setTIMEOUT(Integer.parseInt(servicestimeout));
			}
			getLog().fatal("Services timeout is " + LoJackServicesConnector.getTIMEOUT());

			String preventserver = SystemPropertyUtils.getSystemPropertValue("prevent.server");
			if (preventserver != null) {
				PreventConnector.setPreventServer(preventserver);
			}
			getLog().fatal("Prevent server is " + (preventserver == null ? "null" : preventserver));
			
			String preventloginurl = SystemPropertyUtils.getSystemPropertValue("prevent.loginurl");
			if (preventloginurl != null) {
				PreventConnector.setPreventLoginUrl(preventloginurl);
			}
			getLog().fatal("Prevent login url is " + (preventloginurl == null ? "null" : preventloginurl));
			
			String preventtoken = SystemPropertyUtils.getSystemPropertValue("prevent.token");
			if (preventtoken != null) {
				PreventConnector.setPreventToken(preventtoken);
			}
			getLog().fatal("Prevent token is " + (preventtoken == null ? "null" : preventtoken));
			
			String preventtimeout = SystemPropertyUtils.getSystemPropertValue("prevent.timeout");
			if (preventtimeout != null) {
				PreventConnector.setTIMEOUT(Integer.parseInt(preventtimeout));
			}
			getLog().fatal("Prevent timeout is " + PreventConnector.getTIMEOUT());
			
			
			
			String petsloginurl = SystemPropertyUtils.getSystemPropertValue("pets.loginurl");
			if (petsloginurl != null) {
				PetsConnector.setPetsLoginUrl(petsloginurl);
			}
			getLog().fatal("Pets login url is " + (petsloginurl == null ? "null" : petsloginurl));
			
			String petsMobileloginurl = SystemPropertyUtils.getSystemPropertValue("pets.mobile.loginurl");
			if (petsMobileloginurl != null) {
				PetsConnector.setPetsMobileLoginUrl(petsMobileloginurl);
			}
			getLog().fatal("Pets mobile login url is " + (petsMobileloginurl == null ? "null" : petsMobileloginurl));
			
			String petstoken = SystemPropertyUtils.getSystemPropertValue("pets.token");
			if (petstoken != null) {
				PetsConnector.setPetsToken(petstoken);
			}
			getLog().fatal("Pets token is " + (petstoken == null ? "null" : petstoken));
			

			getLog().fatal("Starting middleware jobs updater");
			int jobrefreshtime = Integer.parseInt(SystemPropertyUtils.getSystemPropertValue("job.refresh.time"));
			int jobaborttime = Integer.parseInt(SystemPropertyUtils.getSystemPropertValue("job.abort.time"));
			int jobclientrefreshtime = Integer.parseInt(SystemPropertyUtils.getSystemPropertValue("job.client.refresh.time"));
			UpdateMiddlewareJobsThread.setJobRefreshTime(jobrefreshtime);
			UpdateMiddlewareJobsThread.setJobAbortTime(jobaborttime);
			UpdateMiddlewareJobsThread.setJobClientRefreshTime(jobclientrefreshtime);
			getLog().fatal("Middleware job refresh time is " + jobrefreshtime + " millis");
			getLog().fatal("Middleware job abort time is " + jobaborttime + " millis");
			getLog().fatal("Middleware job client refresh time is " + jobclientrefreshtime + " millis");

			UpdateMiddlewareJobsThread updateMiddlewareJobsThread = new UpdateMiddlewareJobsThread();
			UpdateMiddlewareJobsThread.setUpdateMiddlewareJobsThread(updateMiddlewareJobsThread);
			updateMiddlewareJobsThread.start();
			getLog().fatal("Middleware jobs updater started");
		} catch (Exception e) {
			e.printStackTrace(System.out);
			getLog().error(e.getMessage(), e);
			throw new RuntimeException(e);
		}

	}

	@Override
	protected String getLogDir() {
		return "lojacklogs";
	}

	private void loadFilteredWords() {

	}

	@Override
	protected void initXMLALias() {
		// XMLUtils.addAlias("RankingPositions", RankingPositions.class);
	}

	@Override
	protected void initRoles() {
		Role.addRole(WebsiteUser.INSTANCE);
		Role.addRole(HomeUser.INSTANCE);
		Role.addRole(PreventUser.INSTANCE);
	}

	@Override
	protected void loadProperties() {
	}

	@Override
	protected void loadPropertiesFromDBInTransaction() {
		try {
			List<SystemProperty> list = DAOManager.getSystemPropertyDAO().selectSystemPropertyByExample(new SystemPropertyExample());
			for (SystemProperty property : list) {
				getLog().fatal("LOJACKConfig: " + property.getPropkey() + "=" + property.getPropvalue());
				SystemPropertyCache.put(property.getPropkey(), property.getPropvalue());
			}
		} catch (SQLException e) {
			getLog().error(e.getMessage(), e);
		}

	}

	@Override
	protected void initBlobCache() {
		getLog().fatal("LOJACKConfig: blob cache starting ");
		String path = SystemPropertyCache.getTempPath();
		path = path + "/lojackblobs/";
		File file = new File(path);
		if (!file.exists()) {
			try {
				FileUtils.forceMkdir(file);
			} catch (IOException e) {
				getLog().equals("No se pudo crear el cache de blobs en " + path);
				return;
			}
		}
		try {
			File testCreate = File.createTempFile("test", "test", file);
			testCreate.delete();
		} catch (IOException e) {
			getLog().equals("No se pudo escribir en el cache de blobs " + path);
			return;
		}
		getLog().fatal("LOJACKConfig: blob cache started at " + path);
		BlobLocalDiskCache.setDiskBlobLocation(path);
		BlobLocalDiskCache.addBlobResolver(BlobDataType.PUBLIC, new PublicBlobResolver(None.INSTANCE_ARR));
	}

	public static String getFRONT_SERVER() {
		return FRONT_SERVER;
	}

	public static void setFRONT_SERVER(String fRONT_SERVER) {
		FRONT_SERVER = fRONT_SERVER;
	}

	public static long getFRONT_LOGIN_DELAY() {
		return FRONT_LOGIN_DELAY;
	}

	public static void setFRONT_LOGIN_DELAY(long fRONT_LOGIN_DELAY) {
		FRONT_LOGIN_DELAY = fRONT_LOGIN_DELAY;
	}

	public static String getGUID() {
		return GUID;
	}

	public static void setGUID(String gUID) {
		GUID = gUID;
	}

}
