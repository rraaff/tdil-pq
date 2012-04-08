package com.tdil.ibatis;

import java.io.IOException;
import java.io.Reader;
import java.sql.SQLException;
import java.util.Properties;

import org.apache.log4j.Logger;

import com.ibatis.common.resources.Resources;
import com.ibatis.sqlmap.client.SqlMapClient;
import com.ibatis.sqlmap.client.SqlMapClientBuilder;
import com.tdil.log4j.LoggerProvider;

public class IBatisManager {
	/**
	 * SqlMapClient instances are thread safe, so you only need one. In this
	 * case, we'll use a static singleton. So sue me. ;-)
	 */
	static SqlMapClient sqlMapper;

	
	private static Logger getLog() {
		return LoggerProvider.getLogger(IBatisManager.class);
	}
	
	public synchronized static void init(String configName, Properties properties) {
		try {
			Reader reader = Resources.getResourceAsReader("com/tdil/ibatis/config/" + configName);
			sqlMapper = SqlMapClientBuilder.buildSqlMapClient(reader, properties);
			reader.close();
		} catch (IOException e) {
			getLog().error(e.getMessage(), e);
			throw new RuntimeException("Something bad happened while building the SqlMapClient instance." + e, e);
		}
	}
	
	public static void beginTransaction() throws SQLException {
		sqlMapper.startTransaction();
	}
	
	public static void commitTransaction() throws SQLException {
		sqlMapper.commitTransaction();
	}
	
	public static void endTransaction() throws SQLException {
		sqlMapper.endTransaction();
	}
	
	public static SqlMapClient getClient() throws SQLException {
		return sqlMapper;
	}
	
}
