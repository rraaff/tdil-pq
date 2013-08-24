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

import com.tdil.ibatis.TransactionProvider;
import com.tdil.log4j.LoggerProvider;
import com.tdil.struts.TransactionalAction;
import com.tdil.struts.ValidationException;
import com.tdil.users.None;
import com.tdil.users.Role;

public abstract class SystemConfig {

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
	}

	protected void migrateDatabase() {
		
	}

	public void loadPropertiesFromDB() {
		getLog().fatal("SystemConfig loading properties from db");
		try {
			TransactionProvider.executeInTransaction(new TransactionalAction() {
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
						if(log4j.exists() && log4j.length() < 1400) {
							log4j.delete();
							InputStream io = SystemConfig.this.getClass().getResourceAsStream("log4j.xml");
							String log4jContent = IOUtils.toString(io);
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
