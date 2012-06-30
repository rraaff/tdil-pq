package com.tdil.milka.web;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import com.tdil.ibatis.TransactionProvider;
import com.tdil.log4j.LoggerProvider;
import com.tdil.milka.daomanager.DAOManager;
import com.tdil.milka.model.valueobjects.GoodMorningValueObject;
import com.tdil.struts.TransactionalActionWithResult;
import com.tdil.web.PaginationUtils;
import com.tdil.web.SearchPage;

public class GoodMorningUtils {

	private static final Logger LOG = LoggerProvider.getLogger(GoodMorningUtils.class);
	
	public static final int PAGE_SIZE = 13;
	public static final int PAGE_SIDE = 1;

	private static final class GoodMorningCountTransactionalAction implements TransactionalActionWithResult {
		public Object executeInTransaction() throws SQLException {
			return DAOManager.getGoodMorningDAO().countApproved();
		}
	}
	
	private static final class GoodMorningGetTransactionalAction implements TransactionalActionWithResult {
		int id; 
		
		public Object executeInTransaction() throws SQLException {
			return DAOManager.getGoodMorningDAO().selectGoodMorningValueObjectByPrimaryKey(id);
		}
	}
	
	private static final class GoodMorningPageTransactionalAction implements TransactionalActionWithResult {
		
		private int pageNumber;
		
		public GoodMorningPageTransactionalAction(int pageNumber) {
			super();
			this.pageNumber = pageNumber;
		}

		public Object executeInTransaction() throws SQLException {
			int limit = PaginationUtils.currentPageLimit(pageNumber, PAGE_SIZE);
			int start = pageNumber * PAGE_SIZE;
			// List<WallWritting> result = DAOManager.getWallWrittingDAO().selectApprovedPapapedia(limit);
			List<GoodMorningValueObject> result = DAOManager.getGoodMorningDAO().selectApproved(start, PAGE_SIZE + 1);
			int size = result.size();
			if (size == 0) {
				return new SearchPage<GoodMorningValueObject>(new ArrayList<GoodMorningValueObject>(), false);
			} else {
				return new SearchPage<GoodMorningValueObject>(result, size > PAGE_SIZE);
				//return new SearchPage<WallWritting>(result, pageNumber, PAGE_SIZE);
			}
		}
	}
	
	public static int getGoodMorningCount() {
		try {
			 return (Integer)TransactionProvider.executeInTransactionWithResult(new GoodMorningCountTransactionalAction());
		} catch (SQLException e) {
			LOG.error(e.getMessage(), e);
			return 0;
		}
	}
	
	public static SearchPage<GoodMorningValueObject> getPage(int pageNumber) {
		try {
			 return (SearchPage<GoodMorningValueObject>)TransactionProvider.executeInTransactionWithResult(new GoodMorningPageTransactionalAction(pageNumber));
		} catch (SQLException e) {
			LOG.error(e.getMessage(), e);
			return new SearchPage<GoodMorningValueObject>(new ArrayList<GoodMorningValueObject>(), false);
		}
	}
	
	public static void setFirst(SearchPage<GoodMorningValueObject> page, int lnk) {
		try {
			boolean found = false;
			for (GoodMorningValueObject ww : page.getPage()) {
				if (ww.getId() == lnk) {
					found = true;
				}
			}
			if (!found) {
				GoodMorningGetTransactionalAction getTransactionalAction = new GoodMorningGetTransactionalAction();
				getTransactionalAction.id = lnk;
				GoodMorningValueObject ww = (GoodMorningValueObject)TransactionProvider.executeInTransactionWithResult(getTransactionalAction);
				page.getPage().set(0, ww);
			} else {
				GoodMorningGetTransactionalAction getTransactionalAction = new GoodMorningGetTransactionalAction();
				getTransactionalAction.id = lnk;
				GoodMorningValueObject ww = (GoodMorningValueObject)TransactionProvider.executeInTransactionWithResult(getTransactionalAction);
				GoodMorningValueObject zeroPosition = page.getPage().get(0);
				if (!ww.getId().equals(zeroPosition.getId())) {
					int indexOriginal = 0;
					for (GoodMorningValueObject mtcvo : page.getPage()) {
						if (mtcvo.getId().equals(ww.getId())) {
							page.getPage().set(0, ww);
							page.getPage().set(indexOriginal, zeroPosition);
							return;
						}
						indexOriginal = indexOriginal + 1;
					}
				}
			}
		} catch (SQLException e) {
			LOG.error(e.getMessage(), e);
		}
	}
}
