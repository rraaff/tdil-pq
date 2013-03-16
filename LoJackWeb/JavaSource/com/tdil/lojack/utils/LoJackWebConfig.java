package com.tdil.lojack.utils;

import java.io.File;
import java.io.FileInputStream;
import java.util.Enumeration;
import java.util.Properties;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import org.apache.log4j.BasicConfigurator;
import org.apache.log4j.Level;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.apache.poi.hssf.record.formula.functions.Proper;

import com.tdil.log4j.LoggerProvider;
import com.tdil.lojack.gis.GISConnector;
import com.tdil.lojack.roles.WebsiteUser;
import com.tdil.struts.resources.ApplicationResources;
import com.tdil.thalamus.client.cache.ThalamusCache;
import com.tdil.thalamus.client.core.ThalamusClient;
import com.tdil.thalamus.client.facade.ThalamusClientBeanFacade;
import com.tdil.users.Role;

public class LoJackWebConfig implements ServletContextListener {

	private static Logger getLog() {
		return LoggerProvider.getLogger(LoJackWebConfig.class);
	}

	public void contextDestroyed(ServletContextEvent arg0) {
		// TODO Auto-generated method stub

	}

	public void contextInitialized(ServletContextEvent sce) {
		BasicConfigurator.configure();
		Logger rootLogger = Logger.getRootLogger();
		rootLogger.setLevel(Level.ERROR);
		try {
			getLog().fatal("LoJackWebConfig initializing");

			String propertiesLocation = System.getProperty("lojack.configuration");
			if (propertiesLocation == null) {
				getLog().fatal("no lojack.configuration provided");
			}
			Properties prop = new Properties();
			File file = new File(propertiesLocation);
			if (file == null || !file.exists()) {
				getLog().fatal("lojack.configuration not found: " + (propertiesLocation == null ? "null" : propertiesLocation));
			} else {
				getLog().fatal("LoJackWebConfig initializing from " + propertiesLocation);
			}
			FileInputStream fileInput = null;
			try {
				fileInput = new FileInputStream(file);
				prop.load(fileInput);
			} catch (Exception e1) {
				getLog().fatal(e1.getMessage(), e1);
			} finally {
				if (fileInput != null) {
					fileInput.close();
				}
			}
			String logFilePath = prop.getProperty("log4j.location");
			LoggerProvider.initialize(logFilePath, LogManager.getCurrentLoggers());
			
			ServletContext c = sce.getServletContext();
			if (c != null) {
				String applicationResourcesParam = c.getInitParameter("application.resources");
				getLog().fatal(
						"LoJackWebConfig application.resources is "
								+ (applicationResourcesParam == null ? "null" : applicationResourcesParam));
				ApplicationResources.init(applicationResourcesParam);
				
				String thalamusserver = prop.getProperty("thalamus.server");
				if (thalamusserver != null) {
					ThalamusClient.setTHALAMUS_SERVER(thalamusserver);
				}
				getLog().fatal(
						"Thalamus server is " + ThalamusClient.getTHALAMUS_SERVER());
				
				String thalamustouchpointCode = prop.getProperty("thalamus.touchpoint.code");
				if (thalamustouchpointCode != null) {
					ThalamusClient.setTHALAMUS_TOUCHPOINT_CODE(thalamustouchpointCode);
				}
				getLog().fatal(
						"Thalamus touchpoint is " + ThalamusClient.getTHALAMUS_TOUCHPOINT_CODE());
				
				String thalamustouchpointToken = prop.getProperty("thalamus.touchpoint.token");
				if (thalamustouchpointToken != null) {
					ThalamusClient.setTHALAMUS_TOUCHPOINT_TOKEN(thalamustouchpointToken);
				}
				getLog().fatal(
						"Thalamus touchpoint is " + ThalamusClient.getTHALAMUS_TOUCHPOINT_TOKEN());
				ThalamusCache.configureWith(prop);
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
				
				String gisserver = prop.getProperty("gis.server");
				
				if (gisserver != null) {
					GISConnector.setGisServer(gisserver);
				}
				getLog().fatal(
						"GIS server is " + (gisserver == null ? "null" : gisserver));
			}
			getLog().fatal("LoJackWebConfig initialized");
		} catch (Exception e) {
			e.printStackTrace(System.out);
			getLog().error(e.getMessage(), e);
		}
	}

}
