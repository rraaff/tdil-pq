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
import com.tdil.milka.model.valueobjects.MailToParentValueObject;
import com.tdil.milka.struts.forms.PostItForm;
import com.tdil.struts.TransactionalActionWithResult;

public class MailToParentUtils {

	private static final String FLASH_CONFIG = "internal.cartasDeHijosAPadres";
	
	private static Logger getLog() {
		return LoggerProvider.getLogger(PostItForm.class);
	}
	
	private static final class GetMailToParent implements TransactionalActionWithResult {
		public Object executeInTransaction() throws SQLException {
			RawInsertExample rawInsertExample = new RawInsertExample();
			rawInsertExample.createCriteria().andInserttypeEqualTo(FLASH_CONFIG);
			List<RawInsert> rawInserts = DAOManager.getRawInsertDAO().selectRawInsertByExampleWithBLOBs(rawInsertExample);
			String data = "";
			if (rawInserts.size() > 0) {
				data = rawInserts.get(0).getHtmlcontent();
			}
			return new FlashListServletResult<MailToParentValueObject>(data , DAOManager.getMailToParentDAO().selectMailToParentToReviewWithAuthor());
		}
	}

	@SuppressWarnings("unchecked")
	public static FlashListServletResult<MailToParentValueObject> getMailToParent() {
		try {
			FlashListServletResult<MailToParentValueObject> result = (FlashListServletResult<MailToParentValueObject>)TransactionProvider.executeInTransactionWithResult(new GetMailToParent());
			return result;
		} catch (SQLException e) {
			getLog().error(e.getMessage(), e);
			return new FlashListServletResult<MailToParentValueObject>("", new ArrayList<MailToParentValueObject>());
		}
	}
}
