package com.tdil.lojack.utils;

import com.tdil.log4j.LoggerProvider;
import com.tdil.lojack.camera.CameraLogger;

public class CameraLog4jLogger implements CameraLogger {

	private static final org.apache.log4j.Logger LOG = LoggerProvider.getLogger(CameraLog4jLogger.class);
	
	@Override
	public void error(Throwable e) {
		LOG.error(e.getMessage(), e);
	}

}
