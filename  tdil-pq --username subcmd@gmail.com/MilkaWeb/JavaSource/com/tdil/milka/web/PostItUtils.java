package com.tdil.milka.web;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import com.tdil.ibatis.TransactionProvider;
import com.tdil.log4j.LoggerProvider;
import com.tdil.milka.daomanager.DAOManager;
import com.tdil.milka.model.valueobjects.PostItValueObject;
import com.tdil.struts.TransactionalActionWithResult;

public class PostItUtils {
	

	private static Logger getLog() {
		return LoggerProvider.getLogger(PostItUtils.class);
	}
	
	private static final class GetPostIts implements TransactionalActionWithResult {
		public Object executeInTransaction() throws SQLException {
			List<PostItValueObject> photos = DAOManager.getPostItDAO().selectPostItsToReviewWithAuthor();
			return photos;
		}
	}

	@SuppressWarnings("unchecked")
	public static List<PostItValueObject> getPostIts() {
		try {
			List<PostItValueObject> result = (List<PostItValueObject>)TransactionProvider.executeInTransactionWithResult(new GetPostIts());
			return result;
		} catch (SQLException e) {
			getLog().error(e.getMessage(), e);
			return new ArrayList<PostItValueObject>();
		}
	}
	
}
