package com.tdil.djmag.utils;

import java.io.File;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.apache.log4j.Logger;

import com.tdil.cache.blob.BlobLocalDiskCache;
import com.tdil.djmag.cache.blob.NoteImageResolver;
import com.tdil.djmag.cache.blob.PublicBlobResolver;
import com.tdil.djmag.dao.impl.SystemPropertyDAOImpl;
import com.tdil.djmag.model.BlobDataType;
import com.tdil.djmag.model.RankingPosition;
import com.tdil.djmag.model.RankingPositions;
import com.tdil.djmag.model.SystemProperty;
import com.tdil.djmag.model.SystemPropertyExample;
import com.tdil.djmag.roles.Administrator;
import com.tdil.ibatis.IBatisManager;
import com.tdil.log4j.LoggerProvider;
import com.tdil.users.None;
import com.tdil.users.Role;
import com.tdil.utils.SystemConfig;
import com.tdil.utils.SystemPropertyCache;
import com.tdil.utils.XMLUtils;

public class DJMagConfig extends SystemConfig {

	private static Logger getLog() {
		return LoggerProvider.getLogger(DJMagConfig.class);
	}
	
	@Override
	protected void initXMLALias() {
		XMLUtils.addAlias("RankingPositions", RankingPositions.class);
		XMLUtils.addAlias("RankingPosition", RankingPosition.class);
	}
	
	@Override
	protected void initRoles() {
		Role.addRole(Administrator.INSTANCE);
	}
	
	@Override
	protected void loadProperties() {
	}
	
	@Override
	protected void initBlobCache() {
		getLog().fatal("DJMagConfig: blob cache starting ");
		String path = SystemPropertyCache.getTempPath();
		path = path + "/djmagblobs/";
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
		getLog().fatal("DJMagConfig: blob cache started at " + path);
		BlobLocalDiskCache.setDiskBlobLocation(path);
		BlobLocalDiskCache.addBlobResolver("note", new NoteImageResolver(None.INSTANCE_ARR));
		BlobLocalDiskCache.addBlobResolver(BlobDataType.PUBLIC, new PublicBlobResolver(None.INSTANCE_ARR));
//	TODO EXAMPLE	BlobLocalDiskCache.getBlob("note", 1, 0, "aaa.jpg", null);
//		BlobLocalDiskCache.getBlob("note", 1, 0, "a.jpg", null);
	}

	@Override
	protected void loadPropertiesFromDBInTransaction() {
		try {
			List<SystemProperty> list = new SystemPropertyDAOImpl(IBatisManager.getClient()).selectSystemPropertyByExample(new SystemPropertyExample());
			for (SystemProperty property : list) {
				getLog().fatal("DJMagConfig: " + property.getPropkey() + "=" + property.getPropvalue());
				SystemPropertyCache.put(property.getPropkey(), property.getPropvalue());
			}
		} catch (SQLException e) {
			getLog().error(e.getMessage(), e);
		}

	}

}
