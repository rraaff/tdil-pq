package com.tdil.milka.web;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import com.tdil.ibatis.TransactionProvider;
import com.tdil.log4j.LoggerProvider;
import com.tdil.milka.daomanager.DAOManager;
import com.tdil.milka.model.valueobjects.EmailEndingsValueObject;
import com.tdil.milka.struts.forms.PostItForm;
import com.tdil.struts.TransactionalActionWithResult;

public class EmailEndingsUtils {

	private static Logger getLog() {
		return LoggerProvider.getLogger(PostItForm.class);
	}
	
	private static final class GetEmailEndings implements TransactionalActionWithResult {
		public Object executeInTransaction() throws SQLException {
			return DAOManager.getEmailEndingsDAO().selectEmailEndingsToReviewWithAuthor();
		}
	}

	@SuppressWarnings("unchecked")
	public static List<EmailEndingsValueObject> getEmailEndings() {
		try {
			List<EmailEndingsValueObject> result = (List<EmailEndingsValueObject>)TransactionProvider.executeInTransactionWithResult(new GetEmailEndings());
			return result;
		} catch (SQLException e) {
			getLog().error(e.getMessage(), e);
			return new ArrayList<EmailEndingsValueObject>();
		}
	}
}
