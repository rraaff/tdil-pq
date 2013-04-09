package com.tdil.tuafesta.utils;

import java.sql.SQLException;
import java.util.List;

import org.apache.log4j.Logger;

import com.tdil.cache.CacheEntry;
import com.tdil.cache.CacheManager;
import com.tdil.ibatis.TransactionProvider;
import com.tdil.log4j.LoggerProvider;
import com.tdil.struts.TransactionalActionWithResult;
import com.tdil.tuafesta.daomanager.DAOManager;
import com.tdil.tuafesta.model.RawInsert;
import com.tdil.tuafesta.model.RawInsertExample;

public class Banner {
	
	private static final class GetRawInsert implements TransactionalActionWithResult {
		private int id;
		
		public GetRawInsert(int id) {
			this.id = id;
		}
		public Object executeInTransaction() throws SQLException {
			Integer version = CacheRegionUtils.getVersionInTransaction(Banner.class.getName());
			CacheEntry<String> cached = CacheManager.getCacheEntry(Banner.class.getName(), String.valueOf(this.id), version);
			if (cached != null) {
				return cached.getValue();
			}
			RawInsertExample rawInsertExample = new RawInsertExample();
			rawInsertExample.createCriteria().andIdEqualTo(this.id);
			List<RawInsert> rawInserts = DAOManager.getRawInsertDAO().selectRawInsertByExampleWithBLOBs(rawInsertExample);
			String data = "";
			if (rawInserts.size() > 0) {
				data = rawInserts.get(0).getHtmlcontent();
			}
			CacheManager.put(Banner.class.getName(), String.valueOf(this.id), data, version);
			return data;
		}
	}

	@SuppressWarnings("unchecked")
	public static String banner(int id) {
		try {
			String result = (String)TransactionProvider.executeInTransactionWithResult(new GetRawInsert(id));
			return result;
		} catch (SQLException e) {
			getLog().error(e.getMessage(), e);
			return "";
		}
	}
	
	private static Logger getLog() {
		return LoggerProvider.getLogger(Banner.class);
	}
}
