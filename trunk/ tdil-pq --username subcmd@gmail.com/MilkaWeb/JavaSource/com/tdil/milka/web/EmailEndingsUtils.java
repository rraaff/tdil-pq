package com.tdil.milka.web;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import com.tdil.ibatis.TransactionProvider;
import com.tdil.log4j.LoggerProvider;
import com.tdil.milka.daomanager.DAOManager;
import com.tdil.milka.model.RawInsert;
import com.tdil.milka.model.RawInsertExample;
import com.tdil.milka.model.valueobjects.EmailEndingsValueObject;
import com.tdil.milka.struts.forms.PostItForm;
import com.tdil.struts.TransactionalActionWithResult;

public class EmailEndingsUtils {

	private static final String FLASH_CONFIG = "internal.finalesDeEmail";
	
	private static Logger getLog() {
		return LoggerProvider.getLogger(PostItForm.class);
	}
	
	private static final class GetEmailEndings implements TransactionalActionWithResult {
		public Object executeInTransaction() throws SQLException {
			RawInsertExample rawInsertExample = new RawInsertExample();
			rawInsertExample.createCriteria().andInserttypeEqualTo(FLASH_CONFIG);
			List<RawInsert> rawInserts = DAOManager.getRawInsertDAO().selectRawInsertByExampleWithBLOBs(rawInsertExample);
			String data = "";
			if (rawInserts.size() > 0) {
				data = rawInserts.get(0).getHtmlcontent();
			}
			return new FlashListServletResult<EmailEndingsValueObject>(data , DAOManager.getEmailEndingsDAO().selectEmailEndingsToReviewWithAuthor());
		}
	}

	@SuppressWarnings("unchecked")
	public static FlashListServletResult<EmailEndingsValueObject> getEmailEndings() {
		try {
			FlashListServletResult<EmailEndingsValueObject> result = (FlashListServletResult<EmailEndingsValueObject>)TransactionProvider.executeInTransactionWithResult(new GetEmailEndings());
			return result;
		} catch (SQLException e) {
			getLog().error(e.getMessage(), e);
			return new FlashListServletResult<EmailEndingsValueObject>("", new ArrayList<EmailEndingsValueObject>());
		}
	}
}
