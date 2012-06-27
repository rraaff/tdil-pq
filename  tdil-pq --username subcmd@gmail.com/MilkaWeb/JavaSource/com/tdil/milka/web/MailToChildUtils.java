package com.tdil.milka.web;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import com.tdil.ibatis.TransactionProvider;
import com.tdil.log4j.LoggerProvider;
import com.tdil.milka.daomanager.DAOManager;
import com.tdil.milka.model.WallWritting;
import com.tdil.milka.model.valueobjects.MailToChildValueObject;
import com.tdil.struts.TransactionalActionWithResult;
import com.tdil.web.PaginationUtils;
import com.tdil.web.SearchPage;

public class MailToChildUtils {

	private static final Logger LOG = LoggerProvider.getLogger(MailToChildUtils.class);
	
	public static final int PAGE_SIZE = 10;
	public static final int PAGE_SIDE = 1;

	private static final class MailToChildCountTransactionalAction implements TransactionalActionWithResult {
		public Object executeInTransaction() throws SQLException {
			return DAOManager.getMailToChildDAO().countApproved();
		}
	}
	
	private static final class MailToChildGetTransactionalAction implements TransactionalActionWithResult {
		int id; 
		
		public Object executeInTransaction() throws SQLException {
			return DAOManager.getMailToChildDAO().selectMailToChildValueObjectByPrimaryKey(id);
		}
	}
	
	private static final class MailToChildPageTransactionalAction implements TransactionalActionWithResult {
		
		private int pageNumber;
		
		public MailToChildPageTransactionalAction(int pageNumber) {
			super();
			this.pageNumber = pageNumber;
		}

		public Object executeInTransaction() throws SQLException {
			int limit = PaginationUtils.currentPageLimit(pageNumber, PAGE_SIZE);
			int start = pageNumber * PAGE_SIZE;
			// List<WallWritting> result = DAOManager.getWallWrittingDAO().selectApprovedPapapedia(limit);
			List<MailToChildValueObject> result = DAOManager.getMailToChildDAO().selectApproved(start, PAGE_SIZE + 1);
			int size = result.size();
			if (size == 0) {
				return new SearchPage<MailToChildValueObject>(new ArrayList<MailToChildValueObject>(), false);
			} else {
				return new SearchPage<MailToChildValueObject>(result, 0, PAGE_SIZE);
				//return new SearchPage<WallWritting>(result, pageNumber, PAGE_SIZE);
			}
		}
	}
	
	public static int getMailToChildCount() {
		try {
			 return (Integer)TransactionProvider.executeInTransactionWithResult(new MailToChildCountTransactionalAction());
		} catch (SQLException e) {
			LOG.error(e.getMessage(), e);
			return 0;
		}
	}
	
	public static SearchPage<MailToChildValueObject> getPage(int pageNumber) {
		try {
			 return (SearchPage<MailToChildValueObject>)TransactionProvider.executeInTransactionWithResult(new MailToChildPageTransactionalAction(pageNumber));
		} catch (SQLException e) {
			LOG.error(e.getMessage(), e);
			return new SearchPage<MailToChildValueObject>(new ArrayList<MailToChildValueObject>(), false);
		}
	}
	
	public static void setFirst(SearchPage<MailToChildValueObject> page, int lnk) {
		try {
			boolean found = false;
			for (MailToChildValueObject ww : page.getPage()) {
				if (ww.getId() == lnk) {
					found = true;
				}
			}
			if (!found) {
				MailToChildGetTransactionalAction getTransactionalAction = new MailToChildGetTransactionalAction();
				getTransactionalAction.id = lnk;
				MailToChildValueObject ww = (MailToChildValueObject)TransactionProvider.executeInTransactionWithResult(getTransactionalAction);
				page.getPage().set(0, ww);
			}
		} catch (SQLException e) {
			LOG.error(e.getMessage(), e);
		}
	}
}
