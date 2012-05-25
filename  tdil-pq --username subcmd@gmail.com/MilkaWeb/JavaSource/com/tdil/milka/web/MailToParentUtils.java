package com.tdil.milka.web;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import com.tdil.ibatis.TransactionProvider;
import com.tdil.log4j.LoggerProvider;
import com.tdil.milka.daomanager.DAOManager;
import com.tdil.milka.model.valueobjects.MailToParentValueObject;
import com.tdil.milka.struts.forms.PostItForm;
import com.tdil.struts.TransactionalActionWithResult;

public class MailToParentUtils {

	private static Logger getLog() {
		return LoggerProvider.getLogger(PostItForm.class);
	}
	
	private static final class GetMailToParent implements TransactionalActionWithResult {
		public Object executeInTransaction() throws SQLException {
			return DAOManager.getMailToParentDAO().selectMailToParentToReviewWithAuthor();
		}
	}

	@SuppressWarnings("unchecked")
	public static List<MailToParentValueObject> getMailToParent() {
		try {
			List<MailToParentValueObject> result = (List<MailToParentValueObject>)TransactionProvider.executeInTransactionWithResult(new GetMailToParent());
			return result;
		} catch (SQLException e) {
			getLog().error(e.getMessage(), e);
			return new ArrayList<MailToParentValueObject>();
		}
	}
}
