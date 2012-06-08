package com.tdil.milka.web;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import com.tdil.ibatis.TransactionProvider;
import com.tdil.log4j.LoggerProvider;
import com.tdil.milka.daomanager.DAOManager;
import com.tdil.milka.model.WallWritting;
import com.tdil.struts.TransactionalActionWithResult;
import com.tdil.web.PaginationUtils;
import com.tdil.web.SearchPage;

public class PapapediaUtils {

	private static final Logger LOG = LoggerProvider.getLogger(PapapediaUtils.class);
	
	public static final int PAGE_SIZE = 10;
	public static final int PAGE_SIDE = 1;

	private static final class PapapediaCountTransactionalAction implements TransactionalActionWithResult {
		public Object executeInTransaction() throws SQLException {
			return DAOManager.getWallWrittingDAO().countApprovedPapapedia();
		}
	}
	
	private static final class PapapediaGetTransactionalAction implements TransactionalActionWithResult {
		int id; 
		
		public Object executeInTransaction() throws SQLException {
			return DAOManager.getWallWrittingDAO().selectWallWrittingByPrimaryKey(id);
		}
	}
	
	private static final class PapapediaPageTransactionalAction implements TransactionalActionWithResult {
		
		private int pageNumber;
		
		public PapapediaPageTransactionalAction(int pageNumber) {
			super();
			this.pageNumber = pageNumber;
		}

		public Object executeInTransaction() throws SQLException {
			int limit = PaginationUtils.currentPageLimit(pageNumber, PAGE_SIZE);
			int start = pageNumber * PAGE_SIZE;
			// List<WallWritting> result = DAOManager.getWallWrittingDAO().selectApprovedPapapedia(limit);
			List<WallWritting> result = DAOManager.getWallWrittingDAO().selectApprovedPapapedia(start, PAGE_SIZE + 1);
			int size = result.size();
			if (size == 0) {
				return new SearchPage<WallWritting>(new ArrayList<WallWritting>(), false);
			} else {
				return new SearchPage<WallWritting>(result, 0, PAGE_SIZE);
				//return new SearchPage<WallWritting>(result, pageNumber, PAGE_SIZE);
			}
		}
	}
	
	private static final class PapapediaTransactionalAction implements TransactionalActionWithResult {
		
		public PapapediaTransactionalAction() {
			super();
		}

		public Object executeInTransaction() throws SQLException {
			return DAOManager.getWallWrittingDAO().selectPapapediaToReview();
		}
	}

	
	public static int getPapapediaCount() {
		try {
			 return (Integer)TransactionProvider.executeInTransactionWithResult(new PapapediaCountTransactionalAction());
		} catch (SQLException e) {
			LOG.error(e.getMessage(), e);
			return 0;
		}
	}
	
	public static SearchPage<WallWritting> getPapapediaPage(int pageNumber) {
		try {
			 return (SearchPage<WallWritting>)TransactionProvider.executeInTransactionWithResult(new PapapediaPageTransactionalAction(pageNumber));
		} catch (SQLException e) {
			LOG.error(e.getMessage(), e);
			return new SearchPage<WallWritting>(new ArrayList<WallWritting>(), false);
		}
	}
	
	public static List<WallWritting> getPapapediaList() {
		try {
			 return (List<WallWritting>)TransactionProvider.executeInTransactionWithResult(new PapapediaTransactionalAction());
		} catch (SQLException e) {
			LOG.error(e.getMessage(), e);
			return new ArrayList<WallWritting>();
		}
	}
	
	public static void setFirst(SearchPage<WallWritting> page, int lnk) {
		try {
			boolean found = false;
			for (WallWritting ww : page.getPage()) {
				if (ww.getId() == lnk) {
					found = true;
				}
			}
			if (!found) {
				PapapediaGetTransactionalAction papapediaGetTransactionalAction = new PapapediaGetTransactionalAction();
				papapediaGetTransactionalAction.id = lnk;
				WallWritting ww = (WallWritting)TransactionProvider.executeInTransactionWithResult(papapediaGetTransactionalAction);
				page.getPage().set(0, ww);
			}
		} catch (SQLException e) {
			LOG.error(e.getMessage(), e);
		}
	}
}
