package com.tdil.lojack.utils;

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
import com.tdil.log4j.LoggerProvider;
import com.tdil.lojack.cache.blob.BlobDataType;
import com.tdil.lojack.cache.blob.PublicBlobResolver;
import com.tdil.lojack.camera.IPCamera;
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
import com.tdil.lojack.vlu.VLUImportThread;
import com.tdil.thalamus.client.cache.ThalamusCache;
import com.tdil.thalamus.client.core.ProxyConfiguration;
import com.tdil.thalamus.client.core.ThalamusClient;
import com.tdil.thalamus.client.facade.ThalamusClientBeanFacade;
import com.tdil.users.None;
import com.tdil.users.Role;
import com.tdil.utils.SystemConfig;
import com.tdil.utils.SystemPropertyCache;

public class LoJackConfig extends SystemConfig {

	public static final String CAMERA_MOBILE_LOCAL = "local";
	public static final String CAMERA_MOBILE_PROXY = "proxy";
	public static final String CAMERA_MOBILE_EXTERNAL = "external";
	public static final String CAMERA_MOBILE_SOCKET = "socket";

	private static String FRONT_SERVER;

	private static String GUID;
	private static String DB_VERSION;
	
	private static long FRONT_LOGIN_DELAY;
	
	private static boolean showAgenda = true;
	private static boolean showEmailNotification = true;
	
	private static ProxyConfiguration HTTP_PROXY;
	private static ProxyConfiguration HTTPS_PROXY;
	private static ProxyConfiguration SOCKS_PROXY;
	
	private static int cameraConnectTimeOut = 2000;
	private static int cameraReadTimeOut = 2000;
	private static int cameraCache = 1000;
	private static int cameraCacheSize = 50;
	
	private static String cameraMobileMode;
	private static String cameraMobileExternalUrl;
	private static ProxyConfiguration cameraMobileSocket;
	
	private static String videocar = "http://www.youtube.com/embed/5Xe5pODPq1I";
	private static String videohome = "http://www.youtube.com/embed/Iz_VvsFwXQI";
	private static String videopets = "http://www.youtube.com/embed/M8VhrMM0j-Q";
	private static String videoloapp = "http://www.youtube.com/embed/G18ElDg9s-o";
	
	private static String mobilevideocar = "http://youtu.be/5Xe5pODPq1I";
	private static String mobilevideohome = "http://youtu.be/Iz_VvsFwXQI";
	private static String mobilevideopets = "http://youtu.be/M8VhrMM0j-Q";
	
	private static String clubLoJackUrl = "http://www.clublojack.com.ar/";
	private static boolean clubLoJackShow = false;
	
	private static String mapsUrl = "http://tms.lojackgis.com.ar/osm_tiles2/${z}/${x}/${y}.png";
	
	private static String peugeotSign = "";
	
	private static long startTime = System.currentTimeMillis();
	
	public static NativeAppsConfig nativeAppsConfig = new NativeAppsConfig();
	
	public static VLUImportThread importThread;
	
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
			
			basicInitSystem();

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
	protected boolean overwriteLog4jOnStart() {
		return true;
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
		
		String videoHome = SystemPropertyUtils.getSystemPropertValue("video.home");
		if (!StringUtils.isEmpty(videoHome)) {
			setVideohome(videoHome);
		}
		getLog().fatal("Videohome is " + getVideohome());
		
		String videoCar = SystemPropertyUtils.getSystemPropertValue("video.car");
		if (!StringUtils.isEmpty(videoCar)) {
			setVideocar(videoCar);
		}
		getLog().fatal("Videocar is " + getVideocar());
		
		String videopets = SystemPropertyUtils.getSystemPropertValue("video.pets");
		if (!StringUtils.isEmpty(videopets)) {
			setVideopets(videopets);
		}
		getLog().fatal("Videopets is " + getVideopets());
		
		String videoloapp = SystemPropertyUtils.getSystemPropertValue("video.loapp");
		if (!StringUtils.isEmpty(videoloapp)) {
			setVideoloapp(videoloapp);
		}
		getLog().fatal("Videoloapp is " + getVideoloapp());

		String videoMobileHome = SystemPropertyUtils.getSystemPropertValue("video.mobile.home");
		if (!StringUtils.isEmpty(videoMobileHome)) {
			setMobilevideohome(videoMobileHome);
		}
		getLog().fatal("Videohome mobile is " + getMobilevideohome());
		
		String videoMobileCar = SystemPropertyUtils.getSystemPropertValue("video.mobile.car");
		if (!StringUtils.isEmpty(videoMobileCar)) {
			setMobilevideocar(videoMobileCar);
		}
		getLog().fatal("Videocar mobile is " + getMobilevideocar());
		
		String videoMobilepets = SystemPropertyUtils.getSystemPropertValue("video.mobile.pets");
		if (!StringUtils.isEmpty(videoMobilepets)) {
			setMobilevideopets(videoMobilepets);
		}
		getLog().fatal("Videopets mobile is " + getMobilevideopets());
		
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
		
		String mwprotocol = SystemPropertyUtils.getSystemPropertValue("mw.protocol");
		if (StringUtils.isEmpty(mwprotocol)) {
			mwprotocol = JSONProtocol.PROTOCOL;
		} 
		LoJackServicesConnector.setProtocol(JSONProtocol.PROTOCOL.equalsIgnoreCase(mwprotocol) ? new JSONProtocol() : new CarpathiaProtocol());
		getLog().fatal("MW protocol is " + mwprotocol);
		
		String showAgenda = SystemPropertyUtils.getSystemPropertValue("mw.showAgenda");
		if (StringUtils.isEmpty(showAgenda)) {
			setShowAgenda(true);
		} else {
			setShowAgenda("true".equals(showAgenda));
		}
		getLog().fatal("MW showAgenda is " + isShowAgenda());
		
		String showEmailNotification = SystemPropertyUtils.getSystemPropertValue("mw.showEmailNotification");
		if (StringUtils.isEmpty(showEmailNotification)) {
			setShowEmailNotification(true);
		} else {
			setShowEmailNotification("true".equals(showEmailNotification));
		}
		getLog().fatal("MW showEmailNotification is " + isShowEmailNotification());

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
		
		String middlewareproxy = SystemPropertyUtils.getSystemPropertValue("middleware.proxy");
		if (!StringUtils.isEmpty(middlewareproxy) && "true".equalsIgnoreCase(middlewareproxy)) {
			if (LoJackServicesConnector.getGisServer().startsWith("https")) {
				if (getHTTPS_PROXY() != null) {
					LoJackServicesConnector.setPROXY(getHTTPS_PROXY());
					getLog().fatal("Middleware proxy is " + getHTTPS_PROXY().getServer() + ":" + getHTTPS_PROXY().getPort());
				} else {
					getLog().fatal("No Middleware proxy");
				}
			} else {
				if (getHTTP_PROXY() != null) {
					LoJackServicesConnector.setPROXY(getHTTP_PROXY());
					getLog().fatal("Middleware proxy is " + getHTTP_PROXY().getServer() + ":" + getHTTP_PROXY().getPort());
				} else {
					getLog().fatal("No Middleware proxy");
				}
			}
		} else {
			getLog().fatal("No Middleware proxy");
		}

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
		
		String cameraproxy = SystemPropertyUtils.getSystemPropertValue("camera.proxy");
		if (!StringUtils.isEmpty(cameraproxy ) && "true".equalsIgnoreCase(cameraproxy )) {
			if (getHTTPS_PROXY() != null) {
				IPCamera.setProxyConfigurationHttps(new com.tdil.lojack.camera.ProxyConfiguration(getHTTPS_PROXY().getServer(), getHTTPS_PROXY().getPort()));
				getLog().fatal("Camera proxy for https is " + getHTTPS_PROXY().getServer() + ":" + getHTTPS_PROXY().getPort());
			} else {
				getLog().fatal("No camera proxy for https");
			}
			if (getHTTP_PROXY() != null) {
				IPCamera.setProxyConfiguration(new com.tdil.lojack.camera.ProxyConfiguration(getHTTP_PROXY().getServer(), getHTTP_PROXY().getPort()));
				getLog().fatal("Camera proxy for is " + getHTTP_PROXY().getServer() + ":" + getHTTP_PROXY().getPort());
			} else {
				getLog().fatal("No camera proxy for http");
			}
		} else {
			getLog().fatal("No camera proxy");
		}
		
		String cameraConnectTimeOut = SystemPropertyUtils.getSystemPropertValue("camera.connectTimeOut");
		if (!StringUtils.isEmpty(cameraConnectTimeOut )) {
			setCameraConnectTimeOut(Integer.parseInt(cameraConnectTimeOut));
		} 
		getLog().fatal("Camera connect time out is " + getCameraConnectTimeOut());
		
		String cameraReaTimeOut = SystemPropertyUtils.getSystemPropertValue("camera.readTimeOut");
		if (!StringUtils.isEmpty(cameraReaTimeOut )) {
			setCameraReadTimeOut(Integer.parseInt(cameraReaTimeOut));
		} 
		getLog().fatal("Camera read time out is " + getCameraReadTimeOut());
		
		String cameraCache = SystemPropertyUtils.getSystemPropertValue("camera.cache");
		if (!StringUtils.isEmpty(cameraCache )) {
			setCameraCache(Integer.parseInt(cameraCache));
		} 
		getLog().fatal("Camera cache is " + getCameraCache());
		
		String cameraCacheSize = SystemPropertyUtils.getSystemPropertValue("camera.cacheSize");
		if (!StringUtils.isEmpty(cameraCacheSize )) {
			setCameraCacheSize(Integer.parseInt(cameraCacheSize));
		} 
		getLog().fatal("Camera cacheSize is " + getCameraCacheSize());
		
		CameraCache.init(getCameraCache(), getCameraCacheSize());
		
		String cameraMobileMode = SystemPropertyUtils.getSystemPropertValue("camera.mobile.mode");
		if (StringUtils.isEmpty(cameraMobileMode )) {
			cameraMobileMode = CAMERA_MOBILE_LOCAL;
		} 
		setCameraMobileMode(cameraMobileMode);
		getLog().fatal("Camera mobile mode is " + cameraMobileMode);
		
		String cameraMobileExternalUrl = SystemPropertyUtils.getSystemPropertValue("camera.mobile.external.url");
		if (StringUtils.isEmpty(cameraMobileExternalUrl )) {
			cameraMobileExternalUrl = "";
		} 
		setCameraMobileExternalUrl(cameraMobileExternalUrl);
		getLog().fatal("Camera mobile mode external url is " + cameraMobileExternalUrl);
		
		String cameraMobileSocket = SystemPropertyUtils.getSystemPropertValue("camera.mobile.socket");
		if (!StringUtils.isEmpty(cameraMobileSocket)) {
			String proxyConf[] = cameraMobileSocket.split(":");
			setCameraMobileSocket(new ProxyConfiguration(proxyConf[0], Integer.parseInt(proxyConf[1])));
		} 
		getLog().fatal("Camera mobile socket is " + getCameraMobileSocket());
		
		// antive apps
		String cameraNativeRefreshTime = SystemPropertyUtils.getSystemPropertValue("camera.native.refreshTime");
		if (!StringUtils.isEmpty(cameraNativeRefreshTime)) {
			nativeAppsConfig.setCameraNativeRefreshTime(Long.parseLong(cameraNativeRefreshTime));
		} 
		getLog().fatal("Camera Native Refresh Time is " + nativeAppsConfig.getCameraNativeRefreshTime());
		String videoCarNative = SystemPropertyUtils.getSystemPropertValue("video.car.native");
		if (StringUtils.isEmpty(videoCarNative )) {
			videoCarNative = "";
		} 
		nativeAppsConfig.setVideoCarNative(videoCarNative);
		getLog().fatal("Video car native is " + videoCarNative);
		String videoPetsNative = SystemPropertyUtils.getSystemPropertValue("video.pets.native");
		if (StringUtils.isEmpty(videoPetsNative )) {
			videoPetsNative = "";
		} 
		nativeAppsConfig.setVideoPetsNative(videoPetsNative);
		getLog().fatal("Video pets native is " + videoPetsNative);
		
		String preventNativeUrl = SystemPropertyUtils.getSystemPropertValue("prevent.native.url");
		if (StringUtils.isEmpty(preventNativeUrl )) {
			preventNativeUrl = "";
		} 
		nativeAppsConfig.setPreventNativeUrl(preventNativeUrl);
		getLog().fatal("prevent native url is " + preventNativeUrl);
		
		String petsNativeUrl = SystemPropertyUtils.getSystemPropertValue("pets.native.url");
		if (StringUtils.isEmpty(petsNativeUrl )) {
			petsNativeUrl = "";
		} 
		nativeAppsConfig.setPetsNativeUrl(petsNativeUrl);
		getLog().fatal("pets native url is " + petsNativeUrl);
		
		
		String tmpClubLoJack = SystemPropertyUtils.getSystemPropertValue("clubLoJack.url");
		if (!StringUtils.isEmpty(tmpClubLoJack)) {
			setClubLoJackUrl(tmpClubLoJack);
		}
		getLog().fatal("clubLoJack.url is " + getClubLoJackUrl());
		
		String clubLoJackShow = SystemPropertyUtils.getSystemPropertValue("clubLoJack.show");
		if (StringUtils.isEmpty(clubLoJackShow)) {
			setClubLoJackShow(false);
		} else {
			setClubLoJackShow("true".equals(clubLoJackShow));
		}
		getLog().fatal("ClubloJack show is " + isClubLoJackShow());
		
		String mapsUrl = SystemPropertyUtils.getSystemPropertValue("maps.url");
		if (!StringUtils.isEmpty(mapsUrl)) {
			setMapsUrl(mapsUrl);
		}
		getLog().fatal("maps.url is " + getMapsUrl());
		
		String peugeotSign = SystemPropertyUtils.getSystemPropertValue("peugeot.sign");
		if (!StringUtils.isEmpty(peugeotSign)) {
			setPeugeotSign(peugeotSign);
		}
		getLog().fatal("peugeotSign is " + getPeugeotSign());
		
		IPCamera.setLogger(new CameraLog4jLogger());

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
		
		String importRangeRepaired = SystemPropertyUtils.getSystemPropertValue("vlu.deleteRepaired.range");
		if (!StringUtils.isEmpty(importRangeRepaired) && importRangeRepaired.contains("-")) {
			String ranges[] = importRangeRepaired.split("-");
			if (ranges.length == 2 && ranges[0].contains(":") && ranges[1].contains(":")) {
				try {
					String start[] = ranges[0].split(":");
					VLUImportThread.setStartRepairedHour(Integer.parseInt(start[0]));
					VLUImportThread.setStartRepairedMinutes(Integer.parseInt(start[1]));
					String end[] = ranges[1].split(":");
					VLUImportThread.setEndRepairedHour(Integer.parseInt(end[0]));
					VLUImportThread.setEndRepairedMinutes(Integer.parseInt(end[1]));
				} catch (Exception e) {
					getLog().error(e.getMessage(), e);
				}
			}
		}
		getLog().fatal("Vlu import repaired domains range is " + VLUImportThread.getStartRepairedHour() + ":" + VLUImportThread.getStartRepairedMinutes() + "-"
				+ VLUImportThread.getEndRepairedHour() + ":" + VLUImportThread.getEndRepairedMinutes());
		
		String repairedDomainsUrl = SystemPropertyUtils.getSystemPropertValue("vlu.deleteRepaired.url");
		if (!StringUtils.isEmpty(repairedDomainsUrl)) {
			VLUImportThread.setRepairedDomainURL(repairedDomainsUrl);
		}
		getLog().fatal("Vlu import repaired domains url is " + VLUImportThread.getRepairedDomainURL());
		
		String repairedDomainsProxy = SystemPropertyUtils.getSystemPropertValue("vlu.deleteRepaired.proxy");
		if (!StringUtils.isEmpty(repairedDomainsProxy ) && "true".equalsIgnoreCase(repairedDomainsProxy )) {
			if (repairedDomainsProxy.startsWith("https")) {
				if (getHTTPS_PROXY() != null) {
					VLUImportThread.setPROXY(new ProxyConfiguration(getHTTPS_PROXY().getServer(), getHTTPS_PROXY().getPort()));
					getLog().fatal("Vlu import repaired domains proxy for https is " + getHTTPS_PROXY().getServer() + ":" + getHTTPS_PROXY().getPort());
				} else {
					getLog().fatal("Vlu import repaired domains proxy https not configured");
				}
			} else {
				if (getHTTP_PROXY() != null) {
					VLUImportThread.setPROXY(new ProxyConfiguration(getHTTP_PROXY().getServer(), getHTTP_PROXY().getPort()));
					getLog().fatal("Vlu import repaired domains proxy for http is " + getHTTP_PROXY().getServer() + ":" + getHTTP_PROXY().getPort());
				} else {
					getLog().fatal("Vlu import repaired domains proxy http not configured");
				}
			}
		} else {
			getLog().fatal("Vlu import repaired domains not using proxy");
		}
		
		
		if (importThread == null) {
			importThread = new VLUImportThread();
			importThread.start();
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
			getLog().error("No se pudo escribir en el cache de blobs " + path);
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

	public static String getVideocar() {
		return videocar;
	}

	public static void setVideocar(String videocar) {
		LoJackConfig.videocar = videocar;
	}

	public static String getVideohome() {
		return videohome;
	}

	public static void setVideohome(String videohome) {
		LoJackConfig.videohome = videohome;
	}

	public static String getVideopets() {
		return videopets;
	}

	public static void setVideopets(String videopets) {
		LoJackConfig.videopets = videopets;
	}

	public static String getVideoloapp() {
		return videoloapp;
	}

	public static void setVideoloapp(String videoloapp) {
		LoJackConfig.videoloapp = videoloapp;
	}

	public static String getMobilevideocar() {
		return mobilevideocar;
	}

	public static void setMobilevideocar(String mobilevideocar) {
		LoJackConfig.mobilevideocar = mobilevideocar;
	}

	public static String getMobilevideohome() {
		return mobilevideohome;
	}

	public static void setMobilevideohome(String mobilevideohome) {
		LoJackConfig.mobilevideohome = mobilevideohome;
	}

	public static String getMobilevideopets() {
		return mobilevideopets;
	}

	public static void setMobilevideopets(String mobilevideopets) {
		LoJackConfig.mobilevideopets = mobilevideopets;
	}

	public static int getCameraConnectTimeOut() {
		return cameraConnectTimeOut;
	}

	public static void setCameraConnectTimeOut(int cameraConnectTimeOut) {
		LoJackConfig.cameraConnectTimeOut = cameraConnectTimeOut;
	}

	public static int getCameraReadTimeOut() {
		return cameraReadTimeOut;
	}

	public static void setCameraReadTimeOut(int cameraReadTimeOut) {
		LoJackConfig.cameraReadTimeOut = cameraReadTimeOut;
	}
	
	public static boolean isCameraMobileModeLocal() {
		return CAMERA_MOBILE_LOCAL.equals(getCameraMobileMode());
	}
	public static boolean isCameraMobileModeProxy() {
		return CAMERA_MOBILE_PROXY.equals(getCameraMobileMode());
	}
	public static boolean isCameraMobileModeExternal() {
		return CAMERA_MOBILE_EXTERNAL.equals(getCameraMobileMode());
	}
	public static boolean isCameraMobileModeSocket() {
		return CAMERA_MOBILE_SOCKET.equals(getCameraMobileMode());
	}

	public static String getCameraMobileMode() {
		return cameraMobileMode;
	}

	public static void setCameraMobileMode(String cameraMobileMode) {
		LoJackConfig.cameraMobileMode = cameraMobileMode;
	}

	public static String getCameraMobileExternalUrl() {
		return cameraMobileExternalUrl;
	}

	public static void setCameraMobileExternalUrl(String cameraMobileExternalUrl) {
		LoJackConfig.cameraMobileExternalUrl = cameraMobileExternalUrl;
	}

	public static boolean isShowAgenda() {
		return showAgenda;
	}

	public static void setShowAgenda(boolean showAgenda) {
		LoJackConfig.showAgenda = showAgenda;
	}

	public static boolean isShowEmailNotification() {
		return showEmailNotification;
	}

	public static void setShowEmailNotification(boolean showEmailNotification) {
		LoJackConfig.showEmailNotification = showEmailNotification;
	}

	public static ProxyConfiguration getCameraMobileSocket() {
		return cameraMobileSocket;
	}

	public static void setCameraMobileSocket(ProxyConfiguration cameraMobileSocket) {
		LoJackConfig.cameraMobileSocket = cameraMobileSocket;
	}

	public static ProxyConfiguration getSOCKS_PROXY() {
		return SOCKS_PROXY;
	}

	public static void setSOCKS_PROXY(ProxyConfiguration sOCKS_PROXY) {
		SOCKS_PROXY = sOCKS_PROXY;
	}

	public static int getCameraCache() {
		return cameraCache;
	}

	public static void setCameraCache(int cameraCache) {
		LoJackConfig.cameraCache = cameraCache;
	}

	public static int getCameraCacheSize() {
		return cameraCacheSize;
	}

	public static void setCameraCacheSize(int cameraCacheSize) {
		LoJackConfig.cameraCacheSize = cameraCacheSize;
	}

	public static String getClubLoJackUrl() {
		return clubLoJackUrl;
	}

	public static void setClubLoJackUrl(String clubLoJackUrl) {
		LoJackConfig.clubLoJackUrl = clubLoJackUrl;
	}

	public static boolean isClubLoJackShow() {
		return clubLoJackShow;
	}

	public static void setClubLoJackShow(boolean clubLoJackShow) {
		LoJackConfig.clubLoJackShow = clubLoJackShow;
	}

	public static String getMapsUrl() {
		return mapsUrl;
	}

	public static void setMapsUrl(String mapsUrl) {
		LoJackConfig.mapsUrl = mapsUrl;
	}

	public static long getStartTime() {
		return startTime;
	}

	public static void setStartTime(long startTime) {
		LoJackConfig.startTime = startTime;
	}

	public static String getPeugeotSign() {
		return peugeotSign;
	}

	public static void setPeugeotSign(String peugeotSign) {
		LoJackConfig.peugeotSign = peugeotSign;
	}

}
