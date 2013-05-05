package com.tdil.lojack.utils;

import java.io.File;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import java.util.Properties;

import javax.servlet.ServletContextEvent;

import org.apache.commons.io.FileUtils;
import org.apache.log4j.Logger;

import com.tdil.cache.blob.BlobLocalDiskCache;
import com.tdil.ibatis.IBatisManager;
import com.tdil.log4j.LoggerProvider;
import com.tdil.lojack.cache.blob.BlobDataType;
import com.tdil.lojack.cache.blob.PublicBlobResolver;
import com.tdil.lojack.dao.impl.SystemPropertyDAOImpl;
import com.tdil.lojack.gis.LoJackServicesConnector;
import com.tdil.lojack.gis.UpdateMiddlewareJobsThread;
import com.tdil.lojack.model.SystemProperty;
import com.tdil.lojack.model.SystemPropertyExample;
import com.tdil.lojack.prevent.PreventConnector;
import com.tdil.lojack.roles.WebsiteUser;
import com.tdil.thalamus.client.cache.ThalamusCache;
import com.tdil.thalamus.client.core.ThalamusClient;
import com.tdil.thalamus.client.facade.ThalamusClientBeanFacade;
import com.tdil.users.None;
import com.tdil.users.Role;
import com.tdil.utils.SystemConfig;
import com.tdil.utils.SystemPropertyCache;

public class LoJackConfig extends SystemConfig {


	private static Logger getLog() {
		return LoggerProvider.getLogger(LoJackConfig.class);
	}

	@Override
	public void init(ServletContextEvent sce) {
		super.init(sce);
		this.loadFilteredWords();
		String thalamusserver = SystemPropertyUtils.getSystemPropertValue("thalamus.server");
		if (thalamusserver != null) {
			ThalamusClient.setTHALAMUS_SERVER(thalamusserver);
		}
		getLog().fatal("Thalamus server is " + ThalamusClient.getTHALAMUS_SERVER());

		String thalamustouchpointCode = SystemPropertyUtils.getSystemPropertValue("thalamus.touchpoint.code");
		if (thalamustouchpointCode != null) {
			ThalamusClient.setTHALAMUS_TOUCHPOINT_CODE(thalamustouchpointCode);
		}
		getLog().fatal("Thalamus touchpoint is " + ThalamusClient.getTHALAMUS_TOUCHPOINT_CODE());

		String thalamustouchpointToken = SystemPropertyUtils.getSystemPropertValue("thalamus.touchpoint.token");
		if (thalamustouchpointToken != null) {
			ThalamusClient.setTHALAMUS_TOUCHPOINT_TOKEN(thalamustouchpointToken);
		}
		getLog().fatal("Thalamus touchpoint is " + ThalamusClient.getTHALAMUS_TOUCHPOINT_TOKEN());
		ThalamusCache.configureWith(new Properties());
		// Initializes the caches
		try {
			getLog().fatal("About to initialize cache");
			getLog().fatal("Initializing brand families cache");
			ThalamusClientBeanFacade.getBrandFamilies();
			getLog().fatal("Initializing brands cache");
			ThalamusClientBeanFacade.getBrands();
			getLog().fatal("Initializing channels cache");
			ThalamusClientBeanFacade.getChannels();
			getLog().fatal("Initializing countries cache");
			ThalamusClientBeanFacade.getCountries();
			getLog().fatal("Initializing document types cache");
			ThalamusClientBeanFacade.getDocumentTypes();
			getLog().fatal("Initializing person fields cache");
			ThalamusClientBeanFacade.getPersonFields();
			getLog().fatal("Initializing facebook login");
			ThalamusClientBeanFacade.getFacebookLogin();
			getLog().fatal("Initializing twitter login");
			ThalamusClientBeanFacade.getTwitterLogin();
			getLog().fatal("Cache initialized");
		} catch (Exception e) {
			getLog().error("Can not initialize caches", e);
		}
		Role.addRole(WebsiteUser.INSTANCE);

		String gisserver = SystemPropertyUtils.getSystemPropertValue("gis.server");
		if (gisserver != null) {
			LoJackServicesConnector.setGisServer(gisserver);
		}
		getLog().fatal("GIS server is " + (gisserver == null ? "null" : gisserver));

		String servicesserver = SystemPropertyUtils.getSystemPropertValue("services.server");
		if (servicesserver != null) {
			LoJackServicesConnector.setServicesServer(servicesserver);
		}
		getLog().fatal("Services server is " + (servicesserver == null ? "null" : servicesserver));

		String preventserver = SystemPropertyUtils.getSystemPropertValue("prevent.server");
		if (preventserver != null) {
			PreventConnector.setPreventServer(preventserver);
		}
		getLog().fatal("Prevent server is " + (preventserver == null ? "null" : preventserver));

		getLog().fatal("Starting middleware jobs updater");
		UpdateMiddlewareJobsThread updateMiddlewareJobsThread = new UpdateMiddlewareJobsThread();
		UpdateMiddlewareJobsThread.setUpdateMiddlewareJobsThread(updateMiddlewareJobsThread);
		updateMiddlewareJobsThread.start();
		getLog().fatal("Middleware jobs updater started");

	}

	@Override
	protected String getLogDir() {
		return "lojacklogs";
	}

	private void loadFilteredWords() {

	}

	@Override
	protected void initXMLALias() {
		// XMLUtils.addAlias("RankingPositions", RankingPositions.class);
	}

	@Override
	protected void initRoles() {
	}

	@Override
	protected void loadProperties() {
	}

	@Override
	protected void loadPropertiesFromDBInTransaction() {
		try {
			List<SystemProperty> list = new SystemPropertyDAOImpl(IBatisManager.getClient())
					.selectSystemPropertyByExample(new SystemPropertyExample());
			for (SystemProperty property : list) {
				getLog().fatal("LOJACKConfig: " + property.getPropkey() + "=" + property.getPropvalue());
				SystemPropertyCache.put(property.getPropkey(), property.getPropvalue());
			}
		} catch (SQLException e) {
			getLog().error(e.getMessage(), e);
		}

	}

	@Override
	protected void initBlobCache() {
		getLog().fatal("LOJACKConfig: blob cache starting ");
		String path = SystemPropertyCache.getTempPath();
		path = path + "/lojackblobs/";
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
		getLog().fatal("LOJACKConfig: blob cache started at " + path);
		BlobLocalDiskCache.setDiskBlobLocation(path);
		BlobLocalDiskCache.addBlobResolver(BlobDataType.PUBLIC, new PublicBlobResolver(None.INSTANCE_ARR));
	}

}
