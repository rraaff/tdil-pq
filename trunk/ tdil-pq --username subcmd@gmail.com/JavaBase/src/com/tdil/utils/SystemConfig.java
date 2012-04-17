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
		Role.addRole(None.INSTANCE);
		initRoles();
		initXMLALias();
		loadPropertiesFromDB();
		loadProperties();
		initLogger();
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
						File log = new File(SystemPropertyCache.getTempPath() + "/log");
						if (!log.exists()) {
							FileUtils.forceMkdir(log);
						}
						File log4j = new File(SystemPropertyCache.getTempPath() + "/log/log4j.xml");
						if(log4j.exists() && log4j.length() < 1400) {
							log4j.delete();
							InputStream io = SystemConfig.class.getResourceAsStream("log4j.xml");
							IOUtils.copy(io, new FileOutputStream(SystemPropertyCache.getTempPath() + "/log/log4j.xml"));
						} else {
							if (!log4j.exists()) {
								InputStream io = SystemConfig.class.getResourceAsStream("log4j.xml");
								IOUtils.copy(io, new FileOutputStream(SystemPropertyCache.getTempPath() + "/log/log4j.xml"));
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

	private void initLogger() {
		getLog().fatal("SystemConfig initializing logger");
		String logFilePath = SystemPropertyCache.getTempPath() + "/log/log4j.xml";
		LoggerProvider.initialize(logFilePath, LogManager.getCurrentLoggers());
		getLog().fatal("SystemConfig logger initialized");
	}

	protected abstract void initRoles();
	
	protected abstract void initXMLALias();
	
	protected abstract void loadProperties();
	
	protected abstract void loadPropertiesFromDBInTransaction();
	
	
	public static String getLog4J() {
		return SystemPropertyCache.getTempPath() + "/log/log4j.xml";
	}
	
}
