package com.tdil.log4j;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Category;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;

import com.tdil.log4j.LoggerProvider;

public class LoggerUtils {

	public static String getLevelFor(Class aClass) {
		Logger logger = LoggerProvider.getLogger(aClass);
		return logger.getLevel().toString();
	}
	
	public static List<Logger> getAllLoggers() {
		return new ArrayList<Logger>(LoggerProvider.getAllLoggers());
	}
	
	public static String[] getLevelFor(String aClassName) {
		Class aClass;
		try {
			aClass = Class.forName(aClassName);
			Logger logger = LoggerProvider.getLogger(aClass);
			return getLevelFor(logger);
		} catch (ClassNotFoundException e) {
			Logger logger = Logger.getLogger(aClassName);
			return new String[] {aClassName, logger.getLevel().toString()};
		}
	}
	
	private static String[] getLevelFor(Category logger) {
		Level level = logger.getLevel();
		if (level == null) {
			Category parent = logger.getParent();
			if (parent == null) {
				return new String[]{"null", "null"};
			} else {
				return getLevelFor(parent);
			}
		} else {
			return new String[] {logger.getName(), level.toString()};
		}
	}

	public static void setLogLevel(String categoryName, String level) {
		Class aClass;
		try {
			aClass = Class.forName(categoryName);
			Logger logger = LoggerProvider.getLogger(aClass);
			logger.setLevel(Level.toLevel(level));
		} catch (ClassNotFoundException e) {
			Logger logger = Logger.getLogger(categoryName);
			logger.setLevel(Level.toLevel(level));
			// TODO Auto-generated catch block
			//e.printStackTrace();
		}
	}
}
