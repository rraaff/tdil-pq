package com.tdil.tuafesta.utils;

import java.sql.SQLException;
import java.util.List;

import org.apache.log4j.Logger;

import com.tdil.ibatis.TransactionProvider;
import com.tdil.log4j.LoggerProvider;
import com.tdil.struts.TransactionalAction;
import com.tdil.struts.TransactionalActionWithResult;
import com.tdil.struts.ValidationException;
import com.tdil.tuafesta.daomanager.DAOManager;
import com.tdil.tuafesta.model.CacheRegion;
import com.tdil.tuafesta.model.CacheRegionExample;

public class CacheRegionUtils {

	private static final Logger logger = LoggerProvider.getLogger(CacheRegionUtils.class);
	
	public static Integer getVersion(final String regionName)  {
		try {
			return (Integer)TransactionProvider.executeInTransactionWithResult(new TransactionalActionWithResult() {
				public Object executeInTransaction() throws SQLException {
					return getVersionInTransaction(regionName);
				}
			});
		} catch (SQLException e) {
			logger.error(e.getMessage(), e);
			return -1;
		}
	}

	public static Integer getVersionInTransaction(final String regionName) throws SQLException {
		CacheRegionExample cacheRegionExample = new CacheRegionExample();
		cacheRegionExample.createCriteria().andNameEqualTo(regionName);
		List<CacheRegion> result = DAOManager.getCacheRegionDAO().selectCacheRegionByExample(cacheRegionExample);
		return result.get(0).getVersion();
	}
	
	public static void incrementVersion(final String regionName)  {
		try {
			TransactionProvider.executeInTransaction(new TransactionalAction() {
				public void executeInTransaction() throws SQLException, ValidationException {
					incrementVersionInTransaction(regionName);
				}
			});
		} catch (SQLException e) {
			logger.error(e.getMessage(), e);
		} catch (ValidationException e) {
			logger.error(e.getMessage(), e);
		}
	}

	public static void incrementVersionInTransaction(final String regionName) throws SQLException {
		CacheRegionExample cacheRegionExample = new CacheRegionExample();
		cacheRegionExample.createCriteria().andNameEqualTo(regionName);
		List<CacheRegion> result = DAOManager.getCacheRegionDAO().selectCacheRegionByExample(cacheRegionExample);
		CacheRegion cacheRegion = result.get(0);
		cacheRegion.setVersion(cacheRegion.getVersion() + 1);
		DAOManager.getCacheRegionDAO().updateCacheRegionByPrimaryKey(cacheRegion);
	}
	
}
