package com.tdil.tuafesta.utils;

import java.io.File;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.ServletContextEvent;

import org.apache.commons.io.FileUtils;
import org.apache.log4j.Logger;

import com.tdil.cache.blob.BlobLocalDiskCache;
import com.tdil.ibatis.IBatisManager;
import com.tdil.log4j.LoggerProvider;
import com.tdil.tuafesta.cache.blob.BlobDataType;
import com.tdil.tuafesta.cache.blob.PublicBlobResolver;
import com.tdil.tuafesta.dao.impl.SystemPropertyDAOImpl;
import com.tdil.tuafesta.model.SystemProperty;
import com.tdil.tuafesta.model.SystemPropertyExample;
import com.tdil.tuafesta.roles.Administrator;
import com.tdil.tuafesta.stats.StatsManager;
import com.tdil.users.None;
import com.tdil.users.Role;
import com.tdil.utils.SystemConfig;
import com.tdil.utils.SystemPropertyCache;

public class TuaFestaConfig extends SystemConfig {

	private static Logger getLog() {
		return LoggerProvider.getLogger(TuaFestaConfig.class);
	}
	
	@Override
	public void init(ServletContextEvent sce) {
		super.init(sce);
		this.loadFilteredWords();
		StatsManager.start();
	}
	
	
	@Override
	protected String getLogDir() {
		return "tuafestalogs";
	}
	private void loadFilteredWords() {

	}

	@Override
	protected void initXMLALias() {
		//XMLUtils.addAlias("RankingPositions", RankingPositions.class);
	}
	
	@Override
	protected void initRoles() {
		Role.addRole(Administrator.INSTANCE);
	}
	
	@Override
	protected void loadProperties() {
	}

	@Override
	protected void loadPropertiesFromDBInTransaction() {
		try {
			List<SystemProperty> list = new SystemPropertyDAOImpl(IBatisManager.getClient()).selectSystemPropertyByExample(new SystemPropertyExample());
			for (SystemProperty property : list) {
				getLog().fatal("TUAFESTAConfig: " + property.getPropkey() + "=" + property.getPropvalue());
				SystemPropertyCache.put(property.getPropkey(), property.getPropvalue());
			}
		} catch (SQLException e) {
			getLog().error(e.getMessage(), e);
		}

	}
	
	@Override
	protected void initBlobCache() {
		getLog().fatal("TUAFESTAConfig: blob cache starting ");
		String path = SystemPropertyCache.getTempPath();
		path = path + "/tuafestablobs/";
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
			getLog().equals("No se pudo escribir en el cache de blobs " + path);
			return;
		}
		getLog().fatal("TUAFESTAConfig: blob cache started at " + path);
		BlobLocalDiskCache.setDiskBlobLocation(path);
		BlobLocalDiskCache.addBlobResolver(BlobDataType.PUBLIC, new PublicBlobResolver(None.INSTANCE_ARR));
	}

}
