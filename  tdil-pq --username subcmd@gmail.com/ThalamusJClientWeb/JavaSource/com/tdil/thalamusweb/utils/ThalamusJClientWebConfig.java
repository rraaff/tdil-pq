package com.tdil.thalamusweb.utils;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import org.apache.log4j.BasicConfigurator;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;

import com.tdil.log4j.LoggerProvider;
import com.tdil.struts.resources.ApplicationResources;

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
		try {
			getLog().fatal("ThalamusJClientWebConfig initializing");

			ServletContext c = sce.getServletContext();
			if (c != null) {
				String applicationResourcesParam = c.getInitParameter("application.resources");
				getLog().fatal(
						"ThalamusJClientWebConfig application.resources is "
								+ (applicationResourcesParam == null ? "null" : applicationResourcesParam));
				ApplicationResources.init(applicationResourcesParam);
			}
		} catch (Exception e) {
			e.printStackTrace(System.out);
			getLog().error(e.getMessage(), e);
		}
	}

}
