package com.tdil.milka.web;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import com.tdil.ibatis.TransactionProvider;
import com.tdil.log4j.LoggerProvider;
import com.tdil.milka.daomanager.DAOManager;
import com.tdil.milka.model.LoveNicknames;
import com.tdil.milka.struts.forms.PostItForm;
import com.tdil.struts.TransactionalActionWithResult;

public class LoveNicknamesUtils {

	private static Logger getLog() {
		return LoggerProvider.getLogger(PostItForm.class);
	}
	
	private static final class GetLoveNicknames implements TransactionalActionWithResult {
		public Object executeInTransaction() throws SQLException {
			return DAOManager.getLoveNicknamesDAO().selectLoveNicknamesToReview();
		}
	}

	@SuppressWarnings("unchecked")
	public static List<LoveNicknames> getLoveNicknames() {
		try {
			List<LoveNicknames> result = (List<LoveNicknames>)TransactionProvider.executeInTransactionWithResult(new GetLoveNicknames());
			return result;
		} catch (SQLException e) {
			getLog().error(e.getMessage(), e);
			return new ArrayList<LoveNicknames>();
		}
	}
}
