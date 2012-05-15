package com.tdil.milka.utils;

import java.io.File;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.ServletContextEvent;

import org.apache.commons.io.FileUtils;
import org.apache.log4j.Logger;

import com.tdil.cache.blob.BlobLocalDiskCache;
import com.tdil.ibatis.IBatisManager;
import com.tdil.ibatis.TransactionProvider;
import com.tdil.log4j.LoggerProvider;
import com.tdil.milka.cache.blob.PublicBlobResolver;
import com.tdil.milka.dao.impl.SystemPropertyDAOImpl;
import com.tdil.milka.daomanager.DAOManager;
import com.tdil.milka.model.BlobDataType;
import com.tdil.milka.model.FilteredWord;
import com.tdil.milka.model.SystemProperty;
import com.tdil.milka.model.SystemPropertyExample;
import com.tdil.milka.model.WallFilter;
import com.tdil.milka.roles.Administrator;
import com.tdil.struts.TransactionalAction;
import com.tdil.struts.ValidationException;
import com.tdil.users.None;
import com.tdil.users.Role;
import com.tdil.utils.SystemConfig;
import com.tdil.utils.SystemPropertyCache;

public class MilkaConfig extends SystemConfig {

	private static Logger getLog() {
		return LoggerProvider.getLogger(MilkaConfig.class);
	}
	
	@Override
	public void init(ServletContextEvent sce) {
		super.init(sce);
		this.loadFilteredWords();
	}
	
	
	@Override
	protected String getLogDir() {
		return "milkalogs";
	}
	private void loadFilteredWords() {
		getLog().fatal("MilkaConfig: loading filtered words");
		try {
			TransactionProvider.executeInTransaction(new TransactionalAction() {
				public void executeInTransaction() throws SQLException, ValidationException {
					WallFilter.init();
					List<FilteredWord> list = DAOManager.getFilteredWordDAO().selectFilteredWordByExample(null);
					for (FilteredWord word : list) {
						WallFilter.add(word.getWord());
					}
				}
			});
		} catch (SQLException e) {
			getLog().error(e.getMessage(),e);
		} catch (ValidationException e) {
			getLog().error(e.getMessage(),e);
		}
		getLog().fatal("MilkaConfig: filtered words loaded");
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
				getLog().fatal("MilkaConfig: " + property.getPropkey() + "=" + property.getPropvalue());
				SystemPropertyCache.put(property.getPropkey(), property.getPropvalue());
			}
		} catch (SQLException e) {
			getLog().error(e.getMessage(), e);
		}

	}
	
	@Override
	protected void initBlobCache() {
		getLog().fatal("MilkaConfig: blob cache starting ");
		String path = SystemPropertyCache.getTempPath();
		path = path + "/milkablobs/";
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
		getLog().fatal("MilkaConfig: blob cache started at " + path);
		BlobLocalDiskCache.setDiskBlobLocation(path);
		BlobLocalDiskCache.addBlobResolver(BlobDataType.PUBLIC, new PublicBlobResolver(None.INSTANCE_ARR));
	}

}
