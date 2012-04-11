package com.tdil.djmag.utils;

import java.sql.SQLException;
import java.util.List;

import org.apache.log4j.Logger;

import com.tdil.djmag.dao.impl.SystemPropertyDAOImpl;
import com.tdil.djmag.model.SystemProperty;
import com.tdil.djmag.model.SystemPropertyExample;
import com.tdil.djmag.roles.Administrator;
import com.tdil.ibatis.IBatisManager;
import com.tdil.log4j.LoggerProvider;
import com.tdil.users.Role;
import com.tdil.utils.SystemConfig;
import com.tdil.utils.SystemPropertyCache;

public class DJMagConfig extends SystemConfig {

	private static Logger getLog() {
		return LoggerProvider.getLogger(DJMagConfig.class);
	}
	
	@Override
	protected void loadProperties() {
		Role.addRole(Administrator.INSTANCE);
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
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		

	}

}
