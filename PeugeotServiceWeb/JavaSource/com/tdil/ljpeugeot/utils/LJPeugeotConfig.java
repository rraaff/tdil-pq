package com.tdil.ljpeugeot.utils;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.sql.SQLException;
import java.util.List;
import java.util.Properties;

import javax.servlet.ServletContextEvent;

import org.apache.commons.io.FileUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;

import com.tdil.cache.blob.BlobLocalDiskCache;
import com.tdil.ljpeugeot.cache.blob.BlobDataType;
import com.tdil.ljpeugeot.cache.blob.PublicBlobResolver;
import com.tdil.ljpeugeot.daomanager.DAOManager;
import com.tdil.ljpeugeot.daomanager.MySQLDAOProvider;
import com.tdil.ljpeugeot.daomanager.SQLServerDAOProvider;
import com.tdil.ljpeugeot.model.SystemProperty;
import com.tdil.ljpeugeot.model.SystemPropertyExample;
import com.tdil.ljpeugeot.prevent.PreventConnector;
import com.tdil.ljpeugeot.roles.HomeUser;
import com.tdil.ljpeugeot.roles.PreventUser;
import com.tdil.ljpeugeot.roles.WebsiteUser;
import com.tdil.log4j.LoggerProvider;
import com.tdil.thalamus.client.cache.ThalamusCache;
import com.tdil.thalamus.client.core.ProxyConfiguration;
import com.tdil.thalamus.client.core.ThalamusClient;
import com.tdil.thalamus.client.facade.ThalamusClientBeanFacade;
import com.tdil.users.None;
import com.tdil.users.Role;
import com.tdil.utils.SystemConfig;
import com.tdil.utils.SystemPropertyCache;

public class LJPeugeotConfig extends SystemConfig {

	private static String FRONT_SERVER;

	private static String GUID;
	private static String DB_VERSION;
	
	private static long FRONT_LOGIN_DELAY;
	
	private static ProxyConfiguration HTTP_PROXY;
	private static ProxyConfiguration HTTPS_PROXY;
	private static ProxyConfiguration SOCKS_PROXY;
	
	private static String mapsUrl = "http://tms.lojackgis.com.ar/osm_tiles2/${z}/${x}/${y}.png";
	
	private static long startTime = System.currentTimeMillis();
	
	public static NativeAppsConfig nativeAppsConfig = new NativeAppsConfig();
	
//	public static VLUImportThread importThread;
	
	private static Logger getLog() {
		return LoggerProvider.getLogger(LJPeugeotConfig.class);
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
			
			basicInitSystem();

		} catch (Exception e) {
			e.printStackTrace(System.out);
			getLog().error(e.getMessage(), e);
			throw new RuntimeException(e);
		}

	}

	public static void basicInitSystem() throws MalformedURLException {
		String dbversion = SystemPropertyUtils.getSystemPropertValue("dbversion");
		if (!StringUtils.isEmpty(dbversion)) {
			setDB_VERSION(dbversion);
		} else {
			setDB_VERSION("N/A");
		}
		getLog().fatal("DBVersion is " + getDB_VERSION());
		
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
		
		String httpproxy = SystemPropertyUtils.getSystemPropertValue("proxy.http");
		if (!StringUtils.isEmpty(httpproxy)) {
			String proxyConf[] = httpproxy.split(":");
			setHTTP_PROXY(new ProxyConfiguration(proxyConf[0], Integer.valueOf(proxyConf[1])));
			getLog().fatal("Http proxy is " + httpproxy);
		} else {
			getLog().fatal("No http proxy");
		}
		String httpsproxy = SystemPropertyUtils.getSystemPropertValue("proxy.https");
		if (!StringUtils.isEmpty(httpsproxy)) {
			String proxyConf[] = httpsproxy.split(":");
			setHTTPS_PROXY(new ProxyConfiguration(proxyConf[0], Integer.valueOf(proxyConf[1])));
			getLog().fatal("Https proxy is " + httpproxy);
		} else {
			getLog().fatal("No https proxy");
		}
		
		String socksproxy = SystemPropertyUtils.getSystemPropertValue("proxy.socks");
		if (!StringUtils.isEmpty(socksproxy)) {
			String proxyConf[] = socksproxy.split(":");
			setSOCKS_PROXY(new ProxyConfiguration(proxyConf[0], Integer.valueOf(proxyConf[1])));
			getLog().fatal("Socks proxy is " + socksproxy);
		} else {
			getLog().fatal("No socks proxy");
		}
		

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
		
		String thalamusproxy = SystemPropertyUtils.getSystemPropertValue("thalamus.proxy");
		if (!StringUtils.isEmpty(thalamusproxy) && "true".equalsIgnoreCase(thalamusproxy)) {
			if (ThalamusClient.getTHALAMUS_HOST().startsWith("https")) {
				if (getHTTPS_PROXY() != null) {
					ThalamusClient.setPROXY(getHTTPS_PROXY());
					getLog().fatal("Thalamus proxy is " + getHTTPS_PROXY().getServer() + ":" + getHTTPS_PROXY().getPort());
				} else {
					getLog().fatal("No Thalamus proxy");
				}
			} else {
				if (getHTTP_PROXY() != null) {
					ThalamusClient.setPROXY(getHTTP_PROXY());
					getLog().fatal("Thalamus proxy is " + getHTTP_PROXY().getServer() + ":" + getHTTP_PROXY().getPort());
				} else {
					getLog().fatal("No Thalamus proxy");
				}
			}
		} else {
			getLog().fatal("No Thalamus proxy");
		}

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
		
		String preventproxy = SystemPropertyUtils.getSystemPropertValue("prevent.proxy");
		if (!StringUtils.isEmpty(preventproxy) && "true".equalsIgnoreCase(preventproxy)) {
			if (PreventConnector.getPreventServer().startsWith("https")) {
				if (getHTTPS_PROXY() != null) {
					PreventConnector.setPROXY(getHTTPS_PROXY());
					getLog().fatal("Prevent proxy is " + getHTTPS_PROXY().getServer() + ":" + getHTTPS_PROXY().getPort());
				} else {
					getLog().fatal("No Prevent proxy");
				}
			} else {
				if (getHTTP_PROXY() != null) {
					PreventConnector.setPROXY(getHTTP_PROXY());
					getLog().fatal("Prevent proxy is " + getHTTP_PROXY().getServer() + ":" + getHTTP_PROXY().getPort());
				} else {
					getLog().fatal("No Prevent proxy");
				}
			}
		} else {
			getLog().fatal("No Prevent proxy");
		}
		
		String preventNativeUrl = SystemPropertyUtils.getSystemPropertValue("prevent.native.url");
		if (StringUtils.isEmpty(preventNativeUrl )) {
			preventNativeUrl = "";
		} 
		nativeAppsConfig.setPreventNativeUrl(preventNativeUrl);
		getLog().fatal("prevent native url is " + preventNativeUrl);
		
		String mapsUrl = SystemPropertyUtils.getSystemPropertValue("maps.url");
		if (!StringUtils.isEmpty(mapsUrl)) {
			setMapsUrl(mapsUrl);
		}
		getLog().fatal("maps.url is " + getMapsUrl());
		
/*
		String importRange = SystemPropertyUtils.getSystemPropertValue("vlu.import.range");
		if (!StringUtils.isEmpty(importRange) && importRange.contains("-")) {
			String ranges[] = importRange.split("-");
			if (ranges.length == 2 && ranges[0].contains(":") && ranges[1].contains(":")) {
				try {
					String start[] = ranges[0].split(":");
					VLUImportThread.setStartHour(Integer.parseInt(start[0]));
					VLUImportThread.setStartMinutes(Integer.parseInt(start[1]));
					String end[] = ranges[1].split(":");
					VLUImportThread.setEndHour(Integer.parseInt(end[0]));
					VLUImportThread.setEndMinutes(Integer.parseInt(end[1]));
				} catch (Exception e) {
					getLog().error(e.getMessage(), e);
				}
			}
		}
		getLog().fatal("Vlu import range is " + VLUImportThread.getStartHour() + ":" + VLUImportThread.getStartMinutes() + "-"
				+ VLUImportThread.getEndHour() + ":" + VLUImportThread.getEndMinutes());
		
		importThread = new VLUImportThread();
		importThread.start();*/
	}

	@Override
	protected String getLogDir() {
		return "ljpeugeotlogs";
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
				getLog().fatal("LJPeugeotConfig: " + property.getPropkey() + "=" + property.getPropvalue());
				SystemPropertyCache.put(property.getPropkey(), property.getPropvalue());
			}
		} catch (SQLException e) {
			getLog().error(e.getMessage(), e);
		}

	}

	@Override
	protected void initBlobCache() {
		getLog().fatal("LJPeugeotConfig: blob cache starting ");
		String path = SystemPropertyCache.getTempPath();
		path = path + "/ljpeugeotblobs/";
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
			getLog().error("No se pudo escribir en el cache de blobs " + path);
			return;
		}
		getLog().fatal("LJPeugeotConfig: blob cache started at " + path);
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

	public static ProxyConfiguration getHTTP_PROXY() {
		return HTTP_PROXY;
	}

	public static void setHTTP_PROXY(ProxyConfiguration hTTP_PROXY) {
		HTTP_PROXY = hTTP_PROXY;
	}

	public static ProxyConfiguration getHTTPS_PROXY() {
		return HTTPS_PROXY;
	}

	public static void setHTTPS_PROXY(ProxyConfiguration hTTPS_PROXY) {
		HTTPS_PROXY = hTTPS_PROXY;
	}

	public static String getDB_VERSION() {
		return DB_VERSION;
	}

	public static void setDB_VERSION(String dB_VERSION) {
		DB_VERSION = dB_VERSION;
	}


	public static ProxyConfiguration getSOCKS_PROXY() {
		return SOCKS_PROXY;
	}

	public static void setSOCKS_PROXY(ProxyConfiguration sOCKS_PROXY) {
		SOCKS_PROXY = sOCKS_PROXY;
	}

	public static String getMapsUrl() {
		return mapsUrl;
	}

	public static void setMapsUrl(String mapsUrl) {
		LJPeugeotConfig.mapsUrl = mapsUrl;
	}

	public static long getStartTime() {
		return startTime;
	}

	public static void setStartTime(long startTime) {
		LJPeugeotConfig.startTime = startTime;
	}

}
