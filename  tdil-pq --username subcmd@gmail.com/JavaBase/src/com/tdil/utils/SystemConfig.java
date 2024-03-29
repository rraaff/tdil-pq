package com.tdil.utils;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

import javax.servlet.ServletContextEvent;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import com.tdil.log4j.LoggerProvider;
import com.tdil.struts.TransactionalAction;
import com.tdil.struts.ValidationException;
import com.tdil.subsystem.generic.GenericTransactionExecutionService;
import com.tdil.users.None;
import com.tdil.users.Role;

public abstract class SystemConfig {
	
	public static String STATIC_RESOURCES_VERSION;

	private static Logger getLog() {
		return LoggerProvider.getLogger(SystemConfig.class);
	}

	public static DateFormat getDateFormat() {
		return new SimpleDateFormat("dd MMM yyyy");
	}

	public static DateFormat getDateFormatWithMinutes() {
		return new SimpleDateFormat("dd MMM yyyy HH:mm");
	}

	public void init(ServletContextEvent sce) {
		migrateDatabase();
		Role.addRole(None.INSTANCE);
		initRoles();
		initXMLALias();
		loadPropertiesFromDB();
		loadProperties();
		initLogger();
		initBlobCache();
		
		if ("true".equals(System.getProperty("dev"))) {
			STATIC_RESOURCES_VERSION = String.valueOf(System.currentTimeMillis());
		} else {
			String buildnumberFilePath = sce.getServletContext().getRealPath("buildnumber");
			try {
				File file = new File(buildnumberFilePath);
				if (!file.exists()) {
					STATIC_RESOURCES_VERSION = String.valueOf(System.currentTimeMillis());
				} else {
					STATIC_RESOURCES_VERSION = FileUtils.readFileToString(file);
				}
			} catch (Exception e) {
				STATIC_RESOURCES_VERSION = String.valueOf(System.currentTimeMillis());
			}
		}
	}

	protected void migrateDatabase() {
		
	}

	public void loadPropertiesFromDB() {
		getLog().fatal("SystemConfig loading properties from db");
		try {
			GenericTransactionExecutionService.getInstance().execute(new TransactionalAction() {
				public void executeInTransaction() throws SQLException, ValidationException {

					loadPropertiesFromDBInTransaction();
					getLog().fatal("Using temp dir " + SystemPropertyCache.getTempPath());

					try {
						File file = new File(SystemPropertyCache.getTempPath());
						if (!file.exists()) {
							FileUtils.forceMkdir(file);
						}
						String logDir = SystemPropertyCache.getTempPath() + "/" + SystemConfig.this.getLogDir();
						getLog().fatal("Using log dir " + logDir);
						File log = new File(logDir);
						if (!log.exists()) {
							FileUtils.forceMkdir(log);
						}
						File log4j = new File(logDir + "/log4j.xml");
						if(log4j.exists() && (log4j.length() < 1400 || overwriteLog4jOnStart())) {
							log4j.delete();
							InputStream io = SystemConfig.this.getClass().getResourceAsStream("log4j.xml");
							String log4jContent = IOUtils.toString(io);
							log4jContent = updateLog4j(log4jContent);
							log4jContent = StringUtils.replace(log4jContent, "LOG_FILE_DIR", logDir);
							IOUtils.write(log4jContent, new FileOutputStream(logDir + "/log4j.xml"));
							io.close();
						} else {
							if (!log4j.exists()) {
								InputStream io = SystemConfig.this.getClass().getResourceAsStream("log4j.xml");
								String log4jContent = IOUtils.toString(io);
								log4jContent = StringUtils.replace(log4jContent, "LOG_FILE_DIR", logDir);
								IOUtils.write(log4jContent, new FileOutputStream(logDir + "/log4j.xml"));
								io.close();
							}
						}
					} catch (IOException e) {
						getLog().error(e.getMessage(), e);
					}
				}
			});
		} catch (SQLException e) {
			getLog().error(e.getMessage(), e);
			throw new RuntimeException(e);
		} catch (ValidationException e) {
			getLog().error(e.getMessage(), e);
			throw new RuntimeException(e);
		}
		getLog().fatal("SystemConfig loaded properties from db");
	}

	protected String updateLog4j(String log4jContent) {
		return log4jContent;
	}

	protected boolean overwriteLog4jOnStart() {
		return false;
	}

	protected abstract String getLogDir();

	private void initLogger() {
		getLog().fatal("SystemConfig initializing logger");
		String logFilePath =  SystemPropertyCache.getTempPath() + "/" + SystemConfig.this.getLogDir() +  "/log4j.xml";
		LoggerProvider.initializeAndWatch(logFilePath, LogManager.getCurrentLoggers());
		getLog().fatal("SystemConfig logger initialized");
	}

	protected abstract void initRoles();

	protected abstract void initXMLALias();

	protected abstract void loadProperties();

	protected abstract void loadPropertiesFromDBInTransaction();

	protected abstract void initBlobCache();

}
