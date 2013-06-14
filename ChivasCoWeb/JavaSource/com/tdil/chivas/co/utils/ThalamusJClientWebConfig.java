package com.tdil.chivas.co.utils;

import java.net.URL;
import java.util.Enumeration;
import java.util.Properties;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import org.apache.log4j.BasicConfigurator;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;

import com.tdil.chivas.co.roles.WebsiteUser;
import com.tdil.log4j.LoggerProvider;
import com.tdil.struts.resources.ApplicationResources;
import com.tdil.thalamus.client.cache.ThalamusCache;
import com.tdil.thalamus.client.core.ThalamusClient;
import com.tdil.thalamus.client.facade.ThalamusClientBeanFacade;
import com.tdil.users.Role;

public class ThalamusJClientWebConfig implements ServletContextListener {

	private static Logger getLog() {
		return LoggerProvider.getLogger(ThalamusJClientWebConfig.class);
	}

	public void contextDestroyed(ServletContextEvent arg0) {
		// TODO Auto-generated method stub

	}

	public void contextInitialized(ServletContextEvent sce) {
		BasicConfigurator.configure();
		Logger rootLogger = Logger.getRootLogger();
		rootLogger.setLevel(Level.ERROR);
		LoggerProvider.getLogger(ThalamusClient.class).setLevel(Level.DEBUG);
		try {
			getLog().fatal("ThalamusJClientWebConfig initializing");

			ServletContext c = sce.getServletContext();
			if (c != null) {
				String applicationResourcesParam = c.getInitParameter("application.resources");
				getLog().fatal(
						"ThalamusJClientWebConfig application.resources is "
								+ (applicationResourcesParam == null ? "null" : applicationResourcesParam));
				ApplicationResources.init(applicationResourcesParam);

				String thalamusserver = c.getInitParameter("thalamus.server");
				if (thalamusserver != null) {
					ThalamusClient.setTHALAMUS_SERVER(thalamusserver);
				}
				getLog().fatal(
						"Thalamus server is " + ThalamusClient.getTHALAMUS_SERVER());

				URL thalamusUrl = new URL(thalamusserver);
				String thalamushost = c.getInitParameter("thalamus.host");
				if (!org.apache.commons.lang.StringUtils.isEmpty(thalamushost)) {
					ThalamusClient.setTHALAMUS_HOST(thalamushost);
				} else {
					ThalamusClient.setTHALAMUS_HOST(thalamusUrl.getHost());
				}
				getLog().fatal(
						"Thalamus host is " + ThalamusClient.getTHALAMUS_HOST());

				String thalamuscookiepath = c.getInitParameter("thalamus.cookiePath");
				if (!org.apache.commons.lang.StringUtils.isEmpty(thalamuscookiepath)) {
					ThalamusClient.setTHALAMUS_JSESSIONID_COOKIE_PATH(thalamuscookiepath);
				} else {
					ThalamusClient.setTHALAMUS_JSESSIONID_COOKIE_PATH(thalamusUrl.getPath());
				}
				getLog().fatal(
						"Thalamus cookie path is " + ThalamusClient.getTHALAMUS_JSESSIONID_COOKIE_PATH());


				String thalamustouchpointCode = c.getInitParameter("thalamus.touchpoint.code");
				if (thalamustouchpointCode != null) {
					ThalamusClient.setTHALAMUS_TOUCHPOINT_CODE(thalamustouchpointCode);
				}
				getLog().fatal(
						"Thalamus touchpoint is " + ThalamusClient.getTHALAMUS_TOUCHPOINT_CODE());

				String thalamustouchpointToken = c.getInitParameter("thalamus.touchpoint.token");
				if (thalamustouchpointToken != null) {
					ThalamusClient.setTHALAMUS_TOUCHPOINT_TOKEN(thalamustouchpointToken);
				}
				getLog().fatal(
						"Thalamus touchpoint is " + ThalamusClient.getTHALAMUS_TOUCHPOINT_TOKEN());

				Properties properties = new Properties();
				Enumeration<String> enumeration = c.getInitParameterNames();
				while (enumeration.hasMoreElements()) {
					String key = enumeration.nextElement();
					if (key.startsWith("thalamus.cache")) {
						properties.put(key, c.getInitParameter(key));
					}
				}
				ThalamusCache.configureWith(properties);
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
					//getLog().fatal("Initializing twitter login");
					//ThalamusClientBeanFacade.getTwitterLogin();
					getLog().fatal("Cache initialized");
				} catch (Exception e) {
					getLog().error("Can not initialize caches", e);
				}
				Role.addRole(WebsiteUser.INSTANCE);
			}
			getLog().fatal("ThalamusJClientWebConfig initialized");
		} catch (Exception e) {
			e.printStackTrace(System.out);
			getLog().error(e.getMessage(), e);
		}
	}

}
