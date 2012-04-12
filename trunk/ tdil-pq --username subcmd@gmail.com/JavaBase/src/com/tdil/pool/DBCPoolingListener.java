package com.tdil.pool;

import java.util.Properties;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.sql.DataSource;

import org.apache.log4j.BasicConfigurator;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;

import com.tdil.ibatis.IBatisManager;
import com.tdil.log4j.LoggerProvider;
import com.tdil.struts.resources.ApplicationResources;
import com.tdil.utils.SystemConfig;

public class DBCPoolingListener implements ServletContextListener {

	public void contextInitialized(ServletContextEvent sce) {

		BasicConfigurator.configure();
		Logger rootLogger = Logger.getRootLogger();
		rootLogger.setLevel(Level.ERROR);
		try {
			getLog().fatal("DBCPoolingListener initializing");
			// Obtain our environment naming context
			Context envCtx = (Context) new InitialContext().lookup("java:comp/env");
			// Look up our data source
			DataSource ds = (DataSource) envCtx.lookup("jdbc/database");
			DatasourceManager.setDatasource(ds);
			IBatisManager.init("SqlMapConfig-JNDI.xml", new Properties());
			getLog().fatal("DBCPoolingListener initialized");

			ServletContext c = sce.getServletContext();
			if (c != null) {
				String applicationResourcesParam = c.getInitParameter("application.resources");
				getLog().fatal(
						"DBCPoolingListener application.resources is "
								+ (applicationResourcesParam == null ? "null" : applicationResourcesParam));
				ApplicationResources.init(applicationResourcesParam);
				
				String systemConfigParam = c.getInitParameter("system.config");
				getLog().fatal(
						"DBCPoolingListener system.config is "
								+ (systemConfigParam == null ? "null" : systemConfigParam));
				if (systemConfigParam != null) {
					String name = c.getInitParameter("system.config");
					Class aClass = Class.forName(name);
					SystemConfig systemConfig = (SystemConfig) aClass.newInstance();
					systemConfig.init(sce);
				}
			}

		} catch (NamingException e) {
			e.printStackTrace(System.out);
			getLog().error(e.getMessage(), e);
		} catch (Exception e) {
			e.printStackTrace(System.out);
			getLog().error(e.getMessage(), e);
		}
	}

	private static Logger getLog() {
		return LoggerProvider.getLogger(DBCPoolingListener.class);
	}

	public void contextDestroyed(ServletContextEvent sce) {
	}
}
